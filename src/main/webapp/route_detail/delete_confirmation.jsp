<%--
  Created by IntelliJ IDEA.
  User: Korisnik
  Date: 11/26/2023
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <title>Confirmation</title>
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<jsp:include page="/components/confirmation/confirmation_component.jsp" >
    <jsp:param name="message" value="Route has been successfully deleted :)" />
    <jsp:param name="link" value="${pageContext.request.contextPath}/" />
    <jsp:param name="buttontext" value="Back to list of routes" />
</jsp:include>
</body>
</html>
