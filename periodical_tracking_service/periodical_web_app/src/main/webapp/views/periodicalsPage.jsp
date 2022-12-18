<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 18.04.2021
  Time: 12:49
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
            <h2 class="logo">Periodicals</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a>
            </div>
        </div>
    </div>
</div>
<div class="content">

    <table cellpadding="5" cellspacing="1" id="periodicals">
        <tr>
            <th><div id="id" class="sort">Post Code</div></th>
            <th><div id="name" class="sort">Name</div></th>
            <th><div id="price" class="sort">Price</div></th>
            <th style="display: none"></th>
        </tr>
        <c:forEach items="${periodicalList}" var="periodical">
            <tr>
                <td>${periodical.getId()}</td>
                <td>${periodical.getName()}</td>
                <td>${periodical.getPrice()}</td>
                <td>
                    <a href="subscribe?id=${periodical.getId()}">Subscribe</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}/userInfo">
        <button>Back</button>
    </a>
</div>
<script>
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
