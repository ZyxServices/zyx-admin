/**
 * Created by ZYX on 2016/7/12.
 */

var ISCHANGEIMG = '';/*判断修改活动的时候是否修改了图片*/
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
            }, 'imageR': {
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
                    callback: {
                        message: '开始日期不能大于结束日期',
                        callback: function (value, validator, $field) {
                            var endValue = $('#activityEndTime').val();
                            var endTime = new Date(endValue.replace("-", "/").replace("-", "/"));
                            var startTime = new Date(value.replace("-", "/").replace("-", "/"));
                            if (endValue == '') {
                                return true;
                            }else if (endValue != '') {
                                validator.updateStatus('endTime', 'VALID');
                                return startTime <= endTime;
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
            }/*, 'lastTime': {
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
            }*/, 'memberString': {
                validators: {
                    notEmpty: {
                        message: '必须填一个'
                    }
                }
            }, 'maxPeople': {
                validators: {
                    integer: {
                        message: '请输入整数'
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
                console.log(files)
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
                        if (result.state == 200) {
                            $('#activity-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data.url, 'img');
                        } else {
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
        contentType: "application/x-www-form-urlencoded",
        height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        queryParamsType: "undefined",
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
        pageDataNum: params.pageSize,
        pageNum: params.pageNumber,
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
            dataObj.isDeva = item.isDeva;
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

$("#confirmCmd").click(function () {
    $("#activityRecommend").modal('hide');
    $("#upload").modal('show');
    $("#uploadContent").html('推荐上传中...');
    if($("#recommendFile")[0].files[0] == undefined){/*没选图片*/
        activityRecommend();
    }else{
        var formData = new FormData();
        formData.append('imgFile', $("#recommendFile")[0].files[0]);
        $.ajax({
            url: '/v1/upload/file',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            success:function (result) {
                if(result.state == 200){
                    $("#imageUrl").val(result.data.url);
                    activityRecommend();
                }
            }
        })
    }
})

function activityRecommend() {
    $('#devaForm').ajaxSubmit({
        url: '/v1/deva/add',
        type: 'post',
        dataType: 'json',
        complete:function () {
            $("#upload").modal('hide');
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '推荐成功'
                });
                $('#activity-list-table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    })
}

$("#czS").click(function () {
    var formData = new FormData();
    formData.append('imgFile', $("#lefile")[0].files[0]);
    if ($("#listType").html() == "创建") {
        /*创建*/
        $.ajax({
            url: '/v1/upload/file',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                $("#upload").modal('show');
                $("#uploadContent").html('创建中，请稍后...');
                console.log($("#updateCreateFrom").data('bootstrapValidator').isValid());
                /*传图片之前做验证*/
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
                }
                return $("#updateCreateFrom").data('bootstrapValidator').isValid();
            },
            complete:function () {
                $("#upload").modal('hide');
            },
            success: function (result) {
                if (result.state == 200) {
                    $("#image").val(result.data.url);
                    updateCreateFrom('/v1/activity/release');
                }
            },
            error:function (result) {
                $.Popup({
                    confirm: false,
                    template: result
                });
            }
        })
    } else {
        /*修改*/
        var isChange = $("#image").val();
        if(ISCHANGEIMG != isChange){/*不相等代表换了图片*/
            $.ajax({
                url: '/v1/upload/file',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function () {
                    /*传图片之前做验证*/
                    $("#upload").modal('show');
                    $("#uploadContent").html('活动修改中，请稍后...');
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
                    }
                    return $("#updateCreateFrom").data('bootstrapValidator').isValid();
                },
                success:function (result) {
                    if(result.state == 200){
                        $("#image").val(result.data.url);
                        updateCreateFrom('/v1/activity/update');
                    }
                }
            })
        }else{
            if($("#updateCreateFrom").data('bootstrapValidator').isValid()){
                $("#upload").modal('show');
                $("#uploadContent").html('活动修改中，请稍后...');
                updateCreateFrom('/v1/activity/update');
            }
        }
    }
})

function updateCreateFrom(url) {
    $('#updateCreateFrom').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        complete:function () {
            $("#upload").modal('hide');
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

function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>');
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
    if(row.isDeva){
        dataArray.push('<a class="p5" href="javascript:void(0)" disabled>已推荐</a>')
    }else{
        dataArray.push('<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>');
    }
    return dataArray.join('');
}

var operateEvents = {
    'click .edit': function (e, value, row, index) {
        userList();
        $("#activityEndTime").attr({'disabled': false});
        $("#signEndTime").attr({'disabled': false});
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
        $.ajax({
            url: "/v1/deva/sequence",
            type: 'POST',
            dataType: 'json',
            async:false,
            data: {model: 1,area:1},
            success: function (result) {
                var option = '';
                if (result.state == 200) {
                    if(result.data.length > 0){
                        $("#activityRecommend").modal('show');
                        $.ajax({
                            url: "/v1/activity/queryActivityById",
                            type: 'POST',
                            dataType: 'json',
                            data: {activityId: row.id},
                            success: function (result) {
                                if (result.state == 200) {
                                    var datas = result.data;
                                    $("#activityName").val(datas.title);
                                    $("#activityId").val(datas.id);
                                    $("#activityImage").attr("src", "http://image.tiyujia.com/" + datas.imgUrls);
                                    $("#imageUrl").val(datas.imgUrls);
                                } else {
                                    $.Popup({
                                        confirm: false,
                                        template: result.successmsg
                                    })
                                }
                            }
                        });
                        for(var i = 0;i < result.data.length; i++){
                            option += '<option>'+result.data[i]+'</option>';
                        }
                        $("#sequence").html(option);
                    }else{/*banner序列号满了就不能进入推荐了*/
                        $.Popup({
                            confirm: false,
                            template: '活动banner序列号已满，请先删除其他序列号再推荐'
                        });
                    }
                } else {
                    $.Popup({
                        confirm: false,
                        template: '未获取到banner序号，请刷新页面'
                    })
                }
            }
        });
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
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
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
                $("#image").val(datas.imgUrls);
                ISCHANGEIMG = datas.imgUrls;
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
                        .bootstrapValidator('removeField', 'imageR')
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

function userList(){
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
            $("#choiceUser").html(user)
        }
    });
}
function createActivity() {
    userList();
    $("#listType").html("创建");
    $("#photoCover").html("选择文件");
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
    $("#maxPeople").val("999");
    $('#updateCreateFrom').data('bootstrapValidator').validateField('maxPeople');
}
/*是否需要审核*/
function isReviewed(obj) {
    var val = $("#examine").val();
    if (val == 1) {
        $("#userRequired").show(500);
        $('#updateCreateFrom').bootstrapValidator('addField', 'memberString', {
            validators: {
                notEmpty: {
                    message: '请至少选择一个比填项'
                }
            }
        });
    } else {
        $("#userRequired").hide(500);
        $('#updateCreateFrom').bootstrapValidator('removeField', 'memberString')
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
    $('#updateCreateFrom').bootstrapValidator('addField', 'imageR', {
        validators: {
            notEmpty: {
                message: '请上传图片'
            }
        }
    });
    $("#image").val($(this).val());
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#images").attr("src", objUrl);
        }
    }else {
        $("#photoCover").html("选择文件");
        $("#images").attr("src", "");
    }
});

/*推荐中type=file的样式处理*/
$('input[id=recommendFile]').change(function () {
    if ($(this).val()) {
        $('#recommendPhotoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#recommendImg").attr("src", objUrl);
        }
    }else{
        $("#recommendPhotoCover").html("选择文件");
        $("#recommendImg").attr("src", "");
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
/*
/!*选择推荐图*!/
function choiceDevaImg(obj) {
    var imgUrl = $(obj).find("img").attr("src").split("image.tiyujia.com/");
    $(obj).find("span").toggle();
    var isSelect = $(obj).find("span").hasClass("hide");
    if(isSelect){
        $(obj).find("span").removeClass("hide");
        $("#imageUrl").val(imgUrl);
    }else{
        $(obj).find("span").addClass("hide");
        $("#imageUrl").val("");
    }
}*/
/*活动批量删除*/
function batchDelete() {
    var bathId = [];
    $.map($("#activity-list-table").bootstrapTable('getSelections'), function (row) {
        bathId.push(row.id);
    });
    $.Popup({
        title: '删除',
        template: '这些活动的所有数据将被完全删除，不能再被浏览',
        saveEvent: function () {
            $.ajax({
                url: "/v1/activity/delActivity",
                async: false,
                type: "post",
                data: {id: bathId.join(), delType: 1},
                dateType: "json",
                success: function (result) {
                    if (result.state == 200) {
                        $.Popup({
                            confirm: false,
                            template: '删除成功'
                        })
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
