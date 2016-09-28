/**
 * Created by guochunyan on 2016/7/14.
 **/
//帖子创建
var firstopction = "<option value=''></option>";
//获取圈子列表
var thisURL = document.URL;
var getval = thisURL.split('?')[1];
var showval = getval.split("=")[1];
/*circleList;
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
 $("#circle_id").html(data.details)
 /!*        var html = "";
 html = html
 for (var i = 0; i < data.data.length; i++) {
 html = html + "<option value='" + data.data[i].id + "'>" + data.data[i].title + "</option>"
 }
 $("#circleList").append(firstopction + html);*!/
 }

 });
 };*/
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
//summernote图片路径处理
$('#post-summernote').on('summernote.change', function (content, $editable) {
    $("#desc").val($editable);
    var makrup = $('#post-summernote').summernote('code');
    $("input[name=content]").val(makrup);
    $("#CirclePost").bootstrapValidator('updateStatus', 'content', 'NOT_VALIDATED');
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
                    console.log(result);
                    if (result.state == 200) {
                        $('#post-summernote').summernote('insertImage', "http://image.tiyujia.com/" + result.data.url, 'img');
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
//创建圈子
function createPost() {
    $("#postList").hide();
    $("#postCreate").show();
    officeUser;
    $("input").val("");
    $('#post-summernote').summernote('code', "");
    $("#circleList").chosen();
    $("#createId").chosen();
    $("#CirclePost").attr("value", 1);
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
            "create_id": {
                validators: {
                    notEmpty: {
                        message: '用户不能为空'
                    }
                }
            }, "circle_id": {
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
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 0,            //初始化加载第一页，默认第一页
        pageSize: 10,
        //每页的记录行数（*）
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
                searchText: params.searchText,
                circleId: showval,
                //sortName: params.sortName
                //sortOrder: params.sortOrder
            };
            return param;
        },
        onLoadSuccess: function (data) {  //加载成功时执行
            console.log(data)
        },
        columns: [
            {field: 'id', title: 'id', align: 'center', valign: 'middle', width: '20'},
            {field: 'circleTypeName', title: '圈子类别'},
            {field: 'title', title: '帖子标题'},
            {field: 'createUser', title: '发布人'},
            {field: 'createTime', title: '发布时间', formatter: getLocalTime},
            {field: 'circleTitle', title: '所属圈子', formatter: circleTitle},
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
function circleTitle(value) {
    var circleNmae = value;
    $("#circle_id").html(circleNmae);

}
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

        $("#circleList option[value='" + row.circleId + "']").attr("selected", true);
        $("#createId option[value='" + row.createId + "']").attr("selected", true);
        $("#circleList").chosen();
        $("#createId").chosen();
        $("#postSure").hide();
        if (row.content == "<p><br></p>") {
            $('#post-summernote').summernote('code', "您没有填写帖子内容哦！！");
            $('#post-summernote').summernote('destroy');
        } else {
            $('#post-summernote').summernote('code', row.content);
            $('#post-summernote').summernote('destroy');
        }
        $("#category").attr("disabled", "disabled");

    },
    //编辑帖子
    'click .edit': function (e, value, row, index) {
        $("#postList").hide();
        $("#postCreate").show();
        $("input[name=title]").val(row.title);
        $("input[name=content]").val(row.content);
        $("#postSure").show();
        $("#circleList option[value='" + row.circleId + "']").attr("selected", true);
        $("#createId option[value='" + row.createId + "']").attr("selected", true);
        $("#createId").chosen();
        $("#circleList").chosen();
        $('#post-summernote').summernote('code', row.content);
        $("#CirclePost").attr("value", 2);
        $(".row").val(row.id)
        $("#PostUserBox").show();
        $("#PostUserChose").hide();
        $("#PostUser").html(row.createUser)
    },
    //帖子推荐
    'click .recommend': function (e, value, row, index) {
        $("#circleModal").modal("show");
        $("#cricleTitle").html(row.title);
        $(".radio_box").hide();
        $("#headImgShow").hide();
        $("input[name=modelId]").val(row.id);
        $("input[name=area]").attr("checked", false);
        $("#hotSelect").empty();
        $("#chosenSelect").empty();


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
//创建帖子编辑帖子
$("#postSure").click(function () {
    var states = $("#CirclePost").val();
    console.log(states);
    if (states == "1") {
        circleEidtor("#CirclePost", "../../circleItem/createCircleItem", 200, "发布成功");
    } else if (states == "2") {
        var RowId = $(".row").val();
        circleEidtor("#CirclePost", "../../circleItem/edit/?circleItemId= " + RowId, 200, "编辑成功");
    }
});
//创建、编辑公用方法
function circleEidtor(form, url, state, text) {
    $(form).ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#CirclePost").data('bootstrapValidator').isValid();
        },
        success: function (result) {
            if (result.state == state) {
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
    });
}
//帖子推荐
var $checked = $('#radio_checked label');
$checked.click(function () {
    $(".radio_box").show();
    $(this).addClass('current').siblings().removeClass('current');
    /*  $(this).find("input").attr("checked", true).siblings().attr("checked", false);*/
    /*  $(this).siblings().find("input").attr("checked", false);*/
    var index = $checked.index(this);
    $('div.radio_box> div').eq(index).show().siblings().hide();

});
function List(model, area, id) {
    $.post("/v1/deva/sequence", {model: model, area: area}, function (data) {
        console.log(data);
        if (data.state == 200) {
            if (data.data.length > 0) {
                for (var i = 0; i < data.data.length; i++) {
                    $(id).append("<option value='" + data.data[i] + "'>" + data.data[i] + "</option>");
                }
            }
            else {
                $(id).html('<option value="">圈子序列号已满，请先删除</option>');
                $("#circleSure").attr("disabled", true);
            }
        }

    });
}

$('input:radio[name="area"]').change(function () {
    var inputValue = $(this).val();
    if (inputValue == "1") {
        List("4", "1", "#hotSelect");
    }
    else if (inputValue == "3") {
        List("4", "3", "#chosenSelect");
    }
})

$("#circleSure").click(function (e) {
    var inputValue = $('input:radio[name="area"]:checked').val();
    var formData = new FormData();
    var firstSequence = $("#hotSelect").val();
    var SecendSequence = $("#chosenSelect").val();
    if (SecendSequence != '') {
        if (inputValue == "1") {
            $("input[name=imageUrl]").val();
            $("input[name=sequence]").val(firstSequence);
            $("input[name=model]").val("4");
            $("#PostRecommend").ajaxSubmit({
                type: "post",
                dateType: "json",
                url: "/v1/deva/add",
                async: false,
                success: function (result) {
                    $.Popup({
                        confirm: false,
                        title: "首页帖子推荐成功"
                    });
                    $("#post-list-table").bootstrapTable('refresh');
                    $("#circleModal").modal("hide");
                }

            })
        } else if (inputValue == "3") {
            console.log(222222);
            $("input[name=imageUrl]").val();
            $("input[name=sequence]").val(SecendSequence);
            $("input[name=model]").val("4");
            formData.append('imgFile', $("#lefile")[0].files[0]);
            $.ajax({
                url: "/v1/upload/file",
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    console.log(data);
                    $("input[name=imageUrl]").val(data.data.url);
                    $("#PostRecommend").ajaxSubmit({
                        type: "post",
                        dateType: "json",
                        url: "/v1/deva/add",
                        async: false,
                        success: function (result) {
                            $.Popup({
                                confirm: false,
                                title: "圈子帖子推荐成功"
                            });
                            $("#post-list-table").bootstrapTable('refresh');
                            $("#circleModal").modal("hide");
                        }

                    })
                }

            })
        }
    }

});

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