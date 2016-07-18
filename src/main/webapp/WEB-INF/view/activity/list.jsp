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
  <link rel="stylesheet" href="../css/summernote.css" />
  <link rel="stylesheet" href="../css/self-style/style.css" />
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
            <table id="activity-list-table">
              <thead>
              <tr>
                <th data-checkbox="true"></th>
                <th data-field="id">id</th>
                <th data-field="name">组合名称</th>
                <th data-field="time">发布时间</th>
                <th data-field="startTime">开始时间</th>
                <th data-field="createPerson">创建人</th>
                <th data-field="place">活动地点</th>
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

          <!-- BEGIN STYLE CUSTOMIZER -->

          <jsp:include page="../public/color-panel.jsp"/>

          <!-- END BEGIN STYLE CUSTOMIZER -->

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

            <li class="pull-right no-text-shadow">

              <div class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">

                <i class="icon-calendar"></i>

                <span></span>

                <i class="icon-angle-down"></i>

              </div>

            </li>

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
                <button type="submit" class="btn btn-default">上传图片</button>
                <span class="help-inline">只能上传一张图片</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">内容</label>
              <div class="controls summernote">
                <div id="activity-summernote"></div>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">时间</label>
              <div class="controls">
                <input type="text" class="span6 m-wrap" />
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

            <div class="control-group">
              <label class="control-label">组合</label>
              <div class="controls">
                <select class="span6 m-wrap">
                  <option>世界杯</option>
                  <option>欧洲杯</option>
                  <option>亚洲杯</option>
                </select>
                <span class="help-inline">*</span>
              </div>
            </div>

            <hr>

            <h4>高级选项</h4>

            <div class="control-group">
              <label class="control-label">报名时间</label>
              <div class="controls">
                <input type="text" class="span6 m-wrap" />
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
                  <option>朋友</option>
                  <option>自己</option>
                </select>
                <span class="help-inline">*</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">用户报名必填</label>
              <div class="controls">
                <label class="checkbox"><input type="checkbox">手机号码</label>
                <label class="checkbox"><input type="checkbox">姓名</label>
                <label class="checkbox"><input type="checkbox">身份证号码</label>
                <label class="checkbox"><input type="checkbox">性别</label>
                <label class="checkbox"><input type="checkbox">年龄</label>
                <label class="checkbox"><input type="checkbox">地址</label>
                <a href="#">选择更多</a>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">是否需要审核申请者</label>
              <div class="controls">
                <select class="span6 m-wrap">
                  <option>是</option>
                  <option>否</option>
                </select>
                <span class="help-inline">*</span>
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
<script src="../js/app.js" type="text/javascript"></script>
<%--<script src="../js/index.js" type="text/javascript"></script>--%>
<script type="text/javascript" src="../js/activity/list.js"></script>
<script>

  jQuery(document).ready(function() {

    App.init(); // initlayout and core plugins

  });

</script>
</body>
</html>
