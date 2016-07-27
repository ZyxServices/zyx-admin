/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
function initTable() {
    $('#live_table').bootstrapTable({
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 2,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [1, 25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        //  clickToSelect: true,        //是否启用点击选中行
        //showColumns: true,
        //showHeader: true,
        columns: [{field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'photo', title: '商品照片'},
            {field: 'name', title: '商品名称'},
            {field: 'number', title: '货号'},
            {field: 'type', title: '成本价', sortable: true},
            {field: 'price', title: '单价/金币', sortable: true},
            {field: 'startTime', title: '购买量', sortable: true},
            {field: 'overTime', title: '商品类别', sortable: true},
            {field: 'online', title: '是否上架', sortable: true},
            {field: 'comment', title: '上架时间  ', sortable: true},
            {field: 'praise', title: '库存量', sortable: true},
            {field: 'share', title: '收藏量', sortable: true},
            {field: 'operation', title: '评论量', align: 'center', events: operateEventssssss, formatter: operateFormatter},
            {field: 'Report', title: '浏览量'},
            {field: 'see', title: '操作', events: seeUrl, formatter: seeUrlFormatter},
            {field: 'see', title: '查看url', events: seeUrl, formatter: seeUrlFormatter}],

        data: [{
            id: 1,
            class: '广场',
            name: '科比见面会',
            author: '汪汪',
            type: '视频直播',
            price: '正在直播',
            startTime: '2016-07-14 17:35',
            overTime: 'Item 1',
            online: '2987',
            comment: '765',
            praise: '231',
            share: '142',
            Report: '正常',
            see: '查看'
        }, {
            id: 2,
            class: '大咖',
            name: '科比见面会',
            author: '房房',
            type: '图文直播',
            price: '尚未开始',
            startTime: '2016-07-14 17:35',
            overTime: 'Item 1',
            online: '2987',
            comment: '765',
            praise: '231',
            share: '142',
            Report: '80人举报',
            see: '查看'
        }]
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
    $table.on('all.bs.table', function (e, name, args) {
        //console.log(name, args);
    });
    $remove.click(function () {
        var ids = getIdSelections();
        $table.bootstrapTable('remove', {
            field: 'id',
            field: 'id',
            values: ids
        });
        $remove.prop('disabled', true);
    });
    $(window).resize(function () {
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
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
//操作事件
var operateEventssssss = {
    'click .preview': function (e, value, row, index) {
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
var seeUrl={
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