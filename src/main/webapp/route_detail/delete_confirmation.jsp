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
<div class="confirm">

</div>
<div class="create-confirmation">
    <h1>Route has been successfully deleted :)</h1>
    <button class="btn-primary">
        <a href="${pageContext.request.contextPath}/">Back to list of routes</a>
    </button>
</div>

</body>
</html>
