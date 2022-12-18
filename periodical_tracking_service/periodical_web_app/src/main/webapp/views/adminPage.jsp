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
            <h1 class="logo">Admin's Page</h1>
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
    <table border="1" cellpadding="5" cellspacing="1" id="periodicals">
        <tr>
            <td><div id="id" class="sort">Id</div></td>
            <td><div id="name" class="sort">Name</div></td>
            <td><div id="price" class="sort">Price</div></td>
            <td colspan="2">
                <div class="centered">
                    <button id="addButton">+</button>
                </div>
            </td>
        </tr>
        <c:forEach items="${periodicalList}" var="periodical">
            <tr>
                <td>${periodical.getId()}</td>
                <td>${periodical.getName()}</td>
                <td>${periodical.getPrice()}</td>
                <td>
                    <a href="editPeriodical?id=${periodical.getId()}">Edit</a>
                </td>
                <td>
                    <a href="deletePeriodical?id=${periodical.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>
    <form style="display: none" id="addForm" action="admin" method="post">
        <table>
            <tbody>
            <tr>
                <td>Periodicals index</td>
                <td><input type="number" name="ind" min="1" required></td>
                </td>
            </tr>
            <tr>
                <td>Periodicals name</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Monthly price</td>
                <td><input type="number" name="price" step="any" min="0" required></td>
            </tr>
            </tbody>
            <tr>
                <td colspan="2">
                    <button type="submit">Add periodical</button>
                </td>
            </tr>

        </table>
    </form>
</div>
<script>
    document.getElementById("addButton").onclick = handleButtonClick;

    function handleButtonClick() {
        let form = document.getElementById("addForm");
        form.style.display = "block"
    }
    document.getElementById("id").onclick = sortIdAscend;
    document.getElementById("name").onclick = sortNameAscend;
    document.getElementById("price").onclick = sortPriceAscend;
    periodicals = document.getElementById("periodicals")
    function sortIdAscend() {
        let sortedRows = Array.from(periodicals.rows)
            .slice(1).sort((rowA, rowB) => rowA.cells[0].innerHTML > rowB.cells[0].innerHTML ? 1 : -1);
        periodicals.tBodies[0].append(...sortedRows);
        document.getElementById("id").onclick = sortIdDescend;
    }
    function sortIdDescend() {
        let sortedRows = Array.from(periodicals.rows)
            .slice(1).sort((rowA, rowB) => rowA.cells[0].innerHTML < rowB.cells[0].innerHTML ? 1 : -1);
        periodicals.tBodies[0].append(...sortedRows);
        document.getElementById("id").onclick = sortIdAscend;
    }
    function sortNameAscend() {
        let sortedRows = Array.from(periodicals.rows)
            .slice(1).sort((rowA, rowB) => rowA.cells[1].innerHTML > rowB.cells[1].innerHTML ? 1 : -1);
        periodicals.tBodies[0].append(...sortedRows);
        document.getElementById("name").onclick = sortNameDescend;
    }
    function sortNameDescend() {
        let sortedRows = Array.from(periodicals.rows)
            .slice(1).sort((rowA, rowB) => rowA.cells[1].innerHTML < rowB.cells[1].innerHTML ? 1 : -1);
        periodicals.tBodies[0].append(...sortedRows);
        document.getElementById("name").onclick = sortNameAscend;
    }
    function sortPriceAscend() {
        let sortedRows = Array.from(periodicals.rows)
            .slice(1).sort((rowA, rowB) => parseFloat(rowA.cells[2].innerHTML) > parseFloat(rowB.cells[2].innerHTML) ? 1 : -1);
        periodicals.tBodies[0].append(...sortedRows);
        document.getElementById("price").onclick = sortPriceDescend;
    }
    function sortPriceDescend() {
        let sortedRows = Array.from(periodicals.rows)
            .slice(1).sort((rowA, rowB) => parseFloat(rowA.cells[2].innerHTML) < parseFloat(rowB.cells[2].innerHTML) ? 1 : -1);
        periodicals.tBodies[0].append(...sortedRows);
        document.getElementById("price").onclick = sortPriceAscend;
    }
</script>
</body>
</html>
