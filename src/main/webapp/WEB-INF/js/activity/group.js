/**
 * Created by ZYX on 2016/7/12.
 */
$(function () {
    $("#activity-group-table").bootstrapTable({
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
        smartDisplay: false
    })
    $("#choice-activity-table").bootstrapTable({
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
        smartDisplay: false
    })
})

function operate(value, row, index) {
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview" onclick="previewActivity(\'' + row.id + '\')">预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="recommend(\'' + row.id + '\')">推荐</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="modify(\'' + row.id + '\')">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield" onclick="shield(\'' + row.id + '\')">屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove" onclick="del(\'' + row.id + '\')">删除</a>'
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
    console.log(id);
    $("#activity-group-shield").modal('toggle');
}
/*删除*/
function del(id) {
    $("#activity-group-del").modal('toggle');
}

function createGroupActivity() {
    $("#activityGroupCreate").show();
    $("#activityGroupList").hide();

}