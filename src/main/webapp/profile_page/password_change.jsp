
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Changing Password</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp" />
<div class="bg-gray-100 h-screen w-screen flex items-center justify-center">
    <div class="bg-white p-8 rounded shadow-md w-96">

        <h2 class="text-2xl font-semibold mb-6 text-center">Change Your Password</h2>

        <!-- Change Password Form -->
        <form action="${pageContext.request.contextPath}/changepassword" method="post">

            <!-- Old Password Input -->
            <div class="mb-4">
                <label for="oldPassword" class="block text-sm font-medium text-gray-600">Old Password</label>
                <input type="password" id="oldPassword" name="oldPassword" placeholder="Old password"
                       class="mt-1 p-2 w-full border rounded-md focus:outline-none focus:border-blue-500" required>
            </div>

            <!-- New Password Input -->
            <div class="mb-4">
                <label for="newPassword" class="block text-sm font-medium text-gray-600">New Password</label>
                <input type="password" id="newPassword" name="newPassword" placeholder="New password"
                       class="mt-1 p-2 w-full border rounded-md focus:outline-none focus:border-blue-500" required minlength="8">
            </div>
            <% if (request.getAttribute("changePSWError") != null) { %>
            <div class="text-red-500 text-sm mb-4">
                <%= request.getAttribute("changePSWError") %>
            </div>
            <% } %>
             <!-- Change Password Button -->
                <button type="submit" class="btn-primary text-white p-2 rounded-md w-full focus:outline-none focus:ring">Change Password</button>
        </form>
        <!-- Register Link -->
        <p class="mt-4 text-sm text-gray-600 text-center">
            Changed your mind? <a href="${pageContext.request.contextPath}/profile" class="text-blue-500">Give up</a>.
        </p>
    </div>
</div>
</body>

</html>
