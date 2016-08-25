/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        locale: 'zh-US',
        pagination: true,
        cache: false,
        search: true,
        strictSearch: true,
        uniqueId: "id",
        height:500,
        pageSize: 20,
        pageList: [20, 50, 100],
        contentType: "application/x-www-form-urlencoded",
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
            return {
                area: 1,
                model: 2,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:homepageFormData
    })
    $("#stand-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        locale: 'zh-US',
        pagination: true,
        cache: false,
        search: true,
        strictSearch: true,
        uniqueId: "id",
        contentType: "application/x-www-form-urlencoded",
        height:500,
        pageSize: 20,
        pageList: [20, 50, 100],
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
            return {
                area: 1,
                model: 2,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:standFormData
    }) 
})

function homepageFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.livename = "活动banner";
            dataObj.image = '<img src="http://image.tiyujia.com/group1/M00/00/05/052YyFe0A9iAWI5kAAA75RxRQgw234.png">';
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 1? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: 20
            // total: res.dataCount
        }
    }
}
function standFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.livename = "活动banner";
            dataObj.image = '<img src="http://image.tiyujia.com/group1/M00/00/05/052YyFe0A9iAWI5kAAA75RxRQgw234.png">';
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 1? "是":"否";
            dataArray.push(dataObj)
        });
        /*if (datas.length == 0) {
            var dataObj = {};
            dataArray.push(dataObj);
        }*/
        return {
            rows: dataArray,
            total: 20
            // total: res.dataCount
        }
    }
}

function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
/*看台列表的操作*/
function standOperate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="standRemove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="standEdit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}

var operateEvents = {
    'click .remove':function (e, value, row, index) {
        alert("删除")
    },
    'click .edit':function (e, value, row, index) {
        alert("编辑")
    },
    'click .standRemove':function (e, value, row, index) {
        alert("删除")
    },
    'click .standEdit':function (e, value, row, index) {
        alert("编辑")
    }
}