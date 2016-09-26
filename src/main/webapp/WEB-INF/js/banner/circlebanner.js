/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    /*
     * 对banner序列号的验证
     * */
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
            },
            'imageR': {
                validators: {
                    notEmpty: {
                        message: '精选圈子的帖子必须有图'
                    }
                }
            }
        }
    });
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        striped: true,           //是否显示行间隔色
        locale: 'zh-US',
        pagination: true,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        uniqueId: "id",
        height:500,
        pageSize: 20,
        pageList: new Array(20, 50, 100),
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        sidePagination: 'server',
        queryParams: function (params) {
            return {
                area: HOMEPAGEAREA,
                model: POSTMODEL,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:homepageFormData
    });
})

function circleDevaChange(obj) {
    var _val = $(obj).val();
    if(_val == 1){
        $("#homepage-list").show();
        $("#circle-list").hide();
        $("#post-list").hide();
        $("#homepage-list-table").bootstrapTable('destroy');
        $("#homepage-list-table").bootstrapTable({
            url: "/v1/deva/list",
            method:'post',
            locale: 'zh-US',
            striped: true,           //是否显示行间隔色
            pagination: true,
            cache: false,
            contentType: "application/x-www-form-urlencoded",
            uniqueId: "id",
            height:500,
            pageSize: 20,
            pageList: new Array(20, 50, 100),
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            sidePagination: 'server',
            queryParams: function (params) {
                return {
                    area: HOMEPAGEAREA,
                    model: POSTMODEL,
                    pageDataNum: params.limit,
                    pageNum: (params.offset + 1),
                    search: params.search
                }
            },
            responseHandler:homepageFormData
        });
    }else if(_val == 2){
        $("#homepage-list").hide();
        $("#circle-list").show();
        $("#post-list").hide();
        $("#circle-list-table").bootstrapTable('destroy');
        $("#circle-list-table").bootstrapTable({
            url: "/v1/deva/list",
            method:'post',
            locale: 'zh-US',
            striped: true,           //是否显示行间隔色
            pagination: true,
            cache: false,
            uniqueId: "id",
            height:500,
            contentType: "application/x-www-form-urlencoded",
            pageSize: 20,
            pageList: [20, 50, 100],
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            sidePagination: 'server',
            queryParams: function (params) {
                return {
                    area: CIRCLEMODEL,
                    model: CIRCLEMODEL,
                    pageDataNum: params.limit,
                    pageNum: (params.offset + 1),
                    search: params.search
                }
            },
            responseHandler:circleFormData
        });
    }else if(_val == 3){
        $("#homepage-list").hide();
        $("#circle-list").hide();
        $("#post-list").show();
        $("#post-list-table").bootstrapTable('destroy');
        $("#post-list-table").bootstrapTable({
            url: "/v1/deva/list",
            method:'post',
            locale: 'zh-US',
            pagination: true,
            striped: true,           //是否显示行间隔色
            cache: false,
            uniqueId: "id",
            height:500,
            contentType: "application/x-www-form-urlencoded",
            pageSize: 20,
            pageList: [20, 50, 100],
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            sidePagination: 'server',
            queryParams: function (params) {
                return {
                    area: CIRCLEAREA,
                    model: POSTMODEL,
                    pageDataNum: params.limit,
                    pageNum: (params.offset + 1),
                    search: params.search
                }
            },
            responseHandler:postFormData
        });
    }
}

function homepageFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.modelTitle = item.devaModelVo.modelTitle;
            dataObj.sequence = item.sequence;
            dataObj.state = item.state == 1 ? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}
function postFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.modelTitle = item.devaModelVo.modelTitle;
            dataObj.area = item.area;
            dataObj.model = item.model;
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>'
            }
            dataObj.sequence = item.sequence;
            dataObj.state = item.state == 1 ? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}
function circleFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.modelTitle = item.devaModelVo.modelTitle;
            dataObj.sequence = item.sequence;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.state = item.state == 1 ? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}
