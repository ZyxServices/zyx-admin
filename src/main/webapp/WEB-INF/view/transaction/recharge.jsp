<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/15
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单列表</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-充值记录" name="description"/>
    <meta content="" name="author"/>
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../css/self-style/style.css"/>
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>
<div class="page-container">
    <jsp:include page="../public/nav.jsp"/>
    <div class="page-content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <jsp:include page="../public/color-panel.jsp"/>
                    <h3 class="page-title">
                        充值列表
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">充值</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">列表</a></li>
                        <li class="pull-right no-text-shadow">
                            <div id="dashboard-report-range"
                                 class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive"
                                 data-tablet="" data-desktop="tooltips" data-placement="top"
                                 data-original-title="Change dashboard date range">
                                <i class="icon-calendar"></i>
                                <span></span>
                                <i class="icon-angle-down"></i>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="activity-list">
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="order-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">id</th>
                                <th data-field="username">用户名称</th>
                                <th data-field="probateStatus">认证状态</th>
                                <th data-field="probateInfo">认证信息</th>
                                <th data-field="concernNum">关注人数</th>
                                <th data-field="fansNum">粉丝人数</th>
                                <th data-field="dynamicNum">动态数量</th>
                                <th data-field="goldNum">金币数量</th>
                                <th data-field="registerTime">注册时间</th>
                                <th data-field="contactWay">联系电话（联系时间）</th>
                                <th data-field="sex">性别</th>
                                <th data-field="birthday">生日</th>
                                <th data-field="location">所在地</th>
                                <th data-formatter="operate">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>
<script src="../js/app.js" type="text/javascript"></script>
<script src="../js/index.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/transaction/recharge.jsp"></script>
<script>
    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins
    });
</script>
</body>
</html>
