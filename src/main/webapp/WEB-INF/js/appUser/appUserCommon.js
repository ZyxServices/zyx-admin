// 认证状态格式化
function authFormatter(value) {
    return value == 2 ? "已认证" : value == 1 ? "待审核" : value == 3 ? "认证失败" : "未认证";
}
// 状态格式化
function statusFormatter(value, row, index) {
    if (row.del && row.mask) {
        return "已删除-已屏蔽";
    }
    if (row.del && !row.mask) {
        return "已删除";
    }
    if (!row.del && row.mask) {
        return "已屏蔽";
    }
    if (!row.del && !row.mask) {
        return "正常";
    }
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
// 详情显示
function detailFormatter(index, row, c) {
    var html = [];
    $.each(row, function (key, value) {
        if ("nickname" == key) {
            html.push('<p><b>用户昵称:</b> ' + value + '</p>');
        }
        if ("avatar" == key) {
            var _t = "http://image.tiyujia.com/" + value;
            html.push('<p><b>用户头像:</b><img src=' + _t + '></p>');
        }
        if ("sex" == key) {
            html.push('<p><b>用户性别:</b> ' + value + '</p>');
        }
        if ("phone" == key) {
            html.push('<p><b>手机号码:</b> ' + value + '</p>');
        }
        if ("birthday" == key) {
            html.push('<p><b>用户生日:</b> ' + new Date(value).format("yyyy-mm-dd HH:MM:ss") + '</p>');
        }
        if ("address" == key) {
            html.push('<p><b>用户所在地:</b> ' + value + '</p>');
        }
        if ("lastlogintime" == key) {
            html.push('<p><b>最后登录时间:</b> ' + new Date(value).format("yyyy-mm-dd HH:MM:ss") + '</p>');
        }
    });
    return html.join('');
}
//查看Url
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="seeUrl p5"   href="javascript:void(0)" title="Like">查看</a>',
    ].join('');
}
// 操作事件edit
var operateEvent = {
    'click .preview': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .edit': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .recommend': function (e, value, row, index) {

    },
    'click .authPass': function (e, value, row, index) {// 审核通过
        // 弹出审核页面
        $.ajax({
            url: "/v1/appUser/authPass",
            data: {id: row.id},
            type: "GET",
            dataType: 'json',
            success: function () {
                $('#app_user_table').bootstrapTable("refresh");
            },
            error: function (er) {
                alert("操作失败");
            }
        });
    },
    'click .authFail': function (e, value, row, index) {// 审核不通过
        // 弹出审核页面
        $.ajax({
            url: "/v1/appUser/authFail",
            data: {id: row.id},
            type: "GET",
            dataType: 'json',
            success: function () {
                $('#app_user_table').bootstrapTable("refresh");
            },
            error: function (er) {
                alert("操作失败");
            }
        });
    },
    'click .mask': function (e, value, row, index) {
        if (confirm('这是屏蔽是否的对话框?')) {
            $.ajax({
                url: "/v1/appUser/mask",
                data: {id: row.id},
                type: "GET",
                dataType: 'json',
                success: function () {
                    $('#app_user_table').bootstrapTable("refresh");
                },
                error: function (er) {
                    alert("操作失败");
                }
            });
        }
    },
    'click .unMask': function (e, value, row, index) {
        if (confirm('这是恢复屏蔽是否的对话框?')) {
            $.ajax({
                url: "/v1/appUser/unMask",
                data: {id: row.id},
                type: "GET",
                dataType: 'json',
                success: function () {
                    $('#app_user_table').bootstrapTable("refresh");
                },
                error: function (er) {
                    alert("操作失败");
                }
            });
        }
    },
    'click .del': function (e, value, row, index) {
        if (confirm('这是删除是否的对话框?')) {
            $.ajax({
                url: "/v1/appUser/del",
                data: {id: row.id},
                type: "GET",
                dataType: 'json',
                success: function () {
                    $('#app_user_table').bootstrapTable("refresh");
                },
                error: function (er) {
                    alert("操作失败");
                }
            });
        }
    },
    'click .unDel': function (e, value, row, index) {
        if (confirm('这是恢复删除是否的对话框?')) {
            $.ajax({
                url: "/v1/appUser/unDel",
                data: {id: row.id},
                type: "GET",
                dataType: 'json',
                success: function () {
                    $('#app_user_table').bootstrapTable("refresh");
                },
                error: function (er) {
                    alert("操作失败");
                }
            });
        }
    }
};