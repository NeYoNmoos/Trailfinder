<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function deleteAllCookies() {
            var cookies = document.cookie.split(";");

            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i];
                var eqPos = cookie.indexOf("=");
                var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
            }
        }
        window.onload = function() {
            deleteAllCookies();
        };
    </script>
    <title>Account Deleted</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/create_route/create_route.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<jsp:include page="/components/confirmation/confirmation_component.jsp" >
    <jsp:param name="message" value="Account successfully deleted :)" />
    <jsp:param name="link" value="${pageContext.request.contextPath}/home" />
    <jsp:param name="buttontext" value="Back home page" />
</jsp:include>
</body>
</html>
