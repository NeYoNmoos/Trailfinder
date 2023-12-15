<%@ page import="at.fhv.hike.data.RouteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="at.fhv.hike.data.AttributeEntity" %>
<%@ page import="at.fhv.hike.data.GalleryEntity" %>
<%@ page import="java.util.Base64" %><%--
  Created by IntelliJ IDEA.
  User: metedastan
  Date: 12.12.23
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Profile</title>
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/profile_page/profile.css">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="container">
  <jsp:include page="/components/profile_menu/profile_menu.jsp"/>
  <jsp:include page="/components/display_routes/display_routes.jsp">
    <jsp:param name="title" value="My routes" />
  </jsp:include>
</div>

</body>
</html>
