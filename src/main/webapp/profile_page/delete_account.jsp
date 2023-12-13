<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 11.12.2023
  Time: 06:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <title>Account Deletion</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="bg-gray-100 h-screen w-screen flex items-center justify-center">
    <div class="bg-white p-8 rounded shadow-md w-96">
        <h1>Delete Account</h1>
        <p class="mb-4">Confirm your identity: type in your password.</p>
        <form action="${pageContext.request.contextPath}/deleteaccount" method="post">

            <!-- Password Input -->
            <div class="mb-4">
                <label for="Password" class="block text-sm font-medium text-gray-600">Password</label>
                <input type="password" id="Password" name="Password" placeholder="password"
                       class="mt-1 p-2 w-full border rounded-md focus:outline-none focus:border-blue-500" required>
            </div>

            <% if (request.getAttribute("deleteAccError") != null) { %>
            <div class="text-red-500 text-sm mb-4">
                <%= request.getAttribute("deleteAccError") %>
            </div>
            <% } %>
            <!-- Change Password Button -->
            <button type="submit" class="btn-primary text-white p-2 rounded-md w-full focus:outline-none focus:ring">Delete Account</button>
        </form>
        <!-- Register Link -->
        <p class="mt-4 text-sm text-gray-600 text-center">
            Changed your mind? <a href="${pageContext.request.contextPath}/profile" class="text-blue-500">Give up</a>.
        </p>
    </div>
</div>
</body>
</html>