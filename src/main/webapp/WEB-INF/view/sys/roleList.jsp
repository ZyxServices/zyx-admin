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
                        <table id="role-list-table" class="table table-hover"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true">
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

                    <form action="/v1/role/" id="roleCreateForm" method="post" class="form-horizontal" role="form">
                        <div class="control-group form-group">
                            <label class="control-label">角色名称</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="roleCreateName" name="roleName" class="span6"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">角色描述</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="roleCreateDesc" name="roleDesc" class="span6"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">角色权限选择</label>

                            <div class="controls col-xs-5">
                                <div class="span6">
                                    <select id="menu-create-select" multiple="multiple">
                                        <option value="menu:activity:list" data-section="活动">活动列表</option>
                                        <option value="menu:activity:group" data-section="活动">组合活动</option>

                                        <option value="menu:live:living" data-section="直播">直播</option>

                                        <option value="menu:dynamic:dynamicIndex" data-section="动态">动态</option>

                                        <option value="menu:circle:circleclassify" data-section="圈子">圈子类别</option>
                                        <option value="menu:circle:circlelist" data-section="圈子">圈子列表</option>
                                        <option value="menu:circle:circlepost" data-section="圈子">帖子列表</option>

                                        <option value="menu:shop:goods" data-section="商城">商品</option>
                                        <option value="menu:shop:order" data-section="商城">订单</option>

                                        <option value="menu:appUser:allAppUser" data-section="用户管理">普通用户列表</option>
                                        <option value="menu:appUser:yrzAppUser" data-section="用户管理">已认证用户列表</option>
                                        <option value="menu:appUser:dshAppUser" data-section="用户管理">待审核用户列表</option>
                                        <option value="menu:appUser:officialAppUser" data-section="用户管理">官方用户列表</option>

                                        <option value="menu:message:messageIndex" data-section="消息">消息</option>

                                        <option value="menu:banner:activitybanner" data-section="banner推荐">活动banner推荐</option>
                                        <option value="menu:banner:livebanner" data-section="banner推荐">直播banner推荐</option>
                                        <option value="menu:banner:circlebanner" data-section="banner推荐">圈子banner推荐</option>
                                        <option value="menu:banner:packagebanner" data-section="banner推荐">背包banner推荐</option>
                                        <option value="menu:banner:dynamicbanner" data-section="banner推荐">动态banner推荐</option>

                                    </select>
                                </div>
                                <span class="help-inline required">*</span>
                                <div style="clear: both"></div>
                                <input type="text" class="hideInput" id="menuCreatePerm" name="menuPerm">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button id="createButton" class="btn" onclick="beginCreate()">确认创建
                                </button>
                                <a href="javascript:void(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
                            </div>
                        </div>
                    </form>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>

        <%--权限等级编辑--%>
        <div class="container-fluid hide" id="jurisdictionEdit">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        权限等级编辑
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">权限等级</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">编辑</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="jurisdiction-edit">

                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">

                    <form action="/v1/role/" id="roleEditForm" method="post"
                          class="form-horizontal" novalidate="novalidate" role="form">
                        <input type="hidden" id="editRoleId" name="editRoleId">
                        <div class="control-group form-group">
                            <label class="control-label">角色名称</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="roleEditName" name="roleName" disabled class="span6"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">角色描述</label>
                            <div class="controls col-xs-5">
                                <input type="text" id="roleEditDesc" name="roleDesc" class="span6"/>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="control-group form-group">
                            <label class="control-label">角色权限选择</label>

                            <div class="controls col-xs-5">
                                <div class="span6">
                                    <select id="menu-edit-select" multiple="multiple">
                                        <option value="menu:activity:list" data-section="活动" data-index="1">活动列表</option>
                                        <option value="menu:activity:group" data-section="活动" data-index="2">组合活动</option>

                                        <option value="menu:live:living" data-section="直播" data-index="3">直播</option>

                                        <option value="menu:dynamic:dynamicIndex" data-section="动态" data-index="4">动态</option>

                                        <option value="menu:circle:circleclassify" data-section="圈子">圈子类别</option>
                                        <option value="menu:circle:circlelist" data-section="圈子" data-index="5">圈子列表</option>
                                        <option value="menu:circle:circlepost" data-section="圈子" data-index="6">帖子列表</option>

                                        <option value="menu:shop:goods" data-section="商城" data-index="7">商品</option>
                                        <option value="menu:shop:order" data-section="商城" data-index="8">订单</option>

                                        <option value="menu:appUser:allAppUser" data-section="用户管理">普通用户列表</option>
                                        <option value="menu:appUser:yrzAppUser" data-section="用户管理">已认证用户列表</option>
                                        <option value="menu:appUser:dshAppUser" data-section="用户管理">待审核用户列表</option>
                                        <option value="menu:appUser:officialAppUser" data-section="用户管理">官方用户列表</option>

                                        <option value="menu:message:messageIndex" data-section="消息">消息</option>

                                        <option value="menu:banner:activitybanner" data-section="banner推荐">活动banner推荐</option>
                                        <option value="menu:banner:livebanner" data-section="banner推荐">直播banner推荐</option>
                                        <option value="menu:banner:circlebanner" data-section="banner推荐">圈子banner推荐</option>
                                        <option value="menu:banner:packagebanner" data-section="banner推荐">背包banner推荐</option>
                                        <option value="menu:banner:dynamicbanner" data-section="banner推荐">动态banner推荐</option>
                                        <option value="menu:banner:userbanner" data-section="banner推荐">用户banner推荐</option>
                                    </select>
                                </div>
                                <span class="help-inline required">*</span>
                                <div style="clear: both"></div>
                                <input type="text" class="hideInput"  id="menuEditPerm" name="menuPerm">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" id="editButton" class="btn" onclick="beginEdit()">确认修改
                                </button>
                                <a href="javascript:void(0)" class="btn btn-default" onclick="window.location.reload();">返回</a>
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
<script type="text/javascript" src="../../js/sys/role-list.js"></script>
<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>

