<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24/07/2023
  Time: 10:13 SA
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
<div class="container d-flex justify-content-center">
    <div class="col-6 mt-5">
        <form method="post" >
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

            <div class="row mt-3 mb-3">
                <label class="col-3" for="">Tên: </label>
                <div class="col-9">
                    <input type="text" class="form-control" name = "name" value="${requestScope.teacher.getName()}"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" for="">Sở thích: </label>
                <div class="col-9">
                    <input type="text" class="form-control" name="hobbie" value="${requestScope.teacher.getHobbie()}"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" for="">Giới tính: </label>
                <div class="col-9">
                    <input type="radio" id="option1" name="gender" value="NAM">
                    <label for="option1">NAM</label><br>
                    <input type="radio" id="option2" name="gender" value="NU">
                    <label for="option2">NỮ</label><br>

                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" for="">Ngày sinh: </label>
                <div class="col-9">
                    <input type="date" class="form-control" name="date" value="${requestScope.teacher.getDateOfBirth()}"/>
                </div>
            </div>
            <div class="row mb-3">
                <label class="col-3" for="">Bằng cấp: </label>
                <div class="col-9">
                    <select name="degree">
                        <c:forEach items="${requestScope.degrees}" var="d">
                            <option value="${d.getId()}">${d.getName()}</option>
                        </c:forEach>

                    </select>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-9 offset-3">
                    <button class="btn btn-primary">Update</button>
                    <a href="/teacher"><button type="button" class="btn btn-dark">Cancel</button></a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
