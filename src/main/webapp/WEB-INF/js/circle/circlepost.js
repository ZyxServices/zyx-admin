/**
 * Created by guochunyan on 2016/7/14.
 */
$(function(){
    $('#post-summernote').summernote({
        height:200
    });
    $("#post-list-table").bootstrapTable({
        type: 'get',
        url: ("../../circleItem/circleItemList"),
        dataType: "json",
        toolbar: '#toolbar',        //工具按钮用哪个容器
        queryParams: {
            start: 0,
            pageSize: 25
        },
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,               //初始化加载第一页，默认第一页
        // 每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        //  clickToSelect: true,        //是否启用点击选中行
        //showColumns: true,
        //showHeader: true,
        onLoadSuccess: function (data) {  //加载成功时执行
            console.log(data)
        },
        columns: [
            {field: 'id', title: 'id', align: 'center', valign: 'middle', width: '20'},
            {field: 'circleTitle', title: '圈子类别名称'},
            {field: 'circleTitle', title: '帖子标题'},
            {field: 'createUser', title: '发布人'},
            {field: 'createTime', title: '发布时间',formatter: getLocalTime},
            {field: 'typeName', title: '所属圈子'},
            {field: 'typeName', title: '点赞量'},
            {field: 'typeName', title: '评论量'},
            {field: 'typeName', title: '分享量'},
            {field: 'typeName', title: '收藏量'},
            {field: 'typeName', title: '浏览量'},
            {field: 'typeName', title: '操作'},
            {field: 'typeName', title: '举报状态'}
        ]
    })
})
//时间转换
function getLocalTime(value) {
    return (new Date(value).format("yyyy-mm-dd HH:MM:ss"));
}
function createPost() {
    $("#postList").hide();
    $("#postCreate").show();
}
