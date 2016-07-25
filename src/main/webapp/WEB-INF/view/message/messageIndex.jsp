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
  <title>消息操作</title>
  <meta charset="utf-8"/>
  <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
  <meta content="体育家-消息操作" name="description"/>
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
        <div class="live_operate">
          <h3 style="margin: 0;display: inline-block">消息操作</h3>
          <%--<button class="fr btn btn-default btn-lg" style="margin-top: 10px">查看已认证用户</button>--%>
          <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
          <button class="create_live btn btn-default btn-lg ">发布动态</button>
        </div>
        <div class="live_manage">
          <h3 style="margin: 0;display: inline-block">消息管理 </h3>
          <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
          <button class="fl btn btn-default ">自动/手动排序优先</button>
          <button id="remove" class="btn btn-danger" style="margin-left: 10px" disabled>
            <i class="glyphicon glyphicon-remove"></i> 批量删除
          </button>
          <table id="live_table"></table>
        </div>
      </div>
      <div class="create_liveType">
        <h3 style="margin: 0;display: inline-block">发布动态 </h3>
        <h5 style="margin: 0;display: inline-block">图文动态 </h5>
        <h5 style="margin: 0;display: inline-block">语音动态 </h5>
        <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
        <input style="width: 40%; height: 20%" type="text"/>
        <input style="display: block" type="file"/>
        <button class="btn btn-danger">
          <发布></发布>
        </button>
        <button onclick="window.location.reload();" class="btn btn-danger">返回</button>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../public/common-footer.jsp"/>
</body>
<script type="text/javascript" src="../../js/message/message.js"></script>