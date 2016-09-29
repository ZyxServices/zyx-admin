// 认证状态格式化
function authFormatter(value) {
    return value == 2 ? "已认证" : value == 1 ? "待审核" : value == 3 ? "认证失败" : "未认证";
}
// 状态格式化
function statusFormatter(value, row, index) {
    /*if (row.del && row.mask) {
        return "已删除-已屏蔽";
    }
    if (row.del && !row.mask) {
        return "已删除";
    }*/
    if (row.mask) {
        return "已屏蔽";
    }else if (!row.mask) {
        return "正常";
    }
}
// 日期格式化
function dateFormatter(value, row, index) {
    if (null == value || "" == value) {
        return "-";
    }
    try {
        return new Date(value).format("yyyy-mm-dd HH:MM:ss");
    } catch (e) {
        return "-";
    }
}
// 详情显示
function detailFormatter(index, row, c) {
    var html = [];
    console.log(row);
    $.each(row, function (key, value) {
        if ("nickname" == key) {
            html.push('<p><b>用户昵称:</b> ' + value + '</p>');
        }
        if ("avatar" == key) {
            var _t = "http://image.tiyujia.com/" + value;
            html.push('<p><b>用户头像:</b><img src=' + _t + '></p>');
        }
        if ("sex" == key) {
            var _temp = value == 1 ? "男" : "女";
            html.push('<p><b>用户性别:</b> ' + _temp + '</p>');
        }
        if ("phone" == key) {
            html.push('<p><b>手机号码:</b> ' + value + '</p>');
        }
        // if ("birthday" == key) {
        //     html.push('<p><b>用户生日:</b> ' + new Date(value).format("yyyy-mm-dd HH:MM:ss") + '</p>');
        // }
        if ("address" == key && value != null) {
            html.push('<p><b>用户所在地:</b> ' + value + '</p>');
        }
        if ("signature" == key) {
            var _temp = value == null ? "这家伙很懒什么都没有留下" : value;
            html.push('<p><b>用户签名档:</b> ' + _temp + '</p>');
        }
        if ("lastlogintime" == key) {
            html.push('<p><b>最后登录时间:</b> ' + new Date(value).format("yyyy-mm-dd HH:MM:ss") + '</p>');
        }
    });
    // 认证信息-待审核
    // if ("appUserAuthDto" == key && row["authenticate"] == 1) {
    if (row["appUserAuthDto"]) {
        var _temp = row["appUserAuthDto"];
        html.push('<p><b>认证信息:</b> ' + _temp["authInfo"] + '</p>');
        var _temp_array = _temp["authFile"].split(",");
        html.push('<p><b>认证图片:</b></p>');
        _temp_array.forEach(function (a) {
            var _t = "http://image.tiyujia.com/" + a;
            html.push('<p><img src=' + _t + '></p>');
        });
    }
    // }
    return html.join('');
}
//查看Url
function seeUrlFormatter(value, row, index) {
    return [
        '<a class="seeUrl p5"   href="javascript:void(0)" title="Like">查看</a>'
    ].join('');
}
// 操作事件edit
var operateEvent = {
    'click .preview': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .edit': function (e, value, row, index) {
        $("#userId").val(row.id);
        $(".live_index").hide();
        $(".create_liveType").show();
        $("#nickname").val(row.nickname);
        $("#phone").val(row.phone);
        $("#password").val(row.password);
        $('input[name=sex]').eq(!row.sex).attr({"checked": "checked"});
        if(row.avatar){
            $("#avatarImg").attr("src","http://image.tiyujia.com/" + row.avatar);
        }
        $("#address").val(row.address);
        $('#editUserForm').data('bootstrapValidator').validate();
    },
    'click .recommend': function (e, value, row, index) {
        $.post("/v1/deva/sequence", {model: 6, area: 1}, function (data) {
            if (data.state == 200) {
                if (data.data.length > 0) {
                    $("#devaUserNickname").html(row.nickname);
                    $("#modelId").val(row.id);
                    if (row["avatar"]) {
                        $("#devaUserAvatar").attr("src", "http://image.tiyujia.com/" + row["avatar"]);
                    }
                    for (var i = 0; i < data.data.length; i++) {
                        $("#sequence").append("<option value='" + data.data[i] + "'>" + data.data[i] + "</option>");
                    }
                    $("#appUserRecommend").modal('show');
                }
                else {
                    $.Popup({
                        confirm: false,
                        template: '用户banner序列号已满，请先删除其他序列号再推荐'
                    })
                }
            }

        });
    },
    'click .audit': function (e, value, row, index) {// 审核通过
        // 弹出审核页面
        $("#authId").val(row.id);
        if(row.appUserAuthDto != null){
            $("#authName").val(row.appUserAuthDto.authName);
            $("#authIDCard").val(row.appUserAuthDto.authIDCard);
            $("#authMob").val(row.appUserAuthDto.authMob);
            $("#cardImg").attr("src","http://image.tiyujia.com/" + row.appUserAuthDto.authFile);
            $("#authInfo").val(row.appUserAuthDto.authInfo);
            if(row.appUserAuthDto.authFileWork){
                $("#workImg").attr("src","http://image.tiyujia.com/" + row.appUserAuthDto.authFileWork);
            }
        }
        $(".live_index").hide();
        $("#appUserAuth").show();
    },
    'click .mask': function (e, value, row, index) {
        $.Popup({
            title: '屏蔽',
            template: '屏蔽之后该用户将不能再启用',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/appUser/mask",
                    async: false,
                    type: "get",
                    data: {id: row.id},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $('#app_user_table').bootstrapTable("refresh");
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '操作失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .unMask': function (e, value, row, index) {
        $.Popup({
            title: '恢复屏蔽',
            template: '这是恢复屏蔽是否的对话框',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/appUser/unMask",
                    async: false,
                    type: "get",
                    data: {id: row.id},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $('#app_user_table').bootstrapTable("refresh");
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '操作失败'
                            })
                        }
                    }
                });
            }
        })
    },
    'click .del': function (e, value, row, index) {
        $.Popup({
            title: '删除',
            template: '该活动的所有数据将被完全删除，不能再被浏览',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/appUser/del",
                    async: false,
                    type: "get",
                    data: {id: row.id},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $('#app_user_table').bootstrapTable("refresh");
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
    'click .Authentication':function (e, value, row, index) {
        $("#authForm")[0].reset();
        $('#authForm').data('bootstrapValidator').resetForm(true);
        $(".live_index").hide();
        $(".create_liveType").hide();
        $("#appUserAuth").show();
        $("#authId").val(row.id);
    },
    'click .resetPwd': function (e, value, row, index) {
        $.Popup({
            title: '密码重置',
            template: '确定密码重置？重置后密码为：123456！',
            saveEvent: function () {
                $.ajax({
                    url: "/v1/appUser/resetPwd",
                    async: false,
                    type: "post",
                    data: {phone: row.phone},
                    dateType: "json",
                    success: function (result) {
                        if (result.state == 200) {
                            $.Popup({
                                confirm: false,
                                template: '密码重置成功，请重新登录'
                            });
                            $('#app_user_table').bootstrapTable("refresh");
                        } else {
                            $.Popup({
                                confirm: false,
                                template: '重置失败，请刷新后再次尝试'
                            })
                        }
                    }
                });
            }
        })
    }
};
function beginDeva() {
    $("#devaForm").ajaxSubmit({
        url: '/v1/deva/add',
        type: 'post',
        dataType: 'json',
        data: {model: 6, area: 1},
        beforeSubmit: function () {
            $("#devaButton").attr("disabled", true);
            return true;
        },
        success: function (result) {
            if (result.state == 200) {
                $.Popup({
                    confirm: false,
                    template: '推荐成功'
                });
                $("#appUserRecommend").modal('hide');
                $("#devaButton").attr("disabled", false);
                $('#app_user_table').bootstrapTable('refresh');
            } else {
                $.Popup({
                    confirm: false,
                    template: result["errmsg"]
                });
                $("#devaButton").attr("disabled", false);
            }
        },
        error: function () {
            $("#createButton").attr("disabled", false);
        }
    });
}
