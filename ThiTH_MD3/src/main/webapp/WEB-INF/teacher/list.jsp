<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 24/07/2023
  Time: 10:12 SA
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
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
<div class="container">

    <div class="content">
        <c:if test="${sessionScope.messageEdit !=null}">
            <script>
                window.onload = ()=>{
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'Sửa thành công',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            </script>
            <%--        <c:set scope="session" var="messageEdit" value="null"></c:set>--%>
            <% session.setAttribute("messageEdit", null);%>
        </c:if>

        <c:if test="${sessionScope.messageDelete !=null}">
            <script>
                window.onload = ()=>{
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'Xóa thành công',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            </script>
            <%--        <c:set scope="session" var="messageEdit" value="null"></c:set>--%>
            <% session.setAttribute("messageDelete", null);%>
        </c:if>

        <div class="container-fluid">
            <div class="row my-5">
                <h3 class="fs-4 mb-3">Danh sách Tours</h3>
<%--                <div class="head-control d-flex justify-content-between mt-5">--%>
<%--                    <a href="/tours?action=create"><button class="btn btn-primary">Create</button></a>--%>
<%--                </div>--%>
                <div class="head-control d-flex justify-content-between mt-5">
                    <a href="/teacher?action=create"><button class="btn btn-primary">Create</button></a>
                    <form method="get">
                        <select name="customer-type">
                            <option value="-1">ALL</option>
<%--                            <c:forEach items="${customerTypes}" var="c">--%>
<%--                                <option ${pageable.getCustomerType() == c.getId() ? 'selected' : ''}  value="${c.getId()}">${c.getName()}</option>--%>
<%--                            </c:forEach>--%>
                        </select>
                        <select name="customer-type">
                            <option value="-1">ALL</option>
<%--                            <c:forEach items="${customerTypes}" var="c">--%>
<%--                                <option ${pageable.getCustomerType() == c.getId() ? 'selected' : ''}  value="${c.getId()}">${c.getName()}</option>--%>
<%--                            </c:forEach>--%>
                        </select>
                        <button>Search</button>
                    </form>
                </div>
                <div class="col">
                    <table class="table bg-white rounded shadow-sm  table-hover">

                        <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Sở thích</th>
                            <th scope="col">Ngày sinh</th>
                            <th scope="col">Giới tính</th>
                            <th scope="col">Bằng cấp</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.teachers}" var="t">
                            <tr>
                                <td>${t.getName()}</td>
                                <td>${t.getHobbie()}</td>
                                <td>${t.getDateOfBirth()}</td>
                                <td>${t.getGender()}</td>
                                <td>${t.getDegree().getName()}</td>
                                <td>
                                    <a href="/teacher?action=edit&id=${t.getId()}"><i class="fa-solid fa-pen-to-square"></i></a>
                                    <a href="javascript:void(0)" onclick="handleDeleteClick(${t.getId()})"><i class="fa-solid fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>


                        </tbody>

                    </table>

                </div>
            </div>

        </div>
    </div>
</div>
<script>
    function handleDeleteClick(id){
        Swal.fire({
            title: 'Are you sure?',
            text: "Bạn có muốn xóa tour hay ko",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            console.log(result);
            if (result.isConfirmed) {
                // /customers?action=delete&id=
                location.assign("/teacher?action=delete&id=" + id);
            }
        })
    }
</script>
</body>
</html>