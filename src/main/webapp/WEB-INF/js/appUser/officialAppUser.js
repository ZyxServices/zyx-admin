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
        height: 500,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        strictSearch: true,
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        method: "get",
        url: "/v1/appUser/list/official",
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
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: operateFormatter}
        ]
    });
}
//操作
function operateFormatter(value, row, index) {
    var _html = [];
    // _html.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    if(row.deva){
        _html.push('<a class="p5" href="javascript:void(0)" disabled>已推荐</a>')
    }else{
        _html.push('<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>');
    }
    if (row.mask) {
        _html.push('<a class="unMask p5" href="javascript:void(0)" title="unMask">取消屏蔽</a>');
    } else {
        _html.push('<a class="mask p5" href="javascript:void(0)" title="mask">屏蔽</a>');
    }
    if(row.authenticate == 1){
        _html.push('<a class="p5" href="javascript:void(0)" title="Authentication">待审核</a>');
    }else if(row.authenticate == 2){
        _html.push('<a class="p5" href="javascript:void(0)" title="Authentication">已认证</a>');
    }else if(row.authenticate == 3){
        _html.push('<a class="Authentication p5" href="javascript:void(0)" title="Authentication">再次申请</a>');
    }else{
        _html.push('<a class="Authentication p5" href="javascript:void(0)" title="Authentication">申请认证</a>');
    }
    return _html.join('');
}
$(function () {
    $("#editUserForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            'nickname': {
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    }
                }
            },'phone': {
                validators: {
                    notEmpty: {
                        message: '电话号码必填'
                    },
                    regexp: {
                        regexp: /^(1[3|4|5|7|8]\d{9})$/,/*只支持手机电话*/
                        message: '请输入正确手机号码'
                    }
                }
            },'address': {
                validators: {
                    notEmpty: {
                        message: '所在地必填'
                    }
                }
            }
        }
    });
    /*审核的验证*/
    $("#authForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            'authName': {
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    }
                }
            },'authIDCard': {
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    regexp: {
                        regexp: /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,
                        message: '请输入正确的身份证号码'
                    }
                }
            },'authMob': {
                validators: {
                    notEmpty: {
                        message: '手机号码必填'
                    },
                    regexp: {
                        regexp: /^(1[3|4|5|7|8]\d{9})$/,/*只支持手机电话*/
                        message: '请输入正确手机号码'
                    }
                }
            },'imagePhoto': {
                validators: {
                    notEmpty: {
                        message: '必须上传手持身份证的照片'
                    }
                }
            }
        }
    });
    initTable();
});
$("#createButton").click(function () {
    $("#editUserForm").ajaxSubmit({
        url: '/v1/appUser/update',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $('#editUserForm').data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state == 200) {
                backToUsers();
            } else {
                if (result.state == 5001) {
                    $.Popup({
                        confirm: false,
                        template: '账号已存在'
                    });
                }
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
})
$('input[id=avatar]').change(function () {
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#avatarImg").attr("src", objUrl);
        }
    }else{
        $("#photoCover").html("选择文件");
        $("#avatarImg").attr("src", "");
    }
});
$('#authFile').change(function () {
    if ($(this).val()) {
        $('#authPhotoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#cardImg").attr("src", objUrl);
        }
    }else{
        $("#authPhotoCover").html("选择文件");
        $("#cardImg").attr("src", "");
    }
});
$('#workFile').change(function () {
    if ($(this).val()) {
        $('#workPhotoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#workImg").attr("src", objUrl);
        }
    }else{
        $("#workPhotoCover").html("选择文件");
        $("#workImg").attr("src", "");
    }
});
function getImgURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

