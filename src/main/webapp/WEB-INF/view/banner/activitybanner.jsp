<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/14
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页banner</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-首页banner" name="description" />

    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>

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

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <div class="modal hide" id="delActivityBanner">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>删除banner</h3>
            </div>
            <div class="modal-body">
                该banner的所有数据将被完全删除，不能再被浏览。
            </div>
            <div class="modal-footer">
                <button class="btn btn-default">确定</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid" id="bannerList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        首页banner<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">首页banner</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">列表</a></li>
                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="banner-list">

                <!-- BEGIN DASHBOARD STATS -->

                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createBanner()">上传banner</a>
                        <%--<a class="btn btn-default" href="<%=request.getContextPath()%>/homepage/create">上传banner</a>--%>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="homepage-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="url">跳转url</th>
                                <th data-field="image">图片</th>
                                <th data-field="order">排序</th>
                                <th data-field="activation">是否激活</th>
                                <th data-formatter="operate">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>
        <%--创建banner--%>
        <div class="container-fluid hide" id="bannerCreate">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        上传banner<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">首页banner</a>

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
                            <label class="control-label">url</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">banner排序</label>
                            <div class="controls">
                                <input type="text" class="span6 m-wrap" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">banner状态</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" name="bannerState">激活</label>
                                <label class="radio"><input type="radio" name="bannerState">未激活</label>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">封面</label>
                            <div class="controls">
                                <button class="btn btn-default">上传图片</button>
                                <span class="help-inline">只能上传一张图片</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn btn-default">确定</button>
                                <a href="javascript:(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
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
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/banner/activitybanner.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
