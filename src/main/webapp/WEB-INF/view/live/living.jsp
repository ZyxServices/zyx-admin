<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>直播操作</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-直播操作" name="description"/>
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
                    <h3 style="margin: 0;display: inline-block">直播操作 </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <button class="create_live btn btn-default btn-lg ">创建直播分类</button>
                </div>
                <div class="live_manage">
                    <h3 style="margin: 0;display: inline-block">直播管理 </h3>
                    <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                    <button class="fl btn btn-default ">自动/手动排序优先</button>
                    <button id="remove" class="btn btn-danger" style="margin-left: 10px" disabled>
                        <i class="glyphicon glyphicon-remove"></i> 批量删除
                    </button>
                    <table id="live_table" class="table table-hover">


                    </table>
                </div>
            </div>
            <div class="create_liveType row-fluid form-horizontal bv-form">
                <h3 style="margin: 0;display: inline-block">创建直播分类 </h3>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>

                <div class="control-group form-group">
                    <label class="control-label " style="width: 58px;margin-right: 10px;text-align: left">创建名称</label>
                    <div class=" col-xs-5">
                        <input  id="lab" type="text"/>
                    </div>
                </div>

                <div class="control-group form-group">
                    <label class="control-label" style="width: 58px;margin-right: 10px;text-align: left">备注</label>
                    <div class=" col-xs-5">
                       <input id="desc" type="text"/>
                    </div>
                </div>

                <button onclick="operateEventclass.creatLive()" class="btn btn-danger">确定</button>
                <button onclick="window.location.reload();" class="btn btn-danger">返回</button>
                <h3 style="margin: 0;display: block">编辑直播分类 </h3>
                <HR style="border:1 dashed #987cb9;margin: 5px 0" width="100%" color=rgb(51, 51, 51) SIZE=1>
                <table id="editLive"></table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../public/common-footer.jsp"/>
<script type="text/javascript" src="../../js/public/dialog.js"></script>
<script type="text/javascript" src="../../js/live/live.js"></script>
</body>
</html>
