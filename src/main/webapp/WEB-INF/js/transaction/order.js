/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#order-list-table").bootstrapTable({
        data: [{
            orderNum: "154686565464",
            image: 'url',
            goodsNo: '4546484515',
            costPrice: '$45',
            price: '45',
            purchaseQuantity: '78',
            buyers: '我',
            actualCash: '85',
            actualGold: '22',
            netQrofit: '66',
            tradeTime: '2016-02-05',
            tradeState: '完成',
            comment: '好'
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
    var e = '<a href="#" onclick="lookUp(\''+ row.id + '\')">查看</a> ';
    var d = '<a href="#" onclick="del(\''+ row.id +'\')">删除</a> ';
    return e + d;
}
function lookUp(id) {
    console.log(id)
}
function del(id) {
    console.log(id)
}