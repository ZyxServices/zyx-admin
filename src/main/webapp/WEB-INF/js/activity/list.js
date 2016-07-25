/**
 * Created by ZYX on 2016/7/12.
 */
$(function () {

    $("#appQueryEnd").click(function () {
        $('#userTable').bootstrapTable("refresh")
    })
    function queryParams(params) {
        console.log(params)
        return {
            pageDataNum: params.limit,
            pageNum: (params.offset + 1),
            search:params.search
        };
    }

    function fromData(res) {
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
                dataObj.time = item.createTime;
                dataObj.startTime = item.startTime;
                dataObj.createPerson = item.userId;
                dataObj.address = item.address;
                dataObj.pv = 0;
                dataObj.report = 0;
                dataObj.url = 0;
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

    $('#activity-summernote').summernote({
        lang: 'zh-CN',
        height:200
    });
    $("#activity-list-table").bootstrapTable({
        url: "/activity/queryActivity",
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
        smartDisplay: false,
        sidePagination: "server",
        method: "get",
        queryParamsType: "limit",
        queryParams: queryParams,
        responseHandler: fromData

    })
    $('#activityStartTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: true,
        todayBtn: true,
        autoclose: true,
        todayHighlight: 1,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    });
    $('#activityEndTime').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        weekStart: true,
        todayBtn: true,
        autoclose: true,
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    });
    $('#signStartTime').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        format: 'yyyy-mm-dd hh:ii',
        todayBtn: true,
        autoclose: true,
        todayHighlight: 1,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    });
    $('#signEndTime').datetimepicker({
        language: 'zh-CN',
        weekStart: true,
        todayBtn: true,
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayHighlight: true,
        minView: false,
        forceParse: true,
        pickerPosition: "bottom-left",
        showMeridian: false
    });

})

function operate(value, row, index) {
    return [
        '<a class="preview p5"   href="javascript:void(0)" title="preview" onclick="previewActivity(\'' + row.id + '\')">预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="recommend(\'' + row.id + '\',\''+ row.name +'\')">推荐</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="modify(\'' + row.id + '\')">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield" onclick="shield(\'' + row.id + '\')">屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove" onclick="del(\'' + row.id + '\')">删除</a>'
    ].join('');
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}
/*预览*/
function previewActivity(id) {
    console.log(id)
}
/*推荐*/
function recommend(id,name) {
    $("#activityName").html(name);
    $("#activityId").val(id);
    $("#activityRecommend").show();
    $("#activityList").hide();
}
/*编辑*/
function modify(id) {
    $("#activityCreate").show();
    $("#activityList").hide();
}
/*屏蔽*/
function shield(id) {
    console.log(id);
    $("#activity-shield").modal('toggle');
}
/*删除*/
function del(id) {
    $("#activity-del").modal('toggle');
}

function createActivity() {
    $("#activityCreate").show();
    $("#activityList").hide();

}
/*是否需要审核*/
function isReviewed(obj) {
    $("#userRequired").toggle(500);
}

/*选择更多*/
function choiceMore() {
    $("#addChoice").toggle(500);
    $("#addBtn").hide();
}
/*增加用户必填的字段*/
function createRequired() {
    var requiredVal = $("#requiredVal").val();
    var val = '<label class="checkbox"><input type="checkbox">'+requiredVal+'</label>';
    $("#addBtn").before(val);
    $("#requiredVal").val('');
    $("#addBtn").show();
    $("#addChoice").toggle(500);
}