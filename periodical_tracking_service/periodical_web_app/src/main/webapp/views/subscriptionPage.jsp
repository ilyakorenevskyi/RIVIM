<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 12.04.2021
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<head>
    <title>PTS</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <h2 class="logo">My subscriptions</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <table>
        <tr>
            <td>Periodical</td>
            <td>Start date</td>
            <td>Period</td>
            <td>Status</td>
        </tr>
        <c:forEach items="${subscriptionList}" var="subscription">
            <tr>
                <td>${subscription.getPeriodical().getName()}</td>
                <td>${subscription.getDate()}</td>
                <td>${subscription.getPeriod()} mths</td>
                <td>${subscription.isActive() ? "Active" : "Inactive"}</td>
                <td><c:choose>
                    <c:when test="${subscription.isActive()}">
                        <a href="${pageContext.request.contextPath}/deactivate?id=${subscription.getId()}" >Deactivate</a>
                    </c:when>
                    <c:otherwise>
                        <a class= "isDisabled" >Deactivate</a>
                    </c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/viewPeriodicals">
        <button>Add subscription</button>
    </a>
    <a href="${pageContext.request.contextPath}/userInfo">
        <button>Back</button>
    </a>
</div>
</body>
</html>
