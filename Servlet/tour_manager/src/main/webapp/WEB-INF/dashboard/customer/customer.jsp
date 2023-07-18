<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/07/2023
  Time: 6:24 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="/dashboard/assets/admin.css" />
    <title>Admin</title>
</head>

<body>
    <jsp:include page="/WEB-INF/dashboard/layout/sidebar.jsp"></jsp:include>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
            <div class="d-flex align-items-center">
                <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                <h2 class="fs-2 m-0">Trang quản lý khách hàng </h2>
            </div>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

        </nav>

        <div class="container-fluid px-4">
            <div class="row my-5">
                <h3 class="fs-4 mb-3">Danh sách khách hàng</h3>
                <div class="col">
                    <table class="table bg-white rounded shadow-sm  table-hover">

                        <thead>
                        <tr>
<%--                            <th scope="col" width="50">#</th>--%>
                            <th scope="col">Tên Khách hàng</th>
                            <th scope="col">Email</th>
                            <th scope="col">Số điện thoại</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="u" >
                            <tr>
<%--                                <th scope="row">1</th>--%>
                                <td>${u.getFullname()}</td>
                                <td>${u.getEmail()}</td>
                                <td>${u.getPhone()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
<!-- /#page-content-wrapper -->
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>