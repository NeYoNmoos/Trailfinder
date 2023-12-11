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
<form action="changePassword" method="post">
    <label for="oldPassword">Old Password:</label>
    <input type="password" id="oldPassword" name="oldPassword" required />

    <label for="newPassword">New Password:</label>
    <input type="password" id="newPassword" name="newPassword" required />

    <button type="submit">Change Password</button>
</form>
</body>
</html>
