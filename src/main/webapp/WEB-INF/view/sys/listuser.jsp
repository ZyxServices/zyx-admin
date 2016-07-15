<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8"/>
    <title>体育家后台</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="基于Bootstrap的网站后台模板Metronic DEMO" name="description"/>
    <meta content="" name="author"/>
    <jsp:include page="../public/common-styles.jsp"/>
</head>
<jsp:include page="../public/common-header.jsp"/>


        <div class="container-fluid" >
            <!-- BEGIN PAGE HEADER-->
            <div id="dashboard" style="margin-top: 20px">
                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th><i class="icon-briefcase"></i> 用户ID</th>
                        <th class="hidden-phone"><i class="icon-user"></i> 用户名</th>
                        <th><i class="icon-shopping-cart"></i> 昵称</th>
                        <th><i class="icon-shopping-cart"></i> 角色id</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.username}</td>
                            <td>${item.name}</td>
                            <td>${item.roleId}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i> Reload</a>

            </div>

        </div>
        <!-- END PAGE HEADER-->


<jsp:include page="../public/common-footer.jsp"/>