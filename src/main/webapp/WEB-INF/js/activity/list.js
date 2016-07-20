/**
 * Created by ZYX on 2016/7/12.
 */
$(function(){

    $("#appQueryEnd").click(function () {
        $('#userTable').bootstrapTable("refresh")
    })
    function queryParams(params) {
        return {
            pageDataNum: params.limit,
            pageNum: (params.offset + 1)
        };
    }

    function fromData(res) {
        if(res.state == 480){
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
                dataObj.time = (new Date(item.createTime).format("yyyy-mm-dd HH:MM:ss"));
                dataObj.startTime = (new Date(item.startTime).format("yyyy-mm-dd HH:MM:ss"));
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
        height:200
    });
    $("#activity-list-table").bootstrapTable({
        url: "/activity/queryActivity",
        method: 'post',
        dataType: "json",
        cache: false,
        striped: true,
        pagination: true, //分页
        singleSelect: true,
        search: false,
        pageList: [5, 10, 20, 50],
        contentType: "application/x-www-form-urlencoded",
        minimumCountColumns: 2,
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: queryParams,
        responseHandler: fromData

    })
    $('#activityStartTime').datetimepicker({
        language:  'zh-CN',
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
        language:  'zh-CN',
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
        language:  'zh-CN',
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
        language:  'zh-CN',
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
        '<a class="preview p5"   href="javascript:void(0)" title="preview" onclick="previewActivity(\''+ row.id + '\')">预览</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="recommend(\''+ row.id + '\')">推荐</a>',
        '<a class="recommend p5" href="javascript:void(0)" title="recommend" onclick="modify(\''+ row.id + '\')">编辑</a>',
        '<a class="Shield p5" href="javascript:void(0)" title="Shield" onclick="shield(\''+ row.id + '\')">屏蔽</a>',
        '<a class="remove p5" href="javascript:void(0)" title="remove" onclick="del(\''+ row.id + '\')">删除</a>'
    ].join('');
}
/*预览*/
function previewActivity(id) {
    console.log(id)
}
/*推荐*/
function recommend(id) {
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
    console.log(id)
}
/*删除*/
function del(id) {
    console.log(id)
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