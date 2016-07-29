  /**
 * Created by 文楷 on 2016/7/26.
 */
  //定义弹窗构造函数
  var   globalID=0;
  var dialog = function(ele, opt) {
      this.$element = ele,
          this.defaults = {
              confirm:true,
              'title': '系统提示',
              'template':'',
              'saveEvent':'',
              'cancelEvent':''
          },
          this.options = $.extend({}, this.defaults, opt)
  }
  //定义弹窗的方法
  dialog.prototype = {
      init: function() {
          if($('#activity-shield')[0]==undefined){
              globalID +=1;
              if(this.options.confirm==true){
                   var Successbtn="<button class='btn btn-default' id='maskSuccess'>确定</button>"
              }else{
                  var Successbtn=""
              }
              var dialogHtml= "<div id='activity-shield"+ globalID+"' class='modal fade'><div class='modal-header'>"+
                  "<button data-dismiss='modal' class='close' type='button'></button><h3>"+this.options.title+"</h3></div>"+
                  "<div class='modal-body'>"+this.options.template+"</div><div class='modal-footer'>"+
                  Successbtn+"<button class='btn btn-default' id='maskCancel' data-dismiss='modal'>关闭</button></div></div>"
              $('body').append(dialogHtml)
          }
          return globalID;
      },
      saveEvents:function(){
          var sav=this
          $('#maskSuccess').click(function(){
              $('#activity-shield'+globalID+'').modal('hide')
              $('#activity-shield'+globalID+'').remove()
              sav.options.saveEvent()
          })
          $('#maskCancel').click(function(){
              $('#activity-shield'+globalID+'').modal('hide')
              $('#activity-shield'+globalID+'').remove()
          })
      },
      cancelEvent:function(){
          $('#activity-shield'+globalID+'').modal('hide')
          $('#activity-shield'+globalID+'').remove()
      }

  }
  //在插件中使用弹窗对象
  $.Popup = function(options) {
      //创建弹窗的实体
      var dialogs = new dialog(this, options);
      //调用其方法
      dialogs.init();dialogs.saveEvents();
      //调用弹窗
      $("#activity-shield"+globalID+"").modal('toggle');
  }
