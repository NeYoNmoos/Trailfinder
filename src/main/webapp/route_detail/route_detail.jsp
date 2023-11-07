<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="at.fhv.hike.data.CoordinateEntity" %>
<%@ page import="at.fhv.hike.data.TimeOfYearEntity" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 01/11/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    RouteEntity route = (RouteEntity) request.getAttribute("route");
    TimeOfYearEntity timeOfYear = route != null ? route.getTimeOfYearEntity() : null;
    AttributeEntity attributes = route != null ? route.getAttributeEntity() : null;
    List<CoordinateEntity> coordinates = route != null ? route.getCoordinates() : null;
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/route_detail/route_detail.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <title><%= route.getName() %></title>
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div>
    <div class="card">
        <h1><%= route.getName() %></h1>
        <div class="card row">
            <p>Length: <%= route.getLength() %> km</p>
            <p>Altitude: <%= route.getAltitude() %> m</p>
            <p>Location: <%= route.getLocation() %></p>
        </div>

        <% if (attributes != null) { %>
        <div class="card row">
            <p>Power: <%= attributes.getStrength() %>, </p>
            <p>Scenery: <%= attributes.getScenery() %>, </p>
            <p>Experience: <%= attributes.getExperience() %>, </p>
            <p>Condition: <%= attributes.getCondition() %></p>
        </div>
        <% } %>

        <% if (timeOfYear != null) { %>
        <div class="card">
            <h3>Best time to visit:</h3>
            <div class="row">
                <%= timeOfYear.getJanuary() ? "January " : "" %>
                <%= timeOfYear.getFebruary() ? "February " : "" %>
                <%= timeOfYear.getMarch() ? "March " : "" %>
                <%= timeOfYear.getApril() ? "April " : "" %>
                <%= timeOfYear.getMay() ? "May " : "" %>
                <%= timeOfYear.getJune() ? "June " : "" %>
                <%= timeOfYear.getJuly() ? "July " : "" %>
                <%= timeOfYear.getAugust() ? "August " : "" %>
                <%= timeOfYear.getSeptember() ? "September " : "" %>
                <%= timeOfYear.getOctober() ? "October " : "" %>
                <%= timeOfYear.getNovember() ? "November " : "" %>
                <%= timeOfYear.getDecember() ? "December " : "" %>
            </div>
        </div>
        <% } %>

        <p>Duration: <%= route.getDuration() %> hours</p>
        <p>Description: <%= route.getDescription() %></p>

        <% if (coordinates != null && !coordinates.isEmpty()) { %>
        <div>
            <h3>Coordinates:</h3>
            <% for(CoordinateEntity coord : coordinates) { %>
            <p>Latitude: <%= coord.getLatitude() %>, Longitude: <%= coord.getLongitude() %></p>
            <% } %>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>