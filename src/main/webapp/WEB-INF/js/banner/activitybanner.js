/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list/model",
        method:'post',
        locale: 'zh-US',
        pagination: true,
        cache: false,
        search: true,
        strictSearch: true,
        uniqueId: "id",
        height:500,
        pageSize: 20,
        contentType: "application/x-www-form-urlencoded",
        pageList: new Array(20, 50, 100),
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
            return {
                area: 1,
                model: 1,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:groupFromData
    })
})

function groupFromData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.model = "活动banner";
            dataObj.createTime = item.createTime;
            // dataObj.image = '<img src="http://image.tiyujia.com/"'+item.image+'>';
            dataObj.image = '<img src="http://image.tiyujia.com/group1/M00/00/05/052YyFe0A9iAWI5kAAA75RxRQgw234.png">';
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 1? "是":"否";
            dataArray.push(dataObj)
        });
        if (datas.length == 0) {
            var dataObj = {};
            dataArray.push(dataObj);
        }
        return {
            rows: dataArray,
            // total: 20
            total: res.data.length
        }
    }
}

function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
var operateEvents = {
    'click .remove':function (e, value, row, index) {
        alert("删除")
    },
    'click .edit':function (e, value, row, index) {
        alert("编辑")
    }
}

function createBanner() {
    $("#bannerList").hide();
    $("#bannerCreate").show();
}

$('input[id=lefile]').change(function () {
    if ($(this).val()) {
        $('#photoCover').html($(this).val());
        var objUrl = getImgURL(this.files[0]);
        if (objUrl) {
            $("#images").attr("src", objUrl);
        }
    }else{
        $("#photoCover").html("选择图片");
        $("#images").attr("src", "");
    }
});

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