/**
 * Created by guochunyan on 2016/7/14.
 */
$(function(){
    $('#post-summernote').summernote({
        height:200
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
            {field: 'circleTitle', title: '圈子类别名称'},
            {field: 'circleTitle', title: '帖子标题'},
            {field: 'createUser', title: '发布人'},
            {field: 'createTime', title: '发布时间',formatter: getLocalTime},
            {field: 'typeName', title: '所属圈子'},
            {field: 'typeName', title: '点赞量'},
            {field: 'typeName', title: '评论量'},
            {field: 'typeName', title: '分享量'},
            {field: 'typeName', title: '收藏量'},
            {field: 'typeName', title: '浏览量'},
            {field: 'typeName', title: '操作',events: operateEvent, formatter: circlePostFormatter},
            {field: 'typeName', title: '举报状态'}
        ]
    })
});
//分类操作
function circlePostFormatter(value, row, index) {
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
        console.log(111111);
        createPost() ;
        $.ajax({
            url: "../../circleItem/findOne?id=" + row.id,
            async: false,
            type: "get",
            dateType: "json",
            success: function () {
                $("input[name=title]").val(row.title).attr("disabled", "disabled");
                $("input[name=state]").val(row.state).attr("disabled", "disabled");
                $("input[name=details]").val(row.details).attr("disabled", "disabled");
                $("input[name=circleMaster]").val(row.circleMaster).attr("disabled", "disabled");
                $("input[name=masterId]").val(row.masterId).attr("disabled", "disabled");
                $("input[name=adminIds]").val(row.adminIds).attr("disabled", "disabled");
                $("#category").attr("disabled", "disabled");
                $("input[name=headImgUrl]").hide();
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
            }
        })
    },
    //编辑圈子
    'click .edit': function (e, value, row, index) {
        console.log(JSON.stringify(row));
        console.log(row.id);
        circleCreate();
        $.ajax({
            type: "get",
            dateType: "json",
            url: "../../circle/getCircle?id=" + row.id,
            async: false,
            success: function () {
                $("input[name=title]").val(row.title);
                $("input[name=state]").val(row.state);
                $("input[name=details]").val(row.details);
                $("input[name=circleMaster]").val(row.circleMaster);
                $("input[name=masterId]").val(row.masterId);
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
            }
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
                    alert("推荐成功");
                    $("#circleModal").modal("hide");
                }
            })

        })
    },
    //圈子屏蔽
    'click .Shield': function (e, value, row, index) {
        $.Popup({
            template: '屏蔽之后，该圈子将不在“首页”和“精选圈子”中展示，“我的关注”和“我的”中动态保留，仍可以被浏览。 ?',
            saveEvent: function () {
                $.ajax({
                    async: false,
                    type: "delete",
                    url: "../../circle/setState?id=" + row.id + "&state=" + StateNumber,
                    success: function (data) {
                        console.log(row.state);
                        var StateNumber;
                        if (row.state == 0) {
                            StateNumber =-2;
                        }
                        else if (row.state = -2) {
                            StateNumber =-0;

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
                        alert("删除成功");
                        $("#circle-list-table").bootstrapTable('refresh', {url: '../../circle/circleList'});
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
function createPost() {
    $("#postList").hide();
    $("#postCreate").show();
}
