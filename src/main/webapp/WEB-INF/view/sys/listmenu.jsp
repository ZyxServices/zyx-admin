<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

	<meta charset="utf-8" />

	<title>体育家后台</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="基于Bootstrap的网站后台模板Metronic DEMO" name="description" />

	<meta content="" name="author" />

	<jsp:include page="../public/common-styles.jsp"/>

</head>
<jsp:include page="../public/common-header.jsp"/>

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
						<table class="table table-striped table-bordered table-advance table-hover">
							<thead>
										<tr>
										<th><i class="icon-briefcase"></i> 标签ID</th>
											<th class="hidden-phone"><i class="icon-user"></i> 标签名称</th>
											<th><i class="icon-shopping-cart"></i> 标签链接</th>
											<th><i class="icon-shopping-cart"></i> 父标签</th>
											<th><i class="icon-shopping-cart"></i> 标签序号</th>
											<th><i class="icon-shopping-cart"></i> 标签图标</th>
											<th><i class="icon-shopping-cart"></i> 标签类型</th>
										</tr>
							</thead>
							<tbody>
						        <c:forEach items="${pers}" var="item">
						            <tr><td>${item.id}</td><td>${item.menuName}</td><td>${item.menuUrl}</td><td>${item.parentId}</td><td>${item.menuOrder}</td><td>${item.menuIcon}</td><td>${item.menuType}</td></tr>
						        </c:forEach>
						        </tbody>
						     </table>

										<a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i> Reload</a>

									</div>

<jsp:include page="../public/common-footer.jsp"/>