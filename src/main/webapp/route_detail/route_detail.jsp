<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="at.fhv.hike.data.*" %><%--
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="at.fhv.hike.data.*" %><%--
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
  User: matth
  Date: 01/11/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    RouteEntity route = (RouteEntity) request.getAttribute("route");
    AttributeEntity attributes = route != null ? route.getAttributeEntity() : null;
    List<CoordinateEntity> coordinates = route != null ? route.getCoordinates() : null;

    Collections.sort(coordinates, Comparator.comparingInt(CoordinateEntity::getSequence));
%>

<html>
    <head>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine/dist/leaflet-routing-machine.css" />
        <script src="https://unpkg.com/leaflet-routing-machine"></script>
        <script src="${pageContext.request.contextPath}/route_detail/fetch_hiking_route.js"></script>
        <title><%= route.getName() %></title>
    </head>
<body class="bg-gray-100">
<jsp:include page="/components/navigation/nav_bar.jsp"/>

<main class="py-10">
    <div class="max-w-7xl lg:w-[75vw] mx-auto px-4 sm:px-6 lg:px-8">
        <!-- Route Title -->
        <div class="mb-8 flex flex-row justify-between">
            <h1 class="text-3xl font-bold text-gray-900"><%= route.getName() %></h1>
            <% String editPageUrl = "/route-create?routeId=" + route.getRouteId(); %>
            <div class="flex flex-row">
            <a href="${pageContext.request.contextPath}<%= editPageUrl %>"
               class="inline-flex items-center ml-2 text-white bg-blue-500 rounded-lg px-3 py-1 hover:bg-blue-700 transition duration-300" style="float:right">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                </svg>
                Edit
            </a>

            <script>
                function deleteFunction() {
                    var confirmDelete = confirm("Are you sure you want to delete this route?");
                    if (confirmDelete) {
                        var deletePage=<%=route.getRouteId()%>;
                        window.location.href = "${pageContext.request.contextPath}/delete?routeId="+deletePage;
                    }
                }
            </script>
            <button onclick="deleteFunction()" id="deleteButton" data-model-target="deleteModal" data-modal-toggle="deleteModal" class="inline-flex items-center ml-2 px-4 py-2 bg-red-600 hover:bg-red-700 text-white text-sm font-medium rounded-md" style="float:right">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                </svg>
                Delete
            </button>
            </div>
        </div>
    </div>

        <!-- Route Details -->
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
            <div class="px-4 py-5 sm:p-6 flex justify-between items-center">
                <h2 class="text-xl font-bold text-gray-900 mb-1 sm:mb-0">Route Details</h2>

                <form action="https://www.google.com/maps/dir/" method="get" target="_blank">
                    <input type="hidden" name="api" value="1">
                    <% if (coordinates != null && !coordinates.isEmpty()) { %>
                    <button type="submit" name="destination" value="<%= coordinates.get(0).getLatitude() %>,<%= coordinates.get(0).getLongitude() %>" class="w-48 btn-primary rounded-md px-4 py-2">How to get there</button>
                    <% } else { %>
                    <p>No coordinates available</p>
                    <% } %>
                </form>
            </div>
            <div class="px-4 py-5 sm:p-6">
                <div class="mb-2">
                    <p><%= route.getDescription() %> </p>
                </div>
                <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
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
                <div class="flex space-x-4">
                <%// Assuming route.getCreated_at() returns a LocalDateTime object
                    LocalDateTime creationDate = route.getCreated_at();
                    if(creationDate!=null)
                    {
                    // Define the desired date-time format without fractional seconds
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy' 'HH:mm:ss");

                    // Format the LocalDateTime object
                    String formattedCreationDate = creationDate.format(formatter);
                    %><p class="text-gray-400">Creation date: <%= formattedCreationDate %></p>
                  <%}else
                    {%>
                    <p class="text-gray-400">Creation date:</p>
                <%}%>

                <%
                    UserEntity authorName =route.getAuthor();
                    if(authorName!=null)
                    {
                    %><p class="text-gray-400">Created by: <%= authorName.getUsername() %></p><%
                    }
                    else
                    {
                        %><p class="text-gray-400">Created by:</p><%
                    }
                %>
                </div>
                <!--Images slide show-->
                <div class="container mx-auto p-4">
                    <!-- Slideshow Container -->
                    <div id="slideshow" class="max-w-screen-md mx-auto relative">
                        <%
                            List<String> images=new LinkedList<>();
                            images.add("https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?cs=srgb&dl=pexels-pixabay-268533.jpg&fm=jpg");
                            images.add("data:image/jpeg;base64,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");
                            int i=0;
                            for (String img:images) {
                                i=i+1;
                        %>
                        <div class="mySlides fade">
                            <div class="text-white absolute top-0 left-0 p-2"><%=i%> / <%=images.size()%></div>
                            <img src="<%=img%>" class="w-full h-auto">
                        </div>
                        <%
                            }
                        %>
                        <!-- Next and previous buttons -->
                        <a class="prev absolute top-1/2 left-0 transform -translate-y-1/2 cursor-pointer p-4 text-white font-bold text-xl transition duration-500 ease-in-out bg-black bg-opacity-50 border-r-4 border-gray-800" onclick="plusSlides(-1)">&#10094;</a>
                        <a class="next absolute top-1/2 right-0 transform -translate-y-1/2 cursor-pointer p-4 text-white font-bold text-xl transition duration-500 ease-in-out bg-black bg-opacity-50 border-l-4 border-gray-800" onclick="plusSlides(1)">&#10095;</a>
                    </div>

                    <!-- JavaScript for Slideshow -->
                    <script>
                        let slideIndex = 1;
                        showSlides(slideIndex);

                        function plusSlides(n) {
                            showSlides(slideIndex += n);
                        }

                        function currentSlide(n) {
                            showSlides(slideIndex = n);
                        }

                        function showSlides(n) {
                            let i;
                            let slides = document.getElementsByClassName("mySlides");
                            let dots = document.getElementsByClassName("dot");
                            if (n > slides.length) {slideIndex = 1}
                            if (n < 1) {slideIndex = slides.length}
                            for (i = 0; i < slides.length; i++) {
                                slides[i].style.display = "none";
                            }
                            for (i = 0; i < dots.length; i++) {
                                dots[i].className = dots[i].className.replace(" active", "");
                            }
                            slides[slideIndex-1].style.display = "block";
                            dots[slideIndex-1].className += " active";
                        }
                    </script>
                </div>
            </div>
    </div>
    <div class="flex mb-6">
        <!-- Left side -->
        <div class="w-1/2 mr-4">
            <!-- Attributes -->
            <% if (attributes != null) { %>
            <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
                <div class="px-4 py-5 sm:p-6 text-center">
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
                    <!-- Best Time to Visit -->
                    <% if (route.getMonths() > 0) { %>
                    <div class="px-4 py-5 sm:p-6 text-center">
                        <h2 class="text-xl font-bold text-gray-900">Best Time to Visit</h2>
                        <p class="mt-1 text-sm text-gray-900">
                            <% int bm = route.getMonths(); %>
                            <%= (bm & Bitmask.Month_1_Jan) != 0 ? "January" : "" %>
                            <%= (bm & Bitmask.Month_2_Feb) != 0 ? "February " : "" %>
                            <%= (bm & Bitmask.Month_3_Mar) != 0 ? "March " : "" %>
                            <%= (bm & Bitmask.Month_4_Apr) != 0 ? "April " : "" %>
                            <%= (bm & Bitmask.Month_5_May) != 0 ? "May " : "" %>
                            <%= (bm & Bitmask.Month_6_Jun) != 0 ? "June " : "" %>
                            <%= (bm & Bitmask.Month_7_Jul) != 0 ? "July " : "" %>
                            <%= (bm & Bitmask.Month_8_Aug) != 0 ? "August " : "" %>
                            <%= (bm & Bitmask.Month_9_Sep) != 0 ? "September " : "" %>
                            <%= (bm & Bitmask.Month_10_Oct) != 0 ? "October " : "" %>
                            <%= (bm & Bitmask.Month_11_Nov) != 0 ? "November " : "" %>
                            <%= (bm & Bitmask.Month_12_Dec) != 0 ? "December " : "" %>
                        </p>
                    </div>
                    <% } %>
                </div>
            </div>
            <% } %>
        </div>

        <!-- Right side -->
        <div class="w-1/2">
            <!-- Weather -->
            <div class="bg-white shadow overflow-hidden sm:rounded-lg">
                <div class="px-4 py-5 sm:p-6 flex flex-col items-center text-center">
                    <h2 class="text-xl font-bold text-gray-900">Weather</h2>
                    <div class="wrapper">
                        <div class="weather-part">
                            <div class="flex items-center justify-center mb-4">
                                <img src="#" alt="Weather Icon" />
                            </div>
                            <div class="temp1">
                                <span class="numb">_</span>
                                <span class="deg">°C</span>
                            </div>
                            <div class="weather">_</div>
                            <div class="location">
                                <i class="bx bx-map"></i>
                                <span>,</span>
                            </div>
                            </br>
                            <div class="bottom-details flex">
                                <!-- Feels like column -->
                                <div class="column feels flex items-center">
                                    <i class="bx bxs-thermometer"></i>
                                    <div class="details ml-2">
                                        <div class="temp">
                                            <span class="feelstemp">_</span>
                                            <span class="deg">°C</span>
                                        </div>
                                        <p class="mb-0">Feels like</p>
                                    </div>
                                </div>

                                <!-- Humidity column -->
                                <div class="column humidity ml-4 flex items-center">
                                    <i class="bx bxs-droplet-half"></i>
                                    <div class="details ml-2">
                                        <span>_</span>
                                        <p class="mb-0">Humidity</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


        <script>
            // Declare variables
            const infoTxt = document.querySelector(".weather-part .temp");
            const wIcon = document.querySelector(".weather-part img");
            const wrapper = document.querySelector(".wrapper");  //container div

            // Function to handle API request
            function requestApi(latitude, longitude) {
                console.log("latitude"+latitude);
                console.log("longitude"+longitude);

                const api = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&appid=e5f513d1970d2495b51b0417729b36f2";
                console.log("api"+api);
                fetchData(api);
            }

            // Function to handle API fetch
            function fetchData(api) {
                infoTxt.classList.add("pending");
                fetch(api).then(response => response.json()).then(result => weatherDetails(result));
            }

            function weatherDetails(info) {
                console.log(info);
                if (info.cod === 404) {
                    infoTxt.classList.replace("pending", "error");
                    infoTxt.innerText = `isn't a valid city name`;
                } else {
                    // Extracted information
                    const city = info.name;
                    const country = info.sys.country;
                    const { description, id } = info.weather[0];
                    const { feels_like, humidity, temp } = info.main;

                    console.log(temp);
                    // Assign weather icon based on weather condition ID
                    if (id === 800) {
                        wIcon.src = `${pageContext.request.contextPath}/assets/weather_icons/clear.svg`;
                    } else if (id >= 200 && id <= 232) {
                        wIcon.src = `${pageContext.request.contextPath}/assets/weather_icons/storm.svg`;
                    } else if (id >= 600 && id <= 622) {
                        wIcon.src = `${pageContext.request.contextPath}/assets/weather_icons/snowy.svg`;
                    } else if (id >= 701 && id <= 781) {
                        wIcon.src = `${pageContext.request.contextPath}/assets/weather_icons/haze.svg`;
                    } else if (id >= 801 && id <= 804) {
                        wIcon.src = `${pageContext.request.contextPath}/assets/weather_icons/cloudy.svg`;
                    } else if ((id >= 300 && id <= 321) || (id >= 500 && id <= 531)) {
                        wIcon.src = `${pageContext.request.contextPath}/assets/weather_icons/rainy.svg`;
                    }

                    // Update HTML elements with weather details
                    updateWeatherElement(".temp1 .numb", Math.floor(temp));
                    updateWeatherElement(".weather", description.toUpperCase());
                    updateWeatherElement(".location span", city + "," + country);
                    updateWeatherElement(".temp span", Math.floor(feels_like));
                    updateWeatherElement(".humidity span", humidity + " %");

                    // Clear status and input field
                    infoTxt.classList.remove("pending", "error");
                    infoTxt.classList.add("active"); // Show weather details
                }
            }

            function updateWeatherElement(selector, text) {
                const element = document.querySelector(selector);
                if (element) {
                    element.innerText = text;
                } else {
                    console.error(`Element not found for selector: ${selector}`);
                }
            }

            // Initial request with constants
            // Replace with your actual longitude
            requestApi(<%=coordinates.getLast().getLatitude()%>, <%=coordinates.getLast().getLongitude()%>);

        </script>

        <!-- Coordinates -->
        <!-- Coordinates on map -->
        <% if (coordinates != null && !coordinates.isEmpty()) { %>
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">

            <div id="map" class="w-full h-[400px]"></div>
            <script>
                let firstLat = <%= coordinates.get(0).getLatitude() %>;
                let firstLon = <%= coordinates.get(0).getLongitude() %>;
                let map = L.map('map').setView([firstLat, firstLon], 13);

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