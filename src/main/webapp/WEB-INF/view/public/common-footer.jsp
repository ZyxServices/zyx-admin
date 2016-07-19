</div>

<!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="<%=request.getContextPath()%>/public/footer.jsp"/>

<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<jsp:include page="<%=request.getContextPath()%>/public/common-js.jsp"/>

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="<%=request.getContextPath()%>/js/app.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/index.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->

<script>

  jQuery(document).ready(function() {

    App.init(); // initlayout and core plugins
  });

</script>



</html>