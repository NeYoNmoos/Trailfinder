<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="at.fhv.hike.controllers.RouteController" %>
<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 11/2/2023
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RouteController routeController = new RouteController();
    List<RouteEntity> allRoutes = routeController.getAllRoutes();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/home_page/home_page.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>

<header>
    <div class="header-content">
        <h1>Trailfinder</h1>
        <img src="${pageContext.request.contextPath}/assets/home_page_img/header-image.jpg" alt="Header Image">
    </div>
</header>

<div class="search-bar">
    <input type="text" placeholder="Search by Name">
    <button>Search</button>
</div>

<div class="route-boxes">
    <div class="route-box">
        <img src="download.jpg" alt="Route Image 1">
        <div class="route-info">
            <h2>Route 1</h2>
            <p>Short description of Route 1.</p>
        </div>
    </div>
    <%
        for (int i = 0; i < allRoutes.size(); i++) {
            RouteEntity currentRoute = allRoutes.get(i); %>
            <tr>
                <td><%=currentRoute.getName()%></td>
                <td><%=currentRoute.getDescription()%></td>
                <td><%=currentRoute.getLength()%></td>
                <td><%=currentRoute.getAltitude()%></td>
                <td><%=currentRoute.getLocation()%></td>
                <td><%=currentRoute.getDuration()%></td>
            </tr>
        <% }
    %>
</div>
</body>
</html>
