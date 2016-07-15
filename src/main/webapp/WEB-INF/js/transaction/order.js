/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#order-list-table").bootstrapTable({
        data: [{
            id: 1,
            name: 'Item 1',
            time: '2016-07-15',
            startTime: '2016-07-15',
            createPerson: '....',
            place: '....',
            pv: '12',
            report: '12',
            url: 'baidu.com'
        }],
        locale: 'zh-US',
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
function modify(id) {
    console.log(id)
}
function del(id) {
    console.log(id)
}