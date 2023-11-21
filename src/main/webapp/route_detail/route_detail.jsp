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
        <div class="mb-8 d-flex justify-content-between align-items-center" style="display: flex; justify-content: space-between; align-items: center;">
            <h1 class="text-3xl font-bold text-gray-900"><%= route.getName() %></h1>
            <button id="deleteButton" onclick="myFunction()" data-model-target="deleteModal" data-modal-toggle="deleteModal" class="inline-flex items-center px-4 py-2 bg-red-600 hover:bg-red-700 text-white text-sm font-medium rounded-md" style="float:right">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                </svg>
                Delete
            </button>
        </div>
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
    <script>
        function myFunction() {
            var confirmDelete = confirm("Are you sure you want to delete this route?");
            if (confirmDelete) {
                // Hier kannst du den Code einfügen, um die Route zu löschen, Soft-Delete
                // Zum Beispiel kannst du AJAX verwenden, um die Löschung auf der Serverseite durchzuführen
                // oder eine Formularübermittlung initiieren
                // Hier ist ein Beispiel für eine AJAX-Anfrage
                // Hier müsstest du die Logik implementieren, um die Route zu löschen
                /*

                method: 'DELETE',
            })
            .then(response => {
                // Handle the response
                // Reload the page or update the UI as needed
            })
            .catch(error => {
                console.error('Error:', error);
            });
            */
            } else {
                // Aktion, wenn der Benutzer die Löschung abbricht
            }
        }
    </script>

</main>
</body>
</html>