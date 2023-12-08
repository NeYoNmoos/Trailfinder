<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="java.util.UUID" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="at.fhv.hike.controllers.RouteController" %>
<%@ page import="at.fhv.hike.data.CoordinateEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="at.fhv.hike.data.LodgeEntity" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 03/11/2023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RouteEntity route = (RouteEntity) request.getAttribute("route");
    List<CoordinateEntity> coordinates = route != null ? route.getCoordinates() : null;

    if(coordinates != null){
        Collections.sort(coordinates, Comparator.comparingInt(CoordinateEntity::getSequence));
    }
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine/dist/leaflet-routing-machine.css" />
    <script src="https://unpkg.com/leaflet-routing-machine"></script>
    <script src="${pageContext.request.contextPath}/route_detail/fetch_hiking_route.js"></script>
    <title>Create New Route</title>
</head>
<body>
    <jsp:include page="/components/navigation/nav_bar.jsp"/>

    <div class="route-form-container">
        <div class="loading-overlay" id="loadingOverlay">
            <div class="loader"></div>
        </div>
        <form id="createform" action="${pageContext.request.contextPath}/route-create" method="post">
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

            <!-- route creation via map -->
            <h2>Define Route:</h2>
            <div class="route-instructions">
                <h3>How to Create Your Route</h3>
                <ul>
                    <li><strong>Creating Waypoints:</strong> Click on the map to create a new waypoint. The route will automatically update to include the new point.</li>
                    <li><strong>Dragging Waypoints:</strong> Drag any waypoint to a new location to modify the route.</li>
                    <li><strong>Removing Waypoints:</strong> Click on an existing waypoint to remove it from the route.</li>
                </ul>
            </div>
            <div class="map-container">
                <div id="routeMap" style="height: 400px;"></div>
            </div>
            <script>
                // Initialize the map
                <%
                    double defaultLatitude = 47.406121;  // Default latitude value
                    double defaultLongitude = 9.742360; // Default longitude value

                    double mapLatitude = (coordinates != null && !coordinates.isEmpty()) ? coordinates.get(0).getLatitude() : defaultLatitude;
                    double mapLongitude = (coordinates != null && !coordinates.isEmpty()) ? coordinates.get(0).getLongitude() : defaultLongitude;
                %>

                let map = L.map('routeMap').setView([<%= mapLatitude %>, <%= mapLongitude %>], 13);
                // set map style
                L.tileLayer(
                    'https://tile.thunderforest.com/outdoors/{z}/{x}/{y}.png?apikey=35adf7fcff8d4453ab157783a4c0f0be',
                    { attribution: 'Maps © <a href="https://www.thunderforest.com">Thunderforest</a>, Data © <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>' }
                ).addTo(map);

                // start, middle and end icons
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

                let middleIcon = L.icon({
                    iconUrl: '${pageContext.request.contextPath}/assets/icons/middle_pin.png', // Path to your middle icon image
                    iconSize: [25, 25], // Size of the icon
                    iconAnchor: [12, 25] // Anchor point of the icon
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

                // route layer
                let currentRouteLayer = null;

                // Array to store waypoints
                let waypoints = [];

                // Array to store huetten
                let huetten = [];

                // Array to store points of interest
                let pois = [];

                // parse waypoints from existing route for editing
                <% if (coordinates != null){ %>
                    <% for (CoordinateEntity coord : coordinates) { %>
                    addMarker(<%= coord.getLatitude() %>, <%= coord.getLongitude() %>)
                    <% } %>
                    fetchAndDrawRoute();
                <% } %>

                // Function to add marker
                function addMarker(lat, lng) {
                    let marker = L.marker([lat, lng], {
                        draggable: true,
                        icon: waypoints.length === 0 ? startIcon : L.Icon.Default.prototype
                    }).addTo(map);

                    marker.on('dragend', function() {
                        updateRoute();
                    });

                    marker.on('click', function() {
                        map.removeLayer(marker);
                        waypoints = waypoints.filter(wp => wp !== marker);
                        updateRoute();
                    });

                    waypoints.push(marker);
                    updateIcons();
                    updateRoute();
                }

                //Function to add point of interest marker
                function addPoiMarker(lat, lng, poiMarkerData) {
                    let marker = L.marker([lat, lng], {
                        markerData: poiMarkerData,
                        draggable: true,
                        icon: poiIcon
                    }).addTo(map);

                    marker.on('contextmenu', function() {
                        pois = pois.filter(poi => poi !== marker);
                        map.removeLayer(marker);
                    });
                    pois.push(marker);
                }

                //Function to add huette marker
                function addHuetteMarker(lat, lng, huetteMarkerData) {
                    let marker = L.marker([lat, lng], {
                        markerData: huetteMarkerData,
                        draggable: true,
                        icon: huetteIcon
                    }).addTo(map);

                    marker.on('contextmenu', function() {
                        huetten = huetten.filter(huette => huette !== marker);
                        map.removeLayer(marker);
                    });
                    huetten.push(marker);
                }

                // Map click event to add markers
                map.on('click', function(e) {
                    addMarker(e.latlng.lat, e.latlng.lng);
                });

                // Map click event to add point of interest or huette
                map.on('contextmenu', function(e) {
                    if (e.originalEvent.ctrlKey && e.originalEvent.button === 2) {
                        let huetteName = prompt("Please enter the name of your hut:", "");
                        let huetteDescription = prompt("Please enter a description for your hut:", "");
                        if ((huetteName != null) && (huetteName != "") && (huetteDescription != null) && (huetteDescription != "")) {
                            var huetteMarkerData = {
                                name: huetteName,
                                description: huetteDescription
                            }
                            addHuetteMarker(e.latlng.lat, e.latlng.lng, huetteMarkerData);
                        }
                    }
                    else {
                        let poiName = prompt("Please enter the name of your point of interest:", "");
                        let poiDescription = prompt("Please enter a description for your point of interest:", "");
                        if ((poiName != null) && (poiName != "") && (poiDescription != null) && (poiDescription != "")) {
                            var poiMarkerData = {
                                name: poiName,
                                description: poiDescription
                            }
                            addPoiMarker(e.latlng.lat, e.latlng.lng, poiMarkerData);
                        }
                    }
                });

                let geoJsonWaypoints = [];

                // Function to draw the fetched hiking route on the map
                function drawFetchedRoute(geojson) {
                    if (currentRouteLayer) {
                        map.removeLayer(currentRouteLayer);
                    }
                    currentRouteLayer = L.geoJSON(geojson).addTo(map);
                }

                // Function to fetch and draw the hiking route based on waypoints
                function fetchAndDrawRoute() {
                    if (waypoints.length >= 2) {
                        fetchHikingRoute(waypoints.map(marker => marker.getLatLng()))
                            .then(geojson => {
                                drawFetchedRoute(geojson.geojson);
                            })
                            .catch(error => {
                                console.error('Error fetching hiking route:', error);
                            });
                    }
                }

                // Function to update the displayed route
                async function updateRoute() {
                    if (currentRouteLayer) {
                        map.removeLayer(currentRouteLayer); // Remove the old route layer
                        currentRouteLayer = null;
                    }

                    fetchAndDrawRoute(); // This now uses the separated function to fetch and draw the route
                }

                function updateIcons() {
                    if (waypoints.length > 0) {
                        waypoints[0].setIcon(startIcon);
                        if (waypoints.length > 1) {
                            waypoints.forEach((wp, index) => {
                                if (index > 0 && index < waypoints.length - 1) {
                                    wp.setIcon(middleIcon);
                                }
                            });
                            waypoints[waypoints.length - 1].setIcon(goalIcon);
                        }
                    }
                }

                fetchHikingRoute(waypoints).then(geojson => {
                    L.geoJSON(waypoints).addTo(map);
                }).catch(error => {
                    console.error('Error fetching hiking route:', error);
                });

                function addCoordinatesToForm() {
                    // Clear any existing coordinate inputs
                    document.querySelectorAll('.dynamic-coord').forEach(el => el.remove());

                    waypoints.forEach((marker, index) => {
                        // Create inputs for latitude, longitude, and sequence
                        const latInput = createHiddenInput('coords_' + index + '_latitude', marker.getLatLng().lat);
                        const lngInput = createHiddenInput('coords_' + index + '_longitude', marker.getLatLng().lng);
                        const seqInput = createHiddenInput('coords_' +index + '_sequence', index);

                        // Append inputs to the form
                        document.querySelector('form').appendChild(latInput);
                        document.querySelector('form').appendChild(lngInput);
                        document.querySelector('form').appendChild(seqInput);
                    });

                    // Create inputs for Point of Interest
                    pois.forEach((marker, i) => {
                        const latInputPoi = createHiddenInput('poi_' + i + '_latitude', marker.getLatLng().lat);
                        const lngInputPoi = createHiddenInput('poi_' + i + '_longitude', marker.getLatLng().lng);
                        const nameInputPoi = createHiddenInput('poi_' + i + '_name', marker.options.markerData.name);
                        const descriptionInputPoi = createHiddenInput('poi_' + i + '_description', marker.options.markerData.description);


                        document.querySelector('form').appendChild(latInputPoi);
                        document.querySelector('form').appendChild(lngInputPoi);
                        document.querySelector('form').appendChild(nameInputPoi);
                        document.querySelector('form').appendChild(descriptionInputPoi);

                    });

                    // Create inputs for huette
                    huetten.forEach((marker, j) => {
                        const latInputHuette = createHiddenInput('huette_' + j + '_latitude', marker.getLatLng().lat);
                        const lngInputHuette = createHiddenInput('huette_' + j + '_longitude', marker.getLatLng().lng);
                        const nameInputHuette = createHiddenInput('huette_' + j + '_name', marker.options.markerData.name);
                        const descriptionInputHuette = createHiddenInput('huette_' + j + '_description', marker.options.markerData.description);


                        document.querySelector('form').appendChild(latInputHuette);
                        document.querySelector('form').appendChild(lngInputHuette);
                        document.querySelector('form').appendChild(nameInputHuette);
                        document.querySelector('form').appendChild(descriptionInputHuette);

                    });
                }

                function createHiddenInput(name, value) {
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = name;
                    input.value = value;
                    input.classList.add('dynamic-coord');
                    return input;
                }
            </script>
            <!--
            <label>Startpoint</label>
            <div class="coordinate-row">
                <div class="coordinate-input">
                    <input type="text" value="${startLatitude}" placeholder="Latitude" id="startLatitude" name="startLatitude" pattern="(\+|-)?(?:90(?:(?:\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\.[0-9]{1,15})?))$" title="Enter a valid latitude (-90.0 to +90.0)" required>
                </div>
                <div class="coordinate-input">
                    <input type="text" value="${startLongitude}" placeholder="Longitude" id="startLongitude" name="startLongitude" pattern="^(\+|-)?(?:180(?:(?:\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\.[0-9]{1,15})?))$" title="Enter a valid longitude (-180.0 to +180.0)" required>
                </div>
            </div>

            <label>Endpoint</label>
            <div class="coordinate-row">
                <div class="coordinate-input">
                    <input type="text" value="${endLatitude}" placeholder="Latitude" id="endLatitude" name="endLatitude" pattern="(\+|-)?(?:90(?:(?:\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\.[0-9]{1,15})?))$" title="Enter a valid latitude (-90.0 to +90.0)" required>
                </div>
                <div class="coordinate-input">
                    <input type="text" value="${endLongitude}" placeholder="Longitude" id="endLongitude" name="endLongitude" pattern="^(\+|-)?(?:180(?:(?:\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\.[0-9]{1,15})?))$" title="Enter a valid longitude (-180.0 to +180.0)" required>
                </div>
            </div>
            -->

            <%
                //List of huetten
                List<LodgeEntity> allHuetten = (List<LodgeEntity>) request.getAttribute("allHuetten");
                if (allHuetten != null) {
                    int k = 0;
            %>
            <label for="existingHuetten">Choose already existing huts (hold 'Ctrl' or 'Command' while clicking to select multiple)</label>
            <select name="existingHuetten" id="existingHuetten" multiple size="<%=allHuetten.size()%>">

            <%
                    while(k < allHuetten.size()) {



            %>
                <option value="<%=allHuetten.get(k).getLodgeId()%>"}><%=allHuetten.get(k).getName()%></option>
            <%
                    k++;
                    }
                }
            %>
            </select>

            <input type="hidden" name="routeId" value="<%= request.getParameter("routeId") %>">

            <input class="btn-primary" type="submit" value="Save">
        </form>
    </div>
</body>
<script>
    document.querySelector('form').addEventListener('submit', function() {
        addCoordinatesToForm(); // adds coordinates to the form before submitting
        document.getElementById('loadingOverlay').style.display = 'block';
    });
</script>
</html>

