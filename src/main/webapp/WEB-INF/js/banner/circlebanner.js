/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#circle-list-table").bootstrapTable({
        data: [{
            id: 1,
            circlename: 'baidu.com',
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
    $("#post-list-table").bootstrapTable({
        data: [{
            id: 1,
            postname: 'baidu.com',
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
function postOperate(value, row, index) {
    var e = '<a href="#" onclick="postModify(\''+ row.id + '\')">修改</a> ';
    var d = '<a href="#" onclick="postDel(\''+ row.id +'\')">删除</a> ';
    return e + d;
}
function modify(id) {
    console.log(id)
}
function del(id) {
    $("#delCircleBanner").modal('toggle');
}
function postModify(id) {
    console.log(id)
}
function postDel(id) {
    $("#delPostBanner").modal('toggle');
}

function createCircleBanner() {
    $("#circleBannerList").hide();
    $("#circleBannerCreate").show();
}