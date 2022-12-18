<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 09.04.2021
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<head>
    <title>Admin page - PTS</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <h1 class="logo">Admin Page</h1>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="inline_wrap">
        <table class="menu" border="1" cellpadding="5" cellspacing="1">
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin?data=periodicals">Manage Periodicals</a></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin?data=users">Manage Users</a></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/admin?data=statistic">Statistic info</a></td>
            </tr>
        </table>
        <table border="1" cellpadding="5" cellspacing="1">
            <tr>
                <td>Periodical</td>
                <td>Start date</td>
                <td>Period</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${subsList}" var="subscription">
                <tr>
                    <td>${subscription.getPeriodical().getName()}</td>
                    <td>${subscription.getDate()}</td>
                    <td>${subscription.getPeriod()} mths</td>
                    <td>${subscription.isActive() ? "Active" : "Іnactive"}</td>
                    <td><c:choose>
                        <c:when test="${subscription.isActive()}">
                            <a href="${pageContext.request.contextPath}/deactivate?id=${subscription.getPeriodical().getId()}&type=admin">Deactivate</a>
                        </c:when>
                        <c:otherwise>
                            <a  class="isDisabled"> Deactivate </a>
                        </c:otherwise>
                    </c:choose></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
