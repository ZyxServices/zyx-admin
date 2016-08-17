/**
 * Created by ZYX on 2016/7/12.
 */
$(function () {

    /*表单验证*/
    $("#updateCreateFrom").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'title': {
                validators: {
                    notEmpty: {
                        message: '组合名称不能为空'
                    }
                }
            }, 'image': {
                validators: {
                    notEmpty: {
                        message: '请上传图片'
                    }
                }
            }, 'desc': {
                validators: {
                    notEmpty: {
                        message: '请输入内容'
                    }
                }
            }, 'startTime': {
                validators: {
                    notEmpty: {
                        message: '请选择开始时间'
                    },
                    /*date: {
                     message: '请输入正确的日期格式,如:2016-01-01 00:00',
                     format: 'YYYY-MM-DD h:m'
                     },*/
                    callback: {
                        message: '开始日期不能大于结束日期或者开始时间不能小于截止时间',
                        callback: function (value, validator, $field) {
                            var endValue = $('#activityEndTime').val();
                            var lastValue = $('#signEndTime').val();
                            var endTime = new Date(endValue.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var lastTime = new Date(lastValue.replace("-", "/").replace("-", "/"));
                            if (endValue == '' && lastValue == '') {
                                return true;
                            } else if (endValue == '' && lastValue != '') {
                                validator.updateStatus('lastTime', 'VALID');
                                return lastTime <= startTime;
                            } else if (endValue != '' && lastValue == '') {
                                validator.updateStatus('endTime', 'VALID');
                                return startTime <= endTime;
                            } else if (endValue != '' && lastValue != '') {
                                validator.updateStatus('endTime', 'VALID');
                                validator.updateStatus('lastTime', 'VALID');
                                return startTime <= endTime && lastTime <= startTime;
                            }
                        }
                    }
                }
            }, 'endTime': {
                validators: {
                    notEmpty: {
                        message: '请选择结束时间'
                    },
                    callback: {
                        message: '结束日期不能小于开始日期',
                        callback: function (value, validator, $field) {
                            var startValue = $('#activityStartTime').val();
                            var endTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(startValue.replace("-", "/").replace("-", "/"));
                            validator.updateStatus('startTime', 'VALID');
                            return endTime >= startTime;
                        }
                    }
                }
            }, 'lastTime': {
                validators: {
                    notEmpty: {
                        message: '请选择活动截止时间'
                    },
                    callback: {
                        message: '活动截止时间不能大于开始日期',
                        callback: function (value, validator, $field) {
                            var startValue = $('#activityStartTime').val();
                            var lastTime = new Date(value.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(startValue.replace("-", "/").replace("-", "/"));
                            validator.updateStatus('startTime', 'VALID');
                            return lastTime <= startTime;
                        }
                    }
                }
            }
        }
    });

    $('#activity-summernote').on('summernote.change', function (content, $editable) {
        $("#desc").val($editable);
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('desc', 'NOT_VALIDATED', null)
            .validateField('desc');
    }).summernote({
        callbacks: {
            onImageUpload: function (files) {
                //上传图片到服务器，使用了formData对象，至于兼容性...据说对低版本IE不太友好
                var formData = new FormData();
                formData.append('imgFile', files[0]);
                $.ajax({
                    url: '/v1/upload/file',//后台文件上传接口
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (result) {
                        console.log(result)
                        if(result.state == 200){
                            $('#activity-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data, 'img');
                        }else{
                            $.Popup({
                                confirm: false,
                                template: result.successmsg
                            })
                        }
                    }
                });
            }
        },
        lang: 'zh-CN',
        height: 200
    });

    /*查询创建活动时需要选择的用户*/
    $.ajax({
        url: "/v1/appUser/list/official/all",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            var user = '';
            result.rows.forEach(function (item, i) {
                user += '<option value=' + item.id + '>' + item.nickname + '</option>'
            })
            $("#choiceUser").append(user)
        }
    });

    $("#activity-list-table").bootstrapTable({
        url: "/v1/activity/queryActivity",
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        pageList: [10, 15, 20, 25],  //记录数可选列表
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        strictSearch: true,
        height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        // smartDisplay: false, 超过1页才显示页数
        sidePagination: "server",
        method: "get",
        queryParamsType: "limit",
        queryParams: queryParams,
        responseHandler: fromData

    })
    $('#activityStartTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: true,
        todayBtn: true,
        autoclose: true,
        todayHighlight: 1,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('startTime', 'NOT_VALIDATED', null)
            .validateField('startTime');
        $("#activityEndTime").attr({'disabled': false});
        $("#signEndTime").attr({'disabled': false})
    });
    $('#activityEndTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: true,
        todayBtn: true,
        autoclose: true,
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('endTime', 'NOT_VALIDATED', null)
            .validateField('endTime');
    });
    $('#signEndTime').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        todayBtn: true,
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    }).on('hide', function (e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('lastTime', 'NOT_VALIDATED', null)
            .validateField('lastTime');
    });
});

