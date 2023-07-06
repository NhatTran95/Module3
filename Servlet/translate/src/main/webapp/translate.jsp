<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
<form action="/translate" method="post">
<%
  String keyword = "";
  if(request.getAttribute("keyword") != null){
    keyword = request.getAttribute("keyword").toString();
  }
%>
  <input type="text" name="kw" value="<%= keyword%>" />
  <button type="submit">Transalte</button>
</form>
<%
  String result = "";
  if(request.getAttribute("result")!=null){
    result = "Kết quả: " + request.getAttribute("result").toString();
  }
%>
<label><%= result%></label>
</body>
</html>
