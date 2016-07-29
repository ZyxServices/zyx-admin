/**
 * Created by ZYX on 2016/7/12.
 */
$(function () {

    $("#appQueryEnd").click(function () {
        $('#userTable').bootstrapTable("refresh")
    })
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
    $('#activityStartTime,#activityStartTimeRel').datetimepicker({
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
        $('#updateFromRel').data('bootstrapValidator')
            .updateStatus('startTime', 'NOT_VALIDATED',null)
            .validateField('startTime');
    });
    $('#activityStartTimeRel').on('hide',function(e) {
        $('#updateFromRel').data('bootstrapValidator')
            .updateStatus('startTime', 'NOT_VALIDATED',null)
            .validateField('startTime');
    });
    $('#activityEndTimeRel').on('hide',function(e) {
        $('#updateFromRel').data('bootstrapValidator')
            .updateStatus('endTime', 'NOT_VALIDATED',null)
            .validateField('endTime');
    });
    $('#activityEndTime,#activityEndTimeRel').datetimepicker({
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
    })
    $('#activityStartTime').on('hide',function(e) {
        $('#updateFromRel').data('bootstrapValidator')
            .updateStatus('startTime', 'NOT_VALIDATED',null)
            .validateField('startTime');
    });
    $('#activityEndTime').on('hide',function(e) {
        $('#updateFromRel').data('bootstrapValidator')
            .updateStatus('endTime', 'NOT_VALIDATED',null)
            .validateField('endTime');
    });
    $('#signStartTime,#signStartTimeRel').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        format: 'yyyy-mm-dd hh:ii',
        todayBtn: true,
        autoclose: true,
        todayHighlight: 1,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    });
    $('#signEndTime,#signEndTimeRel').datetimepicker({
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

    $('#devaForm').ajaxForm({
        url: '/v1/deva/queryActivity',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            var devaId = $("#devaForm").find('input[name="devaId"]').val();
            var checked = true;
            if (devaId == undefined || devaId == "") {
                alert('未知错误，请刷新页面重试');
                checked = false;
            }
            return checked;
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                alert(result.successmsg)
            } else if (result.state && result.state == 303) {
                alert(result.errmsg)
            }
        }
    });


    $('#updateFrom').ajaxForm({
        url: '/v1/activity/update',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            var title = $('input[name="title"]').val();
            var summernote = $('#activity-summernote').summernote('code');
            var checked = true;
            if (title == undefined || title == "") {
                alert('标题不能为空');
                checked = false;
            }
            if (summernote == undefined || summernote == "") {
                alert('内容不能为空');
                checked = false;
            } else {
                $("#desc").val(summernote);
            }

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
            return checked;
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                alert(result.successmsg)
            } else if (result.state && result.state == 303) {
                alert(result.errmsg)
            }
        }
    });

    $('#updateFromRel').ajaxForm({
        url: '/v1/activity/release',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            var title = $('input[name="titleRel"]').val();
            var file = $('input[name="image"]').val();
            var summernote = $('#activity-summernote').summernote('code');
            var activityStartTime = $("#activityStartTimeRel").val();
            var activityEndTime = $("#activityEndTimeRel").val();
            var address = $("#addressRel").val();
            var signEndTime = $("#signEndTimeRel").val();
            var maxPeople = $("#maxPeopleRel").val();
            var phone = $("#phoneRel").val();
            var checked = true;
            if (title == undefined || title == "") {
                alert('标题不能为空');
                checked = false;
            }
            if(file == undefined || $.trim(file)==''){
                alert('请上传活动封面');
                checked = false;
            }
            if (summernote == undefined || summernote == "") {
                alert('内容不能为空');
                checked = false;
            } else {
                $("#desc").val(summernote);
            }
            if(activityStartTime == undefined || $.trim(activityStartTime)==''){
                alert('请选择活动开始时间');
                checked = false;
            }
            if(activityEndTime == undefined || $.trim(activityEndTime)==''){
                alert('请选择活动结束时间');
                checked = false;
            }
            if(address == undefined || $.trim(address)==''){
                alert('请填写地址');
                checked = false;
            }
            if(signEndTime == undefined || $.trim(signEndTime)==''){
                alert('请选择活动报名截至时间');
                checked = false;
            }
            if(maxPeople == undefined || $.trim(maxPeople)==''){
                alert('请填写参加活动最大人数');
                checked = false;
            }
            if(phone == undefined || $.trim(phone)==''){
                alert('请填写你的联系电话');
                checked = false;
            }

            if ($("#examineRel").val() == 1) {
                var desc = "";
                $("#templateRel").find("input:checked").each(function (item) {
                    if (item == 0) {
                        desc += $(this).val()
                    } else {
                        desc += ("," + $(this).val());
                    }
                });
                $("#memberTemplateRel").val(desc);
            }
            return checked;
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                alert(result.successmsg)
            } else if (result.state && result.state == 303) {
                alert(result.errmsg)
            }
        }
    });


    $("#maskSuccess").click(function () {
        var id = $("#maskSuccess").attr("activityId");
        var maskType = $("#maskSuccess").attr("maskType");
        $.ajax({
            url: "/v1/activity/maskActivity",
            type: 'POST',
            dataType: 'json',
            data: {
                id: id,
                maskType: maskType
            },
            success: function (result) {
                if(result.state == 200){
                    if(maskType == 1){
                        alert("屏蔽成功");
                    }else{
                        alert("解除屏蔽成功");
                    }
                    window.location.reload();
                }else{
                    alert("删除失败")
                }
            }
        });
    });


    $("#delSuccess").click(function () {
        var id = $("#delSuccess").attr("activityId");
        var delType = $("#delSuccess").attr("delType");
        $.ajax({
            url: "/v1/activity/delActivity",
            type: 'POST',
            dataType: 'json',
            data: {
                id: id,
                delType: delType
            },
            success: function (result) {
                if(result.state == 200){
                    if(delType == 1){
                        alert("删除成功");
                    }else{
                        alert("恢复删除成功");
                    }
                    window.location.reload();
                }else{
                    alert("删除失败")
                }
            }
        });
    });
});


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
    $("#title").attr("disabled","disabled");
    $("input[name=image]").remove();
    $('#activity-summernote').summernote({toolbar: false,airMode: true});
    $("#activityStartTime").attr("disabled","disabled");
    $("#activityEndTime").attr("disabled","disabled");
    $("#address").attr("disabled","disabled");
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
                $("#userId").val(datas.userId);
                $("#title").val(datas.title)
                $("#images").attr("src", "http://image.tiyujia.com/" + datas.imgUrls);
                $('#activity-summernote').summernote('code', datas.descContent)
                $("#activityStartTime").val(new Date(datas.startTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#activityEndTime").val(new Date(datas.endTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#address").val(datas.address);
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
                        html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked>" + item + "</label>";
                    })
                    html += "<a href='javascript:void (0)' onclick='choiceMore()' id='addBtn'>+</a>";
                    $("#template").empty();
                    $("#template").append(html)
                }
            } else {
                alert(result.successmsg)
            }
        }
    });
    $("#activityModify").show();
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
                alert(result.successmsg)
            }
        }
    });
    $("#activityRecommend").show();
    $("#activityList").hide();
}
/*编辑*/
function modify(id) {
    $("#listType").html("编辑");
    $('#activity-summernote').summernote({
        lang: 'zh-CN',
        height: 200
    });
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
                $("#title").val(datas.title)
                $("#images").attr("src", "http://image.tiyujia.com/" + datas.imgUrls);
                $('#activity-summernote').summernote('code', datas.descContent);
                $("#activityStartTime").val(new Date(datas.startTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#activityEndTime").val(new Date(datas.endTime).format("yyyy-mm-dd HH:MM:ss"));
                $("#address").val(datas.address);
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
                        html += "<label class='checkbox'><input type='checkbox' value='" + item + "' checked>" + item + "</label>";
                    })
                    html += "<a href='javascript:void (0)' onclick='choiceMore()' id='addBtn'>+</a>";
                    $("#template").empty();
                    $("#template").append(html)
                }
            } else {
                alert(result.successmsg)
            }
        }
    });
    $("#activityModify").show();
    $("#activityList").hide();
}
/*屏蔽*/
function shield(id, type) {
    $("#listType").html("屏蔽");
    $("#maskSuccess").attr("activityId", id);
    $("#maskSuccess").attr("maskType", type);
    $("#activity-shield").modal('toggle');
}
/*删除*/
function del(id, type) {
    $("#listType").html("删除");
    $("#delSuccess").attr("activityId", id);
    $("#delSuccess").attr("delType", type);
    $("#activity-del").modal('toggle');
}

function createActivity() {
    $('#activity-summernoteRel').summernote({
        lang: 'zh-CN',
        height: 200,
        onChange:function (content, $editable) {
            console.log(content);
            console.log($editable)
        }
    });
    $("#userIdRel").val();
    $("#activityCreate").show();
    $("#activityList").hide();

}
/*是否需要审核*/
function isReviewed(obj) {
    $("#userRequired").toggle(500);
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

/*创建活动是否需要审核*/
function isReviewedRel(obj) {
    $("#userRequiredRel").toggle(500);
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

/*表单验证*/
var returnRromRel = $("#updateFromRel").bootstrapValidator({
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