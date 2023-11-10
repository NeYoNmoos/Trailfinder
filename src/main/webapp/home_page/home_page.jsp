<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="at.fhv.hike.controllers.RouteController" %>
<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %><%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 11/2/2023
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    List<RouteEntity> allRoutes = (List<RouteEntity>) request.getAttribute("allRoutes");
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
<div class="route-content">
<div class="route-boxes">
    <%
        for (int i = 0; i < allRoutes.size(); i++) {
            RouteEntity currentRoute = allRoutes.get(i);
            String detailPageUrl = "route-detail?routeId=" + currentRoute.getRouteId(); %>
            <a href="<%= detailPageUrl %>" class="route-box-link">
                <div class="route-box">
                    <div class="route-info">
                        <h2><%=currentRoute.getName()%></h2>

                        <tr class="route-data">
                            <td><%
                            AttributeEntity attributes = currentRoute != null ? currentRoute.getAttributeEntity() : null;
                            if (attributes != null) { %>
                            <td>Power: <%= attributes.getStrength() %>, </td>
                            <td>Scenery: <%= attributes.getScenery() %>, </td>
                            <td>Experience: <%= attributes.getExperience() %>, </td>
                            <td>Condition: <%= attributes.getCondition() %></td>
                            <% } %>
                        </tr>
                        <p>
                            <%=currentRoute.getDescription()%>
                        </p>
                    </div>
                    <tr class="route-data">
                        <td>Length: <%=currentRoute.getLength()%>km, </td>
                        <td>Altitude: <%=currentRoute.getAltitude()%>m, </td>
                        <td>Duration: <%=currentRoute.getDuration()%>h, </td>
                        <td>Location: <%=currentRoute.getLocation()%></td>
                    </tr>
                </div>
            </a>
        <% }
    %>
    </div>
    <div class="filter-box">
        <div class="filter-header"><h1>Filters</h1></div>
        <div class="filter-content">
            <form action="${pageContext.request.contextPath}/" method="post">
            <div class="filter-label">Length</div>
            <div class="filter-inputs">
                <input type="text" name="lengthMin" id="lengthMin" placeholder="Min" class="filter-input">
                <span>to</span>
                <input type="number" name="lengthMax" id="lengthMax" placeholder="Max" class="filter-input">
            </div>
            <div class="filter-label">Altitude</div>
            <div class="filter-inputs">
                <input type="number" name="altitudeMin" id="altitudeMin" placeholder="Min" class="filter-input">
                <span>to</span>
                <input type="number" name="altitudeMax" id="altitudeMax" placeholder="Max" class="filter-input">
            </div>
            <div class="filter-label">Duration</div>
            <div class="filter-inputs">
                <input type="number" name="durationMin" id="durationMin" placeholder="Min" class="filter-input">
                <span>to</span>
                <input type="number" name="durationMax" id="durationMax" placeholder="Max" class="filter-input">
            </div>
            <div class="filter-label">Months</div>
                <div class="filter-inputs">
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
                </div>

                <div class="filter-label">Power level</div>
                <div class="filter-inputs">
                <select name="power" id="power">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                </div>
                <div class="filter-label">Scenery level</div>
                <div class="filter-inputs">
                <select name="scenery" id="scenery">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                </div>

                <div class="filter-label">Experience level</div>
                <div class="filter-inputs">
                <select name="experience" id="experience">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                </div>

                <div class="filter-label">Condition level</div>
                <div class="filter-inputs">
                <select name="condition" id="condition">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                </div>

                <button type="submit" id="applyButton">Apply</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
