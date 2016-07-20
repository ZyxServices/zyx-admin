<%--
  Created by IntelliJ IDEA.
  User: SubDong
  Date: 2016/3/8
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <meta charset="utf-8" />

    <title>体育家后台</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="体育家系统管理" name="description" />

    <meta content="" name="author" />


    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="../css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="../css/uniform.default.css" rel="stylesheet" type="text/css"/>

    <link rel="shortcut icon" href="../images/favicon.ico" />

</head>

<body class="page-header-fixed">


<div class="header navbar navbar-inverse navbar-fixed-top">

    <div class="navbar-inner">
        <div class="container-fluid">

            <a class="brand" href="index.html">

                <img src="../images/logo.png" alt="logo"/>

            </a>
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                <img src="../images/menu-toggler.png" alt="" />

            </a>

            <ul class="nav pull-right">


                <li class="dropdown" id="header_notification_bar">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <i class="icon-warning-sign"></i>

                        <span class="badge">6</span>

                    </a>

                    <ul class="dropdown-menu extended notification">

                        <li>

                            <p>You have 14 new notifications</p>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-success"><i class="icon-plus"></i></span>

                                New user registered.

                                <span class="time">Just now</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-important"><i class="icon-bolt"></i></span>

                                Server #12 overloaded.

                                <span class="time">15 mins</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-warning"><i class="icon-bell"></i></span>

                                Server #2 not respoding.

                                <span class="time">22 mins</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-info"><i class="icon-bullhorn"></i></span>

                                Application error.

                                <span class="time">40 mins</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-important"><i class="icon-bolt"></i></span>

                                Database overloaded 68%.

                                <span class="time">2 hrs</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-important"><i class="icon-bolt"></i></span>

                                2 user IP blocked.

                                <span class="time">5 hrs</span>

                            </a>

                        </li>

                        <li class="external">

                            <a href="#">See all notifications <i class="m-icon-swapright"></i></a>

                        </li>

                    </ul>

                </li>

                <!-- END NOTIFICATION DROPDOWN -->

                <!-- BEGIN INBOX DROPDOWN -->

                <li class="dropdown" id="header_inbox_bar">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <i class="icon-envelope"></i>

                        <span class="badge">5</span>

                    </a>

                    <ul class="dropdown-menu extended inbox">

                        <li>

                            <p>You have 12 new messages</p>

                        </li>

                        <li>

                            <a href="inbox.html?a=view">

                                <span class="photo"><img src="../images/avatar2.jpg" alt="" /></span>

								<span class="subject">

								<span class="from">Lisa Wong</span>

								<span class="time">Just Now</span>

								</span>

								<span class="message">

								Vivamus sed auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="inbox.html?a=view">

                                <span class="photo"><img src="./../images/avatar3.jpg" alt="" /></span>

								<span class="subject">

								<span class="from">Richard Doe</span>

								<span class="time">16 mins</span>

								</span>

								<span class="message">

								Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="inbox.html?a=view">

                                <span class="photo"><img src="./../images/avatar1.jpg" alt="" /></span>

								<span class="subject">

								<span class="from">Bob Nilson</span>

								<span class="time">2 hrs</span>

								</span>

								<span class="message">

								Vivamus sed nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                            </a>

                        </li>

                        <li class="external">

                            <a href="inbox.html">See all messages <i class="m-icon-swapright"></i></a>

                        </li>

                    </ul>

                </li>

                <!-- END INBOX DROPDOWN -->

                <!-- BEGIN TODO DROPDOWN -->

                <li class="dropdown" id="header_task_bar">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <i class="icon-tasks"></i>

                        <span class="badge">5</span>

                    </a>

                    <ul class="dropdown-menu extended tasks">

                        <li>

                            <p>You have 12 pending tasks</p>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">New release v1.2</span>

								<span class="percent">30%</span>

								</span>

								<span class="progress progress-success ">

								<span style="width: 30%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Application deployment</span>

								<span class="percent">65%</span>

								</span>

								<span class="progress progress-danger progress-striped active">

								<span style="width: 65%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Mobile app release</span>

								<span class="percent">98%</span>

								</span>

								<span class="progress progress-success">

								<span style="width: 98%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Database migration</span>

								<span class="percent">10%</span>

								</span>

								<span class="progress progress-warning progress-striped">

								<span style="width: 10%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Web server upgrade</span>

								<span class="percent">58%</span>

								</span>

								<span class="progress progress-info">

								<span style="width: 58%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Mobile development</span>

								<span class="percent">85%</span>

								</span>

								<span class="progress progress-success">

								<span style="width: 85%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li class="external">

                            <a href="#">See all tasks <i class="m-icon-swapright"></i></a>

                        </li>

                    </ul>

                </li>

                <!-- END TODO DROPDOWN -->

                <!-- BEGIN USER LOGIN DROPDOWN -->

                <li class="dropdown user">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <img alt="" src="../images/avatar1_small.jpg" />

                        <span class="username">Bob Nilson</span>

                        <i class="icon-angle-down"></i>

                    </a>

                    <ul class="dropdown-menu">

                        <li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a></li>

                        <li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>

                        <li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox(3)</a></li>

                        <li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>

                        <li class="divider"></li>

                        <li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a></li>

                        <li><a href="<%=request.getContextPath()%>/login/out"><i class="icon-key"></i> Log Out</a></li>

                    </ul>

                </li>

                <!-- END USER LOGIN DROPDOWN -->

            </ul>

            <!-- END TOP NAVIGATION MENU -->

        </div>

    </div>

    <!-- END TOP NAVIGATION BAR -->

