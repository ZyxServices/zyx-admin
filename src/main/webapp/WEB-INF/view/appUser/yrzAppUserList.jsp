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
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        用户管理
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">已认证用户管理</a>
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
        </div>
    </div>
</div>
<!-- 用户推荐开始 -->
<!-- 模态框（Modal） -->

<div class="modal fade" id="appUserRecommend" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>用户推荐</h3></div>
    <div class="modal-body">
        <form class="form-horizontal" id="devaForm">
            <input  name="modelId" type="hidden" id="modelId">
            <div class="control-group"><label class="control-label">用户昵称</label>
                <div class="controls"><span class="help-inline" id="devaUserNickname"></span></div>
            </div>
            <div class="control-group"><label class="control-label">用户头像</label>
                <div class="controls showImg"><img id="devaUserAvatar" src="http://image.tiyujia.com/group1/M00/00/00/052YyFeIeIqASfONAAAJDB3enOc610.jpg">
                </div>
            </div>
            <div class="control-group"><label class="control-label">首页更多用户推荐</label>
                <div class="controls"><select id="sequence" name="sequence">

                </select></div>
            </div>
            <div class="control-group"><label class="control-label">推荐状态</label>
                <div class="controls">
                    <label class="radio"><input type="radio" checked value="1" name="state">激活</label>
                    <label class="radio"><input type="radio" value="0" name="state">未激活</label>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button class="btn btn-default" id="devaButton" onclick="beginDeva()">确定</button>
        <button class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</div>
<!-- 用户推荐结束 -->
<jsp:include page="../public/common-footer.jsp"/>
</body>
<script type="text/javascript" src="../../js/appUser/appUserCommon.js"></script>
<script type="text/javascript" src="../../js/appUser/yrzAppUser.js"></script>