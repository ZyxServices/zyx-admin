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
                area: 1,
                model: 3,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:homepageFormData
    });
    $("#post-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        locale: 'zh-US',
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
                area: 3,
                model: 3,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:postFormData
    });
    $("#circle-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        locale: 'zh-US',
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
                area: 2,
                model: 3,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:circleFormData
    })
})

function circleDevaChange(obj) {
    var _val = $(obj).val();
    if(_val == 1){
        $("#homepage-list").show();
        $("#circle-list").hide();
        $("#post-list").hide();
    }else if(_val == 2){
        $("#homepage-list").hide();
        $("#circle-list").show();
        $("#post-list").hide();
    }else if(_val == 3){
        $("#homepage-list").hide();
        $("#circle-list").hide();
        $("#post-list").show();
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
            dataObj.modelTitle = item.modelTitle;
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 0? "是":"否";
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
            dataObj.modelTitle = item.modelTitle;
            dataObj.image = '<img style="width: 30px" src="http://image.tiyujia.com/group1/M00/00/05/052YyFe0A9iAWI5kAAA75RxRQgw234.png">';
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 0? "是":"否";
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
            dataObj.modelTitle = item.modelTitle;
            dataObj.sequence = item.sequence;
            dataObj.activation = item.activation == 0? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}

function homepageOperate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="homepageRemove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="homepageEdit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
function postOperate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="postRemove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="postEdit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
function circleOperate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="circleRemove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="circleEdit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
function removeBanner(id,table) {
    $.Popup({
        title: '删除',
        template: '确定删除该banner？',
        saveEvent: function () {
            $.ajax({
                url: "/v1/deva/delete?id=" + id,
                async: false,
                type: "delete",
                success: function (result) {
                    if (result.state == 200) {
                        $.Popup({
                            confirm: false,
                            template: '删除成功'
                        })
                        $('#'+table).bootstrapTable('refresh');
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
}
var operateEvents = {
    'click .homepageRemove':function (e, value, row, index) {
        removeBanner(row.id,"homepage-list-table");
    },
    'click .homepageEdit':function (e, value, row, index) {
        console.log(row);
        $("#circleBannerList").hide();
        $("#circleBannerEdit").show();
        $("#circleName").val(row.modelTitle);
        $("#circleId").val(row.id);
        if(row.area == 1){
            $('input[name=area]').eq(0).attr({"checked":"checked"});
            $("#homepageSequence").show();
            $("#circleSequence").hide();
            $("#circleSequence").removeAttr("name");
            $("#homepageSequence").attr({"name":'sequence'});
            bannerSequence("homepageSequence",1,row.sequence);
            bannerSequence("circleSequence",2,'');
            $("#homepageSequence").val(row.sequence)
        }else{
            $('input[name=area]').eq(1).attr({"checked":"checked"});
            $("#circleSequence").show();
            $("#homepageSequence").hide();
            $("#homepageSequence").removeAttr("name");
            $("#circleSequence").attr({"name":'sequence'});
            bannerSequence("circleSequence",2,row.sequence);
            bannerSequence("homepageSequence",1,'');
            $("#circleSequence").val(row.sequence)
        }
        if(row.activation == "是"){
            $('input[name=state]').eq(0).attr({"checked":"checked"});
        }else{
            $('input[name=state]').eq(1).attr({"checked":"checked"});
        }
        
    },
    'click .circleRemove':function (e, value, row, index) {
        removeBanner(row.id,"circle-list-table");
    },
    'click .circleEdit':function (e, value, row, index) {
        console.log(row);
        $("#circleBannerList").hide();
        $("#circleBannerEdit").show();
    },
    'click .postRemove':function (e, value, row, index) {
        removeBanner(row.id,"post-list-table");
    },
    'click .postEdit':function (e, value, row, index) {
        console.log(row)
    }
}

function bannerSequence(id, area,sequence) {
    $.ajax({
        url: "/v1/deva/sequence",
        type: 'POST',
        dataType: 'json',
        async:false,
        data: {model: 3,area:area},
        success: function (result) {
            var bannerNoArr = result.data;
            var option = '';
            bannerNoArr.push(sequence);
            bannerNoArr = bannerNoArr.sort(function(a,b){/*排序*/
                return a - b
            });
            if(result.state == 200){
                for(var i = 0;i < result.data.length; i++){
                    option += '<option>'+result.data[i]+'</option>';
                }
                $("#"+id).html(option);
            }
        }
    });
}

$("#confirmDeva").click(function () {
    $('#circleBannerForm').ajaxSubmit({
        url: '/v1/deva/update',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: result.successmsg
                });
                $("#circleBannerList").show();
                $("#circleBannerEdit").hide();
                $('#homepage-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
})

$("input[name=area]").change(function () {
    var _val = $(this).val();
    var option = '';
    if(_val == 1){/*首页*/
        $("#homepageArea").show();
        $("#circleArea").hide();
        $("#circleArea").removeAttr("name");
        $("#homepageArea").attr({"name":'sequence'});
    }else{
        $("#circleArea").show();
        $("#homepageArea").hide();
        $("#homepageArea").removeAttr("name");
        $("#circleArea").attr({"name":'sequence'});
    }
})