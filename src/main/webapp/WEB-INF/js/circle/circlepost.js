/**
 * Created by guochunyan on 2016/7/14.
 **/
//帖子内容
/*var save = function () {
 var makrup = $('#post-summernote').summernote('code');
 $("input[name=content]").val(makrup);
 };*/
//帖子创建
$('#post-summernote').on('summernote.change', function (content, $editable) {
    $("#desc").val($editable);
    $('#CirclePost').data('bootstrapValidator');
}).summernote({
    callbacks: {
        onImageUpload: function (files) {
            //上传图片到服务器，使用了formData对象，至于兼容性...据说对低版本IE不太友好
            var formData = new FormData();
            formData.append('imgFile', files[0]);
            $.ajax({
                url: '/v1/upload/file',//后台文件上传接口
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    console.log(result)
                    if (result.state == 200) {
                        $('#post-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data, 'img');
                    } else {
                        $.Popup({
                            confirm: false,
                            template: result.successmsg
                        })
                    }
                }
            });
        }
    },
    lang: 'zh-CN',
    height: 200
});
//获取圈子列表
var circleList = function () {
    $.ajax({
        type: "get",
        dateType: "json",
        url: "../../circle/circleList",
        data: {
            start: 0,
            pageSize: 20

        },
        async: false,
        success: function (data) {
            console.log(data);
            var html = "";
            html = html
            for (var i = 0; i < data.data.length; i++) {
                html = html + "<option value='" + data.data[i].id + "'>" + data.data[i].title + "</option>"
            }
            $("#circleList").append(html);
        }
    });
};
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
        $("#createId").append(html)
    }
});
function createPost() {
    $("#postList").hide();
    $("#postCreate").show();
    /*   $("#postSure").click(function () {
     save();
     });*/
    circleList();
    officeUser;
    $("#circleList").chosen();
    $("#createId").chosen();
    circleEidtor("#postSure", "#CirclePost", '../../circleItem/createCircleItem', 200, "发布成功");
}
$(function () {
    $("#CirclePost").bootstrapValidator({
        fields: {
            "title": {
                validators: {
                    notEmpty: {
                        message: '请输入标题'
                    }
                }
            },
            "content": {
                validators: {
                    notEmpty: {
                        message: '内容不能为空'
                    }
                }
            },
            "circle_id": {
                validators: {
                    notEmpty: {
                        message: '圈子不能为空'
                    }
                }
            }
        }
    });
    $("#post-list-table").bootstrapTable({
        type: 'get',
        url: ("../../circleItem/circleItemList"),
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
        pageNumber: 1,               //初始化加载第一页，默认第一页
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
        onLoadSuccess: function (data) {  //加载成功时执行
            console.log(data)
        },
        columns: [
            {field: 'id', title: 'id', align: 'center', valign: 'middle', width: '20'},
            {field: 'circleTypeName', title: '圈子类别'},
            {field: 'title', title: '帖子标题'},
            {field: 'createUser', title: '发布人'},
            {field: 'createTime', title: '发布时间', formatter: getLocalTime},
            {field: 'circleTitle', title: '所属圈子'},
            {field: 'zanCounts', title: '点赞量'},
            {field: 'commentCounts', title: '评论量'},
            {field: 'typeName', title: '分享量'},
            {field: 'typeName', title: '收藏量'},
            {field: 'typeName', title: '浏览量'},
            {field: 'operation', title: '操作', events: operateEvents, formatter: circlePostFormatter},
            {field: 'typeName', title: '举报状态'}
        ]
    })
});
//分类操作
function circlePostFormatter(value, row, index) {
    var btnText;
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
var operateEvents = {
    //预览帖子
    'click .preview': function (e, value, row, index) {
        $("#postList").hide();
        $("#postCreate").show();
        $("input[name=title]").val(row.title).attr("disabled", "disabled");
        circleList();
        $("#circleList option[value='" + row.circleId + "']").attr("selected", true);
        $("#circleList").chosen();
        $("#postSure").hide();
        if (row.content == "<p><br></p>") {
            $('#post-summernote').summernote('code', "您没有填写帖子内容哦！！");
            $('#post-summernote').summernote('destroy');
        } else {
            $('#post-summernote').summernote('code', row.content);
            $('#post-summernote').summernote('destroy');
        }
        /*        $("#circleList").trigger("liszt:updated");
         $("#circleList option[value='" + row.circleId + "']").attr("selected", true);*/

        $("#category").attr("disabled", "disabled");

    },
    //编辑帖子
    'click .edit': function (e, value, row, index) {
        $("#postList").hide();
        $("#postCreate").show();
        $("input[name=title]").val(row.title);
        $("input[name=content]").val(row.content);
        $("#postSure").show();
        circleList();
        $("#circleList option[value='" + row.circleId + "']").attr("selected", true);
        $("#circleList").chosen();
        $('#post-summernote').summernote('code', row.content);
        circleEidtor("#postSure", "#CirclePost", "../../circleItem/edit/?circleItemId= " + row.id, 200, "编辑成功");
    },
    //帖子推荐
    'click .recommend': function (e, value, row, index) {
        $("#circleModal").modal("show");
        $("#cricleTitle").html(row.title);
        $(".radio_box").hide();
        var $checked = $('#radio_checked label');
        $checked.click(function () {
            $(".radio_box").show();
            $(this).addClass('current').siblings().removeClass('current');
            var index = $checked.index(this);
            $('div.radio_box> div').eq(index).show().siblings().hide();
        })
        $("#circleSure").click(function () {
            var selectValue = $("#circleSelect").val();
            $.ajax({
                type: "get",
                dateType: "json",
                url: "../../circle/setTop?circleId=" + row.id + "&topSize=" + selectValue,
                async: false,
                success: function (result) {
                    alert("推荐成功");
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
        $.Popup({
            template: '屏蔽之后，该圈子将不在“首页”和“精选圈子”中展示，“我的关注”和“我的”中动态保留，仍可以被浏览。 ?',
            saveEvent: function () {
                $.ajax({
                    async: false,
                    type: "delete",
                    url: "../../circleItem/setState?id=" + row.id + "&state=" + state,
                    success: function () {
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
                        $("#post-list-table").bootstrapTable('refresh');
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
                    url: "../../circleItem/deleteOne?id=" + row.id,
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
                        $("#post-list-table").bootstrapTable('refresh');
                    }
                });
            }
        });

    }
};
//时间转换
function getLocalTime(value) {
    return (new Date(value).format("yyyy-mm-dd HH:MM:ss"));
}
//创建、编辑圈子公用方法
function circleEidtor(id, button, url, state, text) {
    $(id).click(function (e) {
        $(button).ajaxSubmit({
            url: url,
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.state == state) {
                    e.preventDefault();
                    var $form = $(e.target);
                    $form.serialize();
                    $.Popup({
                        confirm: false,
                        title: text
                    });
                    $("#post-list-table").bootstrapTable('refresh');
                    $("#postList").show();
                    $("#postCreate").hide();

                }
                else {
                    $.Popup({
                        confirm: false,
                        title: result.errmsg
                    });
                }
            }
        })
    });
}
