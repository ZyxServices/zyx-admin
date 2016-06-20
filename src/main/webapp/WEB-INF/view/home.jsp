<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>体育家运营后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap Docs -->
<link href="../css/docs.min.css"
	rel="stylesheet" media="screen">

<!-- Bootstrap -->
<link rel="stylesheet" media="screen" href="../css/bootstrap.min.css">
<link rel="stylesheet" media="screen" href="../css/bootstrap-theme.min.css">

<!-- Bootstrap Admin Theme -->
<link rel="stylesheet" media="screen"
	href="../css/bootstrap-admin-theme.css">
<link rel="stylesheet" media="screen"
	href="../css/bootstrap-admin-theme-change-size.css">

<!-- Custom styles -->
<style type="text/css">
@font-face {
	font-family: Ubuntu;
	src: url('../fonts/Ubuntu-Regular.ttf');
}

.bs-docs-masthead {
	background-color: #6f5499;
	background-image: linear-gradient(to bottom, #563d7c 0px, #6f5499 100%);
	background-repeat: repeat-x;
}

.bs-docs-masthead {
	padding: 0;
}

.bs-docs-masthead h1 {
	color: #fff;
	font-size: 40px;
	margin: 0;
	padding: 34px 0;
	text-align: center;
}

.bs-docs-masthead a:hover {
	text-decoration: none;
}

.meritoo-logo a {
	background-color: #fff;
	border: 1px solid rgba(66, 139, 202, 0.4);
	display: block;
	font-family: Ubuntu;
	padding: 22px 0;
	text-align: center;
}

.meritoo-logo a, .meritoo-logo a:hover, .meritoo-logo a:focus {
	text-decoration: none;
}

.meritoo-logo a img {
	display: block;
	margin: 0 auto;
}

.meritoo-logo a span {
	color: #4e4b4b;
	font-size: 18px;
}

.row-urls {
	margin-top: 4px;
}

.row-urls .col-md-6 {
	text-align: center;
}

.row-urls .col-md-6 a {
	font-size: 14px;
}
</style>

</head>
<body class="bootstrap-admin-with-small-navbar">
	<!-- small navbar -->
	<nav
		class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm"
		role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="collapse navbar-collapse">
						<ul
							class="nav navbar-nav navbar-left bootstrap-admin-theme-change-size">
							<li class="text">Change size:</li>
							<li><a class="size-changer small">Small</a></li>
							<li><a class="size-changer large active">Large</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							<li><a href="#">Reminders <i
									class="glyphicon glyphicon-bell"></i></a></li>
							<li><a href="#">Settings <i
									class="glyphicon glyphicon-cog"></i></a></li>
							<li><a href="#">Go to frontend <i
									class="glyphicon glyphicon-share-alt"></i></a></li>
							<li class="dropdown"><a href="#" role="button"
								class="dropdown-toggle" data-hover="dropdown"> <i
									class="glyphicon glyphicon-user"></i> Username <i class="caret"></i></a>
								<ul class="dropdown-menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li role="presentation" class="divider"></li>
									<li><a href="index.html">Logout</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>

	<!-- main / large navbar -->
	<nav
		class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
		role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".main-navbar-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="about.html">控制面板</a>
					</div>
					<div class="collapse navbar-collapse main-navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Link</a></li>
							<li><a href="#">Link</a></li>
							
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
			</div>
		</div>
		<!-- /.container -->
	</nav>

	<div class="container">
		<!-- left, vertical navbar & content -->
		<div class="row">
			<!-- left, vertical navbar -->
			<div class="col-md-2 bootstrap-admin-col-left">
				<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
					<li class="active"><a href="<%=request.getContextPath()%>/permission/list"><i
							class="glyphicon glyphicon-chevron-right"></i> 查看权限</a></li>
					<li><a href="dashboard.html"><i
							class="glyphicon glyphicon-chevron-right"></i> Dashboard</a></li>
				</ul>
			</div>

			<!-- content -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="page-header bootstrap-admin-content-title">
							<h1>Bootstrap 3.x Admin's Theme</h1>
							<a href="https://github.com/meritoo/Bootstrap-3-Admin-Theme"
								class="action btn"> Go to GitHub &raquo; </a> <a
								href="https://github.com/meritoo/Bootstrap-3-Admin-Theme/archive/master.zip"
								class="action">
							</a>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">Details</div>
							</div>
						</div>
					</div>


				<div class="row">
				</div>

				<div class="row row-urls">
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<div class="navbar navbar-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<footer role="contentinfo">
						<p class="left">Bootstrap 3.x Admin Theme</p>
						<p class="right">
							&copy; 2013 <a href="http://www.meritoo.pl" target="_blank">Meritoo.pl</a>
						</p>
					</footer>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../js/twitter-bootstrap-hover-dropdown.min.js"></script>
	<script type="text/javascript"
		src="../js/bootstrap-admin-theme-change-size.js"></script>
</body>
</html>
