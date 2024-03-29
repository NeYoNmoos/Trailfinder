<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="at.fhv.hike.data.*" %>
<%@ page import="java.util.*" %>
<%@ page import="at.fhv.hike.controllers.CookieController" %>
<%@ page import="at.fhv.hike.controllers.CommentController" %><%--
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
  User: matth
  Date: 01/11/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    RouteEntity route = (RouteEntity) request.getAttribute("route");
    Boolean canEdit = (Boolean) request.getAttribute("canEdit");
    AttributeEntity attributes = route != null ? route.getAttributeEntity() : null;
    List<CoordinateEntity> coordinates = route != null ? route.getCoordinates() : null;
    Boolean isFavorite = (Boolean) request.getAttribute("isFavorite");


    Collections.sort(coordinates, Comparator.comparingInt(CoordinateEntity::getSequence));

    List<LodgeEntity> huetten = (List<LodgeEntity>) request.getAttribute("huetten");

    List<PointOfInterestEntity> pois = (List<PointOfInterestEntity>) request.getAttribute("pois");
%>

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
        <title><%= route.getName() %></title>
    </head>
<body class="bg-gray-100">
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<%
    String sessionToken= CookieController.getLogedInUserId(request.getCookies());
%>



<main class="py-10 max-w-7xl w-75vw lg:w-[75vw] mx-auto px-4 sm:px-6 lg:px-8 ">
    <div class="bg-white rounded-lg pt-4 mb-4 shadow">
        <!-- Route Title -->
        <div class="mb-8 px-4 flex flex-row justify-between">
            <h1 class="text-3xl font-bold text-gray-900"><%= route.getName() %></h1>
            <div class="flex space-x-4">
                <%
                    LocalDateTime creationDate = route.getCreated_at();
                    if(creationDate!=null)
                    {
                        // Define the desired date-time format without fractional seconds
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm");

                        // Format the LocalDateTime object
                        String formattedCreationDate = creationDate.format(formatter);
                %><p class="text-gray-400"><%= formattedCreationDate %></p>
                <%}else
                {%>
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
            <div class="flex flex-row">
            <% if (sessionToken != null) { %>
                <form action="${pageContext.request.contextPath}/add_favorite" method="POST" class="m-0">
                    <input type="hidden" name="routeId" value="<%= route.getRouteId() %>" />
                    <button type="submit" class="inline-flex items-center text-white bg-blue-500 rounded-lg h-11 px-3 py-1 hover:bg-blue-700 transition duration-300">
                        <% if(isFavorite) { %>
                        <i class="fas fa-star text-white" title="Favorited"></i>
                        <% } else { %>
                        <i class="far fa-star text-white" title="Not Favorited"></i>
                        <% } %>
                    </button>
                </form>

                <!-- Add date to done button -->
                <script>
                    function addDate(){
                        let date = prompt("Please enter the date the route was done.");
                        const dateInput = document.createElement('input');
                        dateInput.type = 'hidden';
                        dateInput.name = "doneDate";
                        dateInput.value = date;
                        document.getElementById("doneForm").appendChild(dateInput);
                    }
                </script>

                <form id="doneForm" action="${pageContext.request.contextPath}/add_Done" method="POST" class="m-0 ml-2">
                    <input type="hidden" name="routeId" value="<%= route.getRouteId() %>" />
                    <button type="submit" onClick="addDate()" class="inline-flex items-center text-white bg-blue-500 rounded-lg h-11 px-3 py-1 hover:bg-blue-700 transition duration-300">
                        <i class="fas fa-check text-white" title="Done"></i>
                    </button>
                </form>
            <% } %>
            <%if(canEdit){%>
            <% String editPageUrl = "/route-create?routeId=" + route.getRouteId(); %>
            <a href="${pageContext.request.contextPath}<%= editPageUrl %>"
               class="h-[44px] inline-flex items-center ml-2 text-white bg-blue-500 rounded-lg px-3 py-1 hover:bg-blue-700 transition duration-300" style="float:right">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="mr-2 bi bi-pencil" viewBox="0 0 16 16">
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
                        <button onclick="deleteFunction()" id="deleteButton" data-model-target="deleteModal" data-modal-toggle="deleteModal" class="h-[44px] inline-flex items-center ml-2 px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded-md" style="float:right">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                            </svg>
                            Delete
                        </button>
                    <%}%>
            </div>
        </div>
            <div class="px-4 py-5 sm:p-6 flex justify-between items-center">
                <h2 class="text-xl font-bold text-gray-900 mb-1 sm:mb-0">Route Details</h2>


                <div class="flex flex-row space-x-4">
                    <form action="${pageContext.request.contextPath}/summary" method="get">
                        <input type="hidden" name="routeId" value="<%= route.getRouteId() %>">
                        <button type="submit" class="btn-primary w-12 h-12 rounded-full">
                            <i class="fas fa-print text-black text-lg"></i>
                        </button>
                    </form>
                    <form action="https://www.google.com/maps/dir/" method="get" target="_blank">
                        <input type="hidden" name="api" value="1">
                        <% if (coordinates != null && !coordinates.isEmpty()) { %>
                        <button type="submit" name="destination" value="<%= coordinates.get(0).getLatitude() %>,<%= coordinates.get(0).getLongitude() %>"
                                class="btn-primary w-48 h-12 rounded-md px-4 py-2">How to get there</button>
                        <% } else { %>
                        <p>No coordinates available</p>
                        <% } %>
                    </form>
                </div>

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

                <!--Images slide show-->
                <div class="container mx-auto p-4">
                    <%
                        List<GalleryEntity> gallery = (List<GalleryEntity>) request.getAttribute("gallery");
                        if (gallery != null && !gallery.isEmpty()) {
                    %>
                    <!-- Slideshow Container -->
                    <div id="slideshow" class="max-w-screen-md mx-auto relative">
                        <%
                            int i = 0;
                            for (GalleryEntity imageEntity : gallery) {
                                i++;
                                byte[] imageBytes = imageEntity.getPicture();
                                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                        %>
                        <div class="mySlides fade">
                            <div class="text-white absolute top-0 left-0 p-2"><%= i %> / <%= gallery.size() %></div>
                            <img src="data:image/jpeg;base64,<%= base64Image %>" class="w-full h-auto">
                        </div>
                        <%
                            }
                        %>
                        <!-- Next and previous buttons -->
                        <a class="prev absolute top-1/2 left-0 transform -translate-y-1/2 cursor-pointer p-4 text-white font-bold text-xl transition duration-500 ease-in-out bg-black bg-opacity-50 border-r-4 border-gray-800" onclick="plusSlides(-1)">&#10094;</a>
                        <a class="next absolute top-1/2 right-0 transform -translate-y-1/2 cursor-pointer p-4 text-white font-bold text-xl transition duration-500 ease-in-out bg-black bg-opacity-50 border-l-4 border-gray-800" onclick="plusSlides(1)">&#10095;</a>
                    </div>
                    <%
                        }
                    %>


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
    <div class="grid grid-cols-1 gap-4 mb-6 md:grid-cols-2">
        <!-- Left side -->
        <div class="bg-white shadow overflow-hidden sm:rounded-lg">
            <!-- Attributes -->
            <% if (attributes != null) { %>
            <div class="">
                <div class="px-4 py-5 sm:p-6 text-center">
                    <h2 class="text-xl font-bold text-gray-900">Attributes</h2>
                    <dl class="grid grid-cols-1 gap-x-4 gap-y-8 sm:grid-cols-2">
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
                    <div class="px-4 py-5 sm:p-6 text-center">
                        <h2 class="text-xl font-bold text-gray-900">Best Time to Visit</h2>
                        <div class="mt-1 flex flex-wrap justify-center gap-2">
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
        <% if (coordinates != null && !coordinates.isEmpty()) { %>

        <!-- Right side -->
        <div class="bg-white shadow overflow-hidden sm:rounded-lg">
            <!-- Weather -->

            <div class="">
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
        <% } %>

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
            <% if (coordinates != null && !coordinates.isEmpty()) { %>
            requestApi(<%=coordinates.get(coordinates.size() - 1).getLatitude()%>, <%=coordinates.get(coordinates.size() - 1).getLongitude()%>);
            <% } %>

        </script>

        <!-- Coordinates -->
        <!-- Coordinates on map -->
        <% if (coordinates != null && !coordinates.isEmpty()) { %>
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">

            <div id="map" class="w-full h-[400px]"></div>
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
                        draggable: false,
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
                        draggable: false,
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
    <!--Comments-->
    <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
        <div class="px-4 py-5 sm:p-6">
            <div class="mb-8 flex flex-row justify-between">
                <h1 class="text-3xl font-bold text-gray-900">Comments</h1>

                <%if(sessionToken!=null){%>
                <div class="flex flex-row">
                    <!-- Add Comment Button -->
                    <button id="addCommentButton" class="btn-primary rounded-md px-4 py-2 mt-4">Add Comment</button>
                </div>
                <%}%>
            </div>
                    <!-- Comment Input Field (initially hidden) -->
            <div id="commentInput" class="hidden mt-4">
                <%String link="comment-servlet?routeId=" + route.getRouteId();%>
                <form action="${pageContext.request.contextPath}/<%=link%>" method="post">
                    <input type="hidden" name="action" value="add">
                    <label for="comment">What do you think about this route?</label>
                    <textarea name="comment"  id="comment" rows="4" class="w-full p-2 border rounded"></textarea>
                    <div class="mt-2 flex items-center space-x-2">
                        <i class="fas fa-bolt text-yellow-500" title="Power"></i>
                        <label for="power">Power</label>
                        <select name="power" id="power">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                        <i class="fas fa-tree text-green-500" title="Scenery"></i>
                        <label for="scenery">Scenery</label>
                        <select name="scenery" id="scenery">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                        <i class="fas fa-brain text-purple-500" title="Experience"></i>
                        <label for="experience">Experience</label>
                        <select name="experience" id="experience">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                        <i class="fas fa-heartbeat text-red-500" title="Condition"></i>
                        <label for="condition">Condition</label>
                        <select name="condition" id="condition">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </div>
                    <button type="submit" class="btn-primary rounded-md px-4 py-2 mt-2">Post Comment</button>
                </form>
            </div>

            <!-- Display comments-->
                <%
                    List<CommentEntity> comments = route.getComments();
                    if (comments != null && !comments.isEmpty()) {
                        CommentController.sortComments(comments);
                        for (CommentEntity comment : comments) {
                            if(comment.getActive()){
                %>
                <div class="bg-gray-200 rounded-md p-4 mb-2">
                    <div class="flex items-center justify-between mb-2">
                        <div class="flex items-center space-x-2">
                            <h4 class="text-gray-900"><%= comment.getAuthor().getUsername() %> </h4>
                            <p class="text-gray-500"><%= comment.getDateComment().format(DateTimeFormatter.ofPattern("HH:mm:ss' 'dd-MM-yyyy")) %></p>
                        </div>
                        <%if(comment.getAuthor().getUserId().toString().equals(sessionToken)){%>
                        <div class="flex items-center space-x-2">
                            <!-- Edit button-->
                            <button class="text-blue-500 hover:text-blue-700" onclick="editComment('<%=comment.getCommentId()%>')">Edit</button>
                            <!-- Delete button -->
                            <button class="text-red-500 hover:text-red-700" onclick="deleteComment('<%=comment.getCommentId()%>')">Delete</button>
                        </div>
                        <%}%>
                    </div>
                    <div id='display<%=comment.getCommentId()%>'>
                        <p class="mt-2 text-gray-800"><%= comment.getComment() %></p>
                        <%AttributeEntity comAttributes=comment.getAttributes();
                        if(comAttributes!=null){%>
                        <div class="mt-2 flex items-center space-x-2">
                            <i class="fas fa-bolt text-yellow-500" title="Power"></i>
                            <span><%= comAttributes.getStrength() %></span>
                            <i class="fas fa-tree text-green-500" title="Scenery"></i>
                            <span><%= comAttributes.getScenery() %></span>
                            <i class="fas fa-brain text-purple-500" title="Experience"></i>
                            <span><%= comAttributes.getExperience() %></span>
                            <i class="fas fa-heartbeat text-red-500" title="Condition"></i>
                            <span><%= comAttributes.getCondition() %></span>
                        </div>
                       <%}%>
                    </div>
                    <div id='delete<%=comment.getCommentId()%>' class="hidden mt-4">
                        <p class="mt-2 text-gray-800"><%= comment.getComment() %></p>
                        <p>Are you sure you want to delete this comment?</p>
                        <div class="mt-2 flex items-center space-x-2">
                            <form action="${pageContext.request.contextPath}/comment-servlet" method="post">
                                <button type="button" onclick="displayComment('<%=comment.getCommentId()%>')" class="btn-primary rounded-md px-4 py-2 mt-4">NO</button>
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="routeId" value="<%=route.getRouteId()%>">
                                <input type="hidden" name="commentId" value="<%= comment.getCommentId() %>">
                                <button type="submit" class="btn-primary rounded-md px-4 py-2 mt-4">YES delete</button>
                            </form>
                        </div>
                    </div>
                    <div id='edit<%=comment.getCommentId()%>' class="hidden mt-4">
                        <form action="${pageContext.request.contextPath}/comment-servlet" method="post">

                        <label for="newComment">Edit comment:</label>
                        <input type="text" id="newComment" value="<%=comment.getComment()%>" name="newComment" required>
                            <div class="mt-2 flex items-center space-x-2">
                                <i class="fas fa-bolt text-yellow-500" title="Power"></i>
                                <label for="newPower">Power</label>
                                <select name="newPower" id="newPower">
                                    <option value="1"  <%= ((Integer)comment.getAttributes().getStrength()) == 1 ? "selected" : "" %>>1</option>
                                    <option value="2" <%= ((Integer)comment.getAttributes().getStrength()) == 2 ? "selected" : "" %>>2</option>
                                    <option value="3"  <%= ((Integer)comment.getAttributes().getStrength()) ==3 ? "selected" : "" %>>3</option>
                                    <option value="4" <%= ((Integer)comment.getAttributes().getStrength()) == 4 ? "selected" : "" %>>4</option>
                                    <option value="5" <%= ((Integer)comment.getAttributes().getStrength()) == 5 ? "selected" : "" %>>5</option>
                                </select>
                                <i class="fas fa-tree text-green-500" title="Scenery"></i>
                                <label for="newScenery">Scenery</label>
                                <select name="newScenery" id="newScenery">
                                    <option value="1" <%= ((Integer)comment.getAttributes().getScenery()) == 1 ? "selected" : "" %>>1</option>
                                    <option value="2" <%= ((Integer)comment.getAttributes().getScenery()) == 2 ? "selected" : "" %>>2</option>
                                    <option value="3" <%= ((Integer)comment.getAttributes().getScenery()) == 3 ? "selected" : "" %>>3</option>
                                    <option value="4" <%= ((Integer)comment.getAttributes().getScenery()) == 4 ? "selected" : "" %>>4</option>
                                    <option value="5" <%= ((Integer)comment.getAttributes().getScenery()) == 5 ? "selected" : "" %>>5</option>
                                </select>
                                <i class="fas fa-brain text-purple-500" title="Experience"></i>
                                <label for="newExperience">Experience</label>
                                <select name="newExperience" id="newExperience">
                                    <option value="1"  <%= ((Integer)comment.getAttributes().getExperience()) == 1 ? "selected" : "" %> >1</option>
                                    <option value="2" <%= ((Integer)comment.getAttributes().getExperience()) == 2 ? "selected" : "" %>>2</option>
                                    <option value="3" <%= ((Integer)comment.getAttributes().getExperience()) == 3 ? "selected" : "" %>>3</option>
                                    <option value="4"<%= ((Integer)comment.getAttributes().getExperience()) == 4? "selected" : "" %>>4</option>
                                    <option value="5"<%= ((Integer)comment.getAttributes().getExperience()) == 5 ? "selected" : "" %>>5</option>
                                </select>
                                <i class="fas fa-heartbeat text-red-500" title="Condition"></i>
                                <label for="newCondition">Condition</label>
                                <select name="newCondition" id="newCondition">
                                    <option value="1" <%= ((Integer)comment.getAttributes().getCondition()) == 1 ? "selected" : "" %>>1</option>
                                    <option value="2"<%= ((Integer)comment.getAttributes().getCondition()) == 2 ? "selected" : "" %>>2</option>
                                    <option value="3"<%= ((Integer)comment.getAttributes().getCondition()) == 3 ? "selected" : "" %>>3</option>
                                    <option value="4"<%= ((Integer)comment.getAttributes().getCondition()) == 4 ? "selected" : "" %>>4</option>
                                    <option value="5"<%= ((Integer)comment.getAttributes().getCondition()) == 5 ? "selected" : "" %>>5</option>
                                </select>
                            </div>
                        <div class="mt-2 flex items-center space-x-2">
                            <button type="button" onclick="displayComment('<%=comment.getCommentId()%>')" class="btn-primary rounded-md px-4 py-2 mt-4">Cancel</button>
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="routeId" value="<%=route.getRouteId()%>">
                            <input type="hidden" name="commentId" value="<%= comment.getCommentId() %>">
                            <button type="submit" class="btn-primary rounded-md px-4 py-2 mt-4">Save</button>
                        </div>
                        </form>

                    </div>
                </div>

                   <% }%>
                <%
                    }
                } else {
                %>
                <p>No comments yet.</p>
                <%
                    }
                %>
        </div>
    </div>

    <!-- JavaScript to toggle (show and UNshow) comment input visibility -->
    <script>
        function displayComment(_id) {
            console.log("Displaying comment:", _id);
            // Hide the edit and delete sections, show the display section
            var displayElement = document.getElementById('display'+_id);
            var editElement = document.getElementById('edit'+_id);
            var deleteElement = document.getElementById('delete'+_id);

            console.log("Display element:", displayElement);
            console.log("Edit element:", editElement);
            console.log("Delete element:", deleteElement);

            if (displayElement) displayElement.style.display = 'block';
            if (editElement) editElement.style.display = 'none';
            if (deleteElement) deleteElement.style.display = 'none';
        }

        function editComment(_id) {
            console.log("Editing comment:", _id);
            // Hide the display and delete sections, show the edit section
            var displayElement = document.getElementById('display'+_id);
            var editElement = document.getElementById('edit'+_id);
            var deleteElement = document.getElementById('delete'+_id);

            console.log("Display element:", displayElement);
            console.log("Edit element:", editElement);
            console.log("Delete element:", deleteElement);

            if (displayElement) displayElement.style.display = 'none';
            if (editElement) editElement.style.display = 'block';
            if (deleteElement) deleteElement.style.display = 'none';
        }

        function deleteComment(_id) {
            console.log("Deleting comment:", _id);
            // Hide the display and edit sections, show the delete section
            var displayElement = document.getElementById('display'+_id);
            var editElement = document.getElementById('edit'+_id);
            var deleteElement = document.getElementById('delete'+_id);

            console.log("Display element:", displayElement);
            console.log("Edit element:", editElement);
            console.log("Delete element:", deleteElement);

            if (displayElement) displayElement.style.display = 'none';
            if (editElement) editElement.style.display = 'none';
            if (deleteElement) deleteElement.style.display = 'block';
        }

        document.getElementById('addCommentButton').addEventListener('click', function () {
            // Hide the comment input, change button text back to 'Add Comment'
            document.getElementById('commentInput').classList.toggle('hidden');
            if (document.getElementById('addCommentButton').textContent==="Cancel"){
            document.getElementById('addCommentButton').textContent = 'Add Comment';}
            else
                document.getElementById('addCommentButton').textContent = 'Cancel';
        });
    </script>

</main>
</body>
</html>