<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="at.fhv.hike.HelloServlet" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World. Erste Änderung!" %></h1>
<br/>
<form action="results.jsp" method="get">
  <input type="number" name="value" />
  <br>

  <label>
    <select name="percentage">
      <option>10%</option>
      <option>20%</option>
    </select>
  </label>

  <br>

  <input type="submit" value="Submit Form" style="width:100px;height:50px">


</form>


<a href="hello-servlet">Hello Servlet</a>
</body>
</html>