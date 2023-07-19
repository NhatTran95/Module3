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
<jsp:include page="/WEB-INF/dashboard/layout/sidebar.jsp"></jsp:include>
<div class="container d-flex justify-content-center">
    <div class="col-6 mt-5 ">
        <form method="post">
            <h4>Edit</h4>
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
                <label class="col-3" >Destination: </label>
                <div class="col-9">
                    <input type="text" class="form-control" name = "destination" value="${tour.getDestination()}"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" >Route: </label>
                <div class="col-9">
                    <input type="text" class="form-control" name="route" value="${tour.getRoute()}"/>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-3" >StartDate: </label>
                <div class="col-9">
                    <input type="date" class="form-control" name="startDate" value="${tour.getStartDate()}"/>
                </div>
            </div>

            <div class="row mb-3">
                <label class="col-3" >EndDate: </label>
                <div class="col-9">
                    <input type="date" class="form-control" name="endDate" value="${tour.getEndDate()}"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" >Price: </label>
                <div class="col-9">
<%--                    String.format("%.0f", t.getPrice())--%>
                    <input type="text" class="form-control" name="price" value="${String.format("%.0f", tour.getPrice())}"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" >Img: </label>
                <div class="col-9">
                    <input type="file" class="form-control" name="img" value="${tour.getImg()}"/>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-9 offset-3">
                    <button class="btn btn-primary">Save</button>
                    <a href="/tours"><button type="button" class="btn btn-dark">Cancel</button></a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>