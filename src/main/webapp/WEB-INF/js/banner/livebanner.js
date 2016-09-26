/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        striped: true,           //是否显示行间隔色
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
    $("#bannerForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'sequence': {
                validators: {
                    notEmpty: {
                        message: 'banner序号不能为空'
                    }
                }
            }
        }
    });
})

function liveDevaChange(obj) {
    var _val = $(obj).val();
    if(_val == 1){
        $("#homepage-list-table").bootstrapTable('destroy');
        $("#homepage-list-table").bootstrapTable({
            url: "/v1/deva/list",
            method:'post',
            locale: 'zh-US',
            pagination: true,
            striped: true,           //是否显示行间隔色
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
        $("#banner-list").show();
        $("#stand-banner-list").hide();
    }else if(_val == 2){
        $("#stand-list-table").bootstrapTable('destroy');
        $("#stand-list-table").bootstrapTable({
            url: "/v1/deva/list",
            method:'post',
            striped: true,           //是否显示行间隔色
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
        });
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
            dataObj.modelTitle = item.devaModelVo.modelTitle;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.createTime = item.createTime;
            if(item.devaModelVo == null) {
                return true;
            }
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
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
            dataObj.modelTitle = item.devaModelVo.modelTitle;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.createTime = item.createTime;
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>'
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