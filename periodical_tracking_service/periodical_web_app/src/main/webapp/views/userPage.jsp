<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 11.04.2021
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <h2 class="logo">Welcome, ${client}!</h2>
            <div class = "header_menu" > <a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a></div>
        </div>
    </div>
</div>
<div class="content">
    <table class="user_menu" border="1" cellpadding="5" cellspacing="1">
        <tr>
           <td> <a href="${pageContext.request.contextPath}/subscriptions">View my subscriptions</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/viewPeriodicals">View all periodicals</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/logout">Log out</a></td>
        </tr>
    </table>


    <!--<editor-fold desc="Description">
    <form action="subscriptions" method="get"><button type="submit" >View my subscriptions</button></form>
    <form action="viewPeriodicals" method="get"><button type="submit" >View all periodicals</button></form>
    <form action="logout" method="get"><button type="submit" >Log out</button></form>
     -->
</div>
</body>
</html>
