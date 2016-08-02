/**
 * Created by ZYX on 2016/7/12.
 */
$(function () {
    $("#activity-group-table").bootstrapTable({
        url: "/v1/combination/queryCombination",
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
        contentType: "application/x-www-form-urlencoded",
        method: "post",
        queryParamsType: "limit",
        queryParams: queryParams,
        responseHandler: groupFromData
    })

})

/*activity-group-table列表请求的数据*/
function queryParams(params) {
    return {
        pageDataNum: params.limit,
        pageNum: params.offset + 1,
        search: params.search
    };
}
/*获取组合列表*/
function groupFromData(res) {
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
            dataObj.name = item.name;
            dataObj.createTime = item.createTime;
            // dataObj.visible = 0;
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

/*choice-group-table的数据处理*/
function choiceFromData(res) {
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
            dataObj.releaseTime = item.createTime;
            dataObj.time = item.startTime;
            dataObj.createMan = item.userId;
            dataObj.address = item.address;
            dataObj.pv = 0;
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
/*预览*/
function previewActivity(id) {
    console.log(id)
}
/*推荐*/
function recommend(id) {
    $("#activityRecommend").show();
    $("#activityList").hide();
}
/*编辑*/
function modify(id) {
    $("#activityGroupCreate").show();
    $("#activityGroupList").hide();
}
/*屏蔽*/
function shield(id) {
    $("#activity-group-shield").modal('toggle');
}
/*删除*/
function del(id,type) {
    // $("#activity-group-del").modal('toggle');
    var delType;
    if(type == 1){
        delType = 1;
    }else{
        delType = 0;
    }
    $.Popup({
        title:'删除',
        template: '该活动组合将被删除，不能再被浏览。独立活动列表中，仍可找到该活动组合中的活动 ?',
        saveEvent: function () {
            $.ajax({
                // url: "/v1/activity/delActivity",
                async: false,
                type: "post",
                data:{id:id,delType:type},
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
/*编辑时table的操作*/
function editeOperate(value, row, index) {
    $("#choice-activity-table").hide();
    $("#edite-choice-table").hide();
    var dataArray = new Array();
    dataArray.push('<a class="preview p5"   href="javascript:void(0)" title="preview" onclick="exitGroup(\'' + row.id + '\')">退出活动组合</a>');
    return dataArray.join('');
}
/*退出活动组合*/
function exitGroup(id) {
    console.log(id)
}
/*activityIds的值*/
var activityIds = [];
function createGroupActivity() {
    $("#activityGroupCreate").show();
    $("#activityGroupList").hide();
    $("#choice-activity-table").show();
    $("#choice-activity-table").bootstrapTable({
        url: "/v1/activity/queryActivity",
        striped: true,           //是否显示行间隔色
        toolbar: '#toolbar',        //工具按钮用哪个容器
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
        responseHandler: choiceFromData,
        onCheck:function (row) {
            activityIds.push(row.id);
            $("#activityIds").val(activityIds);
        },
        onUncheck:function (row) {
            activityIds.splice($.inArray(row.id,activityIds),1);
            $("#activityIds").val(activityIds);
        },
        onCheckAll:function (rows) {
            for(var i = 0;i < rows.length; i++){
                activityIds.push(rows[i].id);
            }
            $("#activityIds").val(activityIds);
        },
        onUncheckAll:function (row) {
            activityIds = [];
            $("#activityIds").val('');
        }
    })

}
/*创建活动组合*/
$('#group-form').ajaxForm({
    url: '/v1/combination/createCombination',
    type: 'post',
    dataType: 'json',
    beforeSubmit: function () {
        return $("#group-form").data('bootstrapValidator').isValid();
    },
    success: function (result) {
        if (result.state && result.state == 200) {
            location.reload();
        } else if (result.state && result.state == 303) {
            alert(result.errmsg)
        }
    }
});
function addGroup() {
    $('#group-form').submit();
}
/*发布时间的转化*/
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
/*type=file的样式处理*/
$('input[id=lefile]').change(function() {
    $('#photoCover').html($(this).val());
    $("#lefile").html($(this).val());
});

/*表单验证*/
$("#group-form").bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
        validating: 'glyphicon glyphicon-refresh'
    },
    fields:{
        'name': {
            validators: {
                notEmpty: {
                    message: '组合名称不能为空'
                }
            }
        },
        'image': {
            validators: {
                notEmpty: {
                    message: '必须上传图片'
                }
            }
        },
        'activityIds': {
            validators: {
                notEmpty: {
                    message: '必须选择活动'
                }
            }
        }
    }
})