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
    <title>直播banner</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-直播banner" name="description" />

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
        <div class="container-fluid" id="liveBannerList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        直播banner<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">直播 banner</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">列表</a></li>
                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="banner-list">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>首页微直播banner管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="homepage-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="livename">直播名称</th>
                                <th data-field="image">图片</th>
                                <th data-field="sequence">排序</th>
                                <th data-field="activation">是否激活</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>
            </div>
            <hr>
<%--看台轮播--%>
            <div id="stand-banner-list">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>看台轮播banner管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="stand-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="livename">直播名称</th>
                                <th data-field="image">图片</th>
                                <th data-field="sequence">排序</th>
                                <th data-field="activation">是否激活</th>
                                <th data-formatter="standOperate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>
            </div>

        </div>
        <%--创建banner--%>
        <div class="container-fluid hide" id="liveBannerCreate">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        直播上传banner<small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">直播banner</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">上传</a></li>
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
                            <label class="control-label">直播名称</label>
                            <div class="controls">
                                <input type="text" class="span6 form-control" />
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">推荐模块</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" name="module">首页微直播</label>
                                <label class="radio"><input type="radio" name="module">看台轮播</label>
                                <span class="help-inline">备注：默认不选择状态，下拉框隐藏。选择哪一个推荐模块，对应的下拉框才会出现，两个推荐模块可同时选择</span>
                            </div>
                        </div>

                        <div class="control-group hide">
                            <label class="control-label"></label>
                            <div class="controls">
                                <select class="span3">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                </select>
                                <select class="span3">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">图片</label>
                            <div class="controls">
                                <button class="btn btn-default">上传图片</button>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">推荐状态</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" name="bannerState">激活</label>
                                <label class="radio"><input type="radio" name="bannerState">未激活</label>
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
<script type="text/javascript" src="../../js/banner/livebanner.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
