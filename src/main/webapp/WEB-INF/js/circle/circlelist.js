/**
 * Created by guochunyan on 2016/7/14.
 */
//创建圈子
function circleCreate() {
    $("#circleList").hide();
    $("#circleCreate").show();
    //圈子创建
    $("#circleBtnSure").click(function (e) {
        $("#circleCreates").ajaxSubmit({
            url: '../../circle/createCircle',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.state == 200) {
                    e.preventDefault();
                    var $form = $(e.target);
                    $form.serialize();
                    $("#circle-list-table").bootstrapTable('refresh', {url: '../../circle/circleList'});
                    $("#circleList").show();
                    $("#circleCreate").hide();
                }
                else {
                    $.Popup({
                        confirm: false,
                        title: "该圈子已存在，请重新输入圈子名称"
                    });
                }
            }
        })
    });

}

$(function () {
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
                        message: '请上传头像'
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
            "circleMaster": {
                validators: {
                    notEmpty: {
                        message: '请选择圈主'
                    }
                }
            },
            "masterId": {
                validators: {
                    notEmpty: {
                        message: '请设置管理员'
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
    })

//圈子表格数据
    $("#circle-list-table").bootstrapTable({
        type: 'get',
        url: ("../../circle/circleList"),
        dataType: "json",
        toolbar: '#toolbar',        //工具按钮用哪个容器
        queryParams: {
            start: 0,
            pageSize: 25
        },
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        // 每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        //  clickToSelect: true,        //是否启用点击选中行
        //showColumns: true,
        //showHeader: true,
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'title', title: '圈子名称'},
            {field: 'circleType', title: '圈子类别'},
            {field: 'createTime', title: '创建时间', formatter: getLocalTime},
            {field: 'userName', title: '创建人'},
            {field: 'masterName', title: '圈主', sortable: true},
            {field: 'adminIds', title: '管理员', sortable: true},
            {field: '', title: '帖子数量', sortable: true},
            {field: '', title: '关注人数', sortable: true},
            {field: '', title: '浏览量（主页）', sortable: true},
            {field: 'operation', title: '操作', align: 'center', events: operateEvent, formatter: circleFormatter}
        ]
    })
});
//时间转换
function getLocalTime(value) {
    return (new Date(value).format("yyyy-mm-dd HH:MM:ss"));
}
//分类操作
function circleFormatter(value, row, index) {
    var btnText;
    console.log(row.state);
    if (row.state == 0) {
        btnText = "屏蔽"
    } else if (row.state == -2) {
        btnText = "取消屏蔽"
    }
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
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
        $("input[name=title]").val(row.title).attr("disabled", "disabled");
        $("input[name=state]").val(row.state).attr("disabled", "disabled");
        $("input[name=details]").val(row.details).attr("disabled", "disabled");
        $("input[name=circleMaster]").val(row.circleMaster).attr("disabled", "disabled");
        $("input[name=masterId]").val(row.masterId).attr("disabled", "disabled");
        $("input[name=adminIds]").val(row.adminIds).attr("disabled", "disabled");
        $("#category").attr("disabled", "disabled");
        $("input[name=headImgUrl]").hide();
        console.log(row.circleType);
        $("#category").find("option[value='" + row.circleType + "']").attr("selected", true);
        // 获取图片
        if (row.headImgUrl == "") {
            $("#headImgShow").html("您未上传圈子头像哦！！")
        }
        else {
            var img = document.createElement('img');
            img.width = "300";
            img.src = "http://image.tiyujia.com/" + row.headImgUrl;
            document.getElementById('headImgShow').appendChild(img);
        }
        $("#circleList").hide();
        $("#circleCreate").show();
    },
    //编辑圈子
    'click .edit': function (e, value, row, index) {
        $("#circleList").hide();
        $("#circleCreate").show();
        $("input[name=circleId]").val(row.id);
        $("input[name=title]").val(row.title);
        $("input[name=state]").val(row.state);
        $("input[name=details]").val(row.details);
        $("input[name=circleMaster]").val(row.circleMaster);
        $("input[name=masterId]").val(row.masterId);
        $("input[name=adminIds]").val(row.adminIds);
        $("#category").find("option[value='" + row.circleType + "']").attr("selected", true);
        // 获取图片
        if (row.headImgUrl == "") {
            $("#headImgShow").html("您未上传圈子头像哦！！")
        }
        else {
            var img = document.createElement('img');
            img.width = "300";
            img.src = "http://image.tiyujia.com/" + row.headImgUrl;
            document.getElementById('headImgShow').appendChild(img);
        }
        $("#circleBtnSure").click(function (e) {
            $("#circleCreates").ajaxSubmit({
                url: '../../circle/edit',
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    $("#circleList").show();
                    $("#circleCreate").hide();
                    $.Popup({
                        confirm: false,
                        title: "修改成功"
                    });
                    $("#circle-list-table").bootstrapTable('refresh', {url: '../../circle/circleList'});
                }
            })
        })

    },
    //圈子推荐
    'click .recommend': function (e, value, row, index) {
        $("#circleModal").modal("show");
        $("#circleSure").click(function () {
            console.log(row.id);
            var selectValue = $("#circleSelect").val();
            console.log(selectValue);
            $.ajax({
                type: "get",
                dateType: "json",
                url: "../../circle/setTop?circleId=" + row.id + "&topSize=" + selectValue,
                async: false,
                success: function (result) {
                    $.Popup({
                        confirm: false,
                        title: "推荐成功"
                    });
                    $("#circleModal").modal("hide");
                }
            })

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
                                title: "屏蔽成功"
                            });
                        }
                        else if (row.state = 0) {
                            $.Popup({
                                confirm: false,
                                title: "取消屏蔽成功"
                            });

                        }
                        $("#circle-list-table").bootstrapTable('refresh', {url: '../../circle/circleList'});
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
                            title: "删除成功"
                        });
                        $("#circle-list-table").bootstrapTable('refresh', {url: '../../circle/circleList'});
                    }
                });
            }
        });

    }
};
