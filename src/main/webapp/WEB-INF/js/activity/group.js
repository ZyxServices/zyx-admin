/**
 * Created by ZYX on 2016/7/12.
 */
/*activityIds的值*/
var activityIds = [];
var checkedId = [];
$(function () {
    /*表单验证*/
    $("#group-form").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            'name': {
                validators: {
                    notEmpty: {
                        message: '组合名称不能为空'
                    }
                }
            },
            'imageR': {
                validators: {
                    notEmpty: {
                        message: '必须上传图片'
                    }
                }
            },
            'activityIds': {
                validators: {
                    notEmpty: {
                        message: '必须选择活动'
                    }
                }
            }
        }
    });
    /*组合活动的列表*/
    $("#activity-group-table").bootstrapTable({
        url: "/v1/combination/queryCombination",
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
        sortable: true,           //是否启用排序
        strictSearch: true,
        height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        sidePagination: "server",
        contentType: "application/x-www-form-urlencoded",
        method: "post",
        queryParamsType: "undefined",
        queryParams: queryParams,
        responseHandler: groupFromData
    })
})

function initActivity() {
    /*所有活动的列表*/
    $('#choice-activity-table').bootstrapTable('destroy');
    $("#choice-activity-table").bootstrapTable({
        url: "/v1/activity/queryActivity",
        striped: true,           //是否显示行间隔色
        toolbar: '#toolbar',        //工具按钮用哪个容器
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,            //初始化加载第一页，默认第一页
        pageSize: 10,            //每页的记录行数（*）
        pageList: [10, 15, 20, 25],  //记录数可选列表
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        strictSearch: true,
        height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        queryParamsType: "undefined",
        sidePagination: "server",
        method: "get",
        queryParams: queryParams,
        responseHandler: choiceFromData,
        onLoadSuccess:function () {
            if($("#listType").html() == '编辑'){
                $("#choice-activity-table").bootstrapTable("checkBy", {field:"id", values:checkedId})
            }
        },
        onCheck:function (row) {
            activityIds.push(row.id);
            $("#activityIds").val(activityIds);
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onUncheck:function (row) {
            activityIds.splice($.inArray(row.id,activityIds),1);
            $("#activityIds").val(activityIds);
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onCheckAll:function (rows) {
            for(var i = 0;i < rows.length; i++){
                activityIds.push(rows[i].id);
            }
            $("#activityIds").val(activityIds);
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        },
        onUncheckAll:function (row) {
            activityIds = [];
            $("#activityIds").val('');
            $('#group-form').data('bootstrapValidator')
                .updateStatus('activityIds', 'NOT_VALIDATED',null)
                .validateField('activityIds');
        }
    })
}

/*activity-group-table列表请求的数据*/
function queryParams(params) {
    return {
        pageDataNum: params.pageSize,
        pageNum: params.pageNumber,
        search: params.search
    };
}
/*组合列表表格数据处理*/
function groupFromData(res) {
    if (res.state == 480) {
        $("#content-wrapper").html("<section class='content'>无权限</section>");
        return false;
    }
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.name = item.name;
            dataObj.createTime = item.createTime;
            dataObj.del = item.del;
            dataObj.mask = item.mask;
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.dataCount
        }
    }
}
/*对需要选择的活动表格数据的处理*/
function choiceFromData(res) {
    if (res.state == 480) {
        $("#content-wrapper").html("<section class='content'>无权限</section>");
        return false;
    }
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.name = item.title;
            dataObj.releaseTime = item.createTime;
            dataObj.time = item.startTime;
            dataObj.createMan = item.userId;
            dataObj.address = item.address;
            dataObj.pv = 0;
            dataArray.push(dataObj)
        });
        if (datas.length == 0) {
            var dataObj = {};
            dataArray.push(dataObj);
        }
        return {
            rows: dataArray,
            total: res.dataCount
        }
    }
}
function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="preview p5"   href="javascript:void(0)" title="preview">预览</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="modify">编辑</a>');
    if (row.mask == 0) {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield">屏蔽</a>')
    } else {
        dataArray.push('<a class="Shield p5" href="javascript:void(0)" title="Shield">解除屏蔽</a>')
    }
    if (row.del == 0) {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>')
    } else {
        dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">恢复删除</a>')
    }
    return dataArray.join('');
}
var operateEvents = {
    'click .preview':function (e, value, row, index) {
        $("#listType").html('预览');
        $("#activityGroupCreate").show();
        // $("#imgWrap").show();
        $("#activityGroupList").hide();
        $("#combinationId").val(row.id);
        $("#name").attr("disabled","disabled");
        $("#addImgWrap").hide();
        $("#createModify").remove();
        getGroupActivityDetail(row.id);
        $("#choice-activity-table").bootstrapTable('destroy');
        $("#choice-activity-table").bootstrapTable({
            url: "/v1/combination/queryCombinationActivity",
            striped: true,           //是否显示行间隔色
            toolbar: '#toolbar',        //工具按钮用哪个容器
            cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,          //是否显示分页（*）
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            pageNumber: 1,            //初始化加载第一页，默认第一页
            pageSize: 10,            //每页的记录行数（*）
            pageList: [10, 20, 25],  //记录数可选列表
            checkboxHeader: "true",
            sortable: true,           //是否启用排序
            strictSearch: true,
            height: 500,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",           //每一行的唯一标识，一般为主键列
            search: true,
            // smartDisplay: false,
            contentType: "application/x-www-form-urlencoded",
            sidePagination: "server",
            method: "post",
            queryParamsType: "limit",
            queryParams: queryChoiceParams,
            responseHandler: choicedFromData
        })
    },
    'click .edit':function (e, value, row, index) {
        initActivity();
        $("#listType").html('编辑');
        $("#combinationId").val(row.id);
        $("#activityGroupList").hide();
        $("#activityGroupCreate").show();
        getGroupActivityDetail(row.id);
        $.ajax({
            url: "/v1/combination/queryCombinationIdByActivity",
            async: true,
            type: "post",
            data: {combinationId: row.id},
            dateType: "json",
            success: function (result) {
                checkedId = result.data;
                $("#activityIds").val(checkedId);
                $("#choice-activity-table").bootstrapTable("checkBy", {field:"id", values:checkedId});
                $('#group-form')
                    .bootstrapValidator('removeField', 'imageR')
                    .data('bootstrapValidator').validate();
            }
        });
    },
    'click .Shield':function (e, value, row, index) {
        var type = row.del == 0 ? 1 : 0;
        $.Popup({
            title:'屏蔽',
            template: '该活动组合将被删除，不能再被浏览。 独立活动列表中，仍可找到该活动组合中的活动。',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/combination/maskCombination",
                    async: false,
                    type: "post",
                    data: {combinationId: row.id, mask: type},
                    dateType: "json",
                    success: function (result) {
                        if(result.state == 200){
                            if(type == 1){
                                $.Popup({
                                    confirm:false,
                                    template:'屏蔽成功'
                                })
                            }else{
                                $.Popup({
                                    confirm:false,
                                    template:'解除屏蔽成功'
                                })
                            }
                            $('#activity-group-table').bootstrapTable('refresh');
                        }else{
                            $.Popup({
                                confirm:false,
                                template:'删除失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .remove':function (e, value, row, index) {
        var type = row.del == 0 ? 1 : 0;
        $.Popup({
            title:'删除',
            template: '该活动组合将被删除，不能再被浏览。独立活动列表中，仍可找到该活动组合中的活动 ?',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/combination/delCombination",
                    async: false,
                    type: "post",
                    data:{combinationId:row.id,delType:type},
                    dateType: "json",
                    success: function (result) {
                        if(result.state == 200){
                            if(type == 1){
                                $.Popup({
                                    confirm:false,
                                    template:'删除成功'
                                })
                            }else{
                                $.Popup({
                                    confirm:false,
                                    template:'恢复删除成功'
                                })
                            }
                            $('#activity-group-table').bootstrapTable('refresh');
                        }else{
                            $.Popup({
                                confirm:false,
                                template:'删除失败'
                            })
                        }
                    }
                });
            }
        })
    }
}
function queryChoiceParams(params) {
    return {
        pageDataNum: params.limit,
        pageNum: params.offset + 1,
        combinationId: Number($("#combinationId").val()),
        search: params.search
    };
}
function choicedFromData(res) {
    if (res.state == 480) {
        $("#content-wrapper").html("<section class='content'>无权限</section>");
        return false;
    }
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.name = item.title;
            dataObj.releaseTime = item.createTime;
            dataObj.time = item.startTime;
            dataObj.createMan = item.userId;
            dataObj.address = item.address;
            dataObj.pv = 0;
            dataArray.push(dataObj)
        });
        if (datas.length == 0) {
            var dataObj = {};
            dataArray.push(dataObj);
        }
        $("#choice-activity-table").bootstrapTable('hideColumn', 'check');
        return {
            rows: dataArray,
            total: res.dataCount
        }
    }
}
/*根据id获取组合活动的名字和图片*/
function getGroupActivityDetail(id) {
    $.ajax({
        url: "/v1/combination/queryCombinationById",
        async: false,
        type: "post",
        data: {combinationId: id},
        dateType: "json",
        success: function (result) {
            if(result.state == 200){
                $("#name").val(result.data.name);
                $("#images").attr("src", "http://image.tiyujia.com/" + result.data.image);
                ISCHANGEIMG = result.data.image;
                $("#imgUrl").val(result.data.image)
            }else{
                $.Popup({
                    confirm:false,
                    template:'查询失败'
                })
            }
        }
    });
}
function createGroupActivity() {
    initActivity();
    activityIds = [];
    $("#listType").html('创建');
    $("#activityGroupCreate").show();
    $("#activityGroupList").hide();
    $("#combinationId").val("");
    $('#group-form').bootstrapValidator('resetForm', true);   /*将表格清空*/
    $('#group-form').bootstrapValidator('addField', 'imageR',{
        validators: {
            notEmpty: {
                message: '请上传图片'
            }
        }
    });
}
/*创建活动组合*/
var ISCHANGEIMG = '';
$("#createModify").click(function () {
    var formData = new FormData();
    formData.append('imgFile', $("#lefile")[0].files[0]);
    if($("#combinationId").val() ==  ""){/*创建*/
        $.ajax({
            url: '/v1/upload/file',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function (){
                return $("#group-form").data('bootstrapValidator').isValid();
            },
            success:function (result) {
                if(result.state == 200){
                    $("#imgUrl").val(result.data);
                    createModify('/v1/combination/createCombination');
                }
            }
        })
    }else{/*编辑*/
        if(ISCHANGEIMG != $("#imgUrl").val()){/*图片发生了改变*/
            $.ajax({
                url: '/v1/upload/file',
                type: 'post',
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function (){
                    return $("#group-form").data('bootstrapValidator').isValid();
                },
                success:function (result) {
                    if(result.state == 200){
                        $("#imgUrl").val(result.data);
                        createModify('/v1/combination/updateCombination');
                    }
                }
            })
        }else{
            if($("#group-form").data('bootstrapValidator').isValid()){
                createModify('/v1/combination/updateCombination');
            }
        }
    }
})
function createModify(url) {
    $('#group-form').ajaxSubmit({
        url: url,
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm:false,
                    template:result.successmsg
                });
                $("#activityGroupCreate").hide();
                $("#activityGroupList").show();
                $('#activity-group-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm:false,
                    template:result.errmsg
                })
            }
        }
    });
}
/*发布时间的转化*/
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
/*type=file的样式处理---图片预览*/
$('input[id=lefile]').change(function() {
    $('#group-form').bootstrapValidator('addField', 'imageR', {
        validators: {
            notEmpty: {
                message: '请上传图片'
            }
        }
    });
    var _val = $(this).val();
    $("#imgUrl").val(_val);
    if($(this).val()){
        $('#photoCover').html(_val);
        $("#lefile").html(_val);
        var objUrl = getImgURL(this.files[0]) ;
        if (objUrl) {
            $("#images").attr("src", objUrl) ;
        }
    }else {
        $("#photoCover").html("选择文件");
        $("#images").attr("src", "");
    }
});
//建立一個可存取到該file的url
function getImgURL(file) {
    var url = null ;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}
