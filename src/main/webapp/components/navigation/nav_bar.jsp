<%@ page import="at.fhv.hike.controllers.CookieController" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 03/11/2023
  Time: 10:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
</head>
<body>
<%
    String sessionToken= CookieController.getLogedInUserId(request.getCookies());
%>
<script>
    // Function to delete a cookie by name
    function deleteCookie(name) {
        document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    }

    // Function to clear all cookies
    function clearAllCookies() {
        var cookies = document.cookie.split("; ");

        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i].split("=");
            var name = cookie[0];
            deleteCookie(name);
        }
    }
    function logoutAndRefresh() {
        // Clear all cookies
        clearAllCookies();
        // Reload the page
        location.reload();
    }

</script>
<nav class="w-full sticky top-0 z-[1000] bg-white shadow py-2 px-4 flex justify-between items-center">
    <!-- Left-aligned logo and title -->
    <a href="${pageContext.request.contextPath}/" class="flex items-center text-black">
        <img src="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png" alt="Logo" class="h-20 w-auto">
        <h2 class="font-bold">Trailfinder</h2>
    </a>

    <!-- Right-aligned elements -->
    <div class="flex items-center space-x-4">

        <% if (sessionToken != null) { %>
        <a href="${pageContext.request.contextPath}/route-create" class="px-4 py-2 rounded-md btn-primary transition-colors">Create Route</a>
        <button onclick="logoutAndRefresh()" class="px-4 py-2 rounded-md btn-primary transition-colors">LogOut</button>
        <a href="${pageContext.request.contextPath}/profile" class="px-4 py-2 rounded-md btn-primary transition-colors">Profile</a>
        <% } else { %>
        <a href="${pageContext.request.contextPath}/login" class="px-4 py-2 rounded-md btn-primary transition-colors">LogIn</a>
        <% } %>
    </div>
</nav>

<script>
</script>
</body>
</html>
