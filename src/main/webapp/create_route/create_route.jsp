<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 03/11/2023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String submitted = request.getParameter("submitted");

    if ("true".equals(submitted)) {
        String name = request.getParameter("name");
        double length = Double.parseDouble(request.getParameter("length"));
        double altitude = Double.parseDouble(request.getParameter("altitude"));
        String location = request.getParameter("location");
        double duration = Double.parseDouble(request.getParameter("duration"));
        String description = request.getParameter("description");

        RouteEntity newRoute = new RouteEntity();
        newRoute.setRouteId(UUID.randomUUID().toString());
        newRoute.setName(name);
        newRoute.setLength(length);
        newRoute.setAltitude(altitude);
        newRoute.setLocation(location);
        newRoute.setDuration(duration);
        newRoute.setDescription(description);

        TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();
        facade.save(newRoute);

        System.out.println("Created new Route!");

        // Optionally redirect after saving
        response.sendRedirect("create_confirmation.jsp");
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <title>Create New Route</title>
</head>
<body>
    <jsp:include page="/components/navigation/nav_bar.jsp"/>

    <div class="route-form-container">
        <div class="loading-overlay" id="loadingOverlay">
            <div class="loader"></div>
        </div>
        <form action="" method="post">
            <input type="hidden" name="submitted" value="true">

            <label for="name">Route Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="length">Length (in km):</label>
            <input type="number" id="length" name="length" step="0.1" required>

            <label for="altitude">Altitude (in m):</label>
            <input type="number" id="altitude" name="altitude" step="0.1" required>

            <label for="location">Location:</label>
            <input type="text" id="location" name="location" required>

            <label for="duration">Duration (in hours):</label>
            <input type="number" id="duration" name="duration" step="0.1" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>

            <input type="submit" value="Create Route">
        </form>
    </div>
</body>
<script>
    document.querySelector('form').addEventListener('submit', function() {
        document.getElementById('loadingOverlay').style.display = 'block';
    });
</script>
</html>
