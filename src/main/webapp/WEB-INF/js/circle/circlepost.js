/**
 * Created by guochunyan on 2016/7/14.
 */
$(function(){
    $("#post-list-table").bootstrapTable({
        locale: 'zh-CN',
        pagination: true,
        smartDisplay: false,
        cache: false,
        search: true,
        strictSearch: true,
        uniqueId: "id",
        pageSize: 20,
        pageList: new Array(20, 50, 100),
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
        }
    })
})

function createPost() {
    $("#postList").hide();
    $("#postCreate").show();
}
