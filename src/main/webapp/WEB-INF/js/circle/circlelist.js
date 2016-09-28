/**
 * Created by guochunyan on 2016/7/14.
 */
//用户列表
var $table = $('#circle-list-table');
var firstopction = "<option value=''></option>";
var userList = $.ajax({
    url: "/v1/appUser/list/all",
    async: false,
    type: "get",
    dateType: "json",
    data: {
        pageNumber: 1,
        pageSize: 100
    },
    success: function (rows) {
        var html = "";
        html = html
        for (var i = 0; i < rows.rows.length; i++) {
            html = html + "<option value='" + rows.rows[i].id + "'>" + rows.rows[i].nickname + "</option>"
        }
        $("#masterId").append(firstopction + html);
        $("#adminIds").append(firstopction + html);
    }
});
/*查询圈子时需要选择的用户*/
var officeUser = $.ajax({
    url: "/v1/appUser/list/official/all",
    type: 'get',
    dataType: 'json',
    success: function (rows) {
        var html = "";
        html = html
        for (var i = 0; i < rows.rows.length; i++) {
            html = html + "<option value='" + rows.rows[i].id + "'>" + rows.rows[i].nickname + "</option>"
        }

        $("#createId").append(firstopction + html)
    }
});

/*创建中type=file的样式处理*/
$('input[id=lefile]').change(function () {
    $("#headImgShow").show();
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        $("#lefile").html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#headImgShow").attr("src", objUrl);
        }
    }
});
//创建圈子
function circleCreate() {
    userList;
    officeUser;
    $("#circleList").hide();
    $("#circleCreate").show();
    $("#masterId").chosen();
    $("#adminIds").chosen();
    $("#createId").chosen();
    $("input").val("");
    $("textarea[name=details]").val("");
    $("#circleRecommend").attr("value", 1);
}

$(function () {
    $("#headImgShow").hide();
    $("#circleCreates").bootstrapValidator({
        fields: {
            "title": {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    }
                }
            },
            "headImgUrl": {
                validators: {
                    notEmpty: {
                        message: '请上传圈子头像'
                    }
                }
            },
            "circleType": {
                validators: {
                    notEmpty: {
                        message: '请选择分类'
                    }
                }
            },
            "details": {
                validators: {
                    notEmpty: {
                        message: '请输入简介'
                    }
                }
            },
            "file": {
                validators: {
                    notEmpty: {
                        message: '图片不能为空'
                    }
                }
            },
            "createId": {
                validators: {
                    notEmpty: {
                        message: '创建者不能为空哦！'
                    }
                }
            }

        }
    });
    //圈子类别数据
    $.ajax({
        url: "../../circleType/circleTypeList",
        async: false,
        type: "get",
        dateType: "json",
        success: function (data) {
            var html = "";
            html = html
            for (var i = 0; i < data.data.length; i++) {
                html = html + "<option value='" + data.data[i].id + "'>" + data.data[i].typeName + "</option>"
            }
            $("#category").append(html)
        }
    });


//圈子表格数据

    $("#circle-list-table").bootstrapTable({
        type: 'get',
        url: ("../../circle/circleList"),
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 0,            //初始化加载第一页，默认第一页
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
                start: 0,
                pageSize: params.pageSize,
                searchText: params.searchText
                //sortName: params.sortName
                //sortOrder: params.sortOrder
            };
            return param;
        },
        onLoadSuccess: function (data) {  //加载成功时执行
            console.log(data)
        },
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'title', title: '圈子名称'},
            {field: 'circleTypeName', title: '圈子类别'},
            {field: 'createTime', title: '创建时间', formatter: getLocalTime},
            {field: '', title: '创建人', formatter: infoFormatter},
            {field: 'masterName', title: '圈主', sortable: true},
            {field: 'adminIds', title: '管理员', sortable: true},
            {field: 'circleItemCount', title: '帖子数量', sortable: true, events: numberEvent, formatter: getCircleId},
            {field: 'concernCount', title: '关注人数', sortable: true},
            {field: '', title: '浏览量（主页）', sortable: true},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: circleFormatter}
        ]
    })
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $("#remove").prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
});
var numberEvent = {
    'click .PostNumber': function (e, value, row, index) {
        window.location.href = "/menu/circle/circlepost?circleId=" + row.id
        if (value != 0 || value != "") {

        }
    }
}
//圈子创建人
function getCircleId(value, row, index) {
    console.log(value);
    var number = row.circleItemCount;
    return [
        '<a class="PostNumber"   href="javascript:void(0)" title="preview">' + number + '</a>',
    ].join('');
}
function infoFormatter(value, row, index) {
    return row.userVo.nickName
}

