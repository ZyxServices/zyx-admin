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
        <div class="container-fluid" id="bannerList">
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
                <div>
                    <select class="form-control" onchange="circleDevaChange(this)">
                        <option value="1">首页热门帖子讨论管理</option>
                        <option value="2">精选圈子里圈子管理</option>
                        <option value="3">精选圈子帖子banner管理</option>
                    </select>
                </div>
            </div>
            <div id="homepage-list">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>首页热门帖子讨论管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="homepage-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="modelTitle">圈子名称</th>
                                <th data-field="sequence">排序</th>
                                <th data-field="state">是否激活</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <%--热门圈子下的圈子推荐--%>
            <div id="circle-list" class="hide">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>精选圈子里圈子管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="circle-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="modelTitle">圈子名称</th>
                                <th data-field="sequence">排序</th>
                                <th data-field="state">是否激活</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <%--帖子banner--%>
            <div id="post-list" class="hide">
                <div class="row-fluid">
                    <div class="span6">
                        <h3>精选圈子帖子banner管理</h3>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="post-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">ID</th>
                                <th data-field="modelTitle">帖子名称</th>
                                <th data-field="image">图片</th>
                                <th data-field="sequence">排序</th>
                                <th data-field="state">是否激活</th>
                                <th data-formatter="operate" data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%--编辑banner----精选圈子--%>
        <div class="container-fluid hide" id="bannerEdit">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        圈子banner管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">圈子banner</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">编辑</a></li>
                    </ul>
                </div>
            </div>
            <div id="activity-create">
                <div class="row-fluid">
                    <form class="form-horizontal" role="form" id="bannerForm">
                        <input type="hidden" name="id" id="devaId">
                        <div class="control-group">
                            <label class="control-label">圈子名称</label>
                            <div class="controls">
                                <input type="text" class="span6 form-control" id="title" disabled/>
                            </div>
                        </div>

                        <div class="control-group" id="preImgWrap">
                            <label class="control-label">精选帖子原有封面图</label>
                            <div class="controls" id="preImage">

                            </div>
                        </div>

                        <div class="control-group" id="area">
                            <label class="control-label">推荐模块</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" name="area" value="1">首页热门圈子</label>
                                <label class="radio"><input type="radio" name="area" value="3">精选圈子</label>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">banner序列号</label>
                            <div class="controls col-xs-5">
                                <select id="homepageSequence" class="span6 hide" name="sequence">
                                </select>
                                <select id="circleSequence" class="span6 hide" name="sequence">
                                </select>
                            </div>
                        </div>

                        <div class="control-group form-group" id="imageWrap">
                            <label class="control-label">封面</label>
                            <div class="controls col-xs-5">
                                <input type="hidden" name="imageUrl" id="imageUrl">
                                <input id="lefile" type="file" class="hideInput" name="imageR">
                                <a class="btn btn-default" href="javascript:void (0)" id="photoCover" onclick="$('input[id=lefile]').click();">选择图片</a>
                                <div style="margin-top: 10px" id="imagesWrap" class="showImg">
                                    <img id="images" src="">
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label">推荐状态</label>
                            <div class="controls">
                                <label class="radio"><input type="radio" name="state" value="1">激活</label>
                                <label class="radio"><input type="radio" name="state" value="0">未激活</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <a class="btn btn-default" href="javascript:(0)" id="confirmDeva">确定</a>
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
<script type="text/javascript" src="../../js/banner/bannerCommon.js"></script>
<script type="text/javascript" src="../../js/banner/circlebanner.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
