<%--
  Created by IntelliJ IDEA.
  User: ZYX
  Date: 2016/7/12
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- BEGIN CORE PLUGINS -->

<script src="../../js/jquery-1.10.1.min.js" type="text/javascript"></script>


<script src="../../js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="../../js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="../../js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="../../js/excanvas.min.js"></script>

<script src="../../js/respond.min.js"></script>

<![endif]-->

<script src="../../js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="../../js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="../../js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="../../js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="../../js/jquery.vmap.js" type="text/javascript"></script>

<script src="../../js/jquery.vmap.russia.js" type="text/javascript"></script>

<script src="../../js/jquery.vmap.world.js" type="text/javascript"></script>

<script src="../../js/jquery.vmap.europe.js" type="text/javascript"></script>

<script src="../../js/jquery.vmap.germany.js" type="text/javascript"></script>

<script src="../../js/jquery.vmap.usa.js" type="text/javascript"></script>

<script src="../../js/jquery.vmap.sampledata.js" type="text/javascript"></script>

<script src="../../js/jquery.flot.js" type="text/javascript"></script>

<script src="../../js/jquery.flot.resize.js" type="text/javascript"></script>

<script src="../../js/jquery.pulsate.min.js" type="text/javascript"></script>

<script src="../../js/date.js" type="text/javascript"></script>

<script src="../../js/daterangepicker.js" type="text/javascript"></script>

<script src="../../js/jquery.gritter.js" type="text/javascript"></script>

<script src="../../js/fullcalendar.min.js" type="text/javascript"></script>

<script src="../../js/jquery.easy-pie-chart.js" type="text/javascript"></script>

<script src="../../js/jquery.sparkline.min.js" type="text/javascript"></script>

<script src="../../js/bootstrap-table.js" type="text/javascript"></script>

<script src="../../js/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript" src="../../js/bootstrap-datetimepicker.js"></script>

<script type="text/javascript" src="../../js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="../../js/summernote.min.js" type="text/javascript"></script>

<script src="../../js/dataformat.js" type="text/javascript"></script>

<script type="text/javascript">

    $(function () {
        var url = window.location.href;
        var urlLength = url.split("/").length;
        var getMenuObj = url.split("/")[urlLength - 2];
        var getSecondMenuObj = url.split("/")[urlLength - 1];
        if(getSecondMenuObj == "home"){
            $("." + getSecondMenuObj).addClass("active");
            $("." + getSecondMenuObj).find(".selected").show();
            return;
        }
        if (getMenuObj == "circle" || getMenuObj == "shop" || getMenuObj == "homepage" || getMenuObj == "banner") {
            $("." + getMenuObj).addClass("open");
            $("." + getMenuObj).addClass("active");
            $("." + getMenuObj).find(".arrow").addClass("open");
            $("." + getMenuObj).find(".selected").show();
            $("." + getMenuObj).find(".sub-menu").show();
            $("." + getSecondMenuObj).addClass("active");
        } else {
            $("." + getMenuObj).addClass("active");
            $("." + getMenuObj).find(".selected").show();
        }
    })

</script>
