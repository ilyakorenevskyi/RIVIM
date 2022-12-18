<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 11.04.2021
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<head>
    <title>Edit periodical</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <h2 class="logo">Edit Periodical</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
<form  id="addForm" action="editPeriodical" method="post">
    <table>
        <tbody>
        <input type="number" name="id" value="${periodical.getId()}" style = "visibility: hidden" >
        <tr>
            <td>Periodicals name </td>
            <td><input type="text" name="name" value="${periodical.getName()}"></td>
        </tr>
        <tr>
            <td>Monthly price </td>
            <td><input type="number"  step="any" name="price" value="${periodical.getPrice()}"></td>
        </tr>
        </tbody>
        <tr>
            <td colspan="2">
                <button type="submit">Save periodical</button>
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
