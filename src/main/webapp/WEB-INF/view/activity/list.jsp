<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/12
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>活动列表</title>
  <meta charset="utf-8" />
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />

  <meta content="体育家-活动列表" name="description" />

  <meta content="" name="author" />

  <!-- BEGIN GLOBAL MANDATORY STYLES -->
  <jsp:include page="../public/common-styles.jsp"/>
  <link rel="stylesheet" href="../../css/summernote.css" />
  <link rel="stylesheet" href="../../css/datetimepicker.css" />
  <link rel="stylesheet" href="../../css/self-style/style.css" />
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

  <!-- BEGIN SIDEBAR -->

  <jsp:include page="../public/nav.jsp"/>

  <!-- END SIDEBAR -->

  <!-- BEGIN PAGE -->

  <div class="page-content">

    <!-- BEGIN PAGE CONTAINER-->
    <%--modal删除--%>
    <div id="activity-del" class="modal fade">
      <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>活动删除</h3>
      </div>
      <div class="modal-body">
        该活动的所有数据将被完全删除，不能再被浏览
      </div>
      <div class="modal-footer">
        <button class="btn btn-default">确定</button>
        <button class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
    <%--modal屏蔽--%>
    <div id="activity-shield" class="modal fade">
      <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>活动屏蔽</h3>
      </div>
      <div class="modal-body">
        屏蔽之后，该活动将不在首页活动和活动列表页展示，“我的关注”和“我的”中活动保留，仍可以被浏览
      </div>
      <div class="modal-footer">
        <button class="btn btn-default">确定</button>
        <button class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
    <%--活动列表--%>
    <div class="container-fluid" id="activityList">

      <!-- BEGIN PAGE HEADER-->

      <div class="row-fluid">

        <div class="span12">

          <h3 class="page-title">

            活动列表<small>statistics and more</small>

          </h3>

          <ul class="breadcrumb">

            <li>

              <i class="icon-home"></i>

              <a href="javascript:void(0)">活动</a>

              <i class="icon-angle-right"></i>

            </li>

            <li><a href="#">列表</a></li>

          </ul>

          <!-- END PAGE TITLE & BREADCRUMB-->

        </div>

      </div>

      <!-- END PAGE HEADER-->

      <div id="activity-list">

        <!-- BEGIN DASHBOARD STATS -->

        <div class="row-fluid margin-bottom-10">
          <div class="span6">
            <%--<a class="btn btn-default" href="<%=request.getContextPath()%>/activity/create">创建活动</a>--%>
            <a class="btn btn-default" href="javascript:void(0)" onclick="createActivity()">创建活动</a>
          </div>
        </div>
        <div class="row-fluid">
          <div class="span12 responsive">
            <table class="table table-hover" id="activity-list-table">
              <thead>
              <tr>
                <th data-checkbox="true"></th>
                <th data-field="id">id</th>
                <th data-field="name">活动名称</th>
                <th data-field="time"  data-formatter="timeFormat">发布时间</th>
                <th data-field="startTime"  data-formatter="timeFormat">开始时间</th>
                <th data-field="createPerson">创建人</th>
                <th data-field="address">活动地点</th>
                <th data-field="pv">浏览量</th>
                <th data-field="report">举报状态</th>
                <th data-field="url">查看url</th>
                <th data-formatter="operate">操作</th>
              </tr>
              </thead>
            </table>
          </div>

        </div>

        <!-- END DASHBOARD STATS -->

      </div>

    </div>

    <%--活动创建--%>
    <div class="container-fluid hide" id="activityCreate">

      <!-- BEGIN PAGE HEADER-->

      <div class="row-fluid">

        <div class="span12">

          <!-- BEGIN PAGE TITLE & BREADCRUMB-->

          <h3 class="page-title">

            活动创建<small>statistics and more</small>

          </h3>

          <ul class="breadcrumb">

            <li>

              <i class="icon-home"></i>

              <a href="javascript:void(0)">活动</a>

              <i class="icon-angle-right"></i>

            </li>

            <li><a href="#">创建</a></li>

          </ul>

          <!-- END PAGE TITLE & BREADCRUMB-->

        </div>

      </div>

      <!-- END PAGE HEADER-->

      <div id="activity-create">

        <!-- BEGIN DASHBOARD STATS -->
        <div class="row-fluid">

          <form class="form-horizontal" role="form">
            <div class="control-group">
              <label class="control-label">名称</label>
              <div class="controls">
                <input type="text" class="span6 m-wrap" />
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">封面</label>
              <div class="controls">
                <input type="file" class="file">
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">内容</label>
              <div class="controls summernote">
                <div class="span6">
                  <div id="activity-summernote"></div>
                </div>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">时间</label>
              <div class="controls">
                <div class="span3"><input type="text" class="span12 m-wrap" id="activityStartTime" placeholder="活动开始时间"/></div>

                <div class="span3"><input type="text" class="span12 m-wrap" id="activityEndTime" placeholder="活动结束时间"/></div>
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">地点</label>
              <div class="controls">
                <input type="text" class="span6 m-wrap" />
                <span class="help-inline">*</span>
              </div>
            </div>

            <hr>

            <h4>高级选项</h4>

            <div class="control-group">
              <label class="control-label">时间</label>
              <div class="controls">
                <div class="span3"><input type="text" class="span12 m-wrap" id="signStartTime" placeholder="开始报名时间"/></div>

                <div class="span3"><input type="text" class="span12 m-wrap" id="signEndTime" placeholder="报名结束时间"/></div>
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">人数限制</label>
              <div class="controls">
                <input type="text" class="span6 m-wrap" />
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">咨询电话</label>
              <div class="controls">
                <input type="text" class="span6 m-wrap" />
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">可见范围</label>
              <div class="controls">
                <select class="span6 m-wrap">
                  <option>所有人</option>
                  <option>我的粉丝</option>
                  <option>我的关注</option>
                </select>
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">是否需要审核申请者</label>
              <div class="controls">
                <select class="span6 m-wrap" onchange="isReviewed(this)">
                  <option value="0">否</option>
                  <option value="1">是</option>
                </select>
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group hide" id="userRequired">
              <label class="control-label">用户报名必填</label>
              <div class="controls">
                <label class="checkbox"><input type="checkbox">手机号码</label>
                <label class="checkbox"><input type="checkbox">姓名</label>
                <label class="checkbox"><input type="checkbox">身份证号码</label>
                <label class="checkbox"><input type="checkbox">性别</label>
                <label class="checkbox"><input type="checkbox">年龄</label>
                <label class="checkbox"><input type="checkbox">地址</label>
                <a href="javascript:void (0)" onclick="choiceMore()" id="addBtn">+</a>
              </div>
            </div>

            <div class="control-group hide" id="addChoice">
              <label class="control-label"></label>
              <div class="controls">
                <input type="text" class="span3 m-wrap" id="requiredVal"><a href="javascript:void(0)" class="btn btn-default" onclick="createRequired()">确定</a>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default">确定</button>
                <a href="javascript:void(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
              </div>
            </div>
          </form>

        </div>

        <!-- END DASHBOARD STATS -->

      </div>

    </div>
    <%--活动推荐--%>
    <div class="container-fluid hide" id="activityRecommend">

      <!-- BEGIN PAGE HEADER-->

      <div class="row-fluid">

        <div class="span12">

          <!-- BEGIN STYLE CUSTOMIZER -->

          <jsp:include page="../public/color-panel.jsp"/>

          <!-- END BEGIN STYLE CUSTOMIZER -->

          <!-- BEGIN PAGE TITLE & BREADCRUMB-->

          <h3 class="page-title">

            活动推荐<small>statistics and more</small>

          </h3>

          <ul class="breadcrumb">

            <li>

              <i class="icon-home"></i>

              <a href="javascript:void(0)">活动</a>

              <i class="icon-angle-right"></i>

            </li>

            <li><a href="#">首页活动banner上传</a></li>

          </ul>

          <!-- END PAGE TITLE & BREADCRUMB-->

        </div>

      </div>

      <!-- END PAGE HEADER-->

      <div id="activity-recommend">

        <!-- BEGIN DASHBOARD STATS -->
        <div class="row-fluid">

          <form action="/deva/queryActivity" method="post"  enctype="multipart/form-data" class="form-horizontal" role="form">
            <div class="control-group">
              <label class="control-label">活动名称</label>
              <div class="controls">
                <sapn class="span6" id="activityName"></sapn>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">banner排序</label>
              <div class="controls">
                <select class="span6 m-wrap" name="sequence">
                  <option>1</option>
                  <option>2</option>
                  <option>3</option>
                  <option>4</option>
                  <option>5</option>
                  <option>6</option>
                </select>
              </div>
            </div>
            <input type="hidden" name="devaId" id="activityId" value="">
            <input type="hidden" name="types" value="1">
            <div class="control-group">
              <label class="control-label">图片</label>
              <div class="controls">
                <input type="file" name="image">
                <button class="btn btn-default">上传图片</button>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">推荐状态</label>
              <div class="controls">
                <label class="radio"><input type="radio" checked value="1" name="activation">激活</label>
                <label class="radio"><input type="radio" value="0" name="activation">未激活</label>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default">确定</button>
                <a href="javascript:void(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
              </div>
            </div>
          </form>

        </div>

        <!-- END DASHBOARD STATS -->

      </div>

    </div>
    <!-- END PAGE CONTAINER-->

  </div>

  <!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>

<script src="../../js/app.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/activity/list.js"></script>
<script>

  jQuery(document).ready(function() {

    App.init(); // initlayout and core plugins

  });

</script>
</body>
</html>