//时间转换
function getLocalTime(value) {
    return (new Date(value).format("yyyy-mm-dd HH:MM:ss"));
}
//分类操作
function circleFormatter(value, row, index) {
    var btnText;
    var Recommend;
    if (row.state == 0) {
        btnText = "屏蔽"
    } else if (row.state == -2) {
        btnText = "取消屏蔽"
    }
   if(row.deva ==true){
       Recommend="已推荐"
   }
    else{
       Recommend="推荐"
   }
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">'+Recommend+'</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">' + btnText + '</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//操作分类事件
var operateEvent = {
    //预览圈子
    'click .preview': function (e, value, row, index) {
        $("#circleList").hide();
        $("#circleCreate").show();
        $("#circleBtnSure").hide();
        $("#photoCover").hide();
        $("input[name=title]").val(row.title).attr("disabled", "disabled");
        $("input[name=state]").val(row.state).attr("disabled", "disabled");
        $("textarea[name=details]").val(row.details).attr("disabled", "disabled");
        $("input[name=circleMaster]").val(row.circleMaster).attr("disabled", "disabled");
        $("#masterName").val(row.masterName).attr("disabled", "disabled");
        $("#masterId").attr("disabled", "disabled");
        $("#adminIds").attr("disabled", "disabled");
        $("#category").attr("disabled", "disabled");
        $("input[name=headImgUrl]").hide();
        $("#masterId option[value='" + row.circleMasterId + "']").attr("selected", true);
        //多个管理员
        adminSelect("#adminIds", row.adminIds);
        $("#adminIds").trigger("liszt:updated");
        $("#masterId").chosen();
        $("#adminIds").chosen();
        $("#category").find("option[value='" + row.circleType + "']").attr("selected", true);
        // 获取图片
        if (row.headImgUrl !== "") {
            $("#headImgShow").show().attr("src", "http://image.tiyujia.com/" + row.headImgUrl);
        }
        else {
            $("#imgWrap").append("暂未上传圈子头像！")
        }
        $("#circleList").hide();
        $("#circleCreate").show();
    },
    //编辑圈子
    'click .edit': function (e, value, row, index) {
        $("#circleRecommend").attr("value", 2);
        console.log(row);
        $("#circleList").hide();
        $("#circleCreate").show();
        $("input[name=circleId]").val(row.id);
        $("input[name=title]").val(row.title);
        $("input[name=createId]").val(row.createId);
        $("input[name=state]").val(row.state);
        $("textarea[name=details]").val(row.details);
        $("input[name=circleMaster]").val(row.circleMaster);
        $("#masterId option[value='" + row.circleMasterId + "']").attr("selected", true);
        //多个管理员
        adminSelect("#adminIds", row.adminIds);
        $("#adminIds").trigger("liszt:updated");
        $("#nameHide").hide();
        $("#nameShow").show();
        $("#creatperson").html(row.userVo.nickName);
        $("#masterId").chosen();
        $("#adminIds").chosen();
        $("#category").find("option[value='" + row.circleType + "']").attr("selected", true);
        // 获取图片
        if (row.headImgUrl !== "") {
            $("#headImgShow").show().attr("src", "http://image.tiyujia.com/" + row.headImgUrl);
        }
        $('input[id=lefile]').change(function () {
            $("#circleBtnSure").click(function (e) {
                var formData = new FormData();
                formData.append('imgFile', $("#lefile")[0].files[0]);
                $.ajax({
                    url: "/v1/upload/file",
                    type: 'post',
                    data: formData,
                    processData: false,
                    contentType: false,
                    beforeSend: function () {
                        return $("#circleCreates").data('bootstrapValidator').isValid();
                    },
                    success: function (data) {
                        $("input[name=imgFile]").val(data.data.url);
                        console.log(data.data);
                        circleEidtor("#circleCreates", '../../circle/edit', 200, "编辑成功");
                    }
                })
            })
        });
    },
    //圈子推荐
    'click .recommend': function (e, value, row, index) {
        $("#circleModal").modal("show");
        $("input[name=modelId]").val(row.id);
        $("#circleSelect").empty();
        $.post("/v1/deva/sequence", {model: "3", area: "3"}, function (result) {
            if (result.state == 200) {
                if (result.data.length > 0) {
                    for (var i = 0; i < result.data.length; i++) {
                        $("#circleSelect").append("<option value='" + result.data[i] + "'>" + result.data[i] + "</option>");
                    }
                }
                else {
                    $("#circleSelect").html('<option value="">圈子序列号已满，请先删除</option>');
                    $("#RdSures").attr("disabled", true);
                }
            }

        })
    },
    //圈子屏蔽
    'click .Shield': function (e, value, row, index) {
        //屏蔽状态
        var state;
        if (row.state == 0) {
            state = -2;
        }
        else if (row.state == -2) {
            state = 0;
        }
        //屏蔽确认
        $.Popup({
            template: '屏蔽之后，该圈子将不在“首页”和“精选圈子”中展示，“我的关注”和“我的”中动态保留，仍可以被浏览。 ?',
            saveEvent: function () {
                $.ajax({
                    async: false,
                    type: "delete",
                    url: "../../circle/setState?id=" + row.id + "&state=" + state,
                    success: function (data) {
                        if (row.state == -2) {
                            $.Popup({
                                confirm: false,
                                template: "取消屏蔽成功"
                            });
                        }
                        else if (row.state = 0) {
                            $.Popup({
                                confirm: false,
                                template: "屏蔽成功"
                            });

                        }
                        $("#circle-list-table").bootstrapTable('refresh');
                    }
                })
            }
        })
    },
    //圈子删除
    'click .remove': function (e, value, row, index) {
        $.Popup({
            template: '确认删除吗?',
            saveEvent: function () {
                $.ajax({
                    url: "../../circle/deleteOne?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (data) {
                        $('#editLive').bootstrapTable('remove', {
                            field: 'id',
                            values: [row.id]
                        });
                        $.Popup({
                            confirm: false,
                            template: "删除成功"
                        });
                        $("#circle-list-table").bootstrapTable('refresh');
                    }
                });
            }
        });

    }
};

