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
<%
    String submitted = request.getParameter("submitted");

    if ("true".equals(submitted)) {
        String name = request.getParameter("name");
        double length = Double.parseDouble(request.getParameter("length"));
        double altitude = Double.parseDouble(request.getParameter("altitude"));
        String location = request.getParameter("location");
        double duration = Double.parseDouble(request.getParameter("duration"));
        String description = request.getParameter("description");

        String[] months = request.getParameterValues("months");
        Integer power = Integer.parseInt(request.getParameter("power"));
        Integer scenery = Integer.parseInt(request.getParameter("scenery"));
        Integer experience = Integer.parseInt(request.getParameter("experience"));
        Integer condition = Integer.parseInt(request.getParameter("condition"));

        TimeOfYearEntity newMonths = new TimeOfYearEntity();
        newMonths.setMonthId(UUID.randomUUID().toString());
        newMonths.setJanuary(false);
        newMonths.setFebruary(false);
        newMonths.setMarch(false);
        newMonths.setApril(false);
        newMonths.setMay(false);
        newMonths.setJune(false);
        newMonths.setJuly(false);
        newMonths.setAugust(false);
        newMonths.setSeptember(false);
        newMonths.setOctober(false);
        newMonths.setNovember(false);
        newMonths.setDecember(false);
        if(months != null){
            for (int i = 0; i < months.length; i++) {
                System.out.println("FOR LOOP");
                switch (months[i]) {
                    case "january":
                        newMonths.setJanuary(true);
                        break;
                    case "february":
                        newMonths.setFebruary(true);
                        break;
                    case "march":
                        newMonths.setMarch(true);
                        break;
                    case "april":
                        newMonths.setApril(true);
                        break;
                    case "may":
                        newMonths.setMay(true);
                        break;
                    case "june":
                        newMonths.setJune(true);
                        break;
                    case "july":
                        newMonths.setJuly(true);
                        break;
                    case "august":
                        newMonths.setAugust(true);
                        break;
                    case "september":
                        newMonths.setSeptember(true);
                        break;
                    case "october":
                        newMonths.setOctober(true);
                        break;
                    case "november":
                        newMonths.setNovember(true);
                        break;
                    case "december":
                        newMonths.setDecember(true);
                        break;
                }
            }
        }

        AttributeEntity newAttributes = new AttributeEntity();
        newAttributes.setAttributeId(UUID.randomUUID().toString());
        newAttributes.setStrength(power);
        newAttributes.setScenery(scenery);
        newAttributes.setExperience(experience);
        newAttributes.setCondition(condition);

        RouteEntity newRoute = new RouteEntity();
        newRoute.setRouteId(UUID.randomUUID().toString());
        newRoute.setName(name);
        newRoute.setLength(length);
        newRoute.setAltitude(altitude);
        newRoute.setLocation(location);
        newRoute.setDuration(duration);
        newRoute.setDescription(description);
        //newRoute.setAttributeId(newAttributes.getAttributeId());
        //newRoute.setMonthId(newMonths.getMonthId());
        newRoute.setAttributeEntity(newAttributes);
        newRoute.setTimeOfYearEntity(newMonths);

        CoordinateEntity startCoord = new CoordinateEntity();
        startCoord.setCoordinateId(UUID.randomUUID().toString());
        startCoord.setSequence(0);
        startCoord.setLatitude(Double.parseDouble(request.getParameter("startLatitude")));
        startCoord.setLongitude(Double.parseDouble(request.getParameter("startLongitude")));

        CoordinateEntity endCoord = new CoordinateEntity();
        endCoord.setCoordinateId(UUID.randomUUID().toString());
        endCoord.setSequence(1);
        endCoord.setLatitude(Double.parseDouble(request.getParameter("endLatitude")));
        endCoord.setLongitude(Double.parseDouble(request.getParameter("endLongitude")));


        newRoute.addCoordinate(startCoord);
        newRoute.addCoordinate(endCoord);

        TrailfinderDatabaseFacade facade = new TrailfinderDatabaseFacade();

        RouteController controller = new RouteController();
        controller.createRoute(newRoute);

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

    <h1>Create a new Route</h1>

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
