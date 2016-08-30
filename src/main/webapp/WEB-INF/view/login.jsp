<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
            <form id="login_form" method="post" action="<%=request.getContextPath()%>/login/in"
                  class="bootstrap-admin-login-form form-horizontal">
                <h1>Login</h1>
                <div class="form-group">
                    <input class="form-control" type="text" id="username" name="username"
                           placeholder="E-mail">
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" id="password" name="password"
                           placeholder="Password">
                </div>
                <div class="form-group">
                    <input class="form-control" type="hidden" id="prm" name="prm">
                </div>
                <div class="form-group">
                    <label class="checkbox"> <input type="checkbox" name="remember_me" style="margin-top: 6px;">
                        记住我
                    </label>
                </div>
                <button id="login_btn" class="btn btn-lg btn-primary" type="button">确定</button>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="loginLoad">
    <div class="modal-body">
        <p>登录中...</p>
    </div>
</div>
<div class=""></div>
<script type="text/javascript"
        src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../js/security/JBase64.js"></script>
<script type="text/javascript" src="../js/security/security.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
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


        $('#login_btn').click(function () {
            var name = $('#username').val();
            var password = $('#password').val();
            if (name == null || name == "") {
                alert("用户名不得为空！");
                return;
            }
            if (password == null || password == "") {
                alert("密码不得为空！");
                return;
            }
            $.ajax({
                type: "get",
                url: "login/in",
                beforeSend:function () {
                    $("#loginLoad").modal('show');
                },
                success: function (rd) {
                    $("#prm").val(rd.m);
                    //通过模和公钥参数获取公钥
                    var key = RSAUtils.getKeyPair(rd.e, "", rd.m16);
                    //颠倒密码的顺序，要不然后解密后会发现密码顺序是反的
                    var reversedPwd = BASE64.encoder(password).split("").reverse().join("");
                    //对密码进行加密传输
                    var encryptedPwd = RSAUtils.encryptedString(key, reversedPwd);

                    $("#password").val(encryptedPwd);

                    $("#login_form").submit();

                }
            })
        })
    });
</script>
</body>
</html>
