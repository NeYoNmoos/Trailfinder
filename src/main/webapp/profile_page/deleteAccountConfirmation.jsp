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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/profile_page/profile.css">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="flex items-center justify-center h-[90%]">
    <div class="flex flex-col items-center justify-center p-4">
        <h1>Account deletion</h1>
        <p class="mb-4">If you are sure that you want to delete your account, type in your password and press the confirm button.</p>
        <form action="accountdeletion" method="post" class="flex flex-col items-center">
            <label for="oldPassword" class="mb-2">Old Password:</label>
            <input type="password" id="oldPassword" name="oldPassword" required class="mb-4 px-2 py-1 border rounded" />
            <button type="submit" class="btn-primary rounded-md px-4 py-2 transition-colors mx-auto">Confirm</button>
        </form>
    </div>
</div>
</body>
</html>