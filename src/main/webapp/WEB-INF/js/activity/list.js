/**
 * Created by ZYX on 2016/7/12.
 */
$(function(){
    $('#activity-summernote').summernote({
        height:200
    });
    $("#activity-list-table").bootstrapTable({
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

function operate(value, row, index) {
    var e = '<a href="#" onclick="modify(\''+ row.id + '\')">修改</a> ';
    var d = '<a href="#" onclick="del(\''+ row.id +'\')">删除</a> ';
    return e + d;
}

function modify(id) {
    console.log(id)
}
function del(id) {
    console.log(id)
}

function createActivity() {
    $("#activityCreate").show();
    $("#activityList").hide();

}