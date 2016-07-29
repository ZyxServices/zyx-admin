/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
function initTable() {
    $('#dynamic_table').bootstrapTable({
        url: ("/concern/concernList?page=1&pageSize=5"),
        method: 'get',
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        strictSearch: false,        //是否启用模糊收索
        columns: [{field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'userId', title: '发布者'},
            {field: 'name', title: '发布者是否认证', sortable: true},
            {field: 'createTime', title: '发布时间', formatter: dateTimeFormatter},
            {field: 'type', title: '动态类型', sortable: true, formatter: typeFormatter},
            {field: 'price', title: '点赞量', sortable: true},
            {field: 'startTime', title: '评论量', sortable: true},
            {field: 'overTime', title: '分享量', sortable: true},
            {field: 'online', title: '收藏量', sortable: true},
            {field: 'comment', title: '浏览量'},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: operateFormatter},
            {field: 'Report', title: '举报'}]
    });
    // sometimes footer render error.
    setTimeout(function () {
        $table.bootstrapTable('resetView');
    }, 200);
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        // save your data, here just save the current page
        selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });
    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        if (index % 2 == 1) {
            $detail.html('Loading from ajax request...');
            $.get('LICENSE', function (res) {
                $detail.html(res.replace(/\n/g, '<br>'));
            });
        }
    });
    //$table.on('all.bs.table', function (e, name, args) {
    //    //console.log(name, args);
    //});
    $remove.click(function () {
        var ids = getIdSelections();
        $table.bootstrapTable('remove', {
            field: 'id',
            field: 'id',
            values: ids
        });
        $remove.prop('disabled', true);
    });
}
//时间戳转化
function dateTimeFormatter(data) {
    return [new Date(data).format("yyyy-mm-dd HH:MM:ss")].join('');
}
//类型
function typeFormatter(data) {
    switch (data) {
        case 0:
            return '测试';
        case 1:
            return '个人动态';
        case 2:
            return '活动动态';
        case 3:
            return '明星动态';
        case 4:
            return '圈子动态';
    }
}
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}
//操作
function operateFormatter(value, row, index) {
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="edit">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//查看Url
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="seeUrl p5"   href="javascript:void(0)" title="Like">查看</a>',
    ].join('');
}
//操作事件eidt
var operateEventssssss = {
    'click .preview': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .edit': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .recommend': function (e, value, row, index) {

    },
    'click .Shield': function (e, value, row, index) {

    },
    'click .remove': function (e, value, row, index) {
        $('#live_table').bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};
//查看Url事件
var seeUrl = {
    'click .seeUrl': function (e, value, row, index) {
        alert('You click like seeUrl action, row: ' + JSON.stringify(row));
    }
}
$(function () {
    $(".create_live").click(function () {
        $(".create_liveType").addClass('on')
        $(".live_index").addClass('hide')
    })
    initTable();
})