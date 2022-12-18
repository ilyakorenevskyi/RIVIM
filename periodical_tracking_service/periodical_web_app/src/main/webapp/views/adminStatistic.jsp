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
                <td>Total users</td>
                <td colspan="2">${stat.getUsers()}</td>
            </tr>
            <tr>
                <td>Total periodicals</td>
                <td colspan="2">${stat.getPeriodicals()}</td>
            </tr>
            <tr>
                <td>Total active subscriptions</td>
                <td colspan="2">${stat.getActiveSubscription()}</td>
            </tr>
            <tr>
                <td colspan="3">All periodicals by active subscriptions to them
                </td>
            </tr>

            <c:forEach items="${stat.getPeriodicalBySubs()}" var="entry">
                <tr>
                    <td>${entry.getValue().getId()}</td>
                    <td>${entry.getValue().getName()}</td>
                    <td>
                            ${entry.getKey()}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
