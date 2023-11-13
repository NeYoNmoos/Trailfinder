<%--
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
<nav class="w-full sticky top-0 z-[1000] bg-white shadow py-2 px-4 flex justify-between items-center">
    <a href="${pageContext.request.contextPath}/" class="flex items-center text-black">
        <img src="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png" alt="Logo" class="h-20 w-auto">
        <h2 class="font-bold">Trailfinder</h2>
    </a>
    <a href="${pageContext.request.contextPath}/route-create" class="text-white px-4 py-2 rounded-md btn-primary transition-colors">
        Create Route
    </a>
</nav>

<script>
</script>
</body>
</html>
