<%@ page import="at.fhv.hike.hibernate.facade.TrailfinderDatabaseFacade" %>
<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="at.fhv.hike.data.CoordinateEntity" %>
<%@ page import="at.fhv.hike.data.TimeOfYearEntity" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URI" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %><%--
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
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/route_detail/route_detail.css">-->
    </head>
<body class="bg-gray-100">
<jsp:include page="/components/navigation/nav_bar.jsp"/>

<main class="py-10">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <!-- Route Title -->
        <div class="mb-8 flex justify-between items-center">
            <h1 class="text-3xl font-bold text-gray-900"><%= route.getName() %></h1>
            <% String editPageUrl = "/route-create?routeId=" + route.getRouteId(); %>
            <a href="${pageContext.request.contextPath}<%= editPageUrl %>"
               class="flex items-center text-white bg-blue-500 rounded-lg px-3 py-1 hover:bg-blue-700 transition duration-300">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                </svg>
                Edit
            </a>
        </div>

        <!-- Route Details -->
        <div class="bg-white shadow overflow-hidden sm:rounded-lg mb-6">
            <div class="px-4 py-5 sm:p-6">
                <h2 class="text-xl font-bold text-gray-900">Route Details</h2>
                <div>
                    <p><%= route.getDescription() %> </p>
                </div>
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

        <div class="flex justify-between mb-6">
            <!-- Best Time to Visit -->
            <% if (timeOfYear != null) { %>
            <div class="bg-white shadow overflow-hidden sm:rounded-lg w-1/2 mr-2">
                <div class="px-4 py-5 sm:p-6 text-center">
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
            <div class="bg-white shadow overflow-hidden sm:rounded-lg w-1/2 ml-2">
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