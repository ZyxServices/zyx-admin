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
        <div class="container-fluid" style="padding-top: 20px ">
            <div class="live_index">
                <div class="live_manage">
                    <h3 style="margin: 0;display: inline-block">用户管理 - 待审核用户列表</h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
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
            <div class="create_liveType">
                <h3 style="margin: 0;display: inline-block">创建用户 </h3>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                <form class="form-horizontal" role="form">
                    <div class="control-group">
                        <label class="control-label">账号</label>

                        <div class="controls">
                            <input type="text" class="span6 m-wrap"/>
                            <span class="help-inline">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">密码</label>

                        <div class="controls">
                            <input type="text" class="span6 m-wrap"/>
                            <span class="help-inline">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">头像</label>

                        <div class="controls">
                            <input type="file">
                            <span class="help-inline">只能上传一张图片</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">性别</label>

                        <div class="controls">
                            <select class="span6 m-wrap">
                                <option>男</option>
                                <option>女</option>
                            </select>
                            <span class="help-inline">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">年龄</label>

                        <div class="controls">
                            <input type="text" class="span6 m-wrap"/>
                            <span class="help-inline">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">所在地</label>

                        <div class="controls">
                            <input type="text" class="span6 m-wrap"/>
                            <span class="help-inline">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">认证信息</label>

                        <div class="controls">
                            <input type="text" class="span6 m-wrap"/>
                            <span class="help-inline">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">认证资料</label>

                        <div class="controls">
                            <input type="file">
                            <span class="help-inline">上传图片</span>
                        </div>
                    </div>
                    <button class="btn">确认创建</button>
                </form>

            </div>
        </div>
    </div>
</div>
<jsp:include page="../public/common-footer.jsp"/>
</body>
<script type="text/javascript" src="../../js/appUser/appUserCommon.js"></script>
<script type="text/javascript" src="../../js/appUser/dshAppUser.js"></script>