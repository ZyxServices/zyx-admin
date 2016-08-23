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
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid" id="bannerList">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        首页banner管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">首页banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                    </ul>
                </div>
            </div>
            <div id="banner-list">
                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createBanner()">上传banner</a>
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
                                <th data-field="sequence">排序</th>
                                <th data-field="activation">是否激活</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>
        <%--创建banner--%>
        <div class="container-fluid hide" id="bannerCreate">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        首页banner管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">首页banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">上传banner</a></li>
                    </ul>
                </div>
            </div>
            <div id="activity-create">
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
                                <label class="radio"><input type="radio" name="bannerState" checked>激活</label>
                                <label class="radio"><input type="radio" name="bannerState">未激活</label>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">封面</label>
                            <div class="controls">
                                <input id="lefile" type="file" class="hideInput" name="image">
                                <a class="btn btn-default" href="javascript:void (0)" id="photoCover" onclick="$('input[id=lefile]').click();">选择图片</a>
                                <div style="margin-top: 10px" id="imagesWrap">
                                    <img id="images" src="">
                                </div>
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
