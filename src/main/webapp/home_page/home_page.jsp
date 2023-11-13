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
    <title>Trailfinder | Find the perfect hiking and skiing trail for you and your friends</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
</head>
<body class="bg-gray-100">
<jsp:include page="/components/navigation/nav_bar.jsp"/>

<main class="container mx-auto px-4 sm:px-6 lg:px-8 pt-8">
    <div class="flex flex-wrap -mx-4">
        <!-- Routes Grid -->
        <div class="w-full lg:w-3/4 px-4 mb-4">
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                <% for (RouteEntity currentRoute : allRoutes) {
                    String detailPageUrl = "route-detail?routeId=" + currentRoute.getRouteId();
                    AttributeEntity attributes = currentRoute.getAttributeEntity();
                    double totalHours = currentRoute.getDuration();
                    int wholeHours = (int) totalHours;
                    int wholeMinutes = (int) Math.round((totalHours - wholeHours) * 60);
                %>
                <a href="<%= detailPageUrl %>" class="block text-black bg-white rounded-lg shadow p-4 hover:shadow-md hover:scale-[1.05] transition duration-300 overflow-hidden">
                    <h2 class="text-xl font-semibold text-gray-800 mb-2"><%= currentRoute.getName() %></h2>
                    <p class="text-gray-600 mb-4 line-clamp-2"><%= currentRoute.getDescription() %></p>
                    <div class="text-sm">
                        <div class="mb-2"><strong>Location:</strong> <%= currentRoute.getLocation() %></div>
                        <div class="flex flex-row">
                            <div class="mb-2 mr-2"><strong>Length:</strong> <%= currentRoute.getLength() %> km</div>
                            <div class="mb-2"><strong>About:</strong> <%= wholeHours %>h <%= wholeMinutes %>m</div>
                        </div>
                        <div class="mb-2"><strong>Altitude:</strong> <%= currentRoute.getAltitude() %> m</div>
                        <% if (attributes != null) { %>
                        <div class="mt-2 flex items-center space-x-2">
                            <i class="fas fa-bolt text-yellow-500" title="Power"></i>
                            <span><%= attributes.getStrength() %></span>
                            <i class="fas fa-tree text-green-500" title="Scenery"></i>
                            <span><%= attributes.getScenery() %></span>
                            <i class="fas fa-brain text-purple-500" title="Experience"></i>
                            <span><%= attributes.getExperience() %></span>
                            <i class="fas fa-heartbeat text-red-500" title="Condition"></i>
                            <span><%= attributes.getCondition() %></span>
                        </div>
                        <% } %>
                    </div>
                </a>
                <% } %>
            </div>
        </div>

        <!-- Filter Card -->
        <div class="w-full lg:w-1/4 px-4">
            <div class="sticky top-32">
                <div class="bg-white rounded-lg shadow p-6">
                    <h2 class="text-xl font-semibold text-gray-800 mb-4">Filters</h2>
                    <form action="${pageContext.request.contextPath}/" method="post">
                        <!-- Length filter -->
                        <div class="mb-4">
                            <label for="lengthMin" class="block text-sm font-medium text-gray-700">Length</label>
                            <div class="flex space-x-2">
                                <input type="number" name="lengthMin" id="lengthMin" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Min">
                                <input type="number" name="lengthMax" id="lengthMax" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Max">
                            </div>
                        </div>
                        <!-- Altitude filter -->
                        <div class="mb-4">
                            <label for="altitudeMin" class="block text-sm font-medium text-gray-700">Altitude</label>
                            <div class="flex space-x-2">
                                <input type="number" name="altitudeMin" id="altitudeMin" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Min">
                                <input type="number" name="altitudeMax" id="altitudeMax" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Max">
                            </div>
                        </div>
                        <!-- Duration filter -->
                        <div class="mb-4">
                            <label for="durationMin" class="block text-sm font-medium text-gray-700">Duration</label>
                            <div class="flex space-x-2">
                                <input type="number" name="durationMin" id="durationMin" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Min">
                                <input type="number" name="durationMax" id="durationMax" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Max">
                            </div>
                        </div>
                        <!-- Power level filter -->
                        <div class="mb-4">
                            <label for="power" class="block text-sm font-medium text-gray-700">Power level</label>
                            <select name="power" id="power" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <!-- Scenery level filter -->
                        <div class="mb-4">
                            <label for="scenery" class="block text-sm font-medium text-gray-700">Scenery level</label>
                            <select name="scenery" id="scenery" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <!-- Experience level filter -->
                        <div class="mb-4">
                            <label for="experience" class="block text-sm font-medium text-gray-700">Experience level</label>
                            <select name="experience" id="experience" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <!-- Condition level filter -->
                        <div class="mb-4">
                            <label for="condition" class="block text-sm font-medium text-gray-700">Condition level</label>
                            <select name="condition" id="condition" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <button type="submit" class="w-full btn-primary text-white rounded-md px-4 py-2 mt-4 ">Apply</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
