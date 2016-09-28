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
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-帖子列表" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/summernote.css"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css"/>


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

        <div class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>删除帖子</h3>

            </div>

            <div class="modal-body">

                确定删除？

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid" id="postList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        圈子列表
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>
                            <a href="/menu/circle/circlelist">圈子列表</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">帖子列表</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="post-list">

                <!-- BEGIN DASHBOARD STATS -->

                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void (0)" onclick="createPost()">发布帖子</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="post-list-table"></table>
                    </div>
                </div>

                <!-- END DASHBOARD STATS -->
            </div>
        </div>
        <%--创建帖子--%>

        <div class="container-fluid hide" id="postCreate">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        帖子发布
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">帖子</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">发布帖子</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="activity-create">

                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">
                    <form class="form-horizontal" role="form" enctype="multipart/form-data" method="post" value=""
                          id="CirclePost">
                        <%--  <input type="hidden" name="circle_id" value="75">--%>
                        <%--   <input type="hidden" name="create_id" >--%>
                        <div class="control-group form-group" id="PostUserChose">
                            <label class="control-label">请选择发布账号:</label>
                            <div class="controls  col-xs-6">
                                <select name="create_id" data-placeholder="请选择发布账号" id="createId" data-rel="chosen"
                                        style="width:350px;" class="chzn-select" tabindex="7">
                                </select>
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                            <div class="control-group form-group" id="PostUserBox" style="display: none">
                                <label class="control-label">发布账号:</label>
                                <div class="controls  col-xs-6">
                                    <div id="PostUser"></div>

                                </div>
                            </div>
                        <div class="control-group form-group">
                            <label class="control-label">标题:</label>
                            <div class="controls">
                                <input type="text" name="title" class="span6 form-control"/>
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">内容:</label>
                            <div class="controls summernote">
                                <div class="span6 form-control">
                                    <div id="post-summernote"></div>
                                    <input id="desc" type="text" class="hideInput" name="content" value="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">所属圈子:</label>
                            <div class="controls">
                                <div id="circle_id"></div>
                                <%--           <select name="circle_id"
                                                   data-placeholder="请选择圈子哦" id="circleList" data-rel="chosen" class="chzn-select"
                                                   style="width:350px;" tabindex="8">
                                           </select>--%>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label"></label>
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn btn-default" id="postSure">确定</button>
                                <a href="javascript:void(0)" class="btn btn-default"
                                   onclick="window.location.reload();">返回</a>
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
<!-- 推荐动态弹窗-->
<div class="modal fade circleModals" role="dialog" aria-labelledby="gridSystemModalLabel" id="circleModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                <h4 class="modal-title" id="gridSystemModalLabel">圈子推荐</h4>
            </div>
            <form class="form-horizontal form_bottom" role="form" id="PostRecommend"
                  enctype="multipart/form-data" method="post">
                <div class="modal-body" style="padding:10px 20px ;">
                    <div class="container-fluid">
                        <div class="row">
                            <label class="control-label ">帖子名称:</label><span id="cricleTitle"></span>
                        </div>
                        <div class="row">
                            <label class="col-xs-6 control-label ">推荐模块:</label>
                           <span class="col-xs-6 col-md-4" id="radio_checked">
                            <label class="control-label "><input type="radio" name="area" value="1"> 首页热门圈子讨论</label>
                            <label class="control-label "><input type="radio" name="area" value="3"> 精选帖子轮播</label>
                           </span>
                            <div class="radio_box">
                                <div style="display: block">
                                    <label class="col-xs-6 control-label "> </label>
                                    <select id="hotSelect"> </select>
                                </div>
                                <div>
                                    <p><label class="col-xs-6  control-label "></label>
                                        <select id="chosenSelect"></select>
                                    </p>
                                    <label class="col-xs-6  control-label ">图片:</label>
                                    <div id="imgWrap">
                                        <input type="hidden" name="imgFile">
                                        <input type="hidden" id="imgFile" name="imageUrl">
                                        <input id="lefile" type="file" name="file" class="hideInput">
                                        <a class="btn btn-default" href="javascript:void (0)" id="photoCover"
                                           style="max-width:200px;white-space:nowrap; text-overflow:ellipsis; overflow: hidden;"
                                           onclick="$('input[id=lefile]').click();">选择文件</a>
                                    </div>
                                    <input type="hidden" name="model">
                                    <input type="hidden" name="modelId">
                                    <input type="hidden" name="sequence">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div style="width:140px;; margin: 0 auto">
                                <img id="headImgShow" src="" style="width: 100% !important; margin:10px auto;">
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-xs-6 control-label ">推荐状态:</label>
                              <span class="col-xs-6 col-md-4 activation">
                            <label class="control-label "><input type="radio" checked name="state" value="1"> 激活</label>
                            <label class="control-label "><input type="radio" name="state" value="0"> 未激活</label>
                           </span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="circleSure">确认</button>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../../js/app.js" type="text/javascript"></script>
<script src="../../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/circle/circlepost.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins

    });
</script>
</body>
</html>
