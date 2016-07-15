<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/14
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单列表</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="体育家-订单列表" name="description"/>
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
                        订单列表
                        <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0)">订单</a>
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
                                <th data-field="orderNum">订单号</th>
                                <th data-field="image">商品图片</th>
                                <th data-field="goodsNo">货号</th>
                                <th data-field="costPrice">成本价</th>
                                <th data-field="price">单价/金币</th>
                                <th data-field="purchaseQuantity">购买量</th>
                                <th data-field="buyers">买家</th>
                                <th data-field="actualCash">实际现金</th>
                                <th data-field="actualGold">实际金币</th>
                                <th data-field="netQrofit">净利润</th>
                                <th data-field="tradeTime">成交时间</th>
                                <th data-field="tradeState">交易状态</th>
                                <th data-field="comment">评价</th>
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
<script type="text/javascript" src="../js/transaction/order.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins
    });
</script>
</body>
</html>
