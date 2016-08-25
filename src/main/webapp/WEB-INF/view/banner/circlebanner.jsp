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
    <title>精选圈子banner推荐</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家-精选圈子banner" name="description" />

    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>

    <link rel="stylesheet" href="../../css/tiyujia/style.css" />
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid" id="circleBannerList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        精选圈子banner<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">精选圈子banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="banner-circle-list">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>首页热门圈子讨论管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="circle-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="circlename">圈子名称</th>
                                <th data-field="image">图片</th>
                                <th data-field="order">排序</th>
                                <th data-field="activation">是否激活</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <hr>
            <%--帖子banner--%>
            <div id="post-list">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>精选圈子banner管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="post-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="postname">帖子名称</th>
                                <th data-field="image">图片</th>
                                <th data-field="order">排序</th>
                                <th data-field="activation">是否激活</th>
                                <th data-formatter="postOperate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%--创建banner--%>
        <div class="container-fluid hide" id="circleBannerCreate">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        上传banner<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">圈子banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">创建</a></li>
                    </ul>
                </div>
            </div>
            <div id="activity-create">
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
                                <label class="checkbox"><input type="radio" name="module">首页微直播</label>
                                <label class="checkbox"><input type="radio" name="module">看台轮播</label>
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
<script type="text/javascript" src="../../js/banner/circlebanner.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
