<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 25.04.2021
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<head>
    <title>PTS - Payment</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <h1>Subscription successful</h1>
    <table>
        <tr>
            <td>Payment id:</td>
            <td>${payment.getId()}</td>
        </tr>
        <tr>
            <td>Periodical:
            </td>
            <td>
                ${periodical.getId()} ${periodical.getName()}
            </td>
        </tr>
        <tr>
            <td>Payment date:</td>
            <td>
                ${payment.getDate()}
            </td>
        </tr>
        <tr>
            <td>Total cost:</td>
            <td>${payment.getTotalAmount()}</td>
        </tr>
    </table>
    <a href="${pageContext.request.contextPath}/subscriptions">View my subscriptions</a>
</div>
</body>
</html>
