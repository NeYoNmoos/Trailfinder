<%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 12/12/2023
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/icons/Trailfinder_logo.png">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Confirmation</title>
</head>
<body>
<div class="flex items-center justify-center h-[90%]">
    <div class="flex flex-col items-center justify-center">
        <h1>${param.message}</h1>
        <div>
            <button class="btn-primary rounded-md px-4 py-2 transition-colors mx-auto" type="button">
                <a href="${param.link}">${param.buttontext}</a>
            </button>
        </div>
    </div>
</div>
</body>
</html>
