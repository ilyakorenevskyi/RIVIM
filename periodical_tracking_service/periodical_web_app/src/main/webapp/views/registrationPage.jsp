<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 26.04.2021
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<head>
    <title>PTS - Register</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/login">Log In</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <form id="regForm" action="registration" method="post">
        <table>
            <tbody>
            <tr>
                <td>Login</td>
                <td><input name="login" minlength="6" required></td>

            </tr>
            <tr>
                <td>Password</td>
                <td><input name="password" type="password" minlength="6" required></td>
            </tr>
            <tr>
                <td>Full Name</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Adress</td>
                <td><input type="text" name="adress" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">Register</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