$("#activityStartTime,#activityEndTime,#signEndTime").focus(function () {
    $(this).blur();
});

function queryParams(params) {
    return {
        pageDataNum: params.limit,
        pageNum: (params.offset + 1),
        search: params.search
    };
}

function fromData(res) {
    if (res.state == 480) {
        $("#content-wrapper").html("<section class='content'>无权限</section>");
        return false;
    }
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.name = item.title;
            dataObj.time = item.createTime;
            dataObj.startTime = item.startTime;
            dataObj.createPerson = item.userName;
            dataObj.address = item.address;
            dataObj.pv = 0;
            dataObj.report = 0;
            dataObj.url = 0;
            dataObj.mask = item.mask;
            dataObj.del = item.del;
            dataArray.push(dataObj)
        });
        if (datas.length == 0) {
            var dataObj = {};
            dataArray.push(dataObj);
        }
        return {
            rows: dataArray,
            total: res.dataCount
        }
    }
}

$('#devaForm').ajaxForm({
    url: '/v1/deva/insertActivityDeva',
    type: 'post',
    dataType: 'json',
    beforeSubmit: function () {
        var devaId = $("#devaForm").find('input[name="devaId"]').val();
        var checked = true;
        if (devaId == undefined || devaId == "") {
            $.Popup({
                confirm: false,
                template: '未知错误，请刷新页面重试'
            })
            checked = false;
        }
        return checked;
    },
    success: function (result) {
        if (result.state && result.state == 200) {
            $.Popup({
                confirm: false,
                template: result.successmsg
            })
            $("#activityList").show();
            $("#activityRecommend").hide();
            $('#activity-list-table').bootstrapTable('refresh');
        } else {
            $.Popup({
                confirm: false,
                template: result.errmsg
            })
        }
    }
});

$("#czS").click(function () {
    if ($("#avtivityId").val() == '') {
        /*创建*/
        $('#updateCreateFrom').ajaxSubmit({
            url: '/v1/activity/release',
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
                var examinefalg = true;
                if ($("#examine").val() == 1) {
                    var desc = "";
                    $("#template").find("input:checked").each(function (item) {
                        if (item == 0) {
                            desc += $(this).val()
                        } else {
                            desc += ("," + $(this).val());
                        }
                    });
                    $("#memberTemplate").val(desc);
                    if ($("#memberTemplate").val() != "") {
                        examinefalg = false;
                    }
                }
                return $("#updateCreateFrom").data('bootstrapValidator').isValid() && examinefalg;
            },
            success: function (result) {
                if (result.state && result.state == 200) {
                    $.Popup({
                        confirm: false,
                        template: result.successmsg
                    });
                    $("#activityList").show();
                    $("#createModify").hide();
                    $('#activity-list-table').bootstrapTable('refresh');
                } else if (result.state && result.state == 303) {
                    $.Popup({
                        confirm: false,
                        template: result.errmsg
                    })
                }
            }
        });
    } else {
        /*修改*/
        $('#updateCreateFrom').ajaxSubmit({
            url: '/v1/activity/update',
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
                var examinefalg = true;
                var isValid = $("#updateCreateFrom").data('bootstrapValidator').isValid();
                if ($("#examine").val() == 1) {
                    var desc = "";
                    $("#template").find("input:checked").each(function (item) {
                        if (item == 0) {
                            desc += $(this).val()
                        } else {
                            desc += ("," + $(this).val());
                        }
                    });
                    $("#memberTemplate").val(desc);
                    if ($("#memberTemplate").val() == "") {
                        examinefalg = false;
                    }
                }
                return isValid && examinefalg;
            },
            success: function (result) {
                if (result.state && result.state == 200) {
                    $.Popup({
                        confirm: false,
                        template: result.successmsg
                    });
                    $("#activityList").show();
                    $("#createModify").hide();
                    $('#activity-list-table').bootstrapTable('refresh');
                } else if (result.state && result.state == 303) {
                    $.Popup({
                        confirm: false,
                        template: result.errmsg
                    })
                }
            }
        });
    }
})

