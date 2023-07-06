<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/07/2023
  Time: 5:17 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>converter</title>
</head>
<body>
  <%
    float rate = Float.parseFloat(request.getParameter("rate"));
    float usd = Float.parseFloat(request.getParameter("usd"));
    float vnd = usd * rate;
  %>
  <h2>rate: <%= rate%></h2>
  <h2>usd: <%= usd%></h2>
  <h2>vnd: <%= vnd%></h2>

</body>
</html>
