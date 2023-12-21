<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="at.fhv.hike.data.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 12/20/2023
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine/dist/leaflet-routing-machine.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <script src="https://unpkg.com/leaflet-routing-machine"></script>
    <script src="${pageContext.request.contextPath}/route_detail/fetch_hiking_route.js"></script>
    <title>Summary</title></head>
<body>
<script>
    // Open printing dialog when the page is loaded
    window.onload = function() {

        // Redirect to the previous page after printing
        setTimeout(function() {
            window.print();

            window.history.back();
        }, 1000);
    };
</script>
<%
    RouteEntity route = (RouteEntity) request.getAttribute("route");
    AttributeEntity attributes = route != null ? route.getAttributeEntity() : null;
    List<CoordinateEntity> coordinates = route != null ? route.getCoordinates() : null;

    Collections.sort(coordinates, Comparator.comparingInt(CoordinateEntity::getSequence));

    List<LodgeEntity> huetten = (List<LodgeEntity>) request.getAttribute("huetten");

    List<PointOfInterestEntity> pois = (List<PointOfInterestEntity>) request.getAttribute("pois");
%>

<div class="bg-white shadow overflow-hidden sm:rounded-lg">
    <div class="p-6 text-center justify-center">
        <!-- Route Title -->
        <div class="flex items-center space-x-2 text-center justify-center">
            <h1 class="text-3xl font-bold text-gray-900 text-center justify-center"><%= route.getName() %></h1>
            <%
                LocalDateTime creationDate = route.getCreated_at();
                if(creationDate!=null)
                {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm");
                    String formattedCreationDate = creationDate.format(formatter);
            %>
            <p class="text-gray-400 text-center justify-center"><%= formattedCreationDate %></p>
            <%}else{%>
            <%}%>

            <%
                UserEntity authorName =route.getAuthor();
                if(authorName!=null)
                {
            %>
            <p class="text-gray-400 text-center justify-center">Created by: <%= authorName.getUsername() %></p>
            <%
            }
            else
            {
            %>
            <p class="text-gray-400 text-center justify-center">Created by:</p>
            <%
                }
            %>
        </div>
    </div>
    <div class="px-4 py-5 sm:p-1 text-center">
        <div class="mb-2">
            <%String description=route.getDescription();
                if(description.length()>500)
                    description=description.substring(0,497) + "...";%>
            <p><%=description%> </p>
        </div>
        <div class="flex justify-center">
        <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2 text-center justify-center">
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Altitude</dt>
                <dd class="mt-1 text-sm text-gray-900"><%= route.getAltitude() %> m</dd>
            </div>
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Location</dt>
                <dd class="mt-1 text-sm text-gray-900"><%= route.getLocation() %></dd>
            </div>
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Length</dt>
                <dd class="mt-1 text-sm text-gray-900"><%= route.getLength() %> km</dd>
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
        <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2 text-center justify-center">
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Calculated Length</dt>
                <dd id="calculatedLength" class="mt-1 text-sm text-gray-900">Calculating...</dd>
            </div>
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Calculated Duration</dt>
                <dd id="calculatedDuration" class="mt-1 text-sm text-gray-900">Calculating...</dd>
            </div>
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Calculated Ascent</dt>
                <dd id="calculatedAscent" class="mt-1 text-sm text-gray-900">Calculating...</dd>
            </div>
            <div class="sm:col-span-1">
                <dt class="text-sm font-medium text-gray-500">Calculated Descent</dt>
                <dd id="calculatedDescent" class="mt-1 text-sm text-gray-900">Calculating...</dd>
            </div>
        </dl>
        </div>
    </div>
</div>


