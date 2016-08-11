<%--
  Created by IntelliJ IDEA.
  User: Rainbow
  Date: 16-8-8
  Time: 上午11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/summernote.css" />
    <link rel="stylesheet" href="../../css/datetimepicker.css" />
    <link rel="stylesheet" href="../../css/self-style/style.css" />
</head>
<body class="page-header-fixed">

    <div class="controls summernote">
            <div id="activity-summernote"></div>
    </div>
    <input type="button" onclick="test()" value="aaaa">
<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>

<script src="../../js/app.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/activity/list.js"></script>
<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

    });
    function test(){
       var asdf = $('#activity-summernote').summernote('code');
        $.ajax({
            url: "/v1/jsoup/Assembled",
            type: 'POST',
            dataType: 'json',
            data: {html: asdf},
            success: function (result) {
                if (result.state == 200) {

                } else {
                    $.Popup({
                        confirm:false,
                        template:result.successmsg
                    })
                }
            }
        });
    }

</script>
</body>
</html>

