<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/07/2023
  Time: 5:41 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css"
        integrity="sha512-t4GWSVZO1eC8BM339Xd7Uphw5s17a86tIZIj8qRxhnKub6WoyhnrxeCIMeAqBPgdZGlCcG2PrZjMc+Wr78+5Xg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.12/sweetalert2.css" integrity="sha512-K0TEY7Pji02TnO4NY04f+IU66vsp8z3ecHoID7y0FSVRJHdlaLp/TkhS5WDL3OygMkVns4bz0/ah4j8M3GgguA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.12/sweetalert2.min.js" integrity="sha512-JbRQ4jMeFl9Iem8w6WYJDcWQYNCEHP/LpOA11LaqnbJgDV6Y8oNB9Fx5Ekc5O37SwhgnNJdmnasdwiEdvMjW2Q=="
          crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  <link rel="stylesheet" href="/dashboard/assets/admin.css" />
  <title>Admin</title>
</head>
<body>
<div class="d-flex" id="wrapper">
  <!-- Sidebar -->
  <div class="bg-white" id="sidebar-wrapper">
    <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom"><i
            class="fas fa-user-secret me-2"></i>Admin</div>
    <div class="list-group list-group-flush my-3">
      <a href="admin.html" class="list-group-item list-group-item-action bg-transparent second-text fw-bold""><i
            class="fas fa-user"></i> Tài Khoản</a>
      <a href="orderlist.html" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-project-diagram me-2">Quản lý Đơn</i>
      </a>
      <a href="#" class="list-group-item list-group-item-action bg-transparent second-text active"><i
              class="fas fa-receipt"></i> Quản lý Tour</a>
      <a href="customerlist.html" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-users"></i> Khách hàng</a>

      <a href="#" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-receipt"></i> Doanh thu</a>

      <a href="signIn.html" class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
              class="fas fa-power-off me-2"></i>Đăng Xuất</a>
    </div>
  </div>
<div class="container d-flex justify-content-center">
  <div class="col-6 mt-5">
    <form method="post" >
      <h4>Create</h4>
      <c:if test="${requestScope.errors != null}">
        <div class="alert alert-danger">
          <ul>
            <c:forEach items="${requestScope.errors}" var="e">
              <li>${e}</li>
            </c:forEach>
          </ul>
        </div>
      </c:if>
      <c:if test="${requestScope.message !=null}">
        <script>
          window.onload = ()=>{
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'Thêm thành công',
              showConfirmButton: false,
              timer: 1500
            })
          }
        </script>
      </c:if>

      <div class="row mt-3 mb-3">
        <label class="col-3" for="">Destination: </label>
        <div class="col-9">
          <input type="text" class="form-control" name = "destination"/>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-3" for="">Route: </label>
        <div class="col-9">
          <input type="text" class="form-control" name="route"/>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-3" for="">StartDate: </label>
        <div class="col-9">
          <input type="date" class="form-control" name="startDate"/>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-3" for="">EndDate: </label>
        <div class="col-9">
          <input type="date" class="form-control" name="endDate"/>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-3" for="">Price: </label>
        <div class="col-9">
          <input type="text" class="form-control" name="price"/>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-3" for="">Img: </label>
        <div class="col-9">
          <input type="text" class="form-control" name="img"/>
        </div>
      </div>

      <div class="row mb-3">
        <div class="col-9 offset-3">
          <button class="btn btn-primary">Create</button>
          <a href="/tours"><button type="button" class="btn btn-dark">Back</button></a>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>
