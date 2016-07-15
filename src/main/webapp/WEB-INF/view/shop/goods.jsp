<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>商品操作</title>
  <meta charset="utf-8"/>
  <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
  <meta content="体育家-商品操作" name="description"/>
  <meta content="" name="author"/>
  <jsp:include page="../public/common-styles.jsp"/>
</head>
<jsp:include page="../public/common-header.jsp"/>
<div class="container-fluid" style="padding-top: 20px ">
  <div class="live_index">
    <div class="live_operate">
      <h3 style="margin: 0;display: inline-block">商品操作 </h3>
      <button class="fr btn btn-default btn-lg" style="margin-top: 10px">查看分类</button>

      <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
      <button class="create_live btn btn-default btn-lg ">上传商品</button>
      <button class="create_live btn btn-default btn-lg ">分类编辑</button>
    </div>
    <div class="live_manage">
      <h3 style="margin: 0;display: inline-block">直播管理 </h3>
      <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
      <button class="fl btn btn-default ">自动/手动排序优先</button>
      <button id="remove" class="btn btn-danger" style="margin-left: 10px" disabled>
        <i class="glyphicon glyphicon-remove"></i> 批量删除
      </button>
      <table id="live_table"></table>
    </div>
  </div>
  <div class="create_liveType">
    <h3 style="margin: 0;display: inline-block">上传商品 </h3>
    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
    <span>创建名称</span>
    <input type="text"/>
    <button>确定</button>
  </div>
</div>
<jsp:include page="../public/common-footer.jsp"/>
<script type="text/javascript" src="../js/shop/goods.js"></script>