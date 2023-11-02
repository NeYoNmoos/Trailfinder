<%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 11/2/2023
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/home_page/home_page.css">
</head>
<body>
<header>
    <div class="header-content">
        <h1>Trailfinder</h1>
        <img src="${pageContext.request.contextPath}/assets/home_page_img/header-image.jpg" alt="Header Image">
    </div>
</header>

<div class="search-bar">
    <input type="text" placeholder="Search by Name">
    <button>Search</button>
</div>

<div class="route-boxes">
    <div class="route-box">
        <img src="download.jpg" alt="Route Image 1">
        <div class="route-info">
            <h2>Route 1</h2>
            <p>Short description of Route 1.</p>
        </div>
    </div>

    <div class="route-box">
        <img src="download.jpg" alt="Route Image 2">
        <div class="route-info">
            <h2>Route 2</h2>
            <p>Short description of Route 2.</p>
        </div>
    </div>

    <!-- Add more route boxes as needed -->
</div>
</body>
</html>
