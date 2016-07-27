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
    <title>首页精彩动态推荐banner</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-首页精彩动态推荐banner" name="description" />

    <meta content="首页精彩动态推荐banner" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>

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

        <div class="container-fluid" id="dynamicBannerList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        首页精彩动态banner<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">首页精彩banner</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">列表</a></li>
                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="banner-dynamic-list">
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="dynamic-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="image">首图</th>
                                <th data-field="publisher">发布者</th>
                                <th data-field="order">排序</th>
                            </tr>
                            </thead>
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
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/banner/dynamicbanner.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