function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>');
    dataArray.push('<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    if (row.mask == 0) {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield">屏蔽</a>')
    } else {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield">解除屏蔽</a>')
    }
    if (row.del == 0) {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>')
    } else {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">恢复删除</a>')
    }
    return dataArray.join('');
}

var operateEvents = {
    'click .edit': function (e, value, row, index) {
        $("#activityEndTime").attr({'disabled': false});
        $("#signEndTime").attr({'disabled': false})
        $("#listType").html("编辑");
        queryActivityById(row.id, 0);
        $("#createModify").show();
        $("#activityList").hide();

    },
    'click .preview': function (e, value, row, index) {
        $("#listType").html("预览");
        $("#choiceUser").attr("disabled", "disabled");
        $("#title").attr("disabled", "disabled");
        $("#imgWrap").hide();
        $('#activity-summernote').summernote('destroy');
        $('#activity-summernote').summernote({toolbar: false, airMode: true});
        $("#activityStartTime").attr("disabled", "disabled");
        $("#activityEndTime").attr("disabled", "disabled");
        $("#address").attr("disabled", "disabled");
        $("#price").attr("disabled", "disabled");
        $("#signEndTime").attr("disabled", "disabled");
        $("#maxPeople").attr("disabled", "disabled");
        $("#phone").attr("disabled", "disabled");
        $("#visible").attr("disabled", "disabled");
        $("#examine").attr("disabled", "disabled");
        $("#czS").remove();
        queryActivityById(row.id, 1);
        $("#createModify").show();
        $("#activityList").hide();
    },
    'click .recommend': function (e, value, row, index) {
        $("#listType").html("推荐");
        $.ajax({
            url: "/v1/activity/queryActivityById",
            type: 'POST',
            dataType: 'json',
            data: {activityId: row.id},
            success: function (result) {
                if (result.state == 200) {
                    var datas = result.data;
                    $("#activityName").html(datas.title);
                    $("#activityId").val(datas.id);
                    $("#activityImage").attr("src", "http://image.tiyujia.com/" + datas.imgUrls)
                } else {
                    $.Popup({
                        confirm: false,
                        template: result.successmsg
                    })
                }
            }
        });
        $("#activityRecommend").show();
        $("#activityList").hide();
    },
    'click .Shield': function (e, value, row, index) {
        var type = row.mask == 0 ? 1 : 0;
        $.Popup({
            title: '屏蔽',
            template: '屏蔽之后，该活动将不在首页活动和活动列表页展示，“我的关注”和“我的”中活动保留，仍可以被浏览',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/activity/maskActivity",
                    async: false,
                    type: "post",
                    data: {id: row.id, maskType: type},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            if (type == 1) {
                                $.Popup({
                                    confirm: false,
                                    template: '屏蔽成功'
                                })
                            } else {
                                $.Popup({
                                    confirm: false,
                                    template: '解除屏蔽成功'
                                })
                            }
                            $('#activity-list-table').bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .remove': function (e, value, row, index) {
        var type = row.del == 0 ? 1 : 0;
        $.Popup({
            title: '删除',
            template: '该活动的所有数据将被完全删除，不能再被浏览',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/activity/delActivity",
                    async: false,
                    type: "post",
                    data: {id: row.id, delType: type},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            if (type == 1) {
                                $.Popup({
                                    confirm: false,
                                    template: '删除成功'
                                })
                            } else {
                                $.Popup({
                                    confirm: false,
                                    template: '恢复删除成功'
                                })
                            }
                            $('#activity-list-table').bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败'
                            })
                        }
                    }
                });
            }
        })
    }
}
/*根据id获取活动详情*/
function queryActivityById(id, type) {
    $.ajax({
        url: "/v1/activity/queryActivityById",
        type: 'POST',
        dataType: 'json',
        data: {activityId: id},
        success: function (result) {
            if (result.state == 200) {
                var datas = result.data;
                $("#avtivityId").val(datas.id);
                $("#choiceUser").val(datas.userId);
                $("#title").val(datas.title);
                $("#images").attr("src", "http://image.tiyujia.com/" + datas.imgUrls);
                $('#activity-summernote').summernote('code', datas.descContent);
                $("#activityStartTime").val(new Date(datas.startTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#activityEndTime").val(new Date(datas.endTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#address").val(datas.address);
                $('input[name=type]').eq(!datas.type).attr({"checked": "checked"});
                $("#price").val(datas.price);
                $("#signEndTime").val(new Date(datas.lastTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#maxPeople").val(datas.maxPeople);
                $("#phone").val(datas.phone);
                $("#visible").val(datas.visible);
                $("#examine").val(datas.examine);
                if (datas.examine == 1) {
                    isReviewed();
                    var template = (datas.memberTemplate).split(",");
                    var html = "";
                    if (type == 1) {
                        template.forEach(function (item, i) {
                            html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked disabled>" + item + "</label>";
                        });
                    } else {
                        template.forEach(function (item, i) {
                            html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked>" + item + "</label>";
                        });
                        html += "<a href='javascript:void (0)' onclick='choiceMore()' id='addBtn'>+</a>";
                    }
                    $("#template").empty();
                    $("#template").append(html)
                }
                if (type == 0) {/*0代表编辑，1代表预览*/
                    $('#updateCreateFrom')
                        .bootstrapValidator('removeField', 'image')
                        .data('bootstrapValidator').validate();
                }
            } else {
                $.Popup({
                    confirm: false,
                    template: result.successmsg
                })
            }
        }
    });
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
function createActivity() {
    $("#listType").html("创建");
    $("#createModify").show();
    $("#activityList").hide();
    $("#images").attr({'src': ''});
    $("#avtivityId").val('');
    $('#activity-summernote').summernote('reset');
    var html = '<label class="checkbox"><input type="checkbox" value="手机号码">手机号码</label><label class="checkbox"><input type="checkbox" value="姓名">姓名</label> <label class="checkbox"><input type="checkbox" value="身份证号码">身份证号码</label> <label class="checkbox"><input type="checkbox" value="性别">性别</label> <label class="checkbox"><input type="checkbox" value="年龄">年龄</label> <label class="checkbox"><input type="checkbox" value="地址">地址</label> <a href="javascript:void (0)" onclick="choiceMore()" id="addBtn">+</a>'
    $("#template").html(html);
    $("#userRequired").hide();
    $('#updateCreateFrom')[0].reset();
    $('#updateCreateFrom').data('bootstrapValidator').resetForm(true);
    /*仅仅清空验证的表单*/
    $('#updateCreateFrom').bootstrapValidator('addField', 'image', {
        validators: {
            notEmpty: {
                message: '请上传图片'
            }
        }
    });
}
/*是否需要审核*/
function isReviewed(obj) {
    var val = $("#examine").val();
    if (val == 1) {
        $("#userRequired").show(500);
    } else {
        $("#userRequired").hide(500);
    }
}
/*修改活动选择更多*/
function choiceMore() {
    $("#addChoice").toggle(500);
    $("#addBtn").hide();
}
/*活动增加用户必填的字段*/
function createRequired() {
    var requiredVal = $("#requiredVal").val();
    if (requiredVal == '') {
        $("#userRequiredInput").html("必填字段不能为空");
        return;
    } else {
        $("#userRequiredInput").html("*");
    }
    var val = '<label class="checkbox"><input type="checkbox" checked value=' + requiredVal.trim() + '>' + requiredVal.trim() + '</label>';
    $("#addBtn").before(val);
    $("#requiredVal").val('');
    $("#addBtn").show();
    $("#addChoice").toggle(500);
}
/*创建活动选择更多*/
function choiceMoreRel() {
    $("#addChoiceRel").toggle(500);
    $("#addBtnRel").hide();
}

/*创建中type=file的样式处理*/
$('input[id=lefile]').change(function () {
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        $("#lefile").html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#images").attr("src", objUrl);
        }
    }
});

/*推荐中type=file的样式处理*/
$('input[id=recommendFile]').change(function () {
    if ($(this).val()) {
        $('#recommendPhotoCover').html($(this).val());
        $("#recommendFile").html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#recommendImg").attr("src", objUrl);
        }
    }
});
/*图片预览*/
//建立一個可存取到該file的url
function getImgURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}
