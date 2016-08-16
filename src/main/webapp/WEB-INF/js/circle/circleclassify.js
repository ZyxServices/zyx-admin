/**
 * Created by guochunyan on 2016/7/14.
 */
$(function () {
    //创建圈子分类
    $("#circleClassify").bootstrapValidator({
        fields: {
            "typeName": {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    },
                    callback: {
                        message: "改类别已使用，请重新填写"
                    }

                }
            }
        }
    });
    $("#circleBtnSure").click(function (e) {
        $("#circleClassify").ajaxSubmit({
            url: '../../circleType/createCircleType',
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.state == 200) {
                    e.preventDefault();
                    var $form = $(e.target);
                    $form.serialize();
                    $.Popup({
                        template: '创建以后不能修改哦?',
                        saveEvent: function () {
                            $("#circle-classify-table").bootstrapTable('refresh', {url: '../../circleType/circleTypeList'});
                            $("#circleList").show();
                            $("#circleCreate").hide();
                        }
                    })
                }
                else {
                    alert("改类别已存在，请重新输入")
                }

            }

        });
    });


    //圈子分类列表
    $("#circle-classify-table").bootstrapTable({
        type: 'get',
        url: ("../../circleType/circleTypeList"),
        dataType: "json",
        toolbar: '#toolbar',        //工具按钮用哪个容器
        queryParams: {
            start: 0,
            pageSize: 25
        },
        striped: true,           //是否显示行间隔色
        cache: true,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,          //是否显示分页（*）
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        pageNumber: 1,               //初始化加载第一页，默认第一页
        // 每页的记录行数（*）
        checkbox: true,
        checkboxHeader: "true",
        sortable: true,           //是否启用排序
        sortOrder: "asc",          //排序方式
        pageList: [25, 50, 100],    //可供选择的每页的行数（*）
        strictSearch: true,
        height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",           //每一行的唯一标识，一般为主键列
        search: true,
        //  clickToSelect: true,        //是否启用点击选中行
        //showColumns: true,
        //showHeader: true,
        onLoadSuccess: function (data) {  //加载成功时执行
            console.log(data)
        },
        columns: [
            {field: 'id', title: 'id', align: 'center', valign: 'middle', width: '20'},
            {field: 'typeName', title: '圈子类别名称'}
        ]
    })
});

function CrateClass() {
    $("#circleList").hide();
    $("#circleCreate").show();
    $("input[name=typeName]").attr("value", "");
}
/*
 var circleMod={
 circleTypeSubmit:function(){
 formUtils.formSubmit("circleClassify","../../circleType/createCircleType",function(res){
 if(res.state==33000){
 $.Popup({
 template: '创建以后不能修改哦?',
 saveEvent: function () {
 $.ajax({
 url: $form.attr('action'),
 type: 'POST',
 data: $form.serialize(),
 success: function () {
 //表格重新加载
 $("#circle-classify-table").bootstrapTable('refresh', {url: '../../circleType/circleTypeList'});
 $("#circleList").show();
 $("#circleCreate").hide();


 }
 });
 }
 })
 }else{
 console.log(res.errmsg);
 }

 });
 }
 }
 */
