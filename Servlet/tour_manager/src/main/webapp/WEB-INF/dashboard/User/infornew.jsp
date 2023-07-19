<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 19/07/2023
  Time: 4:27 SA
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
  <title>Customer</title>
</head>

<body>
<div class="d-flex" id="wrapper">
  <!-- Sidebar -->
  <div class="bg-white" id="sidebar-wrapper">
    <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom"><i
            class="fas fa-user-secret me-2"></i>${requestScope.user.getUsername()}</div>
    <div class="list-group list-group-flush my-3">
      <a href="/user" class="list-group-item list-group-item-action bg-transparent second-text active"><i
              class="fas fa-user"></i> Tài Khoản</a>
      <a href="/home"
         class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-users"></i> Trang chủ</a>
      <a href="#"
         class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-project-diagram me-2"> Đặt chỗ của tôi</i>
      </a>
      <a href="#"
         class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-receipt"></i> Danh sách giao dịch</a>

      <a href="/logout"
         class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
              class="fas fa-power-off me-2"></i>Đăng Xuất</a>
    </div>
  </div>
  <!-- /#sidebar-wrapper -->

  <!-- Page Content -->
  <div id="page-content-wrapper">
    <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
      <div class="d-flex align-items-center">
        <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
        <h2 class="fs-2 m-0">Thông Tin Tài Khoản</h2>
      </div>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

    </nav>

    <div class="container-fluid px-4">
      <div class="row my-5">
        <h3 class="fs-4 mb-3">Hồ Sơ Của Tôi</h3>
        <div class="col">
          <table class="table bg-white rounded shadow-sm  table-hover">
            <form method="post">

              <div class="mb-3">
                <label class="small mb-1" for="inputUsername">Username:</label>
<%--                <input class="form-control" id="inputUsername" type="text" value= "${sessionScope.user.getUsername()}" readonly>--%>
                                <input class="form-control" id="inputUsername" type="text" value= "${requestScope.user.getUsername()}" readonly>
              </div>
              <div class="mb-3">
                <label class="small mb-1" for="inputUsername">Họ và Tên</label>
<%--                <input class="form-control" id="inputUsername" type="text" value= "${sessionScope.user.getFullname()}" readonly>--%>
                                <input class="form-control" id="inputUsername" type="text" value= "${requestScope.user.getFullname()}" readonly>
              </div>
              <!-- Form Group (email address)-->
              <div class="mb-3">
                <label class="small mb-1" for="inputEmailAddress">Email address</label>
<%--                <input class="form-control" id="inputEmailAddress" type="email" value="${sessionScope.user.getEmail()}" readonly>--%>
                                <input class="form-control" id="inputEmailAddress" type="email" value="${requestScope.user.getEmail()}" readonly>
              </div>

              <!-- Form Row-->
              <div class="row gx-3 mb-3">
                <!-- Form Group (phone number)-->
                <div class="col-md-6">
                  <label class="small mb-1" for="inputPhone">Phone number</label>
<%--                  <input class="form-control" id="inputPhone" type="tel" value="${sessionScope.user.getPhone()}" readonly>--%>
                                    <input class="form-control" id="inputPhone" type="tel" value="${requestScope.user.getPhone()}" readonly>
                </div>
                <!-- Form Group (birthday)-->
                <%--                <div class="col-md-6">--%>
                <%--                  <label class="small mb-1" for="inputBirthday">Địa Chỉ</label>--%>
                <%--                  <input class="form-control" id="inputBirthday" type="text" name="Địa chỉ" placeholder="Nhập địa chỉ.." value="Huế" readonly>--%>
                <%--                </div>--%>
              </div>
              <!-- Save changes button-->
                <a href="/user?action=edit&id=${sessionScope.user.getId()}"><button class="btn btn-primary" type="button">Chỉnh sửa thông tin</button></a>
                <a href="/password?action=edit&id=${sessionScope.user.getId()}"><button class="btn btn-primary m-5" type="button">Đổi mật khẩu</button></a>
            </form>

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