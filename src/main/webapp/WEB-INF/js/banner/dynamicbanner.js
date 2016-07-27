/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#dynamic-list-table").bootstrapTable({
        data: [{
            id: 1,
            image: 'Item 1',
            publisher: 'baidu.com',
            order: '1'
        }],
        locale: 'zh-US',
        pagination: true,
        smartDisplay: false,
        cache: false,
        search: true,
        strictSearch: true,
        uniqueId: "id",
        height:500,
        pageSize: 20,
        pageList: new Array(20, 50, 100),
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
        }
    })
})