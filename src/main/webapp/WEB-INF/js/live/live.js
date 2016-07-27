/**
 * Created by 文楷 on 2016/7/14.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
function initTable() {
    $('#live_table').bootstrapTable({
        url: ("/v1/live/list"),
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
        //showColumns: true,
        //showHeader: true,
        columns: [{field: '', checkbox: true, align: 'center', valign: 'middle'},
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
            {field: 'Report', title: '举报'},
            {field: 'state', title: '屏蔽值',visible:false}
        ]

    });

    $('#editLive').bootstrapTable({
        url: ("/v1/live/lab/list"),
        method: 'get',
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber:1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        strictSearch: false,        //是否启用模糊收索
        columns: [{field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'lab', title: '类别'},
            {field: 'operation',title: '操作',align: 'center',events:operateEventclass,formatter: operateFormatterclass},
            {field: 'state', title: '屏蔽值',visible:false}]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
    //批量删除
    $remove.click(function () {
        var ids = getIdSelections();
        ids.forEach(function (e) {
            var delUrl = '/v1/live/delete?id=' + e;
            $.ajax({
                url: delUrl,
                async: false,
                type: "post",
                success: function (meg) {
                    console.log(meg)
                    if (meg.state == 200) {
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

//获取所有行id
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

//直播操作
function operateFormatter(value, row, index) {
    var btnText;
    if(row.state==false){
        btnText="取消屏蔽"
    }else if(row.state==true){
        btnText="屏蔽"
    }
    return [
        '<a class="preview p5"   href="javascript:void(0)" >预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" >推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" >'+btnText+'</a>',
        '<a class="remove p5" href="javascript:void(0)">删除</a>'
    ].join('');
}

//直播列表操作事件
var operateEventssssss = {
    //预览
    'click .preview': function (e, value, row, index) {
        $.ajax({
            url: "/v1/live/lab/update?id=7&lab=171&desc=11171&state=1",
            async: false,
            type: "post",
            success: function (data) {
                console.log(data)
            }
        });
    },
    //推荐
    'click .recommend': function (e, value, row, index) {
        $.ajax({
            url: "/v1/live/lab/list",
            async: false,
            type: "post",
            dateType: "json",
            success: function (data) {
                console.log(data)
            }
        });
    },
    //屏蔽
    'click .Shield': function (e, value, row, index,obj) {
        var state;
        var btnval;
        var btn;
        if (this.innerHTML == '屏蔽') {
            state = 0;
            btnval = '取消屏蔽';

        } else {
            state = 1;
            btnval = '屏蔽';
        }
        $.ajax({
            url: "/v1/live/update?id=" + row.id + "&state=" + state + "",
            async: false,
            type: "post",
            dateType: "json",
            success: function (result) {
                if (result.state == 200) {
                    btn=btnval
                } else {
                    alert(result.successmsg)
                }
            }
        })
        this.innerHTML=btn
    },
    //删除
    'click .remove': function (e, value, row, index) {
        // 调用接口/v1/live/lab/update?id=1 id必须传  做好提示 物理删除
        var delUrl = '/v1/live/delete?id=' + row.id;
        if (confirm('确认删除吗？')) {
            $.ajax({
                url: delUrl,
                async: false,
                type: "post",
                success: function (result) {
                    if (result.state == 200) {
                        $('#live_table').bootstrapTable('remove', {
                            field: 'id',
                            values: [row.id]
                        });
                    } else {
                        alert(result.successmsg)
                    }
                }
            });
        }


    }
};

//直播分类操作
function operateFormatterclass(value, row, index) {
    var btnText;
    if(row.state==false){
        btnText="取消屏蔽"
    }else if(row.state==true){
        btnText="屏蔽"
    }
    return [
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">'+btnText+'</a>',
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
        var state;
        var btnval;
        var btn;
        if (this.innerHTML == '屏蔽') {
            state = 0;
            btnval = '取消屏蔽';

        } else {
            state = 1;
            btnval = '屏蔽';
        }
        $.ajax({
            url: "/v1/live/lab/update?id=" + row.id + "&state=" + state + "",
            async: false,
            type: "post",
            dateType: "json",
            success: function (result) {
                if (result.state == 200) {
                    btn=btnval
                } else {
                    alert(result.successmsg)
                }
            }
        })
        this.innerHTML=btn
    },
    'click .remove': function (e, value, row, index) {
        var delUrl = '/v1/live/lab/delete?id=' + row.id;
        if (confirm('确认删除吗？')) {
            $.ajax({
                url: delUrl,
                async: false,
                type: "post",
                success: function (data) {
                    $('#editLive').bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
                }
            });
        }
    },
    creatLive: function () {
        var labvalue = $('#lab').val()
        var descvalue = $('#desc').val()
        var creatLiveUrl = '/v1/live/lab/create?lab=' + labvalue + '&labvalue&desc=' + descvalue + '';
        $.ajax({
            url: creatLiveUrl,
            async: false,
            type: "post",
            success: function (data) {
                $('#editLive').bootstrapTable('refresh');
            }
        })
    }
};
$(function () {
    $(".create_live").click(function () {
        $(".create_liveType").addClass('on')
        $(".live_index").addClass('hide')
    })
    //$.fzk()
    initTable();
})