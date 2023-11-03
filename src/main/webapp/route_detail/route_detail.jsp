<%@ page import="data.Route" %>
<%@ page import="at.fhv.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="at.fhv.data.RouteEntity" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 01/11/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String routeId = request.getParameter("routeId");

    RouteEntity route = new TrailfinderDatabaseFacade().getRouteById(routeId);
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <title><%= route.getName() %></title>
</head>
<body>
<div>
    <div class="card">
        <h1><%= route.getName() %></h1>
        <div class="card">
            <p>Length: <%= route.getLength() %> km</p>
            <p>Altitude: <%= route.getAltitude() %> m</p>
            <p>Location: <%= route.getLocation() %></p>
        </div>

        <p>Description: <%= route.getDescription() %></p>
        <p>Duration: <%= route.getDuration() %> hours</p>
    </div>
</div>
</body>
</html>