/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
function initTable() {
    //先销毁表格
    $('#app_user_table').bootstrapTable('destroy');
    $('#app_user_table').bootstrapTable({
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        pageList: [10, 15, 20, 25],  //记录数可选列表
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        strictSearch: true,
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        url: "/v1/appUser/list",
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            // alert("加载成功");
            // layer.msg("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            // alert("加载数据失败");
            // layer.msg("加载数据失败", {time : 1500, icon : 2});
        },
        columns: [{field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'nickname', title: '用户昵称'},
            {field: 'phone', title: '手机号码'},
            {field: 'name', title: '认证状态', sortable: true},
            {field: 'author', title: '认证信息'},
            {field: 'type', title: '关注人数', sortable: true},
            {field: 'price', title: '粉丝人数', sortable: true},
            {field: 'startTime', title: '动态数量', sortable: true},
            {field: 'overTime', title: '金币数量', sortable: true},
            {field: 'createTime', title: '注册时间', sortable: true, formatter: dateFormatter},
            {field: 'lastlogintime', title: '最后登录时间', sortable: true, formatter: dateFormatter},
            {field: 'sex', title: '性别'},
            {field: 'birthday', title: '生日', formatter: dateFormatter},
            {field: 'address', title: '所在地'},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: operateFormatter}]
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
// 日期格式化
function dateFormatter(value, row, index) {
    if (null == value || "" == value) {
        return "-";
    }
    try {
        return new Date(value).format("yyyy-mm-dd HH:MM:ss");
    } catch (e) {
        return "-";
    }
}
//
function detailFormatter(value, row, index) {
    return value;
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