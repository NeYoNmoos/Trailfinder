<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 11.12.2023
  Time: 05:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password Changed</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<jsp:include page="/components/confirmation/confirmation_component.jsp" >
    <jsp:param name="message" value="Successfully deleted :)" />
    <jsp:param name="link" value="${pageContext.request.contextPath}/profile" />
    <jsp:param name="buttontext" value="Back to your profile" />
</jsp:include>
</body>
</html>
