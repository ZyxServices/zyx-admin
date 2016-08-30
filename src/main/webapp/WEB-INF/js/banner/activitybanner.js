/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list",
        method:'post',
        locale: 'zh-US',
        striped: true,           //是否显示行间隔色
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
            dataObj.activityName = "活动banner";
            dataObj.createTime = item.createTime;
            if(item.imageUrl){
                dataObj.image = '<img style="width: 30px" src="http://image.tiyujia.com/'+item.imageUrl+'">';
            }
            dataObj.sequence = item.sequence;
            dataObj.activation = item.state == 0? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}

function operate(value, row, index) {
    var dataArray = new Array();
    dataArray.push('<a class="remove p5" href="javascript:void(0)" title="remove">删除</a>');
    dataArray.push('<a class="edit p5" href="javascript:void(0)" title="edit">编辑</a>');
    return dataArray.join('');
}
var operateEvents = {
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
        $("#bannerList").hide();
        $("#bannerEdit").show();
        $("#title").val("活动banner");
        var img = $(row.image).attr("src");
        var imageObj = '<img src='+img+'>';
        $("#preImage").html(imageObj);
        $("#photoCover").html('选择图片');
        $("#images").attr({"src":""});
        $("#sequence").val(row.sequence);
        $("#devaId").val(row.id);
        if(row.activation == "是"){
            $('input[name=state]').eq(0).attr({"checked":"checked"});
        }else{
            $('input[name=state]').eq(1).attr({"checked":"checked"});
        }
        $.ajax({
            url: "/v1/deva/sequence",
            type: 'POST',
            dataType: 'json',
            async:false,
            data: {model: 1,area:1},
            success: function (result) {
                var bannerNoArr = result.data;
                var option = '';
                bannerNoArr.push(row.sequence);
                bannerNoArr = bannerNoArr.sort(function(a,b){/*排序*/
                    return a - b
                });
                if(result.state == 200){
                    for(var i = 0;i < result.data.length; i++){
                        option += '<option>'+result.data[i]+'</option>';
                    }
                    $("#sequence").html(option);
                }
            }
        });
    }
}
$("#confirmDeva").click(function () {
    if($("#lefile")[0].files[0]== undefined){
        confirmDeva();
    }else{
        var formData = new FormData();
        formData.append('imgFile', $("#lefile")[0].files[0]);
        $.ajax({
            url: '/v1/upload/file',
            type: 'post',
            data: formData,
            processData: false,
            contentType: false,
            success:function (result) {
                if(result.state == 200){
                    $("#imageUrl").val(result.data);
                    confirmDeva();
                }
            }
        })
    }
})
function confirmDeva() {
    $('#bannerForm').ajaxSubmit({
        url: '/v1/deva/update',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            if (result.state && result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: result.successmsg
                });
                $("#bannerList").show();
                $("#bannerEdit").hide();
                $('#homepage-list-table').bootstrapTable('refresh');
            } else if (result.state && result.state == 303) {
                $.Popup({
                    confirm: false,
                    template: result.errmsg
                })
            }
        }
    });
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