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
    <link rel="stylesheet" href="../../css/self-style/style.css" />
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
                <div class="live_operate">
                    <h3 style="margin: 0;display: inline-block">用户操作 </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <button class="create_live btn btn-default btn-lg ">创建用户</button>
                </div>
                <div class="live_manage">
                    <h3 style="margin: 0;display: inline-block">用户管理 - 用户列表</h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <button class="fl btn btn-default ">自动/手动排序优先</button>
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
                <form class="form-horizontal" role="form" id="createAppUserForm" enctype="multipart/form-data">
                    <div class="control-group form-group">
                        <label class="control-label">账号</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="phone" id="phone"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">密码</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="password" id="password"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">头像</label>

                        <div class="controls">
                            <input type="file" name="avatar" id="avatar">
                            <span class="help-inline required">只能上传一张图片</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">性别</label>

                        <div class="controls">
                            <select class="span6" name="sex" id="sex">
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                            <%--<span class="help-inline">*</span>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">官方账号</label>

                        <div class="controls">
                            <select class="span6" name="official" id="official">
                                <option value="1">是</option>
                                <option value="0" selected>否</option>
                            </select>
                            <%--<span class="help-inline">*</span>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">所在地</label>

                        <div class="controls">
                            <input type="text" class="span6" name="address" id="address"/>
                            <%--<span class="help-inline">*</span>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">认证信息</label>

                        <div class="controls">
                            <input type="text" class="span6" name="authInfo" id="authInfo"/>
                            <%--<span class="help-inline">*</span>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">认证资料</label>

                        <div class="controls">
                            <input type="file" name="authFile">
                            <span class="help-inline">上传图片</span>
                        </div>

                    </div>
                    <button type="button" id="createButton" class="btn" onclick="beginCreate()">确认创建
                    </button>
                    <button type="button" class="btn" onclick="backToUsers()">返回</button>
                </form>

            </div>
        </div>
    </div>
</div>

<!-- 用户推荐开始 -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="appUserRecommend" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog">
            <form action="/v1/deva/queryActivity" id="devaForm" method="post" enctype="multipart/form-data"
                  class="form-horizontal" novalidate="novalidate" role="form">
            <div class="modal-content">
                <input id="types" name="types" value="5" type="hidden">
                <input id="devaId" name="devaId" type="hidden">
                <input id="activation" name="activation" value="1" type="hidden">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        用户推荐
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="control-group">
                        <label class="control-label">用户昵称</label>
                        <div class="controls" id="devaUserNickname"></div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">用户头像</label>
                        <div class="controls">
                            <img id="devaUserAvatar" src="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">首页更多用户推荐</label>
                        <div class="controls">
                            <select class="span6 m-wrap" id="sequence" name="sequence">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option>5</option>
                                <option>6</option>
                                <option>7</option>
                                <option>8</option>
                                <option>9</option>
                                <option>10</option>
                                <option>11</option>
                                <option>12</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button id="devaButton" type="button" class="btn btn-primary" onclick="beginDeva()">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
            </form>
        </div><!-- /.modal -->

</div>
<!-- 用户推荐结束 -->
<jsp:include page="../public/common-footer.jsp"/>
</body>
<script type="text/javascript" src="../../js/appUser/appUserCommon.js"></script>
<script type="text/javascript" src="../../js/appUser/allAppUser.js"></script>