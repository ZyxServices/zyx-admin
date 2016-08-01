/**
 * Created by ZYX on 2016/7/12.
 */
$(function () {
    $("#activity-group-table").bootstrapTable({
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
        smartDisplay: false
    })

})

/*activity-group-table列表请求的数据*/
function queryParams(params) {
    return {
        pageDataNum: params.limit,
        pageNum: (params.offset + 1),
        search: params.search
    };
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
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview" onclick="previewActivity(\'' + row.id + '\')">预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="recommend(\'' + row.id + '\')">推荐</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="modify(\'' + row.id + '\')">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield" onclick="shield(\'' + row.id + '\')">屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove" onclick="del(\'' + row.id+ '\',this)">删除</a>'
    ].join('');
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
function del(id,obj) {
    // $("#activity-group-del").modal('toggle');
    var delType;
    if($(obj).html() == '删除'){
        delType = 1;
    }else{
        delType = 0;
    }
    /*$.Popup({
        template: '该活动组合将被删除，不能再被浏览。独立活动列表中，仍可找到该活动组合中的活动 ?',
        saveEvent: function () {
            $.ajax({
                url: "/v1/activity/delActivity",
                async: false,
                type: "post",
                data:{id:id,delType:delType},
                dateType: "json",
                success: function (result) {
                    if(delType == 1){
                        $(obj).html('取消删除');
                    }else{
                        $(obj).html('删除');
                    }
                }
            });
        }
    })*/
}

function createGroupActivity() {
    $("#activityGroupCreate").show();
    $("#activityGroupList").hide();
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
        responseHandler: choiceFromData
    })

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
        'groupname': {
            validators: {
                notEmpty: {
                    message: '组合名称不能为空'
                }
            }
        },
        'cover': {
            validators: {
                notEmpty: {
                    message: '必须上传图片'
                }
            }
        }
    }
})