<div class="bg-white shadow overflow-hidden sm:rounded-lg">
    <!-- Attributes -->
    <% if (attributes != null) { %>
    <div class="">
        <div class="px-4 py-5 sm:p-6 text-center">
            <h2 class="text-xl font-bold text-gray-900">Attributes</h2>
            <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-4">
                <!-- Power Attribute -->
                <div class="sm:col-span-1">
                    <dt class="text-sm font-medium text-gray-500">Power</dt>
                    <dd class="mt-1 text-sm text-gray-900 flex justify-center">
                        <% for (int i = 1; i <= 5; i++) { %>
                        <i class="<%= i <= attributes.getStrength() ? "fas fa-bolt text-yellow-500 px-1" : "fas fa-bolt text-gray-300 px-1" %>"></i>
                        <% } %>
                    </dd>
                </div>

                <!-- Scenery Attribute -->
                <div class="sm:col-span-1">
                    <dt class="text-sm font-medium text-gray-500">Scenery</dt>
                    <dd class="mt-1 text-sm text-gray-900 flex justify-center">
                        <% for (int i = 1; i <= 5; i++) { %>
                        <i class="<%= i <= attributes.getScenery() ? "fas fa-tree text-green-500 px-1" : "fas fa-tree text-gray-300 px-1" %>"></i>
                        <% } %>
                    </dd>
                </div>

                <!-- Experience Attribute -->
                <div class="sm:col-span-1">
                    <dt class="text-sm font-medium text-gray-500">Experience</dt>
                    <dd class="mt-1 text-sm text-gray-900 flex justify-center">
                        <% for (int i = 1; i <= 5; i++) { %>
                        <i class="<%= i <= attributes.getExperience() ? "fas fa-brain text-purple-500 px-1" : "fas fa-brain text-gray-300 px-1" %>"></i>
                        <% } %>
                    </dd>
                </div>

                <!-- Condition Attribute -->
                <div class="sm:col-span-1">
                    <dt class="text-sm font-medium text-gray-500">Condition</dt>
                    <dd class="mt-1 text-sm text-gray-900 flex justify-center">
                        <% for (int i = 1; i <= 5; i++) { %>
                        <i class="<%= i <= attributes.getCondition() ? "fas fa-heartbeat text-red-500 px-1" : "fas fa-heartbeat text-gray-300 px-1" %>"></i>
                        <% } %>
                    </dd>
                </div>
            </dl>

    <!-- Best Time to Visit -->
            <% if (route.getMonths() > 0) { %>
            <div class="px-4 py-5 sm:p-3 text-center">
                <h2 class="text-xl font-bold text-gray-900">Best Time to Visit</h2>
                <div class="mt-1 flex flex-wrap justify-center gap-1">
                    <%
                        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                        int bm = route.getMonths();
                    %>
                    <% for (int i = 0; i < months.length; i++) { %>
                    <% int mask = (int) Math.pow(2, i); %>
                    <span class="<%= (bm & mask) != 0 ? "bg-green-500 text-white" : "bg-gray-200 text-gray-500" %> rounded px-2 py-1">
                    <%= months[i] %>
                </span>
                    <% } %>
                </div>
            </div>
            <% } %>
        </div>
    </div>
    <% } %>
</div>


