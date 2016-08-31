/**
 * Created by 文楷 on 2016/7/26.
 */
//定义弹窗构造函数
var globalID = 0;
var dialog = function (ele, opt) {
    this.$element = ele,
        this.defaults = {
            confirm: 1,
            'title': '系统提示',
            'template': '',
            'saveText': '确定',
            'cancelText': '关闭',
            'saveEvent': '',
            'cancelEvent': '',
            'remove': true
        },
        this.options = $.extend({}, this.defaults, opt)
}
//定义弹窗的方法
dialog.prototype = {
    init: function () {
        if ($('#activity-shield')[0] == undefined) {
            globalID += 1;
            var Successbtn;
            //this.options.confirm == true ? "<button class='btn btn-default' id='maskSuccess" + globalID + "'>" + this.options.saveText + "</button>" : "";
            if (this.options.confirm == true) {
                var Successbtn = "<button class='btn btn-default' id='maskSuccess" + globalID + "'>" + this.options.saveText + "</button>"
            } else {
                var Successbtn = ""
            }
            var dialogHtml=""
                dialogHtml+=   "       <div id='activity-shield" + globalID + "' class='modal fade'>"
                dialogHtml+=   "           <div class='modal-header'>"
                dialogHtml+=   "               <button data-dismiss='modal' class='close' id='x" + globalID + "' type='button'></button>"
                dialogHtml+=   "               <h3>" + this.options.title + "</h3>"
                dialogHtml+=   "           </div>"
                dialogHtml+=   "           <div class='modal-body'>" + this.options.template + "</div>"
                dialogHtml+=   "           <div class='modal-footer'>" + Successbtn + ""
                dialogHtml+=   "               <button class='btn btn-default' id='maskCancel" + globalID + "'>" + this.options.cancelText + "</button>"
                dialogHtml+=   "           </div>"
                dialogHtml+=   "       </div>"
            $('body').append(dialogHtml)
        }
        return globalID;
    },
    saveEvents: function () {
        var sav = this
        var globalIDd = globalID;
        $('#maskSuccess' + globalIDd + '').click(function () {
            $('#activity-shield' + globalIDd + '').modal('hide')
            sav.options.saveEvent()
            if (sav.options.remove == true) {
                $('#activity-shield' + globalIDd + '').remove()
            }
        })
        $('#x' + globalID + '').click(function () {
            sav.closeEvents(globalIDd,sav)
        })
        $('#maskCancel' + globalID + '').click(function () {
            sav.closeEvents(globalIDd,sav)
        })
    },
    closeEvents:function(globalIDd,sav){
        $('#activity-shield' + globalIDd + '').modal('hide')
        $('#activity-shield' + globalIDd + '').remove()

        if ($.isFunction(sav.options.cancelEvent)) {
            sav.options.cancelEvent()
        }
    }
}

//ajax 预览，屏蔽， 推荐，删除，封装
var ajaxPlugins = {
    preview: function () {

    },
    edit: function () {

    },
    recommend: function () {

    },
    Shield: function () {

    },
    remove: function (url, tableid, type) {
        $.Popup({
            template: '确认删除吗?',
            saveEvent: function () {
                $.ajax({
                    url: url,
                    async: false,
                    type: type,
                    success: function (result) {
                        if (result.state == 200 || result.successmsg == "删除成功") {
                            //console.log(typeof url.split("?id=")[1],typeof row.id.type)
                            $('#' + tableid + '').bootstrapTable("refresh");
                        } else {
                            alert(result.successmsg)
                        }
                    }
                });
            }
        })
    },
    unRemove: function (url, tableid, type) {
        $.Popup({
            template: '确认撤销删除吗?',
            saveEvent: function () {
                $.ajax({
                    url: url,
                    async: false,
                    type: type,
                    success: function (result) {
                        if (result.state == 200 || result.successmsg == "操作成功") {
                            //console.log(typeof url.split("?id=")[1],typeof row.id.type)
                            $('#' + tableid + '').bootstrapTable("refresh");
                        } else {
                            alert(result.successmsg)
                        }
                    }
                });
            }
        })
    }


}
//时间戳初始化
function timeFormat(data) {
    return new Date(data).format("yyyy-mm-dd HH:MM:ss")
}

//获取所有行id
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}


function removeEvent(id) {
    var sav = this
    var globalIDd = globalID;
    $("#"+id+"").modal('hide');
    $('#activity-shield' + globalIDd + '').remove()
}
//在插件中使用 弹窗对象
$.Popup = function (options) {
    //创建弹窗的实体
    var dialogs = new dialog(this, options);
    //调用其方法
    dialogs.init();
    dialogs.saveEvents();
    //调用弹窗
    $("#activity-shield" + globalID + "").modal('show');
}
