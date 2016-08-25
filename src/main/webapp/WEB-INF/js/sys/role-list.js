/**
 * Created by WMS on 2016/8/2.
 */
$(function () {
    /*验证*/
    $("#roleCreateForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'roleName': {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    }
                }
            }, 'roleDesc': {
                validators: {
                    notEmpty: {
                        message: '请输入描述'
                    }
                }
            }, 'menuPerm': {
                validators: {
                    notEmpty: {
                        message: '请选择角色权限'
                    }
                }
            }
        }
    });
    $("#roleEditForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'roleName': {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    }
                }
            }, 'roleDesc': {
                validators: {
                    notEmpty: {
                        message: '请输入描述'
                    }
                }
            }, 'menuPerm': {
                validators: {
                    notEmpty: {
                        message: '请选择角色权限'
                    }
                }
            }
        }
    });
    //先销毁表格
    $('#role-list-table').bootstrapTable('destroy');

    $('#role-list-table').bootstrapTable({
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
        height: 500,
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        strictSearch: true,
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        url: "/v1/role/list",
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
        },
        columns: [
            {field: 'id', title: 'id'},
            {field: 'roleName', title: '角色名称'},
            {field: 'roleDesc', title: '角色详情'},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: operateFormatter}]
    });

    $('#menu-create-select').treeMultiselect({
        enableSelectAll: true,
        collapsible: true,
        onChange: function (allChecked) {
            var _temp = [];

            allChecked.forEach(function (_r) {
                _temp.push(_r["value"]);
            });

            $("#menuCreatePerm").val(_temp);
            $('#roleCreateForm').data('bootstrapValidator')
                .updateStatus('menuPerm', 'NOT_VALIDATED', null)
                .validateField('menuPerm');
        }
    });

});

function createJurisdiction() {
    $("#jurisdictionList").hide();
    $("#jurisdictionCreate").show();
}

function editJurisdiction(_row) {
    $("#jurisdictionList").hide();
    $("#jurisdictionEdit").show();
    $("#editRoleId").val(_row["id"]);
    $("#roleEditName").val(_row["roleName"]);
    $("#roleEditDesc").val(_row["roleDesc"]);
    var isCheckedData = [];
    if (_row["menuPerm"]) {
        isCheckedData = _row["menuPerm"].split(",");
    }
    $("#menuEditPerm").val(isCheckedData);
    var getObj = $("#menu-edit-select").find('option');
    for (var i = 0; i < isCheckedData.length; i++) {
        for (var j = 0; j < getObj.length; j++) {
            var get = $(getObj[j]).val();
            if (get == isCheckedData[i]) {
                $(getObj[j]).attr("selected", "selected");
            }
        }
    }

    $('#menu-edit-select').treeMultiselect({
        enableSelectAll: true,
        collapsible: true,
        onChange: function (allChecked) {
            var _temp = [];

            allChecked.forEach(function (_r) {
                _temp.push(_r["value"]);
            });

            $("#menuEditPerm").val(_temp);
            $('#roleEditForm').data('bootstrapValidator')
                .updateStatus('menuPerm', 'NOT_VALIDATED', null)
                .validateField('menuPerm');
        }
    });
    $('#roleEditForm').data('bootstrapValidator').validate();

}

function beginCreate() {
    $("#roleCreateForm").ajaxSubmit({
        url: '/v1/role/insert',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#roleCreateForm").data('bootstrapValidator').isValid();
            /*$("#createButton").attr("disabled", true);
             var phone = $("#roleCreateForm").find('#roleCreateName').val();
             var password = $("#roleCreateForm").find('#roleCreateDesc').val();
             var checked = true;
             if (phone.replace(/\s+/g, "") == '') {
             alert("请输入名称");
             checked = false;
             $("#createButton").attr("disabled", false);
             return checked;
             }

             if (password.replace(/\s+/g, "") == '') {
             alert("请输入描述");
             checked = false;
             $("#createButton").attr("disabled", false);
             return checked;
             }

             return checked;*/
        },
        success: function (result) {
            if (result.state == 200) {
                backToRoles();
            } else {
                if (result.state == 9001) {
                    $("#createButton").attr("disabled", false);
                    $.Popup({
                        confirm: false,
                        template: '角色已存在'
                    });
                }
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
}

function beginEdit() {
    $("#roleEditForm").ajaxSubmit({
        url: '/v1/role/edit',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#roleEditForm").data('bootstrapValidator').isValid();
            /*$("#editButton").attr("disabled", true);
             var phone = $("#roleEditForm").find('#roleEditName').val();
             var password = $("#roleEditForm").find('#roleEditDesc').val();
             var checked = true;
             if (phone.replace(/\s+/g, "") == '') {
             alert("请输入名称");
             checked = false;
             $("#editButton").attr("disabled", false);
             return checked;
             }

             if (password.replace(/\s+/g, "") == '') {
             alert("请输入描述");
             checked = false;
             $("#editButton").attr("disabled", false);
             return checked;
             }

             return checked;*/
        },
        success: function (result) {
            $("#editButton").attr("disabled", false);
            if (result.state == 200) {
                backToRoles();
            } else {
                if (result.state == 9001) {
                    $.Popup({
                        confirm: false,
                        template: '角色已存在'
                    });
                }
            }
        },
        error: function () {
            $("#editButton").attr("disabled", false);
        }
    });
}

function backToRoles() {
    $("#jurisdictionList").show();
    $("#jurisdictionCreate").hide();
    $("#jurisdictionEdit").hide();
    $("#menu-edit-select").next(".tree-multiselect").remove();
    $('#role-list-table').bootstrapTable("refresh");
}

// 操作
function operateFormatter() {
    var _html = [];

    _html.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');

    return _html.join('');
}

// 操作事件edit
var operateEvent = {
    'click .edit': function (e, value, row) {
        editJurisdiction(row);
    }
};