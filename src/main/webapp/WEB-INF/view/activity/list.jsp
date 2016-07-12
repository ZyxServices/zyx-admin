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

  <meta content="基于Bootstrap的网站后台模板Metronic DEMO" name="description" />

  <meta content="" name="author" />

  <!-- BEGIN GLOBAL MANDATORY STYLES -->
  <jsp:include page="../public/styles.jsp"/>
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

    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

    <div id="portlet-config" class="modal hide">

      <div class="modal-header">

        <button data-dismiss="modal" class="close" type="button"></button>

        <h3>Widget Settings</h3>

      </div>

      <div class="modal-body">

        Widget settings form goes here

      </div>

    </div>

    <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

    <!-- BEGIN PAGE CONTAINER-->

    <div class="container-fluid">

      <!-- BEGIN PAGE HEADER-->

      <div class="row-fluid">

        <div class="span12">

          <!-- BEGIN STYLE CUSTOMIZER -->

          <jsp:include page="../public/color-panel.jsp"/>

          <!-- END BEGIN STYLE CUSTOMIZER -->

          <!-- BEGIN PAGE TITLE & BREADCRUMB-->

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

            <li class="pull-right no-text-shadow">

              <div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">

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

      <div id="activity-list">

        <!-- BEGIN DASHBOARD STATS -->

        <div class="row-fluid">
          <div class="span12 responsive">
            <table id="activity-list-table">
              <thead>
              <tr>
                <th data-checkbox="true"></th>
                <th>id</th>
                <th>组合名称</th>
                <th>发布时间</th>
                <th>开始时间</th>
                <th>创建人</th>
                <th>活动地点</th>
                <th>浏览量</th>
                <th>操作</th>
                <th>举报状态</th>
                <th>查看url</th>
              </tr>
              </thead>
             <%-- <tbody>
              <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>12</td>
                <td>11</td>
              </tr>
              </tbody>--%>
            </table>
          </div>

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
</body>
<script type="text/javascript" src="../../js/activity/list.js"></script>
</html>
