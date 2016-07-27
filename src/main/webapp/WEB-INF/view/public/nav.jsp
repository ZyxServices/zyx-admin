<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/13
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-sidebar nav-collapse collapse">
    <ul class="page-sidebar-menu">
        <li>
            <div class="sidebar-toggler hidden-phone"></div>
        </li>
        <li>
            <form class="sidebar-search">
                <div class="input-box">
                    <a href="javascript:;" class="remove"></a>
                    <input type="text" placeholder="Search..."/>
                    <input type="button" class="submit" value=" "/>
                </div>
            </form>
        </li>
        <li class="start home">
            <a href="<%=request.getContextPath()%>/home">
                <i class="icon-home"></i>
                <span class="title">首页</span>
                <span class="selected"></span>
            </a>
        </li>
        <%--活动--%>
        <li class="activity">
            <a href="javascript:;">
                <i class="icon-bookmark-empty"></i>
                <span class="title">活动</span>
                <span class="arrow"></span>
                <span class="selected hide"></span>
            </a>
            <ul class="sub-menu">
                <li class="list">
                    <a href="<%=request.getContextPath()%>/menu/activity/list">活动列表<span
                            class="selected"></span></a>
                </li>
                <li class="group">
                    <a href="<%=request.getContextPath()%>/menu/activity/group">组合活动</a>
                </li>
            </ul>
        </li>

        <%--直播--%>
        <li class="live">
            <a href="<%=request.getContextPath()%>/menu/live/living">
                <i class="icon-table"></i>
                <span class="title">直播</span>
                <span class="selected hide"></span>
            </a>
        </li>
        <%--动态--%>
        <li class="dynamic">
            <a href="<%=request.getContextPath()%>/menu/dynamic/dynamicIndex">
                <i class="icon-table"></i>
                <span class="title">动态</span>
                <span class="selected hide"></span>
            </a>
        </li>
        <%--圈子--%>
        <li class="circle">
            <a href="javascript:;">
                <i class="icon-briefcase"></i>
                <span class="title">圈子</span>
                <span class="arrow"></span>
                <span class="selected hide"></span>
            </a>
            <ul class="sub-menu">
                <li class="circlelist circlecreat">
                    <a href="<%=request.getContextPath()%>/menu/circle/circlelist">圈子列表<span
                            class="selected"></span></a>
                </li>
                <li class="circlepost postcreat">
                    <a href="<%=request.getContextPath()%>/menu/circle/circlepost">帖子列表</a>
                </li>
            </ul>
        </li>
        <%--商城--%>
        <li class="shop">
            <a href="javascript:;">
                <i class="icon-gift"></i>
                <span class="title">商城</span>
                <span class="arrow "></span>
                <span class="selected hide"></span>
            </a>
            <ul class="sub-menu">
                <li class="goods">
                    <a href="<%=request.getContextPath()%>/menu/shop/goods">商品</a>
                </li>
                <li class="order">
                    <a href="<%=request.getContextPath()%>/menu/shop/order">订单</a>
                </li>
            </ul>
        </li>
        <%--用户--%>
        <li class="appUser">
            <a href="javascript:void(0)">
                <i class="icon-cogs"></i>
                <span class="title">APP用户管理</span>
                <span class="arrow"></span>
                <span class="selected hide"></span>
            </a>
            <ul class="sub-menu">
                <li class="all-app-user">
                    <a href="<%=request.getContextPath()%>/menu/appUser/allAppUser">普通用户列表</a>
                </li>
                <li class="yrz-app-user">
                    <a href="<%=request.getContextPath()%>/menu/appUser/yrzAppUser">已认证用户列表</a>
                </li>
                <li class="dsh-app-user">
                    <a href="<%=request.getContextPath()%>/menu/appUser/dshAppUser">待审核用户列表</a>
                </li>
                <li class="official-app-user">
                    <a href="<%=request.getContextPath()%>/menu/appUser/officialAppUser">官方用户列表</a>
                </li>
            </ul>
        </li>
        <%--消息--%>
        <li class="message">
            <a href="<%=request.getContextPath()%>/menu/message/messageIndex">
                <i class="icon-gift"></i>
                <span class="title">消息</span>
                <span class="selected hide"></span>
            </a>
        </li>
        <%--banner推荐--%>
        <li class="banner">
            <a href="javascript:;">
                <i class="icon-cogs"></i>
                <span class="title">banner推荐</span>
                <span class="arrow "></span>
                <span class="selected hide"></span>
            </a>
            <ul class="sub-menu">
                <li class="activitybanner">
                    <a href="<%=request.getContextPath()%>/menu/banner/activitybanner">活动banner推荐</a>
                </li>
                <li class="livebanner">
                    <a href="<%=request.getContextPath()%>/menu/banner/livebanner">直播banner推荐</a>
                </li>
                <li class="circlebanner">
                    <a href="<%=request.getContextPath()%>/menu/banner/circlebanner">圈子banner推荐</a>
                </li>
                <li class="packagebanner">
                    <a href="<%=request.getContextPath()%>/menu/banner/packagebanner">背包banner推荐</a>
                </li>
                <li class="dynamicbanner">
                    <a href="<%=request.getContextPath()%>/menu/banner/dynamicbanner">动态banner推荐</a>
                </li>
            </ul>
        </li>
        <%--管理员管理--%>
        <li class="sys">
            <a href="javascript:;">
                <i class="icon-folder-open"></i>
                <span class="title">管理员管理</span>
                <span class="arrow "></span>
                <span class="selected hide"></span>
            </a>
            <ul class="sub-menu">
                <li class="admin">
                    <a href="<%=request.getContextPath()%>/menu/sys/admin">管理员列表</a>
                </li>
                <li class="jurisdiction">
                    <a href="<%=request.getContextPath()%>/menu/sys/jurisdiction">权限设置</a>
                </li>
            </ul>
        </li>

        <%--<li>
            <a class="active" href="javascript:;">
                <i class="icon-sitemap"></i>
                <span class="title">交易</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li><a href="<%=request.getContextPath()%>/transaction/order">订单查询</a></li>
                <li><a href="<%=request.getContextPath()%>/transaction/recharge">充值记录</a></li>
            </ul>
        </li>--%>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
