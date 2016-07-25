/**
 * Created by ZYX on 2016/7/20.
 */
$(function () {
    $("#administrators-list-table").bootstrapTable({
        cache: false,
        striped: true,
        pagination: true, //分页
        singleSelect: true,
        search: false,
        height: 500,
        smartDisplay: false,
        pageList: [5, 10, 20, 50],
        minimumCountColumns: 2,
        paginationPreText: "上一页",
        paginationNextText: "下一页"
    })
})
function createAdministrators() {
    $("#administratorsList").hide();
    $("#administratorsCreate").show();
}