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
                area: HOMEPAGEAREA,
                model: LIVEMODEL,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:homepageFormData
    });
    $("#stand-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        locale: 'zh-US',
        pagination: true,
        cache: false,
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
                area: STANDAREA,
                model: LIVEMODEL,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:standFormData
    })
})

function liveDevaChange(obj) {
    var _val = $(obj).val();
    if(_val == 1){
        $("#homepage-list-table").bootstrapTable('refresh');
        $("#banner-list").show();
        $("#stand-banner-list").hide();
    }else if(_val == 2){
        $("#stand-list-table").bootstrapTable('refresh');
        $("#banner-list").hide();
        $("#stand-banner-list").show();
    }
}

function homepageFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.modelTitle = item.modelTitle;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.createTime = item.createTime;
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
            }
            dataObj.sequence = item.sequence;
            dataObj.state = item.state == 1? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
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
            dataObj.modelTitle = item.modelTitle;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.createTime = item.createTime;
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>'
            }
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 1? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}

$("input[name=area]").change(function () {
    var _val = $(this).val();
    var option = '';
    if(_val == 1){/*首页*/
        $("#homepageSequence").show();
        $("#standSequence").hide();
        $("#standSequence").removeAttr("name");
        $("#homepageSequence").attr({"name":'sequence'});
    }else{
        $("#standSequence").show();
        $("#homepageSequence").hide();
        $("#homepageSequence").removeAttr("name");
        $("#standSequence").attr({"name":'sequence'});
    }
})