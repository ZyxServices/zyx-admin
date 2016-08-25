/**
 * Created by 文楷 on 2016/7/15.
 */
var $table = $('#dynamic_table'),
    $remove = $('#remove');
function initTable() {
    $('#dynamic_table').bootstrapTable({
        url: ("/concern/concernList"),
        method: 'get',
        toolbar: '#toolbar',        //工具按钮用哪个容器
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "desc",          //排序方式
        pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
        smartDisplay: false,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        strictSearch: true,        //是否启用模糊收索
        queryParamsType: "undefined",
        dataField: "data",
        silentSort: false,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                start: params.pageNumber - 1,
                pageSize: params.pageSize,
                searchText: params.searchText,
                sortName: params.sortName
                //sortOrder: params.sortOrder
            };
            return param;
        },
        onLoadSuccess: function (result) {  //加载成功时执行

        },
        onLoadError: function () {  //加载失败时执行
            // alert("加载数据失败");·
            // layer.msg("加载数据失败", {time : 1500, icon : 2});
        },
        columns: [
            {field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'userVo.nickName', title: '发布者'},
            {field: 'userVo.authenticate', title: '发布者是否认证', sortable: true, formatter: authFormatter, sortName: 'md'},
            {field: 'createTime', title: '发布时间', formatter: timeFormat},
            //{field: 'type', title: '动态类型', sortable: true, formatter: typeFormatter},
            {field: 'appUserAuthDto.authInfo', title: '认证标签', sortable: true},
            {field: 'zanCounts', title: '点赞量', sortable: true},
            {field: 'commentCounts', title: '评论量', sortable: true},
            {field: 'overTime', title: '分享量', sortable: true},
            {field: 'online', title: '收藏量', sortable: true},
            {field: 'comment', title: '浏览量'},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: operateFormatter},
            {field: 'Report', title: '举报'}]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
    $remove.click(function () {
        var ids = getIdSelections();
        $.Popup({
            template: '确认批量删除吗?',
            saveEvent: function () {
                ids.forEach(function (e) {
                    var delUrl = '/concern/deleteOne?id=' + e;
                    $.ajax({
                        url: delUrl,
                        async: false,
                        type: "delete",
                        success: function (meg) {
                            if (meg.state == 37000) {
                                $table.bootstrapTable("refresh");
                            }
                        }

                    })
                });
            }
        })
    });
    /*查询创建活动时需要选择的用户*/
    $.ajax({
        url: "/v1/appUser/list/official/all",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            var user = '';
            result.rows.forEach(function (item, i) {
                user += '<option value=' + item.id + ' >' + item.nickname + '</option>'
            })
            $("#choiceUser").append(user)
        }
    });
}
// 认证状态格式化
function authFormatter(value) {
    return value == 2 ? "已认证" : value == 1 ? "待审核" : value == 3 ? "认证失败" : "未认证";
}
//动态类型
function typeFormatter(data) {
    return data == 0 ? "测试" : data == 1 ? "个人动态" : data == 2 ? "活动动态" : data == 3 ? "明星动态" : "圈子动态";
}
//动态状态按钮初始化
function btnState(row) {
    return row.state == 0 ? "屏蔽" : row.state == -1 ? "撤销删除" : "取消屏蔽";
}


