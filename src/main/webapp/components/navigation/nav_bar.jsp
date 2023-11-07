<%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 03/11/2023
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/components/navigation/nav_bar.css">
    <title>Navbar</title>
</head>
<body>
    <div class="navbar">
        <div class="icon">
            <a href="${pageContext.request.contextPath}/">
                <img height="80" width="80" src="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png" alt="Logo">
            </a>
        </div>
        <button>
            <a href="${pageContext.request.contextPath}/create_route/create_route.jsp">Create Route</a>
        </button>
    </div>
</body>
</html>
