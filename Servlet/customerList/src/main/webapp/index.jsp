<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>customer</title>
</head>
<body>
        <form action="/customer" method="post">
            <table>
                <thead>DANH SÁCH KHÁCH HÀNG</thead>
                <tbody>
                <tr>
                    <th>Tên</th>
                    <th>Ngày sinh</th>
                    <th>Địa chỉ</th>
                    <th>Ảnh</th>
                </tr>
                <c:forEach items="${customers}" var="cus">
                    <tr>
                        <td>
                            <c:out value="${cus.name}"/>
                        </td>
                        <td>
                            <c:out value="${cus.birthday}"/>
                        </td>
                        <td>
                            <c:out value="${cus.address}"/>
                        </td>
                        <td>
                            <img src="${cus.image}" style="width: 30px; height: 30px">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit">Display</button>
        </form>

</body>
</html>