<!-- Coordinates -->
<!-- Coordinates on map -->
<% if (coordinates != null && !coordinates.isEmpty()) { %>
<div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">

    <div class="flex justify-center items-center">
        <div id="map" class="h-[400px]" style="max-width:800px; width: 100%;"></div>
    </div>
    <script>
        let firstLat = <%= coordinates.get(0).getLatitude() %>;
        let firstLon = <%= coordinates.get(0).getLongitude() %>;
        let map = L.map

        ('map').setView([firstLat, firstLon], 13);

        /* // default map style
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '© OpenStreetMap contributors'
        }).addTo(map); */

        // thunderforest map style
        L.tileLayer(
            'https://tile.thunderforest.com/outdoors/{z}/{x}/{y}.png?apikey=35adf7fcff8d4453ab157783a4c0f0be',
            { attribution: 'Maps © <a href="https://www.thunderforest.com">Thunderforest</a>, Data © <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>' }
        ).addTo(map);

        // start and end icons
        let startIcon = L.icon({
            iconUrl: '${pageContext.request.contextPath}/assets/icons/home_pin.png',
            iconSize: [40, 40],
            iconAnchor: [20, 41]
        });

        let goalIcon = L.icon({
            iconUrl: '${pageContext.request.contextPath}/assets/icons/goal_pin.png',
            iconSize: [40, 40],
            iconAnchor: [5, 41]
        });

        let poiIcon = L.icon({
            iconUrl: '${pageContext.request.contextPath}/assets/icons/poi_pin.png',
            iconSize: [40, 40],
            iconAnchor: [20, 41]
        });

        let huetteIcon = L.icon({
            iconUrl: '${pageContext.request.contextPath}/assets/icons/huette_pin.png',
            iconSize: [40, 40],
            iconAnchor: [20, 41]
        });

        let waypoints = [];
        <% for (CoordinateEntity coord : coordinates) { %>
        waypoints.push(L.latLng(<%= coord.getLatitude() %>, <%= coord.getLongitude() %>));
        <% } %>

        fetchHikingRoute(waypoints).then(result  => {
            const geojson = result.geojson;
            const details = result.details;

            console.log("details in map: ", details);
            const distanceKm = (details.distance / 1000).toFixed(2);
            document.getElementById('calculatedLength').textContent = distanceKm + ' km';

            // Convert duration from seconds to hours and minutes
            const durationHrs = Math.floor(details.duration / 3600);
            const durationMins = Math.round((details.duration % 3600) / 60);
            document.getElementById('calculatedDuration').textContent = durationHrs + 'h ' + durationMins + 'min';

            const ascent = Math.round(details.ascent * 100)/100;
            const descent = Math.round(details.descent * 100)/100;
            document.getElementById('calculatedAscent').textContent = ascent + 'm';
            document.getElementById('calculatedDescent').textContent = descent + 'm';

            L.geoJSON(geojson).addTo(map);

            // Add Start Marker
            if (waypoints.length > 0) {
                L.marker(waypoints[0], {icon: startIcon}).addTo(map)
                    .bindPopup("Start Point");
            }

            // Add Goal Marker
            if (waypoints.length > 1) {
                L.marker(waypoints[waypoints.length - 1], {icon: goalIcon}).addTo(map)
                    .bindPopup("Goal Point");
            }
        }).catch(error => {
            console.error('Error fetching hiking route:', error);
        });


        let primaryColor = getComputedStyle(document.documentElement).getPropertyValue('--primary').trim();

        // add huetten to map

        function addHuetteMarker(lat, lng, name) {
            let marker = L.marker([lat, lng], {
                draggable: true,
                icon: huetteIcon
            }).addTo(map).bindPopup(name);
        }

        <%
        int i = 0;
        while (i < huetten.size()) {
        %>
        addHuetteMarker(<%=huetten.get(i).getCoordinateEntity().getLatitude()%>,<%=huetten.get(i).getCoordinateEntity().getLongitude()%>, "<%=huetten.get(i).getName()%>");
        <%
            i++;
        }
        %>

        // add Pois to map

        function addPoiMarker(lat, lng, name) {
            let marker = L.marker([lat, lng], {
                draggable: true,
                icon: poiIcon
            }).addTo(map).bindPopup(name);;
        }

        <%
        if(pois != null){
            int j = 0;
            while (j < pois.size()) {
        %>
        addPoiMarker(<%=pois.get(j).getCoordinates().getLatitude()%>,<%=pois.get(j).getCoordinates().getLongitude()%>, "<%=pois.get(j).getName()%>");
        <%
                j++;
            }
        }
        %>

        /*
        // leaflet routing machine
        L.Routing.control({
            waypoints: waypoints,
            lineOptions : {
                color: primaryColor,
                addWaypoints: false,
                styles: [{ color: primaryColor, opacity: 0.95, weight: 4 }]
            },
            createMarker: function(index, wp) {
                return L.marker(wp.latLng).bindPopup(`Waypoint ${index + 1}`);
                    }
                }).addTo(map);
                */

        // used to load the map (without it shows unloaded spots)
        setTimeout(function() {
            map.invalidateSize();
        }, 100);
    </script>

    <!--
            <div class="px-4 py-5 sm:p-6">

                <%if (huetten != null && huetten.size() > 0) {%>
                <h2 class="text-xl font-bold text-gray-900">Huts</h2>
                <ul class="mt-3 grid grid-cols-1 gap-5 sm:gap-6 sm:grid-cols-2 lg:grid-cols-4">
                    <% for(LodgeEntity huette : huetten) { %>
                    <li class="col-span-2 bg-white rounded-lg shadow">
                        <div class="w-full flex items-center justify-between p-6 space-x-6">
                            <div class="flex-1 truncate">
                                <div class="flex items-center space-x-3">
                                    <h3 class="text-gray-900 text-sm leading-5 font-medium truncate"><%=huette.getName()%></h3>
                                </div>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Latitude: <%= huette.getCoordinateEntity().getLatitude() %></p>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Longitude: <%= huette.getCoordinateEntity().getLongitude() %></p>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Description: <%= huette.getDescription() %></p>
                            </div>
                        </div>
                    </li>
                    <% } %>
                </ul>
                <%}%>

                <%if (pois != null && pois.size() > 0) {%>
                <h2 class="text-xl font-bold text-gray-900">Points of Interest</h2>
                <ul class="mt-3 grid grid-cols-1 gap-5 sm:gap-6 sm:grid-cols-2 lg:grid-cols-4">
                    <% for(PointOfInterestEntity poi : pois) { %>
                    <li class="col-span-2 bg-white rounded-lg shadow">
                        <div class="w-full flex items-center justify-between p-6 space-x-6">
                            <div class="flex-1 truncate">
                                <div class="flex items-center space-x-3">
                                    <h3 class="text-gray-900 text-sm leading-5 font-medium truncate"><%=poi.getName()%></h3>
                                </div>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Latitude: <%= poi.getCoordinates().getLatitude() %></p>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Longitude: <%= poi.getCoordinates().getLongitude() %></p>
                                <p class="mt-1 text-gray-500 text-sm leading-5 truncate">Description: <%= poi.getDescription() %></p>
                            </div>
                        </div>
                    </li>
                    <% } %>
                </ul>
                <%}%>
            </div>
            -->
    <% } %>
</div>
</body>
</html>
