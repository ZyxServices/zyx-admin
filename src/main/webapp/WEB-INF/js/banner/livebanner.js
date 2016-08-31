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

/*function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
/!*看台列表的操作*!/
function standOperate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="standRemove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="standEdit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}*/

/*var operateEvents = {
    'click .remove':function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该banner？',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/deva/delete?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#homepage-list-table').bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .edit':function (e, value, row, index) {
        $("#liveBannerList").hide();
        $("#liveBannerEdit").show();
        $("#liveBannerId").val(row.id);
        $("#modelTitle").val(row.modelTitle);
        var imgUrl = $(row.image).attr("href");
        $("#preImage").html('<img src='+imgUrl+'>');
        console.log(row);
        if(row.activation == "是"){
            $('input[name=state]').eq(0).attr({"checked":"checked"});
        }else{
            $('input[name=state]').eq(1).attr({"checked":"checked"});
        }
        if(row.area == 1){
            $('input[name=area]').eq(0).attr({"checked":"checked"});
            $("#homepageSequence").show();
            $("#standSequence").hide();
            $("#standSequence").removeAttr("name");
            $("#homepageSequence").attr({"name":'sequence'});
            bannerSequence("homepageSequence",1,row.sequence);
            bannerSequence("standSequence",3,'');
            $("#homepageSequence").val(row.sequence)
        }else{
            $('input[name=area]').eq(1).attr({"checked":"checked"});
            $("#standSequence").show();
            $("#homepageSequence").hide();
            $("#homepageSequence").removeAttr("name");
            $("#standSequence").attr({"name":'sequence'});
            bannerSequence("circleSequence",3,row.sequence);
            bannerSequence("homepageSequence",1,'');
            $("#standSequence").val(row.sequence)
        }

    },
    'click .standRemove':function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '确定删除该banner？',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/deva/delete?id=" + row.id,
                    async: false,
                    type: "delete",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '删除成功'
                            })
                            $('#stand-list-table').bootstrapTable('refresh');
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '删除失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .standEdit':function (e, value, row, index) {
        alert("编辑")
    }
}*/


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