<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员列表</title>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="体育家-管理员列表" name="description"/>

    <meta content="体育家系统管理" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <jsp:include page="../public/common-styles.jsp"/>
    <link rel="stylesheet" href="../../css/tiyujia/style.css"/>
</head>
<body class="page-header-fixed">
<jsp:include page="../public/header.jsp"/>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <jsp:include page="../public/nav.jsp"/>

    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->

    <div class="page-content">

        <!-- BEGIN PAGE CONTAINER-->
        <%--modal删除--%>
        <div id="admin-del" class="modal fade">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>管理员删除</h3>
            </div>
            <div class="modal-body">
                该管理员的所有数据将被完全删除，不能再进行操作
            </div>
            <div class="modal-footer">
                <button class="btn btn-default">确定</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
        <%--管理员列表--%>
        <div class="container-fluid" id="administratorsList">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        管理员管理
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">管理员</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">列表</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="administrators-list">

                <!-- BEGIN DASHBOARD STATS -->

                <div class="row-fluid">
                    <div class="span6">
                        <a class="btn btn-default" href="javascript:void(0)" onclick="createAdministrators()">创建管理员</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="administrators-list-table">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="id">id</th>
                                <th data-field="name">名称</th>
                                <th data-field="systemRoleListDto.role_name">权限</th>
                                <th data-field="bz">备注</th>
                                <th data-events="operateEvent" data-formatter="operateLog">操作日志</th>
                                <th data-events="operateEvent" data-formatter="operateFormatter">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>

        <%--管理员创建--%>
        <div class="container-fluid hide" id="administratorsCreate">

        <!-- BEGIN PAGE HEADER-->

        <div class="row-fluid">

            <div class="span12">

                <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                <h3 class="page-title">

                    管理员创建
                    <small>statistics and more</small>

                </h3>

                <ul class="breadcrumb">

                    <li>

                        <i class="icon-home"></i>

                        <a href="javascript:void(0)">管理员</a>

                        <i class="icon-angle-right"></i>

                    </li>

                    <li><a href="#">创建</a></li>

                </ul>

                <!-- END PAGE TITLE & BREADCRUMB-->

            </div>

        </div>

        <!-- END PAGE HEADER-->

        <div id="activity-create">

            <!-- BEGIN DASHBOARD STATS -->
            <div class="row-fluid">

                <form action="/v1/sysuser/" id="sysUserCreateForm" method="post" enctype="multipart/form-data"
                      class="form-horizontal" novalidate="novalidate" role="form">

                    <div class="control-group form-group">
                        <label class="control-label">账号</label>
                        <div class="controls col-xs-5">
                            <input type="text" id="username" name="username" class="span6"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">密码</label>
                        <div class="controls col-xs-5">
                            <input type="password" id="pass" name="pass" class="span6"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">管理员名称</label>
                        <div class="controls col-xs-5">
                            <input type="text" id="name" name="name" class="span6"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">权限等级</label>
                        <div class="controls col-xs-5">
                            <select class="span6" id="role_select" name="roleId">

                            </select>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="control-group form-group">
                        <label class="control-label">备注</label>
                        <div class="controls col-xs-5">
                            <input type="text" id="remark" name="remark" class="span6"/>
                            <span class="help-inline required">*</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-default" id="createButton" onclick="beginCreateSysUser()">确定</button>
                            <a href="javascript:void(0)" class="btn btn-default"
                               onclick="window.location.reload();">返回</a>
                        </div>
                    </div>
                </form>

            </div>

            <!-- END DASHBOARD STATS -->

        </div>

    </div>

        <%--权限更改--%>
        <div class="container-fluid hide" id="administratorsRoleEdit">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->

                    <h3 class="page-title">

                        管理员权限更改
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">管理员</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">权限更改</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="sysuser_role_edit">

                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">

                    <form action="/v1/sysuser/" id="sysUserEditForm" method="post" enctype="multipart/form-data"
                          class="form-horizontal" novalidate="novalidate" role="form">
                        <input type="hidden" id="editUserId" name="id">
                        <div class="control-group form-group">
                            <label class="control-label">权限等级</label>
                            <div class="controls col-xs-5">
                                <select class="span6 form-control" id="edit_role_select" name="roleId">

                                </select>
                                <span class="help-inline required">*</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" class="btn btn-default" id="editButton" onclick="beginEditSysUser()">确定</button>
                                <a href="javascript:void(0)" class="btn btn-default"
                                   onclick="window.location.reload();">返回</a>
                            </div>
                        </div>
                    </form>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>

        <%--操作日志--%>
        <div class="container-fluid hide" id="administratorsLog">

            <!-- BEGIN PAGE HEADER-->

            <div class="row-fluid">

                <div class="span12">

                    <h3 class="page-title">

                        管理员管理
                        <small>statistics and more</small>

                    </h3>

                    <ul class="breadcrumb">

                        <li>

                            <i class="icon-home"></i>

                            <a href="javascript:void(0)">管理员</a>

                            <i class="icon-angle-right"></i>

                        </li>

                        <li><a href="#">操作日志</a></li>

                    </ul>

                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>

            </div>

            <!-- END PAGE HEADER-->

            <div id="administrators-log">

                <!-- BEGIN DASHBOARD STATS -->

                <div class="row-fluid">
                    <div class="span12 responsive">
                        <table id="administrators-log-table">
                            <thead>
                            <tr>
                                <th data-field="id">id</th>
                                <th data-field="name">管理员</th>
                                <th data-field="systemRoleListDto.role_name">权限</th>
                                <th data-field="bz">操作</th>
                                <th data-field="operateTime">操作时间</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

                </div>

                <!-- END DASHBOARD STATS -->

            </div>

        </div>
        <!-- END PAGE CONTAINER-->

    </div>

    <!-- END PAGE -->

</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="../public/footer.jsp"/>
<jsp:include page="../public/common-js.jsp"/>

<script src="../../js/app.js" type="text/javascript"></script>
<script type="text/javascript" src="../../js/sys/admin.js"></script>
<script>

    jQuery(document).ready(function () {

        App.init(); // initlayout and core plugins

    });

</script>
</body>
</html>