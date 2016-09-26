<%--
  Created by IntelliJ IDEA.
  User: 文楷
  Date: 2016/7/15
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户操作</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-用户操作" name="description"/>
    <meta content="" name="author"/>
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css" />
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>Widget Settings</h3>
            </div>
            <div class="modal-body">
            </div>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        用户管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">待审核用户管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#" id="listType">用户列表</a></li>
                    </ul>
                </div>
            </div>
            <div class="live_index">
                <div class="live_manage">
                    <table id="app_user_table" class="table table-hover"
                           data-pagination="true"
                           data-show-refresh="true"
                           data-show-toggle="true"
                           data-showColumns="true"
                           data-detail-view="true"
                           data-detail-formatter="detailFormatter">
                    </table>
                </div>
            </div>
            <%--审核详情--%>
            <div class="row-fluid hide" id="appUserAuth">
                <form class="form-horizontal" id="authForm" role="form">
                    <input type="hidden" id="authId">

                    <div class="control-group form-group">
                        <label class="control-label">真实姓名</label>
                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="authName" id="authName" disabled/>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">身份证号码</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="authIDCard" id="authIDCard" disabled/>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">手机号</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="authMob" id="authMob" disabled/>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">手持身份证照片</label>

                        <div class="controls col-xs-5 showImg">
                            <img id="cardImg" src="">
                        </div>
                    </div>

                    <hr>
                    <h3>高级选项</h3>

                    <div class="control-group">
                        <label class="control-label">认证标签</label>
                        <div class="controls">
                            <input type="text" class="span6" name="authInfo" id="authInfo" disabled/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">工作证明照片</label>
                        <div class="controls showImg">
                            <img id="workImg" src="">
                        </div>
                    </div>
                </form>

                <div class="margin-bottom-25">
                    <button id="authSuccess" class="btn">审核通过</button>
                    <button class="btn" id="authFail">审核不通过</button>
                    <button type="button" class="btn" onclick="window.location.reload();">返回</button>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="../public/common-footer.jsp"/>
</body>
<script type="text/javascript" src="../../js/appUser/appUserCommon.js"></script>
<script type="text/javascript" src="../../js/appUser/dshAppUser.js"></script>