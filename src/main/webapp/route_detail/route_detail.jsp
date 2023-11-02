<%@ page import="data.Route" %><%--
  Created by IntelliJ IDEA.
  User: matth
  Date: 01/11/2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /**
     * TODO: Getting Routedata with hibernate
      */
    String routeId = request.getParameter("routeId");

    // mockdata for Route
    Route route = new Route("as33a-34rasdf-asdf", "aefeae-34ae3ra-afsdf", "Karrenrundtour", 5.343, 434, "Dornbirn", "Ein feiner Rundgang zum Karren und zurück in die Dornbirner Innenstadt", 3.4);
%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Global.css">
    <title><%= route.getName() %></title>
</head>
<body>
<div>
    <div class="card">
        <h1><%= route.getName() %></h1>
        <p>ID: <%= route.getId() %></p>
        <p>Author: <%= route.getAuthor() %></p>
        <p>Length: <%= route.getLength() %> km</p>
        <p>Altitude: <%= route.getAltitude() %> m</p>
        <p>Location: <%= route.getLocation() %></p>
        <p>Description: <%= route.getDescription() %></p>
        <p>Duration: <%= route.getDuration() %> hours</p>
    </div>
</div>
</body>
</html>