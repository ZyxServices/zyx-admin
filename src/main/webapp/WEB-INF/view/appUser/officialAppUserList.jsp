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
                        用户管理<small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">官方用户管理</a>
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
            <%--官方用户编辑--%>
            <div class="create_liveType row-fluid">
                <form class="form-horizontal" role="form" id="editUserForm" enctype="multipart/form-data">
                    <div class="control-group form-group">
                        <label class="control-label">昵称</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="nickname" id="nickname" placeholder="输入昵称"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">电话</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="phone" id="phone" placeholder="输入电话号码"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">密码</label>

                        <div class="controls col-xs-5">
                            <input type="text" class="span6" name="password" id="password" placeholder="输入密码"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">头像</label>

                        <div class="controls">
                            <input type="file" class="hideInput" name="avatar" id="avatar">
                            <a class="btn btn-default" href="javascript:void (0)" id="photoCover" onclick="$('input[id=avatar]').click();">选择文件</a>
                            <div style="margin-top: 10px" id="imagesWrap">
                                <img id="avatarImg" src="">
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">性别</label>

                        <div class="controls">
                            <select class="span6" name="sex" id="sex">
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">官方账号</label>
                        <div class="controls">
                            <select class="span6" name="official" id="official">
                                <option value="1">是</option>
                                <option value="0" selected>否</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">所在地</label>
                        <div class="controls">
                            <input type="text" class="span6" name="address" id="address" placeholder="请输入地址"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">认证信息</label>
                        <div class="controls">
                            <input type="text" class="span6" name="authInfo" id="authInfo" placeholder="请输入你的认证信息"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">认证资料</label>
                        <div class="controls">
                            <input type="file" class="hideInput" name="authFile" id="authFile">
                            <a class="btn btn-default" href="javascript:void (0)" id="authFileCover" onclick="$('input[id=authFile]').click();">上传资料</a>
                            <span class="help-inline">最多上传3张照片</span>
                            <div style="margin-top: 10px" id="authImgWrap">
                                <img id="authImg" src="">
                            </div>
                        </div>

                    </div>
                    <button type="button" id="createButton" class="btn">确认创建</button>
                    <button type="button" class="btn" onclick="window.location.reload();">返回</button>
                </form>
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
        <form class="form-horizontal" id="devaForm" enctype="multipart/form-data">
            <input name="devaId" type="hidden" value="6">
            <input id="types" name="types" value="5" type="hidden">
            <input id="activation" name="activation" value="1" type="hidden">
            <div class="control-group"><label class="control-label">用户昵称</label>
                <div class="controls"><span class="help-inline" id="devaUserNickname"></span></div>
            </div>
            <div class="control-group"><label class="control-label">用户头像</label>
                <div class="controls"><img id="devaUserAvatar"src="http://image.tiyujia.com/group1/M00/00/00/052YyFeIeIqASfONAAAJDB3enOc610.jpg">
                </div>
            </div>
            <div class="control-group"><label class="control-label">首页更多用户推荐</label>
                <div class="controls"><select class="span6 m-wrap" id="sequence" name="sequence">
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
                </select></div>
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
<script type="text/javascript" src="../../js/appUser/officialAppUser.js"></script>