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

    <meta content="体育家-圈子列表" name="description"/>

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

                <h3>删除</h3>

            </div>

            <div class="modal-body">

                确定删除？

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid" id="circleList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">

                        圈子操作
                        <small>statistics and more</small>

                    </h3>
                    <hr style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color="rgb(51," 51,="" 51)=""
                        size="1">
                    <div class="row-fluid margin-bottom-10">
                        <div class="span6">
                            <a class="btn btn-default" href="javaScript:void(0)" onclick="circleCreate()" id="Create">创建圈子</a>
                        </div>
                    </div>

                    <!-- END PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">

                        圈子管理
                        <small>statistics and more</small>

                    </h3>

                    <div class="row-fluid margin-bottom-10">
                        <button class="fl btn btn-default ">自动/手动排序优先</button>
                        <button id="remove" class="btn btn-danger" style="margin-left: 10px" disabled>
                            <i class="glyphicon glyphicon-remove"></i> 批量删除
                        </button>
                    </div>
                </div>


            </div>

            <!-- END PAGE HEADER-->

            <div id="circle-list">
                <!-- BEGIN DASHBOARD STATS -->

                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="circle-list-table"></table>
                    </div>

                </div>

                <!-- END DASHBOARD STATS -->
            </div>

        </div>

        <%--创建圈子--%>
        <div class="container-fluid hide" id="circleCreate">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        圈子
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">圈子</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">创建</a></li>

                        <li class="pull-right no-text-shadow">

                            <div class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive"
                                 data-tablet="" data-desktop="tooltips" data-placement="top"
                                 data-original-title="Change dashboard date range">

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
                    <%--       圈子创建--%>
                    <form class="form-horizontal" role="form" id="circleCreates"
                          enctype="multipart/form-data" method="post">
                        <%--     <input name="createId" type="hidden" value="20"/>--%>
                        <input name="circleId" type="hidden"/>
                        <%--        <input name="state" type="hidden" value="-2">--%>
                        <div class="control-group form-group" id="nameShow" style="display:none">
                            <label class="control-label">创建人</label>
                            <div class="controls  col-xs-6">
                                <div id="creatperson"></div>
                            </div>
                        </div>

                        <div class="control-group form-group" id="nameHide">
                            <label class="control-label">请选择发布账号</label>
                            <div class="controls  col-xs-6">
                                <select name="createId" data-placeholder="请选择发布账号" id="createId" data-rel="chosen"
                                        style="width:350px;" class="chzn-select" tabindex="7">

                                </select>
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">名称</label>
                            <div class="controls col-xs-6">
                                <input type="text" name="title" class="span6"/>
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">头像</label>
                            <div class="controls  col-xs-6">
                                <div id="imgWrap" class="showImg">
                                    <input type="hidden" name="imgFile">
                                    <input type="hidden" id="imgFile" name="headImgUrl">
                                    <input id="lefile" type="file" name="file" class="hideInput">
                                    <a class="btn btn-default" href="javascript:void (0)" id="photoCover"
                                       onclick="$('input[id=lefile]').click();">选择文件</a>
                                    <span class="help-inline required">*</span>
                                </div>
                                <img id="headImgShow" src="" style="height: 200px !important; margin:10px 0;"><br>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">类别</label>
                            <div class="controls  col-xs-6">
                                <select class="form-control" name="circleType" id="category">
                                </select>
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">简介</label>
                            <div class="controls  col-xs-6">
                                <textarea type="text" class="span6" name="details"></textarea>
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">圈主</label>
                            <div class="controls  col-xs-6">
                                <select name="masterId" data-placeholder="请设置圈主哦" id="masterId" data-rel="chosen"
                                        style="width:350px;" class="chzn-select" tabindex="7">

                                </select>
                                <span class="help-inline">*</span>
                            </div>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">管理员</label>
                            <div class="controls  col-xs-6">
                                <select data-placeholder="请设置管理员" style="width:350px;" multiple class="chzn-select"
                                        data-rel="chosen"
                                        tabindex="8" name="adminIds" id="adminIds">
                                </select>
                                <span class="help-inline">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label"></label>
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn btn-default" id="circleBtnSure">确定</button>
                                <a href="javascript:void(0)" onclick="window.location.reload();"
                                   class="btn btn-default">返回</a>
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
<!-- 推荐圈子弹窗-->
<div class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" id="circleModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                <h4 class="modal-title" id="gridSystemModalLabel">圈子推荐</h4>
            </div>
            <form class="form-horizontal form_bottom" role="form" id="circleRecommend"
                  enctype="multipart/form-data" method="post" style="margin-bottom:0px;">
                <div class="modal-body" style="padding:10px 20px ;">
                    <div class="container-fluid">
                        <div class="control-group  form-group">
                            <label class="col-xs-6 col-md-4 control-label">
                                精选圈子排序：
                            </label>
                        <span class="col-xs-6 col-md-4">
                            <select id="circleSelect" name="sequence"></select>
                        </span>
                        </div>
                        <div class="control-group form-group">
                            <label class="control-label">推荐状态：</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" checked value="1" name="state">激活</label>
                                <label class="radio"><input type="radio" value="0" name="state">未激活</label>
                            </div>
                        </div>
                        <input type="hidden" name="model" value="3">
                        <input type="hidden" name="modelId">
                        <input type="hidden" name="area" value="3">
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-default" data-dismiss="modal">取消</a>
                    <a class="btn btn-primary" id="RdSures">确认</a>
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
<script type="text/javascript" src="../../js/circle/circlelist.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins
    });
</script>
</body>
</html>
