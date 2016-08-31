/**
 * Created by ZYX on 2016/8/31.
 */
/*
* 修改推荐
* */
var ACTIVITYMODEL = 1;
var LIVEMODEL = 2;
var CIRCLEMODEL = 3;
var POSTMODEL = 4;
var DYNAMICMODEL = 5;
var USERMODEL = 6;
var SYSTEMMODEL = 7;

var HOMEPAGEAREA = 1;
var STANDAREA = 2;
var CIRCLEAREA = 3;
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
});
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
/*
* 图片的上传和预览
* */
$('#lefile').change(function () {
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
/*
* 列表操作
* */
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
    'click .edit':function (e, value, row, index) {/*编辑有不是公用的部分*/
        console.log(row);
        $("#bannerList").hide();
        $("#bannerEdit").show();
        $("#photoCover").html('选择图片');
        $("#images").attr({"src":""});
        if(row.model == ACTIVITYMODEL || row.model == DYNAMICMODEL){
            var imgUrl = $(row.image).attr("href");
            $("#preImage").html('<img src='+imgUrl+'>');
            $("#sequence").val(row.sequence);
            bannerSequence(row.model,row.area,row.sequence,'sequence');
        }else if(row.model == LIVEMODEL){
            var imgUrl = $(row.image).attr("href");
            $("#preImage").html('<img src='+imgUrl+'>');
            if(row.area == ACTIVITYMODEL){
                $('input[name=area]').eq(0).attr({"checked":"checked"});
                $("#homepageSequence").show();
                $("#standSequence").hide();
                $("#standSequence").removeAttr("name");
                $("#homepageSequence").attr({"name":'sequence'});
                bannerSequence(row.model,row.area,row.sequence,"homepageSequence");
                bannerSequence(row.model,STANDAREA,'',"standSequence");
                $("#homepageSequence").val(row.sequence)
            }else{
                $('input[name=area]').eq(1).attr({"checked":"checked"});
                $("#standSequence").show();
                $("#homepageSequence").hide();
                $("#homepageSequence").removeAttr("name");
                $("#standSequence").attr({"name":'sequence'});
                bannerSequence(row.model,row.area,row.sequence,"standSequence");
                bannerSequence(row.model,HOMEPAGEAREA,'',"homepageSequence");
                $("#standSequence").val(row.sequence)
            }
        }
        $("#title").val(row.modelTitle);
        $("#devaId").val(row.id);
        if(row.state == "是"){
            $('input[name=state]').eq(0).attr({"checked":"checked"});
        }else{
            $('input[name=state]').eq(1).attr({"checked":"checked"});
        }

    }
}
/*
* banner序列的请求
* */
function bannerSequence(model, area,currentSequence,idObj) {
    $.ajax({
        url: "/v1/deva/sequence",
        type: 'POST',
        dataType: 'json',
        async:false,
        data: {model: model,area: area},
        success: function (result) {
            var bannerNoArr = result.data;
            var option = '';
            if(currentSequence){
                bannerNoArr.push(currentSequence);
            }
            bannerNoArr = bannerNoArr.sort(function(a,b){/*排序*/
                return a - b
            });
            if(result.state == 200){
                for(var i = 0;i < result.data.length; i++){
                    option += '<option>'+result.data[i]+'</option>';
                }
                $("#"+idObj).html(option);
            }
        }
    });
}
/*
* 列表时间处理
* */
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}