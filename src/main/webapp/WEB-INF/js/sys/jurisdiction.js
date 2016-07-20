/**
 * Created by ZYX on 2016/7/20.
 */
$(function () {
    $("#jurisdiction-list-table").bootstrapTable({
        cache: false,
        striped: true,
        pagination: true, //分页
        singleSelect: true,
        search: false,
        pageList: [5, 10, 20, 50],
        minimumCountColumns: 2,
        paginationPreText: "上一页",
        paginationNextText: "下一页",
    })
})