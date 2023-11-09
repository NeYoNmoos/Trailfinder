<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="java.util.UUID" %>
<%@ page import="at.fhv.hike.data.TimeOfYearEntity" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="at.fhv.hike.controllers.RouteController" %>
<%@ page import="at.fhv.hike.data.CoordinateEntity" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 03/11/2023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <title>Create New Route</title>
</head>
<body>
    <jsp:include page="/components/navigation/nav_bar.jsp"/>

    <h1>Create a new Route</h1>

    <div class="route-form-container">
        <div class="loading-overlay" id="loadingOverlay">
            <div class="loader"></div>
        </div>
        <form action="${pageContext.request.contextPath}/route-create" method="post">
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

            <label for="months">Choose months</label>
            <select name="months" id="months" multiple>
                <option value="january">January</option>
                <option value="february">February</option>
                <option value="march">March</option>
                <option value="may">May</option>
                <option value="june">June</option>
                <option value="july">July</option>
                <option value="august">August</option>
                <option value="september">September</option>
                <option value="october">October</option>
                <option value="november">November</option>
                <option value="december">December</option>
            </select>

            <label for="power">Choose power level</label>
            <select name="power" id="power">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </select>

            <label for="scenery">Choose scenery level</label>
            <select name="scenery" id="scenery">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>

            <label for="experience">Choose experience level</label>
            <select name="experience" id="experience">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>

            <label for="condition">Choose condition level</label>
            <select name="condition" id="condition">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>

            <label>Startpoint</label>
            <div class="coordinate-row">
                <div class="coordinate-input">
                    <input type="text" placeholder="Latitude" id="startLatitude" name="startLatitude" pattern="(\+|-)?(?:90(?:(?:\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid latitude (-90.0 to +90.0)" required>
                </div>
                <div class="coordinate-input">
                    <input type="text" placeholder="Longitude" id="startLongitude" name="startLongitude" pattern="^(\+|-)?(?:180(?:(?:\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid longitude (-180.0 to +180.0)" required>
                </div>
            </div>

            <label>Endpoint</label>
            <div class="coordinate-row">
                <div class="coordinate-input">
                    <input type="text" placeholder="Latitude" id="endLatitude" name="endLatitude" pattern="(\+|-)?(?:90(?:(?:\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid latitude (-90.0 to +90.0)" required>
                </div>
                <div class="coordinate-input">
                    <input type="text" placeholder="Longitude" id="endLongitude" name="endLongitude" pattern="^(\+|-)?(?:180(?:(?:\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid longitude (-180.0 to +180.0)" required>
                </div>
            </div>

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
