<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 25.04.2021
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<head>
    <title>PTS - Log in</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <h2 class="logo">Periodicals Track Service</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/login">Log In</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <table class="user_menu">
        <form action="login" method="post">
            <tr>
                <td style="text-align: center">Login</td>
            </tr>
            <tr>
                <td style="text-align:center" ><div class="centered"> <input type="text" name="login" required/></div> </td>
            </tr>
            <tr>
                <td style="text-align:center" >Password</td>
            </tr>
            <tr>
                <td style="text-align:center" > <div class="centered"><input type="password" name="password" required/></div></td>
            </tr>
            <tr>
                <td style="text-align:center"> <input type="submit" value="Log In" ></td>
            </tr>
        </form>
        <tr>
            <td style="text-align:center"> <a href="${pageContext.request.contextPath}/registration">Register Now</a></td>
        </tr>
    </table>
</div>
</body>
</html>