//多选select 公用方法
function adminSelect(select, values) {
    if (values != "" && values != null) {
        var arr = values.split(',');
        var length = arr.length;
        var value = '';
        for (i = 0; i < length; i++) {
            value = arr[i];
            $(select + " [value='" + value + "']").attr('selected', 'selected');
        }
        $(select).trigger("liszt:updated");
    }
}
//创建帖子编辑帖子
$("#circleBtnSure").click(function (e) {
    var states = $("#circleRecommend").val();
    console.log(states);
    if (states == "1") {
        var formData = new FormData();
        formData.append('imgFile', $("#lefile")[0].files[0]);
        $.ajax({
            url: "/v1/upload/file",
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function () {
                return $("#circleCreates").data('bootstrapValidator').isValid();
            },
            success: function (data) {
                $("input[name=headImgUrl]").val(data.data.url);
                circleEidtor("#circleCreates", '../../circle/createCircle', 200, "创建成功");
            }

        })
    } else if (states == "2") {

    }

})

//创建、编辑圈子公用方法
function circleEidtor(button, url, state, text) {
    $(button).ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state == state) {
                $.Popup({
                    confirm: false,
                    template: text
                });
                $("#circle-list-table").bootstrapTable('refresh');
                $("#circleList").show();
                $("#circleCreate").hide();
            }
            else {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                });

            }
            $("#circleList").show();
            $("#circleCreate").hide();
        }
    });
}
/*图片预览*/
//建立一個可存取到該file的url
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
//圈子推荐
$("#RdSures").click(function () {
    if ($("select[name=sequence]").val() != '') {
        $("#circleRecommend").ajaxSubmit({
            type: "post",
            dateType: "json",
            url: "/v1/deva/add",
            async: false,
            success: function (result) {
                $.Popup({
                    confirm: false,
                    title: "推荐成功"
                });
                $("#circleModal").modal("hide");
                $('#activity-list-table').bootstrapTable('refresh');
            }

        })
    }
})
//批量删除
$("#remove").click(function(){
    var ids = getIdSelections().toString();
    $.Popup({
        template: '确认批量删除吗?',
        saveEvent: function () {
                var delUrl = '/circle/delete/' + ids;
                $.ajax({
                    url: delUrl,
                    async: false,
                    type: 'delete',
                    success: function (meg) {
                        meg.state == 200 ? $table.bootstrapTable("refresh") : ''
                    }
                })
        }
    })
})


