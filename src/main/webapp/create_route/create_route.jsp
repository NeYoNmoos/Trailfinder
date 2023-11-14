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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <title>Create New Route</title>
</head>
<body>
    <jsp:include page="/components/navigation/nav_bar.jsp"/>

    <div class="route-form-container">
        <div class="loading-overlay" id="loadingOverlay">
            <div class="loader"></div>
        </div>
        <form action="${pageContext.request.contextPath}/route-create" method="post">
            <h1>Create a new Route</h1>
            <h2>General Information:</h2>
            <label for="name">Route Name:</label>
            <input type="text" placeholder="Name of your route..." id="name" value="${name}" name="name" required>

            <label for="length">Length (in km):</label>
            <input type="number" placeholder="Length of your route..." id="length" value="${length}" name="length" min="0" step="0.01" required>

            <label for="altitude">Altitude (in m):</label>
            <input type="number" placeholder="Altitude of your route..." id="altitude" value="${altitude}" name="altitude" min="0" step="0.01" required>

            <label for="location">Location:</label>
            <input type="text" placeholder="Location of your route..." id="location"  value="${location}" name="location" required>

            <label for="duration">Duration (in hours):</label>
            <input type="number" placeholder="Duration of your route..." id="duration" value="${duration}" name="duration" min="0" step="0.0001" required>

            <label for="description">Description:</label>
            <textarea id="description" placeholder="Describe your route..." name="description" rows="4" required>${description}</textarea>

            <label for="months">Choose months (hold 'Ctrl' or 'Command' while clicking to select multiple)</label>
            <select name="months" id="months" multiple size="12">
                <option value="january" ${january ? 'selected' : ''}>January</option>
                <option value="february" ${february ? 'selected' : ''}>February</option>
                <option value="march" ${march ? 'selected' : ''}>March</option>
                <option value="april" ${april ? 'selected' : ''}>April</option>
                <option value="may" ${may ? 'selected' : ''}>May</option>
                <option value="june" ${june ? 'selected' : ''}>June</option>
                <option value="july" ${july ? 'selected' : ''}>July</option>
                <option value="august" ${august ? 'selected' : ''}>August</option>
                <option value="september" ${september ? 'selected' : ''}>September</option>
                <option value="october" ${october ? 'selected' : ''}>October</option>
                <option value="november" ${november ? 'selected' : ''}>November</option>
                <option value="december" ${december ? 'selected' : ''}>December</option>
            </select>


            <label for="power">Choose power level</label>
            <select name="power" id="power">
                <option value="1" ${power == 1 ? 'selected' : ''}>1</option>
                <option value="2" ${power == 2 ? 'selected' : ''}>2</option>
                <option value="3" ${power == 3 ? 'selected' : ''}>3</option>
                <option value="4" ${power == 4 ? 'selected' : ''}>4</option>
                <option value="5" ${power == 5 ? 'selected' : ''}>5</option>
            </select>

            <label for="scenery">Choose scenery level</label>
            <select name="scenery" id="scenery">
                <option value="1" ${scenery == 1 ? 'selected' : ''}>1</option>
                <option value="2" ${scenery == 2 ? 'selected' : ''}>2</option>
                <option value="3" ${scenery == 3 ? 'selected' : ''}>3</option>
                <option value="4" ${scenery == 4 ? 'selected' : ''}>4</option>
                <option value="5" ${scenery == 5 ? 'selected' : ''}>5</option>
            </select>

            <label for="experience">Choose experience level</label>
            <select name="experience" id="experience">
                <option value="1" ${experience == 1 ? 'selected' : ''}>1</option>
                <option value="2" ${experience == 2 ? 'selected' : ''}>2</option>
                <option value="3" ${experience == 3 ? 'selected' : ''}>3</option>
                <option value="4" ${experience == 4 ? 'selected' : ''}>4</option>
                <option value="5" ${experience == 5 ? 'selected' : ''}>5</option>
            </select>

            <label for="condition">Choose condition level</label>
            <select name="condition" id="condition">
                <option value="1" ${condition == 1 ? 'selected' : ''}>1</option>
                <option value="2" ${condition == 2 ? 'selected' : ''}>2</option>
                <option value="3" ${condition == 3 ? 'selected' : ''}>3</option>
                <option value="4" ${condition == 4 ? 'selected' : ''}>4</option>
                <option value="5" ${condition == 5 ? 'selected' : ''}>5</option>
            </select>

            <h2>Define Route:</h2>
            <label>Startpoint</label>
            <div class="coordinate-row">
                <div class="coordinate-input">
                    <input type="text" value="${startLatitude}" placeholder="Latitude" id="startLatitude" name="startLatitude" pattern="(\+|-)?(?:90(?:(?:\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid latitude (-90.0 to +90.0)" required>
                </div>
                <div class="coordinate-input">
                    <input type="text" value="${startLongitude}" placeholder="Longitude" id="startLongitude" name="startLongitude" pattern="^(\+|-)?(?:180(?:(?:\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid longitude (-180.0 to +180.0)" required>
                </div>
            </div>

            <label>Endpoint</label>
            <div class="coordinate-row">
                <div class="coordinate-input">
                    <input type="text" value="${endLatitude}" placeholder="Latitude" id="endLatitude" name="endLatitude" pattern="(\+|-)?(?:90(?:(?:\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid latitude (-90.0 to +90.0)" required>
                </div>
                <div class="coordinate-input">
                    <input type="text" value="${endLongitude}" placeholder="Longitude" id="endLongitude" name="endLongitude" pattern="^(\+|-)?(?:180(?:(?:\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\.[0-9]{1,6})?))$" title="Enter a valid longitude (-180.0 to +180.0)" required>
                </div>
            </div>

            <input class="btn-primary" type="submit" value="Save">
        </form>
    </div>
</body>
<script>
    document.querySelector('form').addEventListener('submit', function() {
        document.getElementById('loadingOverlay').style.display = 'block';
    });
</script>
</html>
