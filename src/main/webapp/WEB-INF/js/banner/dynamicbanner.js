/**
 * Created by ZYX on 2016/7/14.
 */
$(function(){
    $("#homepage-list-table").bootstrapTable({
        url: "/v1/deva/list",
        striped: true,           //是否显示行间隔色
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
                model: DYNAMICMODEL,
                pageDataNum: params.limit,
                pageNum: (params.offset + 1),
                search: params.search
            }
        },
        responseHandler:dynamicFormData
    })
})
function dynamicFormData(res) {
    if (res.state == 200) {
        var dataArray = [];
        var datas = res.data;
        datas.forEach(function (item, a) {
            var dataObj = {};
            dataObj.id = item.id;
            dataObj.area = item.area;
            dataObj.model = item.model;
            dataObj.createTime = item.createTime;
            dataObj.modelTitle = item.devaModelVo.nickname;/*动态的发布者*/
            if(item.imageUrl){
                var imgUrl = item.imageUrl.split(".");
                if(imgUrl[1] == 'gif'){
                    dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img style="width: 30px" src="http://image.tiyujia.com/'+imgUrl[0]+'.'+imgUrl[1]+'"></a>';
                }else{
                    dataObj.image = '<a href="http://image.tiyujia.com/'+item.imageUrl+'" target="view_window"><img src="http://image.tiyujia.com/'+imgUrl[0]+'__30x30.'+imgUrl[1]+'"></a>';
                }
            }
            dataObj.sequence = item.sequence;
            dataObj.state = item.state == 1? "是":"否";
            dataObj.official = item.devaModelVo.official == 1? "是":"否";
            dataArray.push(dataObj)
        });
        return {
            rows: dataArray,
            total: res.data.length
        }
    }
}