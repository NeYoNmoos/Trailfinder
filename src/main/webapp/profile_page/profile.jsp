<%@ page import="at.fhv.hike.controllers.CookieController" %><%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 11.12.2023
  Time: 01:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/profile_page/profile.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="container">
        <div class="sidenav">
            <h3 style="padding: 6px 8px 6px 16px"><%= request.getAttribute("user")%></h3>
            <h3 style="padding: 6px 8px 6px 16px"><%= request.getAttribute("email")%></h3>
            <a href="${pageContext.request.contextPath}/profile_page/favourite_routes.jsp">Favourite Routes</a>
            <a href="${pageContext.request.contextPath}/profile_page/done_routes.jsp">Done Routes</a>
            <a href="${pageContext.request.contextPath}/profile_page/todo_routes.jsp">ToDo Routes</a>
            <a href="${pageContext.request.contextPath}/profile_page/my_routes.jsp">My Routes</a>

            <div class="button-container">
                <button type="submit" name="changepw" value="changepw" class="w-48 btn-primary rounded-md px-3 py-2" style="color: #ffffff !important;">
                    <a href="${pageContext.request.contextPath}/changepassword">Change Password</a>
                </button>
                <button type="submit" name="accountdelete" value="accountdelete" class="w-48 btn-primary rounded-md px-3 py-2" style="background-color: #ff0000 !important; color: #ffffff !important;">
                    <a href="${pageContext.request.contextPath}/accountdeletion" style="color: inherit; text-decoration: none;">Delete Account</a>
                </button>
            </div>
        </div>
        <div class="main">
            <h3 style="padding: 6px 8px 6px 16px"><%= request.getAttribute("user")%></h3>
            <h3 style="padding: 6px 8px 6px 16px"><%= request.getAttribute("email")%></h3>
            <div class="create-route-section">
                <a href="${pageContext.request.contextPath}/route-create" class="px-4 py-2 rounded-md btn-primary transition-colors">Create Route</a>
            </div>
        </div>
</div>

</body>
</html>
