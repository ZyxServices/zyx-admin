/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#homepage-list-table").bootstrapTable({
        data: [{
            id: 1,
            url: 'baidu.com',
            image: 'Item 1',
            order: '1',
            activation: '是'
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
    $("#stand-list-table").bootstrapTable({
        data: [{
            id: 1,
            url: 'baidu.com',
            image: 'Item 1',
            order: '1',
            activation: '是'
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

function operate(value, row, index) {
    var e = '<a href="#" onclick="modify(\''+ row.id + '\')">修改</a> ';
    var d = '<a href="#" onclick="del(\''+ row.id +'\')">删除</a> ';
    return e + d;
}
/*看台列表的操作*/
function standoperate(value, row, index) {
    var e = '<a href="#" onclick="standModify(\''+ row.id + '\')">修改</a> ';
    var d = '<a href="#" onclick="standDel(\''+ row.id +'\')">删除</a> ';
    return e + d;
}
function modify(id) {
    console.log(id)
}
function del(id) {
    console.log(id);
    $("#delHomeBanner").modal('toggle');
}
function standModify(id) {
    console.log(id)
}
function standDel(id) {
    console.log(id);
    $("#delStandBanner").modal('toggle');
}

function liveCreateBanner() {
    $("#liveBannerList").hide();
    $("#liveBannerCreate").show();
}