<%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 12/12/2023
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
</head>
<body>
<div class="sidenav bg-gray-400 text-white h-screen p-4 flex flex-col">


    <div class="flex-1 flex flex-col justify-center items-center">
        <a href="${pageContext.request.contextPath}/profile" class="text-lg font-semibold mb-4"><%= request.getAttribute("user") %></a>
        <a href="${pageContext.request.contextPath}/favorite" class="block py-2">Favourite Routes</a>
        <a href="${pageContext.request.contextPath}/done" class="block py-2">Done Routes</a>
        <a href="${pageContext.request.contextPath}/todo" class="block py-2">ToDo Routes</a>
        <a href="${pageContext.request.contextPath}/my_routes" class="block py-2">My Routes</a>
    </div>

    <div class="button-container mt-auto flex">
        <button type="submit" name="changepw" value="changepw" class="flex-1 btn-primary rounded-md px-3 py-2">
            <a href="${pageContext.request.contextPath}/changepassword" class="text-white">Change Password</a>
        </button>
        <button type="submit" name="accountdelete" value="accountdelete" class="flex-1 btn-primary rounded-md px-3 py-2" style="background-color: #ff0000;">
            <a href="${pageContext.request.contextPath}/deleteaccount" class="text-white" style="text-decoration: none;">Delete Account</a>
        </button>
    </div>
</div>
</body>
</html>
