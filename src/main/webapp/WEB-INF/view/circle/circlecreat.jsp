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

                        圈子<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">圈子</a>

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
                            <label class="control-label">头像</label>
                            <div class="controls">
                                <input type="file" name="avatar" role="button" value="上传图片" >
                                <span class="help-inline">只能上传一张图片</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">类别</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">简介</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">圈主</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">管理员</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn btn-default">确定</button>
                                <a href="javascript:history.back()" class="btn btn-default">返回</a>
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
<script src="../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/activity/create.js"></script>
</body>
</html>
