<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html class="bootstrap-admin-vertical-centered">
<head>
<title>体育家运营后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link rel="stylesheet" media="screen" href="../css/bootstrap.min.css">
<link rel="stylesheet" media="screen" href="../css/bootstrap-theme.min.css">

<!-- Bootstrap Admin Theme -->
<link rel="stylesheet" media="screen"
	href="../css/bootstrap-admin-theme.css">

<!-- Custom styles -->
<style type="text/css">
.alert {
	margin: 0 auto 20px;
}
</style>


</head>
<body class="bootstrap-admin-without-padding">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="alert alert-info">
					<a class="close" data-dismiss="alert" href="#">&times;</a> 体育家运营后台
				</div>
				<form method="post" action="<%=request.getContextPath()%>/login/in"
					class="bootstrap-admin-login-form">
					<h1>Login</h1>
					<div class="form-group">
						<input class="form-control" type="text" name="username"
							placeholder="E-mail">
					</div>
					<div class="form-group">
						<input class="form-control" type="password" name="password"
							placeholder="Password">
					</div>
					<div class="form-group">
						<label> <input type="checkbox" name="remember_me">
							记住我
						</label>
					</div>
					<button class="btn btn-lg btn-primary" type="submit">确定</button>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript">
            $(function() {
                // Setting focus
                $('input[name="email"]').focus();

                // Setting width of the alert box
                var alert = $('.alert');
                var formWidth = $('.bootstrap-admin-login-form').innerWidth();
                var alertPadding = parseInt($('.alert').css('padding'));

                if (isNaN(alertPadding)) {
                    alertPadding = parseInt($(alert).css('padding-left'));
                }

                $('.alert').width(formWidth - 2 * alertPadding);
            });
        </script>
</body>
</html>
