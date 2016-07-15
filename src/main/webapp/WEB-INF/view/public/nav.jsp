<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/13
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->

    <ul class="page-sidebar-menu">

        <li>

            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

            <div class="sidebar-toggler hidden-phone"></div>

            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

        </li>

        <li>

            <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->

            <form class="sidebar-search">

                <div class="input-box">

                    <a href="javascript:;" class="remove"></a>

                    <input type="text" placeholder="Search..." />

                    <input type="button" class="submit" value=" " />

                </div>

            </form>

            <!-- END RESPONSIVE QUICK SEARCH FORM -->

        </li>

        <li class="start active ">

            <a href="<%=request.getContextPath()%>/login/in">

                <i class="icon-home"></i>

                <span class="title">首页</span>

                <span class="selected"></span>

            </a>

        </li>

        <li class="">

            <a href="javascript:;">

                <i class="icon-cogs"></i>

                <span class="title">移动端首页</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="<%=request.getContextPath()%>/homepage/banner">

                        首页轮播图</a>

                </li>

                <li >

                    <a href="#">

                        首页推荐活动位</a>

                </li>

            </ul>

        </li>

        <li class="">

            <a href="">

                <i class="icon-bookmark-empty"></i>

                <span class="title">活动</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="<%=request.getContextPath()%>/activity/list">

                        发布活动</a>

                </li>

                <li >

                    <a href="#">

                        审核活动</a>

                </li>


            </ul>

        </li>

        <li class="" >

            <a href="<%=request.getContextPath()%>/live/living">

                <i class="icon-table"></i>

                <span class="title">直播</span>

                <span class="arrow "></span>

            </a>

            <%--<ul class="sub-menu">--%>

                <%--<li >--%>

                    <%--<a href="">--%>

                        <%--图文直播列表</a>--%>

                <%--</li>--%>

                <%--<li >--%>

                    <%--<a href="#">--%>

                        <%--视频直播列表</a>--%>

                <%--</li>--%>


            <%--</ul>--%>

        </li>

        <li class="">

            <a href="javascript:;">

                <i class="icon-briefcase"></i>

                <span class="title">圈子</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">
                <li >
                    <a href="<%=request.getContextPath()%>/circle/circlelist">

                        <i class="icon-time"></i>

                        圈子列表</a>

                </li>
     <%--           <li>
                    <a href="<%=request.getContextPath()%>/circle/circlecreat">
                        <i class="icon-time"></i>

                        圈子列表</a>

                </li>--%>

                <li >

                    <a href="#">

                        <i class="icon-cogs"></i>

                        圈子审核</a>

                </li>

            </ul>

        </li>

        <li class="">

            <a href="javascript:;">

                <i class="icon-gift"></i>

                <span class="title">商城</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="<%=request.getContextPath()%>/shop/goods">

                        商品</a>

                </li>

                <li >

                    <a href="<%=request.getContextPath()%>/shop/order">

                        订单</a>

                </li>


            </ul>

        </li>

        <li>

            <a class="active" href="javascript:;">

                <i class="icon-sitemap"></i>

                <span class="title">交易</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li>

                    <a href="javascript:;">

                        订单

                        <span class="arrow"></span>

                    </a>

                    <ul class="sub-menu">

                        <li><a href="<%=request.getContextPath()%>/transaction/order">订单查询</a></li>

                        <li><a href="#">Sample Link 2</a></li>

                        <li><a href="#">Sample Link 3</a></li>

                    </ul>

                </li>

                <li>

                    <a href="javascript:;">

                        账户

                        <span class="arrow"></span>

                    </a>

                    <ul class="sub-menu">

                        <li><a href="#">充值记录</a></li>

                        <li><a href="#">账户余额</a></li>

                        <li><a href="#">个人账户余额</a></li>

                    </ul>

                </li>


            </ul>

        </li>

        <li>

            <a href="javascript:;">

                <i class="icon-folder-open"></i>

                <span class="title">系统</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li>

                    <a href="javascript:;">

                        <i class="icon-cogs"></i>

                        用户

                        <span class="arrow"></span>

                    </a>

                    <ul class="sub-menu">

                        <!-- <li>

                            <a href="javascript:;">

                            <i class="icon-user"></i>

                            Sample Link 1

                            <span class="arrow"></span>

                            </a>

                            <ul class="sub-menu">

                                <li><a href="#"><i class="icon-remove"></i> Sample Link 1</a></li>

                                <li><a href="#"><i class="icon-pencil"></i> Sample Link 1</a></li>

                                <li><a href="#"><i class="icon-edit"></i> Sample Link 1</a></li>

                            </ul>

                        </li> -->

                        <li><a href="<%=request.getContextPath()%>/syuser/list"><i class="icon-user"></i>  用户列表</a></li>

                        <li><a href="<%=request.getContextPath()%>/syuser/goAdd"><i class="icon-external-link"></i> 添加用户</a></li>


                    </ul>

                </li>

                <li>

                    <a href="javascript:;">

                        <i class="icon-globe"></i>

                        角色

                        <span class="arrow"></span>

                    </a>

                    <ul class="sub-menu">

                        <li><a href="<%=request.getContextPath()%>/sysrole/list"><i class="icon-user"></i>  角色列表</a></li>

                        <li><a href="#"><i class="icon-external-link"></i> 添加角色</a></li>

                        <li><a href="#"><i class="icon-bell"></i> 角色授权</a></li>

                    </ul>

                </li>

                <li>

                    <a href="javascript:;">

                        <i class="icon-globe"></i>

                        标签

                        <span class="arrow"></span>

                    </a>

                    <ul class="sub-menu">

                        <li><a href="<%=request.getContextPath()%>/sysmenu/list" ><i class="icon-cogs"></i>  标签列表</a></li>

                        <li><a href="#"><i class="icon-external-link"></i> 添加标签</a></li>


                    </ul>

                </li>

            </ul>

        </li>

        <li class="">

            <a href="javascript:;">

                <i class="icon-gift"></i>

                <span class="title">消息</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="#">

                        发送系统消息</a>

                </li>

                <li >

                    <a href="#">

                        历史记录</a>

                </li>


            </ul>

        </li>

        <li class="">

            <a href="javascript:;">

                <i class="icon-gift"></i>

                <span class="title">微信平台</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="#">

                        预留功能</a>

                </li>

                <li >

                    <a href="#">

                        预留功能</a>

                </li>


            </ul>

        </li>

        <li class="">

            <a href="javascript:;">

                <i class="icon-gift"></i>

                <span class="title">评论</span>

                <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="#">

                        关键字库</a>

                </li>

                <li >

                    <a href="#">

                        评论管理</a>

                </li>


            </ul>

        </li>


        <!-- <li class="">

            <a href="javascript:;">

            <i class="icon-user"></i>

            <span class="title">Login Options</span>

            <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="login.html">

                    Login Form 1</a>

                </li>

                <li >

                    <a href="login_soft.html">

                    Login Form 2</a>

                </li>

            </ul>

        </li> -->

        <!-- <li class="">

            <a href="javascript:;">

            <i class="icon-th"></i>

            <span class="title">Data Tables</span>

            <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="table_basic.html">

                    Basic Tables</a>

                </li>

                <li >

                    <a href="table_responsive.html">

                    Responsive Tables</a>

                </li>

                <li >

                    <a href="table_managed.html">

                    Managed Tables</a>

                </li>

                <li >

                    <a href="table_editable.html">

                    Editable Tables</a>

                </li>

                <li >

                    <a href="table_advanced.html">

                    Advanced Tables</a>

                </li>

            </ul>

        </li> -->

        <!-- <li class="">

            <a href="javascript:;">

            <i class="icon-file-text"></i>

            <span class="title">Portlets</span>

            <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="portlet_general.html">

                    General Portlets</a>

                </li>

                <li >

                    <a href="portlet_draggable.html">

                    Draggable Portlets</a>

                </li>

            </ul>

        </li> -->

        <!-- <li class="">

            <a href="javascript:;">

            <i class="icon-map-marker"></i>

            <span class="title">Maps</span>

            <span class="arrow "></span>

            </a>

            <ul class="sub-menu">

                <li >

                    <a href="maps_google.html">

                    Google Maps</a>

                </li>

                <li >

                    <a href="maps_vector.html">

                    Vector Maps</a>

                </li>

            </ul>

        </li> -->

        <!-- <li class="last ">

            <a href="charts.html">

            <i class="icon-bar-chart"></i>

            <span class="title">Visual Charts</span>

            </a>

        </li> -->

    </ul>

    <!-- END SIDEBAR MENU -->

</div>
