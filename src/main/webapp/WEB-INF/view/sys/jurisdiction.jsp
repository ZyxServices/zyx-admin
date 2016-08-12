<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/20
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限列表</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-权限列表" name="description"/>

    <meta content="体育家系统管理" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
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

        <!-- BEGIN PAGE CONTAINER-->
        <%--modal删除--%>
        <div id="jurisdiction-del" class="modal fade">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>权限等级删除</h3>
            </div>
            <div class="modal-body">
                该权限等级及该等级下的管理员数据，将被完全删除，不能再进行操作
            </div>
            <div class="modal-footer">
                <button class="btn btn-default">确定</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
        <%--权限列表--%>
        <div class="container-fluid" id="jurisdictionList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        权限列表
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">权限</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">列表</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="jurisdiction-list">

                <!-- BEGIN DASHBOARD STATS -->

                <div class="row-fluid margin-bottom-10">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createJurisdiction()">创建权限等级</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="jurisdiction-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">id</th>
                                <th data-field="name">权限等级</th>
                                <th data-field="jurisdiction">权限相亲</th>
                                <th data-field="remark">管理员名称</th>
                                <th data-formatter="operate">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>

        <%--权限等级创建--%>
        <div class="container-fluid hide" id="jurisdictionCreate">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        权限等级创建
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">权限等级</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">创建</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="jurisdiction-create">

                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">

                    <form action="/v1/role/" id="roleForm" method="post" enctype="multipart/form-data"
                          class="form-horizontal" novalidate="novalidate" role="form">
                        <div class="control-group form-group">
                            <label class="control-label col-xs-5">角色名称</label>
                            <div class="controls">
                                <%--<select class="span6 form-control">--%>
                                <%--<option>一级</option>--%>
                                <%--<option>二级</option>--%>
                                <%--<option>三级</option>--%>
                                <%--</select>--%>
                                <input type="text" id="roleName" name="roleName" class="span6"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">角色描述</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="roleDesc" name="roleDesc" class="span6"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <%--<div class="control-group">--%>
                        <%--<label class="control-label">该权限等级管理员</label>--%>
                        <%--<div class="controls">--%>
                        <%--<input type="text" class="span6 form-control"/>--%>
                        <%--<span class="help-inline">+</span>--%>
                        <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="control-group">--%>
                        <%--<label class="control-label">等级权限</label>--%>
                        <%--<div class="controls">--%>
                        <%--<div class="span6">--%>
                        <%--<select id="jurisdiction-select" multiple="multiple">--%>
                        <%--<option value="活动创建详情页" data-section="活动">活动创建详情页</option>--%>
                        <%--<option value="活动预览详情页" data-section="活动">活动预览详情页</option>--%>
                        <%--<option value="活动编辑详情页" data-section="活动">活动编辑详情页</option>--%>
                        <%--<option value="活动推荐弹出框" data-section="活动">活动推荐弹出框</option>--%>
                        <%--<option value="活动屏蔽弹出框" data-section="活动">活动屏蔽弹出框</option>--%>

                        <%--<option value="创建直播分类详情页" data-section="直播">创建直播分类详情页</option>--%>
                        <%--<option value="分类排序详情页" data-section="直播">分类排序详情页</option>--%>
                        <%--<option value="直播预览详情页" data-section="直播">直播预览详情页</option>--%>
                        <%--<option value="直播预览详情页" data-section="直播">直播编辑详情页</option>--%>
                        <%--<option value="直播推荐弹出框" data-section="直播">直播推荐弹出框</option>--%>
                        <%--<option value="直播预告弹出框" data-section="直播">直播预告弹出框</option>--%>
                        <%--<option value="直播屏蔽弹出框" data-section="直播">直播屏蔽弹出框</option>--%>
                        <%--<option value="直播删除弹出框" data-section="直播">直播删除弹出框</option>--%>

                        <%--<option value="发布语音动态" data-section="动态/发布动态详情页">发布语音动态</option>--%>
                        <%--<option value="发布视频动态" data-section="动态/发布动态详情页">发布视频动态</option>--%>
                        <%--<option value="动态预览详情页" data-section="动态">动态预览详情页</option>--%>
                        <%--<option value="动态编辑详情页" data-section="动态">动态编辑详情页</option>--%>
                        <%--<option value="动态推荐弹出框" data-section="动态">动态推荐弹出框</option>--%>
                        <%--<option value="动态屏蔽弹出框" data-section="动态">动态屏蔽弹出框</option>--%>
                        <%--<option value="动态删除弹出框" data-section="动态">动态删除弹出框</option>--%>

                        <%--<option value="创建圈子详情页" data-section="圈子">创建圈子详情页</option>--%>
                        <%--<option value="圈子预览详情页" data-section="圈子">圈子预览详情页</option>--%>
                        <%--<option value="圈子编辑详情页" data-section="圈子">圈子编辑详情页</option>--%>
                        <%--<option value="圈子推荐弹出框" data-section="圈子">圈子推荐弹出框</option>--%>
                        <%--<option value="圈子屏蔽弹出框" data-section="圈子">圈子屏蔽弹出框</option>--%>
                        <%--<option value="圈子删除弹出框" data-section="圈子">圈子删除弹出框</option>--%>
                        <%--<option value="帖子管理详情页" data-section="圈子">帖子管理详情页</option>--%>
                        <%--<option value="创建帖子详情页" data-section="圈子">创建帖子详情页</option>--%>
                        <%--<option value="创建圈子详情页" data-section="圈子">帖子编辑详情页</option>--%>
                        <%--<option value="帖子推荐弹出框" data-section="圈子">帖子推荐弹出框</option>--%>
                        <%--<option value="帖子屏蔽弹出框" data-section="圈子">帖子屏蔽弹出框</option>--%>
                        <%--<option value="帖子删除弹出框" data-section="圈子">帖子删除弹出框</option>--%>

                        <%--<option value="创建圈子详情页" data-section="商城">上传商品详情页</option>--%>
                        <%--<option value="创建商品分类" data-section="商城">创建商品分类</option>--%>
                        <%--<option value="商品分类排序详情页" data-section="商城">商品分类排序详情页</option>--%>
                        <%--<option value="商品预览详情页" data-section="商城">商品预览详情页</option>--%>
                        <%--<option value="商品编辑详情页" data-section="商城">商品编辑详情页</option>--%>
                        <%--<option value="商品推荐弹出框" data-section="商城">商品推荐弹出框</option>--%>
                        <%--</select>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="control-group">
                            <label class="control-label">角色权限选择</label>
                            <div class="controls">
                                <div class="span6">
                                    <select id="jurisdiction-select" multiple="multiple">
                                        <option value="menu:activity:list" data-section="活动">活动列表</option>
                                        <option value="menu:activity:group" data-section="活动">组合活动</option>

                                        <option value="menu:live:living" data-section="直播">直播</option>

                                        <option value="menu:dynamic:dynamicIndex" data-section="动态/发布动态详情页">动态</option>

                                        <option value="menu:circle:circlelist" data-section="圈子">圈子列表</option>
                                        <option value="menu:circle:circlepost" data-section="圈子">帖子列表</option>

                                        <option value="menu:shop:goods" data-section="商城">商品</option>
                                        <option value="menu:shop:order" data-section="商城">订单</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" id="createButton" class="btn" onclick="beginCreate()">确认创建
                                </button>
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

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>

<script src="../../js/app.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/sys/jurisdiction.js"></script>
<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>
