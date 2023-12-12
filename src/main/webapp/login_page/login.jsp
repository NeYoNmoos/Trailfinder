<%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 12/4/2023
  Time: 9:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="bg-gray-100 h-screen w-screen flex items-center justify-center">
    <div class="bg-white p-8 rounded shadow-md w-96">

    <h2 class="text-2xl font-semibold mb-6 text-center">Login</h2>

    <!-- Login Form -->
    <form action="${pageContext.request.contextPath}/login" method="post">

        <!-- Email Input -->
        <div class="mb-4">
            <label for="email" class="block text-sm font-medium text-gray-600">Email</label>
            <input type="email" id="email" name="email" placeholder="Your email"
                   class="mt-1 p-2 w-full border rounded-md focus:outline-none focus:border-blue-500" required>
        </div>

        <!-- Password Input -->
        <div class="mb-6">
            <label for="password" class="block text-sm font-medium text-gray-600">Password</label>
            <input type="password" id="password" name="password" placeholder="Your password"
                   class="mt-1 p-2 w-full border rounded-md focus:outline-none focus:border-blue-500"
                   required>
        </div>

        <% if (request.getAttribute("loginError") != null) { %>
        <div class="text-red-500 text-sm mb-4">
            <%= request.getAttribute("loginError") %>
        </div>
        <% } %>

        <!-- Login Button -->
        <button type="submit"
                class="btn-primary text-white p-2 rounded-md w-full focus:outline-none focus:ring">
            Login
        </button>
    </form>

    <!-- Register Link -->
    <p class="mt-4 text-sm text-gray-600 text-center">
        Don't have an account? <a href="${pageContext.request.contextPath}/register" class="text-blue-500">Register here</a>.
    </p>

</div>
</div>
</body>

</html>