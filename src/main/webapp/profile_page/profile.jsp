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
            <h3 style="align-content: center"><%= request.getAttribute("user")%></h3>
            <h3 style="align-content: center"><%= request.getAttribute("email")%></h3>
            <a href="#"><h3>Favourite Routes</h3></a>
            <a href="#"><h3>Done Routes</h3></a>
            <a href="#"><h3>ToDo Routes</h3></a>
            <a href="#"><h3>My Routes</h3></a>
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
            <h2>Sidebar</h2>
            <p>This sidebar is of full height (100%) and always shown.</p>
            <p>Scroll down the page to see the result.</p>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, illum definitiones no quo, maluisset concludaturque et eum, altera fabulas ut quo. Atqui causae gloriatur ius te, id agam omnis evertitur eum. Affert laboramus repudiandae nec et. Inciderint efficiantur his ad. Eum no molestiae voluptatibus.</p>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, illum definitiones no quo, maluisset concludaturque et eum, altera fabulas ut quo. Atqui causae gloriatur ius te, id agam omnis evertitur eum. Affert laboramus repudiandae nec et. Inciderint efficiantur his ad. Eum no molestiae voluptatibus.</p>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, illum definitiones no quo, maluisset concludaturque et eum, altera fabulas ut quo. Atqui causae gloriatur ius te, id agam omnis evertitur eum. Affert laboramus repudiandae nec et. Inciderint efficiantur his ad. Eum no molestiae voluptatibus.</p>
            <p>Some text to enable scrolling.. Lorem ipsum dolor sit amet, illum definitiones no quo, maluisset concludaturque et eum, altera fabulas ut quo. Atqui causae gloriatur ius te, id agam omnis evertitur eum. Affert laboramus repudiandae nec et. Inciderint efficiantur his ad. Eum no molestiae voluptatibus.</p>
        </div>
</div>

</body>
</html>
