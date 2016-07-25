/**
 * Created by 文楷 on 2016/7/14.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
function initTable() {
    $('#live_table').bootstrapTable({
        url:("/v1/live/list"),
        method:'get',
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber:25,            //初始化加载第一页，默认第一页
        pageSize:25,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [ 25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        //  clickToSelect: true,        //是否启用点击选中行
        //showColumns: true,
        //showHeader: true,
        columns: [{field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'type', title: '类别'},
            {field: 'title', title: '直播名称'},
            {field: 'userId', title: '直播发起人'},
            {field: 'lab', title: '直播类型', sortable: true},
            {field: 'price', title: '直播状态', sortable: true},
            {field: 'start', title: '直播开始时间', sortable: true},
            {field: 'end', title: '直播结束时间', sortable: true},
            {field: 'online', title: '在线人数', sortable: true},
            {field: 'comment', title: '评论量', sortable: true},
            {field: 'praise', title: '点赞量', sortable: true},
            {field: 'share', title: '分享量', sortable: true},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: operateFormatter},
            {field: 'Report', title: '举报'}
        ]

    });

    $('#editLive').bootstrapTable({
        url:("/v1/live/lab/list"),
        method:'get',
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 25,            //初始化加载第一页，默认第一页
        pageSize: 25,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [ 25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        //  clickToSelect: true,        //是否启用点击选中行
        //showColumns: true,
        //showHeader: true,
        columns: [{field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'lab', title: '类别'},
            {
                field: 'operation',
                title: '操作',
                align: 'center',
                events: operateEventclass,
                formatter: operateFormatterclass
            },],
        data: [{
            id: 1,
            class: '广场'
        }, {
            id: 2,
            class: '大咖'
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
    //$table.on('all.bs.table', function (e, name, args) {
    //    //console.log(name, args);
    //});
    $remove.click(function () {
        var ids = getIdSelections();
        ids.forEach(function(e){
            var delUrl='/v1/live/delete?id='+e;
            $.ajax({
                url:delUrl,
                async: false,
                type: "post",
                success: function (meg) {
                    console.log(meg)
                    if(meg.state==200){
                        $table.bootstrapTable('remove', {
                            field: 'id',
                            field: 'id',
                            values: ids
                        });
                        $remove.prop('disabled', true);
                    }
                }
            });
        })

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
        '<a class="preview p5"   href="javascript:void(0)" >预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" >推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" >屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)">删除</a>'
    ].join('');
}

//查看Url
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="seeUrl p5"   href="javascript:void(0)" title="Like">查看</a>',
    ].join('');
}

//直播列表操作事件
var operateEventssssss = {
    'click .preview': function (e, value, row, index) {
        //alert('You click like action, row: ' + JSON.stringify(row));
        // 调用接口/v1/live/lab/update?id=1&lab=NBA&desc=AAAA;  id必填 lab/desc必填一个否则返回23000
        $.ajax({
                url: "/v1/live/lab/update?id=7&lab=171&desc=11171&state=1",
            async: false,
            type: "post",
            success: function (data) {
                console.log(data)
                //data.data.forEach(function (e) {
                //
                //})

            }
        });
    },
    'click .recommend': function (e, value, row, index) {
        // 调用接口/v1/live/lab/update?state=1;   注：-1 屏蔽   0 正常  1 推荐
        $.ajax({
            url: "/v1/live/lab/list",
            async: false,
            type: "post",
            dateType:"json",
            success: function (data) {
                console.log(data)


            }
        });
    },
    'click .Shield': function (e, value, row, index) {
         // 调用接口 /v1/live/lab/update?state=-1;
    },
    'click .remove': function (e, value, row, index) {
        // 调用接口/v1/live/lab/update?id=1 id必须传  做好提示 物理删除
        var delUrl='/v1/live/delete?id='+row.id;
        $.ajax({
            url:delUrl,
            async: false,
            type: "post",
            success: function (meg) {
               if(meg.state==200){
                   $('#live_table').bootstrapTable('remove', {
                       field: 'id',
                       values: [row.id]
                   });
               }
            }
        });

    }
};

//直播分类操作
function operateFormatterclass(value, row, index) {
    return [
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}

//操作分类事件
var operateEventclass = {
    'click .edit': function (e, value, row, index) {

        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .recommend': function (e, value, row, index) {

    },
    'click .Shield': function (e, value, row, index) {

    },
    'click .remove': function (e, value, row, index) {

        $('#editLive').bootstrapTable('remove', {
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