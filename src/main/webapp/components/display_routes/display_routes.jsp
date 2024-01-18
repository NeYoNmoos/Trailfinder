<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="at.fhv.hike.data.GalleryEntity" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
<div class="main">
    <%
        List<RouteEntity> routes = (List<RouteEntity>) request.getAttribute("routes");
    %>
    <h1>${param.title}</h1>

    <div class="w-full lg:w-3/4 px-4 mb-4">

        <%
            if (routes != null && routes.size() == 0) {
        %>
        <h1 class="text-center">Could not find any routes!</h1>
        <%
            }
        %>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-1 gap-4">
            <%
                for (RouteEntity currentRoute : routes) {
                    String detailPageUrl = "route-detail?routeId=" + currentRoute.getRouteId();
                    AttributeEntity attributes = currentRoute.getAttributeEntity();
                    double totalHours = currentRoute.getDuration();
                    int wholeHours = (int) totalHours;
                    int wholeMinutes = (int) Math.round((totalHours - wholeHours) * 60);
            %>
            <a href="<%= detailPageUrl %>" class="flex text-black bg-white rounded-lg shadow p-4 hover:shadow-md hover:scale-[1.05] transition duration-300 overflow-hidden mb-4">
                <%
                    List<GalleryEntity> gallery = currentRoute.getGallery();
                    if (gallery != null && !gallery.isEmpty()) {
                        byte[] imageBytes = gallery.get(0).getPicture();
                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                %>
                <img src="data:image/jpeg;base64,<%= base64Image %>" alt="<%= currentRoute.getName() %> Image" class="w-1/3 h-40 object-cover object-center rounded-md mr-4">
                <%
                }
                else {
                %>
                <img src="${pageContext.request.contextPath}/assets/home_page_img/header-image.jpg" alt="<%= currentRoute.getName() %> Image" class="w-1/3 h-40 object-cover object-center rounded-md mr-4">
                <%
                    }
                %>
                <div class="w-2/3">
                    <h2 class="text-xl font-semibold text-gray-800 mb-2"><%= currentRoute.getName() %></h2>
                    <p class="text-gray-600 mb-4 line-clamp-2"><%= currentRoute.getDescription() %></p>
                    <div class="text-sm">
                        <div class="mb-2"><strong>Location:</strong> <%= currentRoute.getLocation() %></div>
                        <div class="flex flex-row mb-2">
                            <div class="mr-2"><strong>Length:</strong> <%= currentRoute.getLength() %> km</div>
                            <div><strong>About:</strong> <%= wholeHours %>h <%= wholeMinutes %>min</div>
                        </div>
                        <div class="mb-2"><strong>Altitude:</strong> <%= currentRoute.getAltitude() %> m</div>
                        <% if (attributes != null) { %>
                        <div class="flex items-center space-x-2">
                            <i class="fas fa-bolt text-yellow-500" title="Power"></i>
                            <span><%= (attributes).getStrength() %></span>
                            <i class="fas fa-tree text-green-500" title="Scenery"></i>
                            <span><%= attributes.getScenery() %></span>
                            <i class="fas fa-brain text-purple-500" title="Experience"></i>
                            <span><%= attributes.getExperience() %></span>
                            <i class="fas fa-heartbeat text-red-500" title="Condition"></i>
                            <span><%= attributes.getCondition() %></span>
                        </div>
                        <% } %>
                    </div>
                </div>
            </a>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
