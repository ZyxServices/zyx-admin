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
        fields:{
            'title': {
                validators: {
                    notEmpty: {
                        message: '组合名称不能为空'
                    }
                }
            },'image': {
                validators: {
                    notEmpty: {
                        message: '请上传图片'
                    }
                }
            },'desc': {
                validators: {
                    notEmpty: {
                        message: '请输入内容'
                    }
                }
            },'startTime': {
                validators: {
                    notEmpty: {
                        message: '请选择开始时间'
                    }
                }
            },'endTime': {
                validators: {
                    notEmpty: {
                        message: '请选择结束时间'
                    }
                }
            },'address': {
                validators: {
                    notEmpty: {
                        message: '请填写正确的地址'
                    }
                }
            }
        }
    });

    $('#activity-summernote').on('summernote.change',function (content, $editable) {
        $("#desc").val($editable);
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('desc', 'NOT_VALIDATED',null)
            .validateField('desc');
    }).summernote({
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
                user += '<option value='+item.id+'>'+item.nickname+'</option>'
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
        smartDisplay: false,
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
    }).on('hide',function(e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('startTime', 'NOT_VALIDATED',null)
            .validateField('startTime');
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
    }).on('hide',function(e) {
        $('#updateCreateFrom').data('bootstrapValidator')
            .updateStatus('endTime', 'NOT_VALIDATED',null)
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
    });
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
            dataObj.createPerson = item.userId;
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
    url: '/v1/deva/queryActivity',
    type: 'post',
    dataType: 'json',
    beforeSubmit: function () {
        var devaId = $("#devaForm").find('input[name="devaId"]').val();
        var checked = true;
        if (devaId == undefined || devaId == "") {
            $.Popup({
                confirm:false,
                template:'未知错误，请刷新页面重试'
            })
            checked = false;
        }
        return checked;
    },
    success: function (result) {
        if (result.state && result.state == 200) {
            $.Popup({
                confirm:false,
                template:result.successmsg
            })
        } else if (result.state && result.state == 303) {
            $.Popup({
                confirm:false,
                template:result.errmsg
            })
        }
    }
});

$("#czS").click(function () {
    console.log($("#avtivityId").val())
    if($("#avtivityId").val() == ''){
        /*创建*/
        $('#updateCreateFrom').ajaxSubmit({
            url: '/v1/activity/release',
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
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
            success: function (result) {
                if (result.state && result.state == 200) {
                    $.Popup({
                        confirm:false,
                        template:result.successmsg
                    });
                    $("#activityList").show();
                    $("#createModify").hide();
                    $('#activity-list-table').bootstrapTable('refresh');
                } else if (result.state && result.state == 303) {
                    $.Popup({
                        confirm:false,
                        template:result.errmsg
                    })
                }
            }
        });
    }else{
        /*修改*/
        $('#updateCreateFrom').ajaxSubmit({
            url: '/v1/activity/update',
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
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
                }
                return isValid;
            },
            success: function (result) {
                if (result.state && result.state == 200) {
                    $.Popup({
                        confirm:false,
                        template:result.successmsg
                    });
                    $("#activityList").show();
                    $("#createModify").hide();
                    $('#activity-list-table').bootstrapTable('refresh');
                } else if (result.state && result.state == 303) {
                    $.Popup({
                        confirm:false,
                        template:result.errmsg
                    })
                }
            }
        });
    }
})