</div>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

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

                        <a href="#">

                            首页轮播图</a>

                    </li>

                    <li >

                        <a href="#">

                            首页推荐活动位</a>

                    </li>

                </ul>

            </li>

            <li class="">

                <a href="javascript:;">

                    <i class="icon-bookmark-empty"></i>

                    <span class="title">活动</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li >

                        <a href="#">

                            发布活动</a>

                    </li>

                    <li >

                        <a href="#">

                            审核活动</a>

                    </li>


                </ul>

            </li>

            <li class="">

                <a href="javascript:;">

                    <i class="icon-table"></i>

                    <span class="title">直播</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li >

                        <a href="#">

                            图文直播列表</a>

                    </li>

                    <li >

                        <a href="#">

                            视频直播列表</a>

                    </li>


                </ul>

            </li>

            <li class="">

                <a href="javascript:;">

                    <i class="icon-briefcase"></i>

                    <span class="title">圈子</span>

                    <span class="arrow "></span>

                </a>

                <ul class="sub-menu">

                    <li >

                        <a href="#">

                            <i class="icon-time"></i>

                            圈子列表</a>

                    </li>

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

                        <a href="#">

                            发布商品</a>

                    </li>

                    <li >

                        <a href="#">

                            商品下架</a>

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

                            <li><a href="#">订单查询</a></li>

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

        </ul>

        <!-- END SIDEBAR MENU -->

    </div>

    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <div id="portlet-config" class="modal hide">

            <div class="modal-header">

                <button data-dismiss="modal" class="close" type="button"></button>

                <h3>Widget Settings</h3>

            </div>

            <div class="modal-body">

                Widget settings form goes here

            </div>

        </div>

        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN PAGE CONTAINER-->

        <div class="container-fluid">

            <!-- BEGIN PAGE HEADER-->


            <div class="span12">

                <!-- BEGIN STYLE CUSTOMIZER -->

                <div class="color-panel hidden-phone">

                    <div class="color-mode-icons icon-color"></div>

                    <div class="color-mode-icons icon-color-close"></div>

                    <div class="color-mode">

                        <p>THEME COLOR</p>

                        <ul class="inline">

                            <li class="color-black current color-default" data-style="default"></li>

                            <li class="color-blue" data-style="blue"></li>

                            <li class="color-brown" data-style="brown"></li>

                            <li class="color-purple" data-style="purple"></li>

                            <li class="color-grey" data-style="grey"></li>

                            <li class="color-white color-light" data-style="light"></li>

                        </ul>

                        <label>

                            <span>Layout</span>

                            <select class="layout-option m-wrap small">

                                <option value="fluid" selected>Fluid</option>

                                <option value="boxed">Boxed</option>

                            </select>

                        </label>

                        <label>

                            <span>Header</span>

                            <select class="header-option m-wrap small">

                                <option value="fixed" selected>Fixed</option>

                                <option value="default">Default</option>

                            </select>

                        </label>

                        <label>

                            <span>Sidebar</span>

                            <select class="sidebar-option m-wrap small">

                                <option value="fixed">Fixed</option>

                                <option value="default" selected>Default</option>

                            </select>

                        </label>

                        <label>

                            <span>Footer</span>

                            <select class="footer-option m-wrap small">

                                <option value="fixed">Fixed</option>

                                <option value="default" selected>Default</option>

                            </select>

                        </label>

                    </div>

                </div>

                <!-- END BEGIN STYLE CUSTOMIZER -->

                <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                <h3 class="page-title">

                    首页<small>statistics and more</small>

                </h3>

                <ul class="breadcrumb">

                    <li>

                        <i class="icon-home"></i>

                        <a href="index.html">Home</a>

                        <i class="icon-angle-right"></i>

                    </li>

                    <li><a href="#">Dashboard</a></li>

                    <li class="pull-right no-text-shadow">

                        <div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">

                            <i class="icon-calendar"></i>

                            <span></span>

                            <i class="icon-angle-down"></i>

                        </div>

                    </li>

                </ul>

                <!-- END PAGE TITLE & BREADCRUMB-->

            </div>

        </div>

        <!-- END PAGE HEADER-->

        <div id="dashboard">
            <p>

                403  403  403
            </p>
            <a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i> Reload</a>

        </div>

    </div>
    <a class="title" href="#">Transactions <i class="m-icon-swapright"></i></a>
</div>
<div class="margin-bottom-10 visible-phone"></div>
<!-- BEGIN PORTLET-->
<!-- END PORTLET-->
<!-- BEGIN PORTLET-->
<div class="footer">
    <div class="footer-inner">
        2013 &copy; Metronic by keenthemes.
    </div>

    <div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
    </div>

</div>
<script src="../js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="../js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="../js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="../js/app.js" type="text/javascript"></script>
<script src="../js/index.js" type="text/javascript"></script>


<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });

</script>

<!-- END JAVASCRIPTS -->

<!-- END BODY -->

</html>
