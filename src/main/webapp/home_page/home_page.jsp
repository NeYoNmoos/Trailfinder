status
<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="at.fhv.hike.controllers.RouteController" %>
<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="at.fhv.hike.data.GalleryEntity" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 11/2/2023
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RouteEntity> allRoutes = (List<RouteEntity>) request.getAttribute("routes");
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

    <form action="${pageContext.request.contextPath}/" method="post">
        <!-- Search by name -->
        <div class="flex justify-center">
            <input type="text" name="routename" id="routename" class="mt-1 w-96 h-10 text-lg p-2 border-gray-300 rounded-md shadow-sm content-center my-10" placeholder="Search by Name" value="<%= request.getParameter("routename") == null?"":request.getParameter("routename") %>">
            <button type="submit"class="w-25 h-10 btn-primary text-white rounded-md px-4 py-2 mt-1 mx-1">
                <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                </svg>
                <span class="sr-only">Search</span>
            </button>
        </div>


    <div class="flex flex-wrap -mx-4">
        <!-- Routes Grid -->
        <div class="w-full lg:w-3/4 px-4 mb-4">

            <%
                if (allRoutes.size() == 0) {
            %>
            <h1 class="text-center">Could not find any routes!</h1>
            <%
                }
            %>

            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                <%
                    for (RouteEntity currentRoute : allRoutes) {
                    String detailPageUrl = "route-detail?routeId=" + currentRoute.getRouteId();
                    AttributeEntity attributes = currentRoute.getAttributeEntity();
                    double totalHours = currentRoute.getDuration();
                    int wholeHours = (int) totalHours;
                    int wholeMinutes = (int) Math.round((totalHours - wholeHours) * 60);
                %>
                <a href="<%= detailPageUrl %>" class="block text-black bg-white rounded-lg shadow p-4 hover:shadow-md hover:scale-[1.05] transition duration-300 overflow-hidden">
                    <%
                        List<GalleryEntity> gallery = currentRoute.getGallery();
                        if (gallery != null && !gallery.isEmpty()) {
                            byte[] imageBytes = gallery.get(0).getPicture();
                            String base64Image = Base64.getEncoder().encodeToString(imageBytes);%>
                            <img src="data:image/jpeg;base64,<%= base64Image %>" alt="<%= currentRoute.getName() %> Image" class="mb-4 w-full h-40 object-cover object-center rounded-md"><%
                        }
                        else {%>
                            <img src="${pageContext.request.contextPath}/assets/home_page_img/header-image.jpg" alt="<%= currentRoute.getName() %> Image" class="mb-4 w-full h-40 object-cover object-center rounded-md"><%
                        }
                    %>
                    <h2 class="text-xl font-semibold text-gray-800 mb-2"><%= currentRoute.getName() %></h2>
                    <p class="text-gray-600 mb-4 line-clamp-2"><%= currentRoute.getDescription() %></p>
                    <div class="text-sm">
                        <div class="mb-2"><strong>Location:</strong> <%= currentRoute.getLocation() %></div>
                        <div class="flex flex-row">
                            <div class="mb-2 mr-2"><strong>Length:</strong> <%= currentRoute.getLength() %> km</div>
                            <div class="mb-2"><strong>About:</strong> <%= wholeHours %>h <%= wholeMinutes %>min</div>
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

                        <!-- Duration filter -->
                        <div class="mb-4">
                            <label for="durationMin" class="block text-sm font-medium text-gray-700">Duration</label>
                            <div class="flex space-x-2">
                                <input type="number" name="durationMin" id="durationMin" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
                                       placeholder="Min" value="<%= request.getParameter("durationMin") %>">
                                <input type="number" name="durationMax" id="durationMax" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
                                       placeholder="Max" value="<%= request.getParameter("durationMax") %>">
                            </div>
                        </div>
                        <!-- Length filter -->
                        <div class="mb-4">
                            <label for="lengthMin" class="block text-sm font-medium text-gray-700">Length</label>
                            <div class="flex space-x-2">
                                <input type="number" name="lengthMin" id="lengthMin" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
                                       placeholder="Min" value="<%= request.getParameter("lengthMin") %>">
                                <input type="number" name="lengthMax" id="lengthMax" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
                                       placeholder="Max" value="<%= request.getParameter("lengthMax") %>">
                            </div>
                        </div>

                        <!-- Altitude filter -->
                        <div class="mb-4">
                            <label for="altitudeMin" class="block text-sm font-medium text-gray-700">Altitude</label>
                            <div class="flex space-x-2">
                                <input type="number" name="altitudeMin" id="altitudeMin" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
                                       placeholder="Min" value="<%= request.getParameter("altitudeMin") %>">
                                <input type="number" name="altitudeMax" id="altitudeMax" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
                                       placeholder="Max" value="<%= request.getParameter("altitudeMax") %>">
                            </div>
                        </div>

                        <!--Months filter-->
                    <div class="mb-4">
                    <label for="month" class="block text-sm font-medium text-gray-700">Select A Month</label>
                    <select name="month" id="month" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                        <option value="4095" <%="4095".equals(request.getParameter("month")) ? "selected" : "" %>>Select Month</option>
                        <option value="1" <%="1".equals(request.getParameter("month")) ? "selected" : "" %>>January</option>
                        <option value="2" <%="2".equals(request.getParameter("month")) ? "selected" : "" %>>February</option>
                        <option value="4" <%="4".equals(request.getParameter("month")) ? "selected" : "" %>>March</option>
                        <option value="8" <%="8".equals(request.getParameter("month")) ? "selected" : "" %>>April</option>
                        <option value="16" <%="16".equals(request.getParameter("month")) ? "selected" : "" %>>May</option>
                        <option value="32" <%="32".equals(request.getParameter("month")) ? "selected" : "" %>>June</option>
                        <option value="64" <%="64".equals(request.getParameter("month")) ? "selected" : "" %>>July</option>
                        <option value="128" <%="128".equals(request.getParameter("month")) ? "selected" : "" %>>August</option>
                        <option value="256" <%="256".equals(request.getParameter("month")) ? "selected" : "" %>>September</option>
                        <option value="512" <%="512".equals(request.getParameter("month")) ? "selected" : "" %>>October</option>
                        <option value="1024" <%="1024".equals(request.getParameter("month")) ? "selected" : "" %>>November</option>
                        <option value="2048" <%="2048".equals(request.getParameter("month")) ? "selected" : "" %>>December</option>
                    </select>
                    </div>

                    <!-- Power level filter -->
                        <div class="mb-4">
                            <label for="power" class="block text-sm font-medium text-gray-700">Power level</label>
                            <select name="power" id="power" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1" <%= "1".equals(request.getParameter("power")) ? "selected" : "" %>>1</option>
                                <option value="2" <%= "2".equals(request.getParameter("power")) ? "selected" : "" %>>2</option>
                                <option value="3" <%= "3".equals(request.getParameter("power")) ? "selected" : "" %>>3</option>
                                <option value="4" <%= "4".equals(request.getParameter("power")) ? "selected" : "" %>>4</option>
                                <option value="5" <%= "5".equals(request.getParameter("power")) ? "selected" : "" %>>5</option>
                            </select>
                        </div>

                        <!-- Scenery level filter -->
                        <div class="mb-4">
                            <label for="scenery" class="block text-sm font-medium text-gray-700">Scenery level</label>
                            <select name="scenery" id="scenery" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1" <%= "1".equals(request.getParameter("scenery")) ? "selected" : "" %>>1</option>
                                <option value="2" <%= "2".equals(request.getParameter("scenery")) ? "selected" : "" %>>2</option>
                                <option value="3" <%= "3".equals(request.getParameter("scenery")) ? "selected" : "" %>>3</option>
                                <option value="4" <%= "4".equals(request.getParameter("scenery")) ? "selected" : "" %>>4</option>
                                <option value="5" <%= "5".equals(request.getParameter("scenery")) ? "selected" : "" %>>5</option>
                            </select>
                        </div>

                        <!-- Experience level filter -->
                        <div class="mb-4">
                            <label for="experience" class="block text-sm font-medium text-gray-700">Experience level</label>
                            <select name="experience" id="experience" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1" <%= "1".equals(request.getParameter("experience")) ? "selected" : "" %>>1</option>
                                <option value="2" <%= "2".equals(request.getParameter("experience")) ? "selected" : "" %>>2</option>
                                <option value="3" <%= "3".equals(request.getParameter("experience")) ? "selected" : "" %>>3</option>
                                <option value="4" <%= "4".equals(request.getParameter("experience")) ? "selected" : "" %>>4</option>
                                <option value="5" <%= "5".equals(request.getParameter("experience")) ? "selected" : "" %>>5</option>
                            </select>
                        </div>

                        <!-- Condition level filter -->
                        <div class="mb-4">
                            <label for="condition" class="block text-sm font-medium text-gray-700">Condition level</label>
                            <select name="condition" id="condition" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
                                <option value="1" <%= "1".equals(request.getParameter("condition")) ? "selected" : "" %>>1</option>
                                <option value="2" <%= "2".equals(request.getParameter("condition")) ? "selected" : "" %>>2</option>
                                <option value="3" <%= "3".equals(request.getParameter("condition")) ? "selected" : "" %>>3</option>
                                <option value="4" <%= "4".equals(request.getParameter("condition")) ? "selected" : "" %>>4</option>
                                <option value="5" <%= "5".equals(request.getParameter("condition")) ? "selected" : "" %>>5</option>
                            </select>
                        </div>

                        <button type="submit" class="w-full btn-primary rounded-md px-4 py-2 mt-4">Apply</button>

                </div>
            </div>
        </div>
    </div>
    </form>

</main>
</body>
</html>
