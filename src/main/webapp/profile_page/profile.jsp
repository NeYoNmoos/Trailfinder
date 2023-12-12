<%@ page import="at.fhv.hike.controllers.CookieController" %><%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 11.12.2023
  Time: 01:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/profile_page/profile.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="container">
    <jsp:include page="/components/profile_menu/profile_menu.jsp"/>
    <div class="main">
        <h3 style="padding: 6px 8px 6px 16px"><%= request.getAttribute("user")%></h3>
        <h3 style="padding: 6px 8px 6px 16px"><%= request.getAttribute("email")%></h3>
        <div class="create-route-section">
            <a href="${pageContext.request.contextPath}/route-create" class="px-4 py-2 rounded-md btn-primary transition-colors">Create Route</a>
        </div>
    </div>
</div>

</body>
</html>
