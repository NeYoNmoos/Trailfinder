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
        <script src="https://cdn.tailwindcss.com"></script>
        <title><%= route.getName() %></title>
    </head>
<body class="bg-gray-100">
<jsp:include page="/components/navigation/nav_bar.jsp"/>

<main class="py-10">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <!-- Route Title -->
        <div class="mb-8 d-flex justify-content-between align-items-center ">
            <h1 class="text-3xl font-bold text-gray-900"><%= route.getName() %></h1>
            <% String editPageUrl = "/route-create?routeId=" + route.getRouteId();%>
            <a href="${pageContext.request.contextPath}<%= editPageUrl %>" class="block text-black bg-white rounded-lg shadow p-4 hover:shadow-md hover:scale-[1.05] transition duration-300 overflow-hidden">
                Edit
            </a>
        </div>

        <!-- Route Details -->
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
            <div class="px-4 py-5 sm:p-6">
                <h2 class="text-xl font-bold text-gray-900">Route Details</h2>
                <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Length</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= route.getLength() %> km</dd>
                    </div>
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Altitude</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= route.getAltitude() %> m</dd>
                    </div>
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Location</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= route.getLocation() %></dd>
                    </div>
                    <div class="sm:col-span-1">
                        <%
                            double totalHours = route.getDuration();
                            int wholeHours = (int) totalHours;
                            int wholeMinutes = (int) Math.round((totalHours - wholeHours) * 60);
                        %>
                        <dt class="text-sm font-medium text-gray-500">Duration</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= wholeHours + "h " + wholeMinutes + "min" %></dd>

                    </div>
                </dl>
            </div>
        </div>

        <!-- Attributes -->
        <% if (attributes != null) { %>
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
            <div class="px-4 py-5 sm:p-6">
                <h2 class="text-xl font-bold text-gray-900">Attributes</h2>
                <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Power</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= attributes.getStrength() %></dd>
                    </div>
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Scenery</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= attributes.getScenery() %></dd>
                    </div>
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Experience</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= attributes.getExperience() %></dd>
                    </div>
                    <div class="sm:col-span-1">
                        <dt class="text-sm font-medium text-gray-500">Condition</dt>
                        <dd class="mt-1 text-sm text-gray-900"><%= attributes.getCondition() %></dd>
                    </div>
                </dl>
            </div>
        </div>
        <% } %>

        <!-- Best Time to Visit -->
        <% if (timeOfYear != null) { %>
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
            <div class="px-4 py-5 sm:p-6">
                <h2 class="text-xl font-bold text-gray-900">Best Time to Visit</h2>
                <p class="mt-1 text-sm text-gray-900">
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
                </p>
            </div>
        </div>
        <% } %>

        <!-- Weather -->
        <!-- DORNBIRN:   <div id="ww_87c83204446d5" v='1.3' loc='id' a='{"t":"responsive","lang":"en","sl_lpl":1,"ids":["wl1514"],"font":"Arial","sl_ics":"one","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722","sl_tof":"3"}'>More forecasts: <a href="https://wetterlang.de/wetter_14_tage/" id="ww_87c83204446d5_u" target="_blank">Wettervorhersage 14 tage</a></div><script async src="https://app2.weatherwidget.org/js/?id=ww_87c83204446d5"></script>  -->


        <!-- Coordinates -->
        <% if (coordinates != null && !coordinates.isEmpty()) { %>
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
            <div class="px-4 py-5 sm:p-6">
                <h2 class="text-xl font-bold text-gray-900">Coordinates</h2>
                <ul class="mt-3 grid grid-cols-1 gap-5 sm:gap-6 sm:grid-cols-2 lg:grid-cols-4">
                    <% for(CoordinateEntity coord : coordinates) { %>
                    <li class="col-span-1 bg-white rounded-lg shadow">
                        <div class="w-full flex items-center justify-between p-6 space-x-6">
                            <div class="flex-1 truncate">
                                <div class="flex items-center space-x-3">
                                    <h3 class="text-gray-900 text-sm leading-5 font-medium truncate">Point</h3>
                                </div>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Latitude: <%= coord.getLatitude() %></p>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Longitude: <%= coord.getLongitude() %></p>
                            </div>
                        </div>
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>
    </div>
</main>
</body>
</html>