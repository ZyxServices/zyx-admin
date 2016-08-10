/**
 * Created by guochunyan on 2016/8/9.
 */
var formUtils={
    formSubmit: function (formId, url, fb) {
        var data = {};
        var errorCount = 0;
        $("#" + formId + " input").each(function (i, o) {
            var name = $(o).attr("name");
            if (name) {
                if ($(o).attr("required")) {
                    if ($(o).val() == "") {
                        if ($(o).nextAll("span").html()) {
                            $(o).nextAll("span").show();
                        } else {
                            $(o).closest(".form-group").append($("<span class='help-block'>该项不能为空或者输入类型错误</span>"));
                        }
                        $(o).closest(".form-group").addClass("has-error");
                        errorCount++;
                        return;
                    } else {
                        data[name] = $(o).val();
                        $(o).closest(".form-group").removeClass("has-error");
                        $(o).next("span").hide();
                    }
                } else {
                    $(o).closest(".form-group").removeClass("has-error");
                    data[name] = $(o).val();
                    $(o).next("span").hide();
                }
            }
        });
        $("#" + formId + " select").each(function (i, o) {
            var name = $(o).attr("name");
            var step = $(o).attr("step");
            if (name) {
                if ($(o).attr("required")) {
                    if ($(o).val() == "") {
                        if ($(o).nextAll("span").html()) {
                            $(o).nextAll("span").show();
                        } else {
                            $(o).closest(".form-group").append($("<span class='help-block'>必填项</span>"));
                        }
                        $(o).closest(".form-group").addClass("has-error");
                        errorCount++;
                        return;
                    } else {
                        if (step == "text") {
                            data[name] = $(o).text();
                        } else {
                            data[name] = $(o).val();
                        }
                        $(o).closest(".form-group").removeClass("has-error");
                        $(o).next("span").hide();
                    }
                } else {
                    $(o).closest(".form-group").removeClass("has-error");
                    if (step == "text") {
                        data[name] = $(o).text();
                    } else {
                        data[name] = $(o).val();
                    }
                    $(o).next("span").hide();
                }
            }
        });
        $("#" + formId + " textarea").each(function (i, o) {
            var name = $(o).attr("name");
            if (name) {
                if ($(o).attr("required")) {
                    if ($(o).val() == "") {
                        if ($(o).nextAll("span").html()) {
                            $(o).nextAll("span").show();
                        } else {
                            $(o).closest(".form-group").append($("<span class='help-block'>必填项</span>"));
                        }
                        $(o).closest(".form-group").addClass("has-error");
                        errorCount++;
                        return;
                    } else {
                        data[name] = $(o).val();
                        $(o).closest(".form-group").removeClass("has-error");
                        $(o).next("span").hide();
                    }
                } else {
                    $(o).closest(".form-group").removeClass("has-error");
                    data[name] = $(o).val();
                    $(o).next("span").hide();
                }
            }
        })
        var _check_box_data = [];
        var _check_name;
        $("#" + formId + " input[type='checkbox']").each(function (i, o) {
            _check_name = $(o).attr("name");
            if ($(o).prop("checked")) {
                _check_box_data.push($(o).val());
            }
        });
        if (_check_box_data.length) {
            data[_check_name] = _check_box_data.toString();
        }
        var _radio_box_data = [];
        var _radio_name;
        $("#" + formId + " input[type='radio']").each(function (i, o) {
            _radio_name = $(o).attr("name");
            if ($(o).prop("checked")) {
                _radio_box_data.push($(o).val());
            }
        });
        if (_radio_box_data.length) {
            data[_radio_name] = _radio_box_data.toString();
        }
        if (errorCount > 0) {
            alert("表单有错误，请验证数据！")
            return;
        }
        $.post(url, data, function (res) {
            fb(res);
        });
    }
}