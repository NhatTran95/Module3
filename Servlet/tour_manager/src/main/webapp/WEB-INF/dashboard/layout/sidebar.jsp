<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex" id="wrapper">
  <!-- Sidebar -->
  <div class="bg-white" id="sidebar-wrapper">
    <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom"><i
            class="fas fa-user-secret me-2"></i>${sessionScope.user.getUsername()}</div>
    <div class="list-group list-group-flush my-3">
      <a href="/admin" class="list-group-item list-group-item-action bg-transparent second-text fw-bold "><i
              class="fas fa-user"></i> Tài Khoản</a>
      <a href="orderlist.html" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-project-diagram me-2"></i>Quản lý Đơn
      </a>
      <a href="/tours" class="list-group-item list-group-item-action bg-transparent second-text fw-bold active"><i
              class="fas fa-receipt"></i> Quản lý Tour</a>
      <a href="/customer" class="list-group-item list-group-item-action bg-transparent second-text"><i
              class="fas fa-users"></i> Khách hàng</a>

      <a href="#" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
              class="fas fa-receipt"></i> Doanh thu</a>

      <a href="/logout" class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
              class="fas fa-power-off me-2"></i>Logout</a>
    </div>
  </div>
