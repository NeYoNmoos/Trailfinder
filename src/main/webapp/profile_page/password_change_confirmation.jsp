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
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">

</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="change_confirmation">

</div>
<div class="change-confirmation">
    <h1>Password has been successfully changed :)</h1>
    <button class="btn-primary">
        <a href="${pageContext.request.contextPath}/profile">Back to your profile.</a>
    </button>
</div>
</body>
</html>