//操作
function operateFormatter(value, row, index) {
    return row.state == -1 ? [
        '<a class="unRemove p5" href="javascript:void(0)" title="unRemove">撤销删除</a>',
    ].join('') : ['<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>',
        '<a class="edit p5"   href="javascript:void(0)" title="edit">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend">推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">' + btnState(row) + '</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'].join('');
}
//查看Url
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="seeUrl p5"   href="javascript:void(0)" title="Like">查看</a>',
    ].join('');
}
//操作事件eidt
var operateEventssssss = {
    'click .preview': function (e, value, row, index) {
        $(".dynamicPreview").addClass('on')
        $(".live_index").addClass('hide')
        $('.topicContent').html(row.topicContent)
        $('.username').html(row.userVo.nickName)
        var strArry = row.imgUrl.split(',');
        for (var i in strArry){
            $('.dynamicPic').append('<img style="max-width: 13%"  src='+'http://image.tiyujia.com/'+strArry[i]+'>')
            //$('.dynamicPic img').attr('src', 'http://image.tiyujia.com/' + row.imgUrl + '');
        }
    },
    'click .edit': function (e, value, row, index) {
        //alert('You click like action, row: ' + JSON.stringify(row));
        $(".create_liveType").addClass('on')
        $(".live_index").addClass('hide')
        $('.create_liveType h3').html('编辑动态')
        $('#dynamicContent').val(row.topicContent)
        $(".dynamicEdit").addClass('on')
        $(".release").addClass('hide')
        //$("input[name='imgFile']")
        var createId = $("#choiceUser option:selected").val()

    },
    'click .recommend': function (e, value, row, index) {
        //$.ajax({
        //    url:'/concern/concernList?start=0&pageSize=50',
        //    method:'get',
        //    success: function (result) {
        //        console.log(result)
        //    }
        //})
    },
    'click .Shield': function (e, value, row, index) {
        var state, btnval, btn;
        var btnclick = this
        switch (this.innerHTML) {
            case '屏蔽':
                state = -2, btnval = '取消屏蔽';
                break;
            case '取消屏蔽':
                state = 0, btnval = '屏蔽';
                break;
            case '恢复删除':
                state = -1, btnval = '屏蔽';
                break;
        }
        $.Popup({
            template: '确认屏蔽吗?',
            saveEvent: function () {
                $.ajax({
                    url: "/concern/setState?id=" + row.id + "&state=" + state + "",
                    async: false,
                    type: "delete",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            btnclick
                            btn = btnval
                        } else {
                            alert(result.successmsg)
                        }
                    }
                })
                btnclick.innerHTML = btn;
                //$(btnclick).parent().prevAll()[6].innerHTML = statetext;
            }
        })

    },
    'click .remove': function (e, value, row, index) {
        var delUrl = '/concern/deleteOne?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'dynamic_table', 'DELETE')
    },
    'click .unRemove': function (e, value, row, index) {
        var delUrl = "/concern/setState?id=" + row.id + "&state=0";
        ajaxPlugins.unRemove(delUrl, 'dynamic_table', 'DELETE')
    },
    createDynamic: function (obj) {
        $("#createDynamicForm").ajaxForm({
            url: '/concern/createConcern',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.state == 200) {
                    window.location.reload();
                } else  {
                    $.Popup({
                        confirm:false,
                        template:result.errmsg
                    })
                }
            }
        })

    }
};

//查看Url事件
var seeUrl = {
    'click .seeUrl': function (e, value, row, index) {
        alert('You click like seeUrl action, row: ' + JSON.stringify(row));
    }
}
$(function () {
    $(".create_live").click(function () {
        $(".create_liveType").addClass('on')
        $(".live_index").addClass('hide')
    })
    ///*创建中type=file的样式处理*/
    //$('input[id=imgFile]').change(function () {
    //    if (this.files.length <= 9 && this.files.length > 0) {
    //        $("#imgFile").html($(this).val());
    //        $.each(this.files, function (k, v) {
    //            var objUrl = getImgURL(v);
    //            if (objUrl) {
    //                $("#imagesWrap").append("<img id='images' style='max-width:10%;padding:10px' src='" + objUrl + "'>");
    //            }
    //        });
    //    } else if (this.files.length > 9) {
    //        $.Popup({
    //            confirm: false,
    //            template: '最多不能超过9张图片'
    //        })
    //        this.value = "";
    //    }
    //});
    ///*图片预览*/
    ////建立一個可存取到該file的url
    //function getImgURL(file) {
    //    var url = null;
    //    if (window.createObjectURL != undefined) { // basic
    //        url = window.createObjectURL(file);
    //    } else if (window.URL != undefined) { // mozilla(firefox)
    //        url = window.URL.createObjectURL(file);
    //    } else if (window.webkitURL != undefined) { // webkit or chrome
    //        url = window.webkitURL.createObjectURL(file);
    //    }
    //    return url;
    //}
    $("#demo").zyUpload({
        width            :   "400px",                 // 宽度
        height           :   "200px",                 // 宽度
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
        fileNumber       :     9,
        /* 外部获得的回调接口 */
        onSelect: function(files, allFiles){                    // 选择文件的回调方法
            //console.log(allFiles)
            //if(allFiles.length>3){
            //    $.Popup({
            //        confirm:false,
            //        template:'选择文件不能超过3个'
            //    })
            //}
            //console.info("当前选择了以下文件：");
            //console.info(files);
            //console.info("之前没上传的文件：");
            //console.info(allFiles);
        },
        onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
            console.info("当前删除了此文件：");
            console.info(file);
            console.info("当前剩余的文件：");
            console.info(surplusFiles);
        },
        onSuccess: function(file,response){                    // 文件上传成功的回调方法

            if(JSON.parse(response).state==902){
                    console.log(file)
                alert(JSON.parse(response).errmsg)
            }else{
                $('#imgFileUrl').val($('#imgFileUrl').val()+JSON.parse(response).data+',')
            }
            //$('#uploadInf').append(JSON.parse(response).data)
        },
        onFailure: function(file){                    // 文件上传失败的回调方法
            console.info("此文件上传失败：");
            console.info(file);
        },
        onComplete: function(responseInfo){           // 上传完成的回调方法

            $('#DynamicSubmit').click();
            //operateEventssssss.createDynamic()
            $('#imgFileUrl').val($('#imgFileUrl').val().substr(0,$('#imgFileUrl').val().length-1))
        }
    });
    $(window).resize(function () {
        $('#dynamic_table').bootstrapTable('resetView');
    });
    initTable();
})