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
            dataObj.del = item.del;
            dataObj.mask = item.mask;
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
    $.ajax({
        url: "/v1/combination/queryCombinationById",
        async: false,
        type: "post",
        data: {combinationId: id},
        dateType: "json",
        success: function (result) {
            if(result.state == 200){
                $("#name").val(result.data.name);
                $("#images").attr("src", "http://image.tiyujia.com/" + result.data.image);
            }else{
                $.Popup({
                    confirm:false,
                    template:'查询失败'
                })
            }
        }
    });
    $("#edit-choice-table").bootstrapTable({
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
        contentType: "application/x-www-form-urlencoded",
        smartDisplay: false,
        sidePagination: "server",
        method: "get",
        queryParamsType: "limit",
        queryParams: queryParams,
        responseHandler: choiceEditFromData,
        onCheck:function (row) {
            activityIds.push(row.id);
            $("#activityIds-edit").val(activityIds);
            $('#group-edit-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onUncheck:function (row) {
            activityIds.splice($.inArray(row.id,activityIds),1);
            $("#activityIds-edit").val(activityIds);
            $('#group-edit-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onCheckAll:function (rows) {
            for(var i = 0;i < rows.length; i++){
                activityIds.push(rows[i].id);
            }
            $("#activityIds-edit").val(activityIds);
            $('#group-edit-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onUncheckAll:function (row) {
            activityIds = [];
            $("#activityIds-edit").val('');
            $('#group-edit-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        }
    });
    $("#activityGroupEdit").show();
    $("#activityGroupList").hide();
    $("#activityGroupCreate").hide();
    $("#combinationId").val(id);
}
function choiceEditFromData(res) {
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
        var checkedId = [];
        $.ajax({
            url: "/v1/combination/queryCombinationIdByActivity",
            async: true,
            type: "post",
            data: {combinationId: $("#combinationId").val()},
            dateType: "json",
            success: function (result) {
                checkedId = result.data;
                $("#activityIds-edit").val(checkedId);
                $("#edit-choice-table").bootstrapTable("checkBy", {field:"id", values:checkedId});
            }
        });
        return {
            rows: dataArray,
            total: res.dataCount
        }
    }
}
/*屏蔽*/
function shield(id,type) {
    $.Popup({
        title:'屏蔽',
        template: '该活动组合将被删除，不能再被浏览。 独立活动列表中，仍可找到该活动组合中的活动。',
        saveEvent: function () {
            $.ajax({
                url: "/v1/combination/maskCombination",
                async: false,
                type: "post",
                data: {combinationId: id, mask: type},
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
                        $('#activity-group-table').bootstrapTable('refresh');
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
function del(id,type) {
    $.Popup({
        title:'删除',
        template: '该活动组合将被删除，不能再被浏览。独立活动列表中，仍可找到该活动组合中的活动 ?',
        saveEvent: function () {
            $.ajax({
                url: "/v1/combination/delCombination",
                async: false,
                type: "post",
                data:{combinationId:id,delType:type},
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
                        $('#activity-group-table').bootstrapTable('refresh');
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
function editOperate(value, row, index) {
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
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onUncheck:function (row) {
            activityIds.splice($.inArray(row.id,activityIds),1);
            $("#activityIds").val(activityIds);
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onCheckAll:function (rows) {
            for(var i = 0;i < rows.length; i++){
                activityIds.push(rows[i].id);
            }
            $("#activityIds").val(activityIds);
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onUncheckAll:function (row) {
            activityIds = [];
            $("#activityIds").val('');
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
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

/*修改*/
function confirmUpdate() {
    $('#group-edit-form').submit();
}
$('#group-edit-form').ajaxForm({
    url: '/v1/combination/updateCombination',
    type: 'post',
    dataType: 'json',
    beforeSubmit: function () {
        return $("#group-edit-form").data('bootstrapValidator').isValid();
    },
    success: function (result) {
        if (result.state && result.state == 200) {
            $.Popup({
                confirm:false,
                template:result.successmsg
            });
            $("#activityGroupCreate").hide();
            $("#activityGroupEdit").hide();
            $("#activityGroupList").show();
            $('#activity-list-table').bootstrapTable('refresh');
        } else if (result.state && result.state == 303) {
            $.Popup({
                confirm:false,
                template:result.errmsg
            })
        }
    }
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
$("#group-edit-form").bootstrapValidator({
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
        'activityIds': {
            validators: {
                notEmpty: {
                    message: '必须选择活动'
                }
            }
        }
    }
})