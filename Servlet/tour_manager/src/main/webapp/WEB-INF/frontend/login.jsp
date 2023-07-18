<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/07/2023
  Time: 3:57 SA
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
  <title>Login Form</title>
  <!-- Bootstrap link -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- Bootstrap link -->

  <link rel="stylesheet" href="/frontend/assets/style_lg.css">

</head>

<body>

<!-- Nav Start -->
<nav class="navbar navbar-expand-lg" id="navbar">
  <div class="container">
    <a class="navbar-brand" href="/home" id="logo"><span>T</span>ravel</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span><i class="fa-solid fa-bars"></i></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="/home">Trang chủ</a>
        </li>
<%--        <li class="nav-item">--%>
<%--          <a class="nav-link" href="#book">Book</a>--%>
<%--        </li>--%>
        <li class="nav-item">
          <a class="nav-link" href="/home#packages">Gói</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/home#service">Dịch Vụ</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/home#gallary">Trải Nghiệm</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/home#about">Liên Hệ</a>
        </li>


      </ul>



    </div>

  </div>
</nav>

<!-- nav End -->


<!-- Main Container Start -->
<div class="container d-flex justify-content-center align-items-center min-vh-100">


  <!-- Login Container Start -->
  <div class="row border rounded-5 p-3 bg-white shadow box-area">


    <!-- Left Box start -->
    <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box"
         style="background: #00B0B8;">
      <div class="featured-image mb-3">
        <img src="" class="img-fluid" style="width: 250px;">
      </div>
      <p class="text-white fs-2" style="font-family: 'Courier New', Courier, monospace; font-weight: 600;">New
        here?</p>
      <small class="text-white text-wrap text-center"
             style="width: 17rem;font-family: 'Courier New', Courier, monospace;">Sign up and discover great
        amount of new apportunities</small>

    </div>
    <!-- Left Box End -->


    <!-- right Box End -->
    <div class="col-md-6 right-box">
      <div class="row align-items-center">
        <div class="header-text mb-4">
          <h2>Xin chào</h2>
          <p>Vui vì bạn trở lại</p>
        </div>
        <form method="post">
          <div class="input-group mb-3">
            <input type="text" class="form-control form-control-lg bg-light fs-6"
                   placeholder="Username" name="username">
          </div>
          <div class="input-group mb-1">
            <input type="password" class="form-control form-control-lg bg-light fs-6"
                   placeholder="Password" name="password">
          </div>
<%--          <div class="input-group mb-5 d-flex justify-content-between">--%>
<%--            <div class="form-check">--%>
<%--              <input type="checkbox" class="form-check-input" id="formCheck">--%>
<%--              <label for="formCheck" class="form-check-label text-secondary"><small>Ghi nhớ đăng--%>
<%--                nhập</small></label>--%>
<%--            </div>--%>
<%--            <div class="forgot">--%>
<%--              <small><a href="#">Quên mật khẩu?</a></small>--%>
<%--            </div>--%>
<%--          </div>--%>
          <div class="input-group mb-3">
            <button class="btn btn-lg btn-primary w-100 fs-6"
                    style="background: #00B0B8   ; border: none;">Đăng nhập</button>
          </div>
        </form>


        <div class="row">
          <small>Bạn chưa có tài khoản? <a href="/register">Đăng ký</a></small>
        </div>
      </div>
    </div>
    <!-- right Box End -->
  </div>
  <!-- Login Container End -->
</div>
<!-- Main Container End -->
</body>
</body>

</html>
