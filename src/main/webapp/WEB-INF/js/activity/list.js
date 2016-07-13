/**
 * Created by ZYX on 2016/7/12.
 */
$(function(){
    $("#activity-list-table").bootstrapTable({
        locale: 'zh-CN',
        pagination: true,
        smartDisplay: false,
        cache: false,
        search: true,
        strictSearch: true,
        height:"300",
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