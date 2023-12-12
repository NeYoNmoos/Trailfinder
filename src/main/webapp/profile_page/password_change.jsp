<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 11.12.2023
  Time: 05:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Changing Password</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/profile_page/profile.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<h1>Change your password</h1>
<form action="${pageContext.request.contextPath}/changepassword" method="post">
    <label for="oldPassword">Old Password:</label>
    <input type="password" id="oldPassword" name="oldPassword" />

    <label for="newPassword">New Password:</label>
    <input type="password" id="newPassword" name="newPassword" />

    <div class="flex">
        <a href="${pageContext.request.contextPath}/profile">
            <button type="button" class="px-4 py-2 rounded-md btn-primary transition-colors">Cansel</button>
        </a>
        <button type="submit" class="px-4 py-2 rounded-md btn-primary transition-colors">Change Password</button>
    </div>
</form>
</body>
</html>
