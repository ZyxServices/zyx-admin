/**
 * Created by ZYX on 2016/7/20.
 */
$(function () {
/*创建的验证*/
    $("#sysUserCreateForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'username': {
                validators: {
                    notEmpty: {
                        message: '用户名称不能为空'
                    }
                }
            }, 'pass': {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }, 'name': {
                validators: {
                    notEmpty: {
                        message: '管理员名称不能为空'
                    }
                }
            }, 'roleId': {
                validators: {
                    notEmpty: {
                        message: '请选择权限等级'
                    }
                }
            }, 'remark': {
                validators: {
                    notEmpty: {
                        message: '请输入备注'
                    }
                }
            }
        }
    });

    $("#administrators-list-table").bootstrapTable({
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
        height: 500,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        strictSearch: true,
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        url: "/v1/su/list",
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName,
                sortOrder: params.sortOrder
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
        }
    });

    $("#sysUserCreateForm").ajaxForm({
        url: '/v1/su/insert',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#sysUserCreateForm").data('bootstrapValidator').isValid();
            /*$("#createButton").attr("disabled", true);
            var username = $("#sysUserCreateForm").find('#username').val();
            var pass = $("#sysUserCreateForm").find('#pass').val();
            var name = $("#sysUserCreateForm").find('#name').val();
            var remark = $("#sysUserCreateForm").find('#remark').val();
            var roleId = $("#role_select option:selected").val();

            var checked = true;
            if (name.replace(/\s+/g, "") == '') {
                alert("请输入账号");
                checked = false;
                $("#createButton").attr("disabled", false);
                return checked;
            }

            if (pass.replace(/\s+/g, "") == '') {
                alert("请输入密码");
                checked = false;
                $("#createButton").attr("disabled", false);
                return checked;
            }

            if (name.replace(/\s+/g, "") == '') {
                alert("请输入名称");
                checked = false;
                $("#createButton").attr("disabled", false);
                return checked;
            }

            if (remark.replace(/\s+/g, "") == '') {
                alert("请输入描述");
                checked = false;
                $("#createButton").attr("disabled", false);
                return checked;
            }

            if (roleId.replace(/\s+/g, "") == '') {
                alert("请选择角色");
                checked = false;
                $("#createButton").attr("disabled", false);
                return checked;
            }

            return checked;*/
        },
        success: function (result) {
            if (result.state == 200) {
                backToSysUsers();
            } else {
                if (result.state == 9004) {
                    $.Popup({
                        confirm: false,
                        template: '用户已存在'
                    });
                } else {
                    $.Popup({
                        confirm: false,
                        template: '用户新建失败'
                    });
                }
                $("#createButton").attr("disabled", false);
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });

    $("#sysUserEditForm").ajaxForm({
        url: '/v1/su/editRole',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#sysUserEditForm").data('bootstrapValidator').isValid();
           /* $("#editButton").attr("disabled", true);
            var roleId = $("#edit_role_select option:selected").val();

            var checked = true;

            if (roleId.replace(/\s+/g, "") == '') {
                alert("请选择角色");
                checked = false;
                $("#editButton").attr("disabled", false);
                return checked;
            }

            return checked;*/
        },
        success: function (result) {
            if (result.state == 200) {
                backToSysUsers();
            } else {
                $.Popup({
                    confirm: false,
                    template: '修改权限等级失败'
                });
                $("#editButton").attr("disabled", false);
            }
        },
        error: function () {
            $("#editButton").attr("disabled", false);
        }
    });
})

// 日志列操作
function operateLog(value, row, index) {
    var _html = [];
    _html.push('<a class="p5 look" href="javascript:void(0)" title="查看">查看</a>');
    return _html.join('');
}

// 操作列
function operateFormatter(value, row, index) {
    var _html = [];
    _html.push('<a class="p5 setJurisdiction" href="javascript:void(0)" title="权限设置">权限设置</a>');
    _html.push('<a class="p5 move" href="javascript:void(0)" title="删除">删除</a>');
    return _html.join('');
}

// 操作事件edit
var operateEvent = {
    'click .look': function (e, value, row) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .setJurisdiction': function (e, value, row) {
        $("#administratorsList").hide();
        $("#administratorsCreate").hide();
        $("#administratorsRoleEdit").show();
        $("#editUserId").val(row["id"]);
        $.ajax({
            url: "/v1/role/all",
            type: "GET",
            dataType: 'json',
            success: function (data) {
                var json = data["data"];
                for (var i = json.length - 1; i >= 0; i--) {
                    if (json[i].roleId == row["roleId"]) {
                        $("#edit_role_select").prepend('<option value="' + json[i].roleId + '" selected>' + json[i].roleName + '</option>')
                    } else {
                        $("#edit_role_select").prepend('<option value="' + json[i].roleId + '">' + json[i].roleName + '</option>')
                    }
                }
            },
            error: function (er) {
            }
        });
    },
    'click .move': function (e, value, row) {
        alert('You click like action, row: ' + JSON.stringify(row));
    }
};

function backToSysUsers() {
    $("#administratorsList").show();
    $("#administratorsCreate").hide();
    $("#administratorsRoleEdit").hide();
    $('#administrators-list-table').bootstrapTable("refresh");
}

function createAdministrators() {
    $("#administratorsList").hide();
    $("#administratorsCreate").show();
    $("#administratorsRoleEdit").hide();
    $.ajax({
        url: "/v1/role/all",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            var json = data["data"];
            for (var i = json.length - 1; i >= 0; i--) {
                $("#role_select").prepend('<option value="' + json[i].roleId + '">' + json[i].roleName + '</option>')
            }
            $("#role_select").prepend('<option value="">请选择</option>')
        },
        error: function (er) {
        }
    });
}

function beginCreateSysUser() {
    $("#sysUserCreateForm").submit();
}

function beginEditSysUser() {
    $("#sysUserEditForm").submit();
}
