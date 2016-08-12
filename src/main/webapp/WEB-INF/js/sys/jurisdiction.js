/**
 * Created by ZYX on 2016/7/20.
 */
$(function () {
    $("#roleForm").bootstrapValidator({
        message: '数据无效',
        feedbackIcons: {
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'roleName': {
                validators: {
                    notEmpty: {
                        message: '请输入名称'
                    }
                }
            }, 'roleDesc': {
                validators: {
                    notEmpty: {
                        message: '请输入描述'
                    }
                }
            }
        }
    });
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
    var isCheckedData = ['活动创建详情页', '分类排序详情页', '直播预览详情页', '发布语音动态', '发布视频动态'];
    // var getObj = $("#jurisdiction-select").find('option');
    // for (var i = 0; i < isCheckedData.length; i++) {
    //     for (var j = 0; j < getObj.length; j++) {
    //         var get = $(getObj[j]).val();
    //         if (get == isCheckedData[i]) {
    //             $(getObj[j]).attr("selected", "selected");
    //         }
    //     }
    // }

    // $("#jurisdiction-select").treeMultiselect({
    //     enableSelectAll: true,
    //     collapsible: true,
    //     hideSidePanel: true,
    //     onChange: function (allChecked, checked) {
    //         console.log(allChecked)
    //         console.log(checked)
    //     }
    // });

    $('#jurisdiction-select').val(isCheckedData).treeMultiselect({
        enableSelectAll: true,
        collapsible: true,
        // hideSidePanel: true,
        onChange: function (allChecked, checked) {
            console.log(allChecked)
            console.log(checked)
        }
    });

    $("#roleForm").ajaxForm({
        url: '/v1/role/insert',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return $("#roleForm").data('bootstrapValidator').isValid();
           /* $("#createButton").attr("disabled", true);
            var phone = $("#roleForm").find('#roleName').val();
            var password = $("#roleForm").find('#roleDesc').val();
            var checked = true;
            if (phone.replace(/\s+/g, "") == '') {
                alert("请输入名称");
                checked = false;
            }

            if (password.replace(/\s+/g, "") == '') {
                alert("请输入描述");
                checked = false;
            }

            return checked;*/
        },
        success: function (result) {
            if (result.state == 200) {
                backToUsers();
            } else {
                if (result.state == 5001) {
                    alert("角色已存在");
                }
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
})
function createJurisdiction() {
    $("#jurisdictionList").hide();
    $("#jurisdictionCreate").show();
}

function beginCreate() {
    $("#roleForm").submit();
}