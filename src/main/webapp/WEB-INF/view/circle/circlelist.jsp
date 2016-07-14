<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/13
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>圈子</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-圈子列表" name="description" />

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

                        圈子列表<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">圈子</a>

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

                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="<%=request.getContextPath()%>/circle/circlecreat">创建圈子</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="circle-list-table">

                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th>id</th>
                                <th> 圈子名称</th>
                                <th>创建时间</th>
                                <th>创建人</th>
                                <th>圈主</th>
                                <th>管理员</th>
                                <th>帖子数量</th>
                                <th>关注人数</th>
                                <th>浏览量（主页）</th>
                                <th >操作</th>
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
<jsp:include page="../public/common-js.jsp"/>
<script src="../js/app.js" type="text/javascript"></script>
<script src="../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/circle/circlelist.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
