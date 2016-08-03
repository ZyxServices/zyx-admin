/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
function initTable() {
    $('#dynamic_table').bootstrapTable({
        url: ("/concern/concernList"),
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
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        strictSearch: false,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        queryParams: function queryParams(params) {   //设置查询参数
            console.log(params)
            var param = {
                start: params.pageNumber - 1,
                pageSize: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName
                //sortOrder: params.sortOrder
            };
            return param;
        },
        onLoadSuccess: function (result) {  //加载成功时执行

        },
        onLoadError: function () {  //加载失败时执行
            // alert("加载数据失败");·
            // layer.msg("加载数据失败", {time : 1500, icon : 2});
        },
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'userName', title: '发布者'},
            {field: 'authenticate', title: '发布者是否认证', sortable: true},
            {field: 'createTime', title: '发布时间', formatter: timeFormat},
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
//动态类型
function typeFormatter(data) {
    return data == 0 ? "测试" : data == 1 ? "个人动态" : data == 2 ? "活动动态" : data == 3 ? "明星动态" : "圈子动态";
}
//动态状态按钮初始化
function btnState(row) {
    return row.state == 0 ? "屏蔽" : row.state == -1 ? "撤销删除" : "取消屏蔽";
}


//操作
function operateFormatter(value, row, index) {
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="edit">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">' + btnState(row) + '</a>',
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
        //$.ajax({
        //    url:'/concern/concernList?start=0&pageSize=50',
        //    method:'get',
        //    success: function (result) {
        //        console.log(result)
        //    }
        //})
    },
    'click .Shield': function (e, value, row, index) {
        var state, btnval, btn;
        var btnclick = this
        switch (this.innerHTML) {
            case '屏蔽':
                state = -2, btnval = '取消屏蔽';
                break;
            case '取消屏蔽':
                state = 0, btnval = '屏蔽';
                break;
            case '恢复删除':
                state = -1, btnval = '屏蔽';
                break;
        }
        $.Popup({
            template: '确认屏蔽吗?',
            saveEvent: function () {
                $.ajax({
                    url: "/concern/setState?id=" + row.id + "&state=" + state + "",
                    async: false,
                    type: "delete",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 39000) {
                            btnclick
                            btn = btnval
                        } else {
                            alert(result.successmsg)
                        }
                    }
                })
                btnclick.innerHTML = btn;
                //$(btnclick).parent().prevAll()[6].innerHTML = statetext;
            }
        })

    },
    'click .remove': function (e, value, row, index) {
        var delUrl = '/concern/deleteOne?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'dynamic_table', 'DELETE')
    },
    createDynamic: function () {
        $("#createDynamicForm").ajaxForm({
            url: '/concern/createConcern',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                console.log(result)
                if (result.state == 33000) {
                    window.location.reload()
                } else {
                    if (result.state == 5001) {
                        alert("账号已存在");
                    }
                }
            }
        })
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