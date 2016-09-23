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

  <meta content="体育家-活动组合" name="description" />

  <meta content="" name="author" />

  <!-- BEGIN GLOBAL MANDATORY STYLES -->
  <jsp:include page="../public/common-styles.jsp"/>
  <link rel="stylesheet" href="../../css/summernote.css" />
  <link rel="stylesheet" href="../../css/datetimepicker.css" />
  <link rel="stylesheet" href="../../css/tiyujia/style.css" />
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
    <%--活动列表--%>
    <div class="container-fluid" id="activityGroupList">

      <!-- BEGIN PAGE HEADER-->

      <div class="row-fluid">

        <div class="span12">

          <h3 class="page-title">

            活动组合管理<small>statistics and more</small>

          </h3>

          <ul class="breadcrumb">

            <li>

              <i class="icon-home"></i>

              <a href="javascript:void(0)">活动</a>

              <i class="icon-angle-right"></i>

            </li>

            <li><a href="#">组合列表</a></li>

          </ul>

          <!-- END PAGE TITLE & BREADCRUMB-->

        </div>

      </div>

      <!-- END PAGE HEADER-->

      <div id="activity-group-list">

        <!-- BEGIN DASHBOARD STATS -->

        <div class="row-fluid margin-bottom-10">
          <div class="span6">
            <a class="btn btn-default" href="javascript:void(0)" onclick="createGroupActivity()">创建活动组合</a>
          </div>
        </div>
        <div class="row-fluid">
          <div class="span12 responsive">
            <table class="table table-hover" id="activity-group-table">
              <thead>
              <tr>
                <th data-checkbox="true"></th>
                <th data-field="id">id</th>
                <th data-field="name">活动组合名称</th>
                <th data-field="createTime" data-formatter="timeFormat">组合发布时间</th>
                <th data-field="visible">浏览量</th>
                <th data-formatter="operate" data-events="operateEvents">操作</th>
              </tr>
              </thead>
            </table>
          </div>

        </div>

        <!-- END DASHBOARD STATS -->

      </div>

    </div>

    <%--活动组合创建--%>
    <div class="container-fluid hide" id="activityGroupCreate">

      <!-- BEGIN PAGE HEADER-->

      <div class="row-fluid">

        <div class="span12">

          <!-- BEGIN PAGE TITLE & BREADCRUMB-->

          <h3 class="page-title" id="pageTitle">

            活动组合管理<small>statistics and more</small>

          </h3>

          <ul class="breadcrumb">

            <li>

              <i class="icon-home"></i>

              <a href="javascript:void(0)">活动组合</a>

              <i class="icon-angle-right"></i>

            </li>

            <li><a href="#" id="listType">创建</a></li>

          </ul>

          <!-- END PAGE TITLE & BREADCRUMB-->

        </div>

      </div>

      <!-- END PAGE HEADER-->

      <div id="activity-create">

        <!-- BEGIN DASHBOARD STATS -->
        <div class="row-fluid">

          <form class="form-horizontal" role="form" id="group-form">

            <input type="hidden" id="combinationId" name="id">
            <div class="control-group form-group">
              <label class="control-label">组合名称</label>
                <div class="controls col-xs-5">
                  <input type="text" class="span6" name="name" id="name" placeholder="请输入活动组合名称"/>
                  <span class="help-inline required">*</span>
                </div>
            </div>

            <div class="control-group form-group">
              <label class="control-label">封面</label>
              <div class="controls col-xs-5">
                <div id="addImgWrap">
                  <input type="hidden" name="image" id="imgUrl">
                  <input id="lefile" type="file" class="hideInput" name="imageR">
                  <a class="btn btn-default" href="javascript:void (0)" id="photoCover" onclick="$('input[id=lefile]').click();">选择文件</a>
                  <span class="help-inline required">*</span>
                </div>
                <div style="margin-top: 10px" id="imgWrap"class="showImg">
                  <img id="images" src="">
                </div>
              </div>
            </div>
            <hr>

            <h4>选择活动</h4>

            <div id="choice-activity">
              <div class="row-fluid">
                <div class="span12 responsive">
                  <table class="table table-hover" id="choice-activity-table">
                    <thead>
                    <tr>
                      <th data-checkbox="true" data-field="check"></th>
                      <th data-field="id">id</th>
                      <th data-field="releaseTime" data-formatter="timeFormat">活动发布时间</th>
                      <th data-field="name">活动名称</th>
                      <th data-field="time" data-formatter="timeFormat">活动时间</th>
                      <th data-field="createMan">创建人</th>
                      <th data-field="address">活动地点</th>
                      <th data-field="pv">浏览量</th>
                    </tr>
                    </thead>
                  </table>
                </div>
              </div>
            </div>

            <div class="control-group form-group">
              <label class="control-label"></label>
              <div class="controls col-xs-5">
                <input type="text" class="hideInput" name="activityIds" id="activityIds"/>
              </div>
            </div>

            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" id="createModify">确定</button>
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
<script type="text/javascript" src="../../js/activity/group.js"></script>
<script>

  jQuery(document).ready(function() {

    App.init(); // initlayout and core plugins

  });

</script>
</body>
</html>