/*提交审核*/
$("#authButton").click(function () {
    var formData = new FormData();
    formData.append('imgFile', $("#authFile")[0].files[0]);/*获取图片对象*/
    $.ajax({
        url: '/v1/upload/file',
        type: 'post',
        data: formData,
        processData: false,
        contentType: false,
        beforeSend: function () {
            /*传图片之前做验证*/
            $("#uploadContent").html("提交审核中，请稍后...");
            $("#upload").modal('show');
            return $("#authForm").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state == 200) {
                $("#authFileStr").val(result.data.url);/*身份证必须上传*/
                if($("#workFile")[0].files[0] != undefined){/*如果有工作照片，则上传*/
                    var formWorkData = new FormData();
                    formWorkData.append('imgFile', $("#workFile")[0].files[0]);/*获取图片对象*/
                    $.ajax({
                        url: '/v1/upload/file',
                        type: 'post',
                        data: formWorkData,
                        processData: false,
                        contentType: false,
                        success: function (result) {
                            if (result.state == 200) {
                                $("#authFileWork").val(result.data.url);/*工作照片选择上传*/
                                authFormSubmit();/*最后才是上传表格*/
                            }else{
                                $.Popup({
                                    confirm: false,
                                    template: result.errmsg
                                });
                            }
                        },
                        error:function (result) {
                            $.Popup({
                                confirm: false,
                                template: result
                            });
                        }
                    })
                }else{
                    authFormSubmit();
                }
            }else{
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                });
            }
        },
        error:function (result) {
            $.Popup({
                confirm: false,
                template: result
            });
        }
    })
})

function authFormSubmit() {
    $("#authForm").ajaxSubmit({
        url: '/v1/user/submit_auth',
        type: 'post',
        dataType: 'json',
        beforeSubmit:function () {

        },
        complete:function () {
            $("#upload").modal('hide');
        },
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '认证资料提交成功，请等待审核'
                });
                $(".live_index").show();
                $(".create_liveType").hide();
                $("#appUserAuth").hide();
                $('#app_user_table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
}

/*上传图片*/
function imgUpload(id,image) {
    var formData = new FormData();
    formData.append('imgFile', $("#"+id)[0].files[0]);/*获取图片对象*/
    $.ajax({
        url: '/v1/upload/file',
        type: 'post',
        data: formData,
        processData: false,
        contentType: false,
        beforeSend: function () {
            /*传图片之前做验证*/
            // return $("#authForm").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state == 200) {
                $("#"+image).val(result.data.url);
            }else{
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                });
            }
        },
        error:function (result) {
            $.Popup({
                confirm: false,
                template: result
            });
        }
    })
}

/*多图片上传*/
/*
$("#imgInit").zyUpload({
    width            :   "600px",                 // 宽度
    height           :   "",                 // 宽度
    itemWidth        :   "100px",                 // 文件项的宽度
    itemHeight       :   "100px",                 // 文件项的高度
    url              :   "/v1/upload/file",  // 上传文件的路径
    fileType         :   ["jpg","png","jpeg","bmp","gif"],// 上传文件的类型
    fileSize         :   51200000,                // 上传文件的大小
    tailor           :   false,                    // 是否可以裁剪图片
    multiple         :   true,                    // 是否可以多个文件上传
    dragDrop         :   true,                    // 是否可以拖动上传文件
    del              :   true,                    // 是否可以删除文件
    finishDel        :   false,  				  // 是否在上传文件完成后删除预览
    /!* 外部获得的回调接口 *!/
    onSelect: function(files, allFiles){                    // 选择文件的回调方法
       /!* console.info("当前选择了以下文件：");
        console.info(files);
        console.info("之前没上传的文件：");
        console.info(allFiles.length);*!/
        var html = '';
        if(allFiles.length > 3){
            html = '已选择3张图片不能再多传';
            $("#imgNum").html(html);
            return false;
        }else{
            html = '还需上传'+ (3 - allFiles.length)+'张图片';
            $("#imgNum").html(html);
        }
    },
    onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
        console.info("当前删除了此文件：");
        console.info(file);
        console.info("当前剩余的文件：");
        console.info(surplusFiles);
    },
    onSuccess: function(file,response){                    // 文件上传成功的回调方法
        console.log(response);
        /!*if(JSON.parse(response).state==902){
            console.log(file)
            alert(JSON.parse(response).errmsg)
        }else{
            $('#imgFileUrl').val($('#imgFileUrl').val()+JSON.parse(response).data+',')
        }*!/
        //$('#uploadInf').append(JSON.parse(response).data)
        $('#authFile').val($('#authFile').val()+JSON.parse(response).data+',');
    },
    onFailure: function(file){                    // 文件上传失败的回调方法
        console.info("此文件上传失败：");
        console.info(file);
    },
    onComplete: function(responseInfo){           // 上传完成的回调方法

    }
});*/
