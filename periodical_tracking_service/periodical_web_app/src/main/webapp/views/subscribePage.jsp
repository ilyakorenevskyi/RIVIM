<%--
  Created by IntelliJ IDEA.
  User: ilyak
  Date: 19.04.2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="${pageContext.request.contextPath}/css/customCSS.css" rel="stylesheet" type="text/css">
<script>
    window.onload = countSum;

    function countSum() {
        let months = document.getElementsByName("period")[0].value;
        let price =  ${periodical.getPrice()};
        let totalPrice = months * price;
        document.getElementsByName("total")[0].textContent = "" + totalPrice
    }
</script>
<head>
    <title>PTS - Subscribe</title>
</head>
<body>
<div class="header">
    <div class="content">
        <div class="header_content">
            <h2 class="logo">PTS</h2>
            <h2 class="logo">Subscribe</h2>
            <div class="header_menu"><a class="menu_item" href="${pageContext.request.contextPath}/logout">Log Out</a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <h3> ${periodical.getId()} - ${periodical.getName()}</h3>
    <form action="${pageContext.request.contextPath}/subscribe" method="post">
        <table>
            <tr>
                <td>
                    <label>Select period you want to subscribe
                        <input type="number" min="1" max="12" value="3" name="period" onchange="countSum()">
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Monthly price ${periodical.getPrice()} </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Total cost <span name="total" style="text-align: right"> </span> </label>
                </td>
            </tr>

            <tr>
                <td>
                    <button type="submit">Subscribe</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>

</html>
