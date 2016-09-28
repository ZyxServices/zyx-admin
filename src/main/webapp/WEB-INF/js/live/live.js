/**
 * Created by 文楷 on 2016/7/14.
 */
var $table = $('#live_table'),
    $remove = $('#remove');
//表格初始化
function initTable() {
    $('#live_table').bootstrapTable({
        url: ("/v1/live/list"),
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
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                keyword: params.searchText
                //sortName: params.sortName
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
        columns: [{field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'type', title: '类别'},
            {field: 'title', title: '直播名称'},
            {field: 'userId', title: '直播发起人'},
            {field: 'lab', title: '直播类型', sortable: true, formatter: labFormatter},
            {field: 'dynamic', title: '直播状态', sortable: true, formatter: operateFormatterssssss},
            {field: 'start', title: '直播开始时间', sortable: true, formatter: timeFormat},
            {field: 'end', title: '直播结束时间', sortable: true, formatter: timeFormat},
            {field: 'online', title: '在线人数', sortable: true},
            {field: 'comment', title: '评论量', sortable: true},
            {field: 'praise', title: '点赞量', sortable: true},
            {field: 'share', title: '分享量', sortable: true},
            {field: 'operation', title: '操作', align: 'center', events: operateEventssssss, formatter: operateFormatter},
            {field: 'Report', title: '举报'},
            {field: 'state', title: '屏蔽值', visible: false}
        ]

    });

    $('#editLive').bootstrapTable({
        url: ("/v1/live/lab/list"),
        method: 'get',
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
        sortOrder: "asc",          //排序方式
        pageList: [25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        strictSearch: false,        //是否启用模糊收索
        columns: [{field: '', checkbox: true, align: 'center', valign: 'middle'},
            {field: 'id', title: 'id', align: 'center', valign: 'middle'},
            {field: 'lab', title: '类别'},
            {field: 'description', title: '备注'},
            {
                field: 'operation',
                title: '操作',
                align: 'center',
                events: operateEventclass,
                formatter: operateFormatterclass
            },
            {field: 'state', title: '屏蔽值', visible: false}]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
    'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
    //批量删除
    $remove.click(function () {
        var ids = getIdSelections();
        $.Popup({
            template: '确认批量删除吗?',
            saveEvent: function () {
                ids.forEach(function (e) {
                    var delUrl = '/v1/live/delete?id=' + e;
                    $.ajax({
                        url: delUrl,
                        async: false,
                        type: "post",
                        success: function (meg) {
                            meg.state == 200 ? $table.bootstrapTable("refresh") : ''
                        }
                    })
                });
            }
        })
    });
}

//直播类型
function labFormatter(value, row, index) {
    return row.lab == 1 ? "图文直播" : "视频直播";
}
//直播状态初始化
function operateFormatterssssss(value, row, index) {
    return row.state == 0 ? "正常" : "已屏蔽";
}

//直播状态按钮初始化
function btnState(row) {
    return row.state == 1 ? "取消屏蔽" : "屏蔽";
}

//直播操作按钮初始化
function operateFormatter(value, row, index) {
    return [
        '<a class="preview p5"   href="javascript:void(0)" >预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" >推荐</a>',
        '<a class="Shield p5" href="javascript:void(0)" >' + btnState(row) + '</a>',
        '<a class="remove p5" href="javascript:void(0)">删除</a>'
    ].join('');
}
//直播分类操作按钮初始化
function operateFormatterclass(value, row, index) {
    return [
        '<a class="edit p5"   href="javascript:void(0)" title="preview">编辑</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend"> 推荐 </a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield">' + btnState(row) + '</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>'
    ].join('');
}
//直播列表操作事件
var operateEventssssss = {
    //预览
    'click .preview': function (e, value, row, index) {
        $.ajax({
            url: "/v1/live/lab/update?id=7&lab=171&desc=11171&state=1",
            async: false,
            type: "post",
            success: function (data) {
                console.log(data)
            }
        });
    },
    //推荐
    'click .recommend': function (e, value, row, index) {
        var html = '',
            judgeBtn = 1,
            liveSequence1 = liveSequence(1),
            liveSequence2 = liveSequence(2)
        btnIndex = '<input type="radio" value="1" name="area" onclick="liveSequence(1)" checked="checked">首页',
            btnStand = ' <input type="radio" value="2" name="area" onclick="liveSequence(2)" >看台';
        //序列查詢初始化
        if (liveSequence1 == 0 && liveSequence2 == 0) {
            $.Popup({
                confirm: false,
                template: '推荐位置已满，请到banner推荐列表中删除，再重新上传推荐！！！'
            })
            return;
        } else if (liveSequence1 == 0) {
            btnIndex = '首页位置已满'
            judgeBtn = 2
        } else if (liveSequence2 == 0) {
            btnStand = '看台位置已满'
        }
        judgeBtn == 2 ? btnStand = ' <input type="radio" value="2" name="area" onclick="liveSequence(2)" checked="checked">看台' : ''
        //if (judgeBtn == 2) {
        //    btnStand = ' <input type="radio" value="2" name="area" onclick="liveSequence(2)" checked="checked">看台';
        //}
        html += '   <form class="form-horizontal" role="form" id="recommend" enctype="multipart/form-data">            '
        html += '	<div class="container-fluid">';
        html += '	    <div class="row">';
        html += '	        <label class="control-label ">直播名称:</label><label class="control-label " style="text-align: left" >' + row.title + '</label>';
        html += '	    </div>';
        html += '	    <div class="row">';
        html += '	        <label class="col-xs-4 control-label ">推荐模块:</label>';
        html += '           <div class="controls">';
        html += '	            <label class="radio col-xs-4 ">';
        html += btnIndex;
        html += '	            </label>'
        html += '	            <label class="radio col-xs-4 ">';
        html += btnStand;
        html += '	            </label>';
        html += '	        </div>';
        html += '	    </div>';
        html += '	    <div class="row">';
        html += '	            <div >';
        html += '	                <input style="display: none" name="model" value="2">';
        html += '	                <input style="display: none" name="modelId" value="' + row.id + '">';
        html += '	                <label class="col-xs-6 control-label ">显示顺序: </label>';
        html += '	                <select name="sequence" class="span2" id="hotSelect">';
        html += '	                </select>';
        html += '	            </div>';
        html += '	     </div>';
        html += '	     <div class="row">';
        html += '	         <label class="col-xs-6  control-label ">封面图:</label>';
        html += '	         <label class="control-label "><input name="imageUrl"  id="Cover" type="file"></label>';
        html += '	     </div>';
        html += '	    <div class="row">';
        html += '	        <label class="col-xs-6 control-label ">推荐状态:</label>';
        html += '           <div class="controls">';
        html += '	            <label class="radio col-xs-4 ">';
        html += '	              <input type="radio" value="1" name="state" checked="checked" > 激活';
        html += '	            </label>'
        html += '	            <label class="radio col-xs-4 ">';
        html += '	              <input type="radio" value="0" name="state" > 未激活';
        html += '	            </label>';
        html += '	        </div>';
        html += '	    </div>';
        html += '	</div>';
        html += '   </from>'
        $.Popup({
            title: '直播推荐',
            template: html,
            remove: false,
            saveEvent: function () {
                $("#upload").modal({backdrop: 'static', keyboard: false});
                var formData = new FormData();
                formData.append('imgFile', $('#Cover')[0].files[0]);
                if ($('#Cover')[0].files.length > 0) {
                    $.ajax({
                        url: '/v1/upload/file',//后台文件上传接口
                        type: 'POST',
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function (result) {
                            if (result.state == 200) {
                                $('#recommend').append('<input style="display: none" class="CoverP" name="imageUrl"  value="' + result.data.url + '" >');
                                liveDeva()
                            } else {
                                removeEvent('upload')
                                $.Popup({
                                    confirm: false,
                                    template: result.successmsg
                                })
                            }
                        },
                        error: function (res) {
                            $('#recommend').append('<input style="display: none" class="CoverP" name="imageUrl"  value="" >');
                            liveDeva()
                        }
                    });
                } else {
                    $('#recommend').append('<input style="display: none" class="CoverP" name="imageUrl"  value="' + row.bgmUrl + '" >');
                    liveDeva()
                }
            }
        })
        liveSequence(judgeBtn)
    },
    //屏蔽
    'click .Shield': function (e, value, row, index, obj) {
        var state, btnval, btn, stateText, statetext,html;
        var btnclick = this
        if (this.innerHTML == '屏蔽') {
            state = 1,
            btnval = '取消屏蔽',
            stateText = '已屏蔽',
            html='确认屏蔽吗?'
        } else {
            state = 0;
            btnval = '屏蔽';
            stateText = '正常',
            html='确认取消屏蔽吗?'
        }
        $.Popup({
            template: html,
            saveEvent: function () {
                $.ajax({
                    url: "/v1/live/update?id=" + row.id + "&state=" + state + "",
                    async: false,
                    type: "post",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            btnclick
                            btn = btnval
                            statetext = stateText
                        } else {
                            alert(result.successmsg)
                        }
                    }
                })
                btnclick.innerHTML = btn;
                $(btnclick).parent().prevAll()[6].innerHTML = statetext;
            }
        })


    },
    //删除
    'click .remove': function (e, value, row, index) {
        var delUrl = '/v1/live/delete?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'live_table', 'post');
    }
};
//直播推荐接口
function liveDeva() {
    $.ajax({
        url: '/v1/deva/add',
        type: 'post',
        data: $('#recommend').serialize(),
        dataType: 'json',
        success: function (result) {
            removeEvent('upload')
            if (result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '推荐成功'
                })
            } else if (result.state == 73002) {
                $.Popup({
                    confirm: false,
                    template: '推荐内容重复'
                })
            }
        },
        error: function (res) {
            removeEvent('upload')
            $.Popup({
                confirm: false,
                template: '推荐上传失败（请检查内容是否填写完整！！！）'
            })
        }
    })
}
//直播推荐序列查詢
function liveSequence(area) {
    var result;
    $('#hotSelect').empty()
    $.ajax({
        type: "get",
        dateType: "json",
        url: "/v1/deva/sequence?model=2&area=" + area + "",
        async: false,
        success: function (res) {
            res.data.forEach(function (e) {
                $('#hotSelect').append("<option value='" + e + "'>" + e + "</option>")
            })
            result = res.data.length
        }
    })
    return result
}
//直播操作分类事件
var operateEventclass = {
    'click .edit': function (e, value, row, index) {
        $('#editLab').val(row.lab);
        $('#editDesc').val(row.description);
        $('.editLiveClassId').empty()
        $('.liveEditClass').addClass('on')
        $('.createLiveClass').hide()
        $('.bootstrap-table').hide()
        $('.editLiveClassId').append('<input name="id" style="display:none" value="' + row.id + '" >')
        $('.liveEditBackBtn').click(function () {
            $('.liveEditClass').removeClass('on')
            $('.createLiveClass').show()
            $('.bootstrap-table').show()
        })
    },
    'click .recommend': function (e, value, row, index) {

    },
    'click .Shield': function (e, value, row, index) {
        var state, btnval, btn, stateText,html;
        var btnclick = this
        if (this.innerHTML == '屏蔽') {
            state = 1,
                btnval = '取消屏蔽',
                html='确认屏蔽吗?'
        } else {
            state = 0;
            btnval = '屏蔽';
                html='确认取消屏蔽吗?'
        }
        $.Popup({
            template: html,
            saveEvent: function () {
                $.ajax({
                    url: "/v1/live/lab/update?id=" + row.id + "&lab="+row.state+"&state=" + state + "",
                    async: false,
                    type: "post",
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            btn = btnval
                        } else {
                            alert(result.successmsg)
                        }
                        $('#editLive').bootstrapTable('refresh');
                    }
                })
                btnclick.innerHTML = btn;
            }
        })
        //var state;
        //var btnval;
        //var btn;
        //if (this.innerHTML == '屏蔽') {
        //    state = 0;
        //    btnval = '取消屏蔽';
        //
        //} else {
        //    state = 1;
        //    btnval = '屏蔽';
        //}
        //$.Popup({
        //    template: '确认屏蔽吗?',
        //    saveEvent: function () {
        //        $.ajax({
        //            url: "/v1/live/lab/update?id=" + row.id + "&state=" + state + "",
        //            async: false,
        //            type: "post",
        //            dateType: "json",
        //            success: function (result) {
        //                if (result.state == 200) {
        //                    btn = btnval
        //                } else {
        //                    alert(result.successmsg)
        //                }
        //            }
        //        })
        //        btnclick.innerHTML = btn;
        //        $(btnclick).parent().prevAll()[6].innerHTML = statetext;
        //    }
        //})

        //this.innerHTML = btn
    },
    'click .remove': function (e, value, row, index) {
        var delUrl = '/v1/live/lab/delete?id=' + row.id;
        ajaxPlugins.remove(delUrl, 'editLive', 'post')
    },
    createLive: function () {
        $("#createLiveClass").ajaxSubmit({
            url: '/v1/live/lab/create',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.state == 23002) {
                    $.Popup({
                        confirm: false,
                        template: '这个类别已经存在,请重新填写'
                    })
                } else {
                    $('#editLive').bootstrapTable('refresh');
                    $.Popup({
                        confirm: false,
                        template: '添加成功'
                    })
                }
            }
        })
    },
    editLive: function () {
        $("#editLiveClass").ajaxSubmit({
            url: '/v1/live/lab/update',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                console.log(result)
                if (result.state == 23002) {
                    $.Popup({
                        confirm: false,
                        template: '这个类别已经存在,请重新填写'
                    })
                } else {
                    $('#editLive').bootstrapTable('refresh');
                    $.Popup({
                        confirm: false,
                        template: '编辑成功',
                        cancelEvent: function () {
                            $('.liveEditClass').removeClass('on')
                            $('.createLiveClass').show()
                            $('.bootstrap-table').show()
                        }
                    })

                }
            }
        })
    },
    updateLive: function (e, value, row, index) {

    }
};


$(function () {
    $(".create_live").click(function () {
        $(".create_liveType").addClass('on')
        $(".live_index").addClass('hide')
    })
    initTable();
})