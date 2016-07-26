/**
 * Created by ZYX on 2016/7/20.
 */
$(function () {
    $("#jurisdiction-list-table").bootstrapTable({
        cache: false,
        striped: true,
        pagination: true, //分页
        singleSelect: true,
        search: false,
        height: 500,
        smartDisplay: false,
        pageList: [5, 10, 20, 50],
        minimumCountColumns: 2,
        paginationPreText: "上一页",
        paginationNextText: "下一页"
    });
    var isCheckedData = ['活动创建详情页','分类排序详情页','直播预览详情页'];
    var getObj = $("#jurisdiction-select").find('option');
    for(var i = 0;i < isCheckedData.length;i++){
        for(var j = 0;j < getObj.length; j++){
            var get = $(getObj[j]).val();
            if(get == isCheckedData[i]){
                $(getObj[j]).attr("selected","selected");
            }
        }
    }
    $("#jurisdiction-select").treeMultiselect({
        enableSelectAll: true,
        collapsible:true,
        hideSidePanel:true,
        onChange:function(allChecked,checked){
            console.log(allChecked)
            console.log(checked)
        }
    });
})
function createJurisdiction() {
    $("#jurisdictionList").hide();
    $("#jurisdictionCreate").show();
}