<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="at.fhv.hike.data.*" %>
<%@ page import="java.util.*" %><%--
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="at.fhv.hike.data.*" %><%--
<%@ page import="at.fhv.hike.data.*" %>
<%@ page import="java.util.ArrayList" %><%--
<%@ page import="java.util.List" %>
<%@ page import="java.net.URI" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="at.fhv.hike.data.Bitmask"%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 12.12.2023
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <script src="https://cdn.tailwindcss.com"></script>

    <title>Personal List</title>
</head>
<body>
    <jsp:include page="/components/navigation/nav_bar.jsp"/>
    <%
        List<RouteEntity> routes = (List<RouteEntity>) request.getAttribute("list");
    %>

    <div class="w-full lg:w-3/4 px-4 mb-4">

        <%
            if (routes.size() == 0) {
        %>
        <h1 class="text-center">Could not find any routes!</h1>
        <%
            }
        %>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            <%
                for (RouteEntity currentRoute : routes) {
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
</body>
</html>
