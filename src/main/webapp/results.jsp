<%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 19/10/2023
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    String numberStr = request.getParameter("value");
    String percentageStr = request.getParameter("percentage");
    float number = 0.0f;
    float percentage = 0.0f;

    try {
        number = Float.parseFloat(numberStr);
        // If the percentage has a '%' symbol, remove it before parsing
        percentage = Float.parseFloat(percentageStr.replace("%", ""));
    } catch (NumberFormatException e) {
    }
%>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>

Number: <%= number %>
<br>
Percentage: <%= percentage %>
</body>
</html>