function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="preview p5"   href="javascript:void(0)" title="preview" onclick="previewActivity(\'' + row.id + '\')">预览</a>');
    dataArray.push('<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="recommend(\'' + row.id + '\',\'' + row.name + '\')">推荐</a>')
    dataArray.push('<a class="recommend p5" href="javascript:void(0)" title="modify" onclick="modify(\'' + row.id + '\')">编辑</a>')
    if (row.mask == 0) {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield" onclick="shield(\'' + row.id + '\', 1)">屏蔽</a>')
    } else {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield" onclick="shield(\'' + row.id + '\', 0)">解除屏蔽</a>')
    }
    if (row.del == 0) {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove" onclick="del(\'' + row.id + '\', 1)">删除</a>')
    } else {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove" onclick="del(\'' + row.id + '\', 0)">恢复删除</a>')
    }
    return dataArray.join('');
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
/*预览*/
function previewActivity(id) {
    $("#listType").html("预览");
    $("#choiceUser").attr("disabled","disabled");
    $("#title").attr("disabled","disabled");
    $("#imgWrap").hide();
    $("#imagesWrap").show();
    $('#activity-summernote').summernote('destroy');
    $('#activity-summernote').summernote({toolbar: false,airMode: true});
    $("#activityStartTime").attr("disabled","disabled");
    $("#activityEndTime").attr("disabled","disabled");
    $("#address").attr("disabled","disabled");
    $("#price").attr("disabled","disabled");
    $("#signEndTime").attr("disabled","disabled");
    $("#maxPeople").attr("disabled","disabled");
    $("#phone").attr("disabled","disabled");
    $("#visible").attr("disabled","disabled");
    $("#examine").attr("disabled","disabled");
    $("#czS").remove();
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
                $('input[name=type]').eq(!datas.type).attr({"checked":"checked","disabled":"disabled"});
                $('input[name=type]').eq(datas.type).attr({"disabled":"disabled"});
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
                    template.forEach(function (item, i) {
                        html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked disabled>" + item + "</label>";
                    })
                    // html += "<a href='javascript:void (0)' onclick='choiceMore()' id='addBtn'>+</a>";
                    $("#template").empty();
                    $("#template").append(html)
                }
            } else {
                $.Popup({
                    confirm:false,
                    template:result.successmsg
                })
            }
        }
    });
    $("#createModify").show();
    $("#activityList").hide();
}
/*推荐*/
function recommend(id, name) {
    $("#listType").html("推荐");
    $.ajax({
        url: "/v1/activity/queryActivityById",
        type: 'POST',
        dataType: 'json',
        data: {activityId: id},
        success: function (result) {
            if (result.state == 200) {
                var datas = result.data;
                $("#activityName").html(datas.title);
                $("#activityId").val(datas.id);
                $("#activityImage").attr("src", "http://image.tiyujia.com/" + datas.imgUrls)
            } else {
                $.Popup({
                    confirm:false,
                    template:result.successmsg
                })
            }
        }
    });
    $("#activityRecommend").show();
    $("#activityList").hide();
}
/*编辑*/
function modify(id) {
    $("#listType").html("编辑");
    $.ajax({
        url: "/v1/activity/queryActivityById",
        type: 'POST',
        dataType: 'json',
        data: {activityId: id},
        success: function (result) {
            if (result.state == 200) {
                var datas = result.data;
                $("#avtivityId").val(datas.id);
                $("#userId").val(datas.userId);
                $("#title").val(datas.title);
                $("#images").attr("src", "http://image.tiyujia.com/" + datas.imgUrls);
                $('#activity-summernote').summernote('code', datas.descContent);
                $("#activityStartTime").val(new Date(datas.startTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#activityEndTime").val(new Date(datas.endTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#address").val(datas.address);
                $('input[name=type]').eq(!datas.type).attr({"checked":"checked"});
                $("#signEndTime").val(new Date(datas.lastTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#maxPeople").val(datas.maxPeople);
                $("#phone").val(datas.phone);
                $("#price").val(datas.price);
                $("#visible").val(datas.visible);
                $("#examine").val(datas.examine);

                if (datas.examine == 1) {
                    isReviewed();
                    var template = (datas.memberTemplate).split(",");
                    var html = "";
                    template.forEach(function (item, i) {
                        html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked>" + item + "</label>";
                    })
                    html += "<a href='javascript:void (0)' onclick='choiceMore()' id='addBtn'>+</a>";
                    $("#template").empty();
                    $("#template").append(html)
                }
                $('#updateCreateFrom')
                // Remove field
                .bootstrapValidator('removeField', 'image')
                .data('bootstrapValidator').validate();

            } else {
                $.Popup({
                    confirm:false,
                    template:result.successmsg
                })
            }
        }
    });
    $("#imagesWrap").show();
    $("#createModify").show();
    $("#activityList").hide();
}
/*屏蔽*/
function shield(id, type) {
    $.Popup({
        title:'屏蔽',
        template: '屏蔽之后，该活动将不在首页活动和活动列表页展示，“我的关注”和“我的”中活动保留，仍可以被浏览',
        saveEvent: function () {
            $.ajax({
                url: "/v1/activity/maskActivity",
                async: false,
                type: "post",
                data: {id: id, maskType: type},
                dateType: "json",
                success: function (result) {
                    if(result.state == 200){
                        if(type == 1){
                            $.Popup({
                                confirm:false,
                                template:'屏蔽成功'
                            })
                        }else{
                            $.Popup({
                                confirm:false,
                                template:'解除屏蔽成功'
                            })
                        }
                        $('#activity-list-table').bootstrapTable('refresh');
                    }else{
                        $.Popup({
                            confirm:false,
                            template:'删除失败'
                        })
                    }
                }
            });
        }
    })
}
/*删除*/
function del(id, type) {
    $.Popup({
        title:'删除',
        template: '该活动的所有数据将被完全删除，不能再被浏览',
        saveEvent: function () {
            $.ajax({
                url: "/v1/activity/delActivity",
                async: false,
                type: "post",
                data: {id: id, delType: type},
                dateType: "json",
                success: function (result) {
                    if(result.state == 200){
                        if(type == 1){
                            $.Popup({
                                confirm:false,
                                template:'删除成功'
                            })
                        }else{
                            $.Popup({
                                confirm:false,
                                template:'恢复删除成功'
                            })
                        }
                        $('#activity-list-table').bootstrapTable('refresh');
                    }else{
                        $.Popup({
                            confirm:false,
                            template:'删除失败'
                        })
                    }
                }
            });
        }
    })
}

function createActivity() {
    $("#createModify").show();
    $("#activityList").hide();
    $("#imagesWrap").hide();
    $('#activity-summernote').summernote('code', '');
    $('#updateCreateFrom').data('bootstrapValidator').resetForm(true);
    $('#updateCreateFrom').bootstrapValidator('addField', 'image',{
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
    if(val == 1){
        $("#userRequired").show(500);
    }else{
        $("#userRequired").hide(500);
    }
}

/*修改活动选择更多*/
function choiceMore() {
    $("#addChoice").toggle(500);
    $("#addBtn").hide();
}
/*修改活动增加用户必填的字段*/
function createRequired() {
    var requiredVal = $("#requiredVal").val();
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
/*创建活动增加用户必填的字段*/
function createRequiredRel() {
    var requiredVal = $("#requiredValRel").val();
    var val = '<label class="checkbox"><input type="checkbox" checked value=' + requiredVal.trim() + '>' + requiredVal.trim() + '</label>';
    $("#addBtnRel").before(val);
    $("#requiredValRel").val('');
    $("#addBtnRel").show();
    $("#addChoiceRel").toggle(500);
}

/*创建中type=file的样式处理*/
$('input[id=lefile]').change(function() {
    $('#photoCover').html($(this).val());
    $("#lefile").html($(this).val());
});
