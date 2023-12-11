<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 11.12.2023
  Time: 06:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/components/navigation/nav_bar.jsp"/>
<div class="pw_change_denial">

</div>
<div class="denial-confirmation">
    <h1>Password change has been denied, your old password is wrong :(</h1>
    <button class="btn-primary">
        <a href="${pageContext.request.contextPath}/profile">Back to your profile.</a>
    </button>
</div>
</body>
</html>
