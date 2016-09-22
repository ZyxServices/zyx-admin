<%-- Created by IntelliJ IDEA. User: 文楷 Date: 2016/7/15 Time: 14:10 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<head>
    <title>动态操作</title>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="体育家-动态操作" name="description" />
    <meta content="" name="author" />
    <jsp:include page="../public/common-styles.jsp" />
    <link href="<%=request.getContextPath()%>/css/zyUpload.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../../css/tiyujia/style.css"/>
</head>

<body class="page-header-fixed">
<jsp:include page="../public/header.jsp" />
<div class="modal fade" id="upload"  aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-body">
        <p>推荐上传中...</p>
    </div>
</div>
<div class="page-container">
    <jsp:include page="../public/nav.jsp" />
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
                    <h3 style="margin: 0;display: inline-block">动态操作</h3>
                    <%--<button class="fr btn btn-default btn-lg" style="margin-top: 10px">查看已认证用户</button>--%>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <button class="create_live btn btn-default btn-lg ">发布动态</button>
                </div>
                <div class="live_manage">
                    <h3 style="margin: 0;display: inline-block">直播管理 </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <button class="fl btn btn-default " onclick="initTable()">自动/手动排序优先</button>
                    <button id="remove" class="btn btn-danger" style="margin-left: 10px" disabled>
                        <i class="glyphicon glyphicon-remove"></i> 批量删除
                    </button>
                    <table id="dynamic_table"></table>
                </div>
            </div>
            <div class="create_liveType">
                <h3 style="margin: 0;display: inline-block">发布动态 </h3>
                <h5 style="margin: 0;display: inline-block">图文动态 </h5>
                <h5 style="margin: 0;display: inline-block">语音动态 </h5>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                <%--</button>--%>
                <div id="demo" class="demo">
                    <form class="form-horizontal" role="form" id="createDynamicForm" enctype="multipart/form-data">
                        <div class="control-group form-group officeUser">
                            <label class="control-label " style="width: 100px;margin-right: 10px;text-align: left">选择官方账户户</label>
                            <div class="">
                                <select id="choiceUser" name="createId" class="span3">
                                </select>
                            </div>
                        </div>
                        <div style="font-size: 13px;height: 25px">文字内容</div>
                        <%--<input style="width: 40%; height: 20%; margin-bottom: 15px" name="content" type="text" />--%>
                        <textarea id="dynamicContent" style="resize: none; width: 400px;height: 150px;max-width: 400px;max-height: 150px;margin-bottom: 10px" name="content" rows="5" cols="1000"></textarea>
                        <input type="text" name="type" value="1" style="display: none;" />
                        <input type="text" name="visible" value="1" style="display: none;" />
                        <%--<input type="text" name="createId" value="12" style="display: none;" />--%>
                        <%--<input name="imgFile" style="display: block" type="file" />--%>
                        <%--<input id="imgFile" name="imgFile" style="display: block" type="file" multiple="multiple" accept="image/*" />--%>
                        <input type="hidden" id="imgFileUrl" name="imgFileUrl" value="">
                        <%--<input name="imgFile" style="di splay: block" type="file" multiple="multiple" accept="image/*" />--%>
                        <div style="margin-top: 10px" id="imagesWrap">
                            <img id="images" src="">
                        </div>
                        <button id="DynamicSubmit" onclick="operateEventssssss.createDynamic(this,0)" style="display: none" class=" btn btn-danger">发布</button>
                        <%--<button onclick="operateEventssssss.createDynamic(this)" class="dynamicEdit btn btn-danger">--%>
                        <%--确认修改--%>
                        <%--</button>--%>
                    </form>
                    <span>图片内容</span>
                    <div id="dynamicImg"></div>
                </div>
            </div>
            <div class="dynamicPreview">
                <%--<h3 style="margin: 0;display: inline-block">动态预览</h3>--%>
                <%--<HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>--%>
                <h3 style="margin: 0;display: inline-block">动态发起人</h3>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="20%" color=rgb(51, 51, 51) SIZE=1>
                <div class="username"></div>
                <h3 style="margin: 0;display: inline-block">动态内容</h3>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="20%" color=rgb(51, 51, 51) SIZE=1>
                <div class="topicContent"></div>
                <div class="dynamicPic">
                    <img style="max-width: 13%" src="" alt="" />
                </div>
                <button onclick="window.location.reload();" class="btn btn-danger">返回</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../public/common-footer.jsp" />
</body>
<script type="text/javascript" src="../../js/uploadImg/zyUpload.js"></script>
<script type="text/javascript" src="../../js/dynamic/dynamicIndex.js"></script>