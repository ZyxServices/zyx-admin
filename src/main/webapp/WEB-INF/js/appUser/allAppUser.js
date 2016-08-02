/**
 * Created by 文楷 on 2016/7/15.
 */
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
        height: 500,
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        strictSearch: true,
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        url: "/v1/appUser/list/all",
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            console.log(params)
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
            // {field: 'state', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'nickname', title: '用户昵称'},
            {field: 'phone', title: '手机号码'},
            {field: 'authenticate', title: '认证状态', sortable: true, formatter: authFormatter},
            {field: 'a', title: '状态', formatter: statusFormatter},
            {field: 'type', title: '关注人数', sortable: true},
            {field: 'price', title: '粉丝人数', sortable: true},
            {field: 'startTime', title: '动态数量', sortable: true},
            {field: 'overTime', title: '金币数量', sortable: true},
            {field: 'createTime', title: '注册时间', sortable: true, formatter: dateFormatter},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: operateFormatter}]
    });
}
// 操作
function operateFormatter(value, row, index) {
    var _html = [];

    if (row.del) {
        _html.push('<a class="unDel p5" href="javascript:void(0)" title="unDel">取消删除</a>');
    } else {
        // _html.push('<a class="preview p5" href="javascript:void(0)" title="preview">预览</a>');
        _html.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
        _html.push('<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>');
        if (row.mask) {
            _html.push('<a class="unMask p5" href="javascript:void(0)" title="unMask">取消屏蔽</a>');
        } else {
            _html.push('<a class="mask p5" href="javascript:void(0)" title="mask">屏蔽</a>');
        }
        if (row["authenticate"] == 1) {// 待审核
            // _html.push('<a class="auth p5" href="javascript:void(0)" title="auth">审核</a>');
        }
        _html.push('<a class="del p5" href="javascript:void(0)" title="del">删除</a>');
    }

    return _html.join('');
}

$(function () {
    $(".create_live").click(function () {
        $(".create_liveType").addClass('on')
        $(".live_index").addClass('hide')
        $("#createButton").attr("disabled", false);
    });

    initTable();

    /*表单验证*/
    $("#createAppUserForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            'phone': {
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    }
                }
            },'password': {
                validators: {
                    notEmpty: {
                        message: '密码必填'
                    }
                }
            }
        }
    });

    $("#devaForm").ajaxForm({
        url: '/v1/deva/appUser',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            $("#devaButton").attr("disabled", true);
            return true;
        },
        success: function (result) {
            if (result.state == 200) {
                alert("首推成功");
                $("#appUserRecommend").modal('hide');
                $("#devaButton").attr("disabled", false);
            } else {
                alert(result["errmsg"]);
                $("#devaButton").attr("disabled", false);
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
});

$("#createAppUserForm").ajaxForm({
    url: '/v1/appUser/insert',
    type: 'post',
    dataType: 'json',

    beforeSubmit: function () {
        // $("#createButton").attr("disabled", true);
        // var phone = $("#createAppUserForm").find('#phone').val();
        // var password = $("#createAppUserForm").find('#password').val();
        // var checked = true;
        // if (phone.replace(/\s+/g, "") == '') {
        //     alert("请输入账号");
        //     checked = false;
        // }
        //
        // if (password.replace(/\s+/g, "") == '') {
        //     alert("请输入密码");
        //     checked = false;
        // }
        //
        // return checked;
        return $('#createAppUserForm').data('bootstrapValidator').isValid();
    },
    success: function (result) {
        if (result.state == 200) {
            backToUsers();
        } else {
            if (result.state == 5001) {
                alert("账号已存在");
            }
        }
    },
    error: function () {
        $("#createButton").attr("disabled", false);
    }
});

function beginCreate() {
    $("#createAppUserForm").submit();
}

function beginDeva() {
    $("#devaForm").submit();
}

function backToUsers() {
    $(".create_liveType").addClass('hide');
    $(".create_liveType").removeClass('on');
    $(".live_index").addClass('on');
    $(".live_index").removeClass('hide');
    $('#app_user_table').bootstrapTable('refresh');
}