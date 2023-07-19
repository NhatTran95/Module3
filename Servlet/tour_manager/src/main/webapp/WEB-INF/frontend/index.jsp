<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/07/2023
  Time: 4:33 SA
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
    <title>Travel Website</title>

    <link rel="stylesheet" href="/frontend/assets/style.css">
    <!-- Bootstrap link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Bootstrap link -->


    <!-- Font Awesome Cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Font Awesome Cdn -->

    <!-- Google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
    <!-- Google font -->


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
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#packages">Book</a>--%>
<%--                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="#packages">Gói</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#services">Dịch Vụ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#gallary">Trải Nghiệm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#about">Liên Hệ</a>
                </li>
                <li class="nav-item ">
                        <form method="get" class="d-flex" action="/home?kw=${pageable.getKw()}#packages" >
                            <input class="form-control me-2" type="text" placeholder="Tìm kiếm" name="kw" value="${pageable.getKw()}">
                            <button class="search">Search</button>
                        </form>

                </li>

            </ul>

            <form class="d-flex">
                <div class="left-items">
                    <i class="fa-sharp fa-solid fa-cart-shopping" id="cart-icon"></i><span class="cart-price">0</span>
                </div>
                <c:if test="${sessionScope.user != null}">
                    <div class="right-items">
                        <a class="nav-link" href="${sessionScope.user.getRole().toString().equals('ADMIN') ? '/admin' : '/user'}">${sessionScope.user.getUsername()}</a>
                    </div>
                    <div class="right-items">
                        <a class="nav-link" href="/logout">Đăng xuất</a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <div class="right-items">
                        <a class="nav-link" href="/login">Đăng nhập</a>
                    </div>
                </c:if>


            </form>

        </div>

    </div>
</nav>





<!-- Home Section Start -->
<div class="home">
    <div class="content">
        <h5>Trải nghiệm kỳ nghỉ tuyệt vời </h5>
        <h1>Điểm đến <span class="changecontent"></span></h1>
        <p>Combo khách sạn - tour du lịch - đưa đón tận tình với giá tốt nhất</p>
        <a href="#packages">Book Tour</a>
    </div>
</div>
<!-- Home Section End -->


<!-- Section packages Start -->
<section class="packages" id="packages">
    <div class="container">

        <div class="main-txt">
            <h1><span>G</span>ói</h1>
        </div>

        <div class="row" style="margin-top: 30px;">


                <c:forEach items="${requestScope.tours}" var="t">
                    <div class="col-md-4 py-3 py-md-0 ">
                        <div class="card">
                            <img src="${t.getImg()}" alt="">
                            <div class="card-body">
                                <h3>${t.getDestination()}</h3>
                                <p>${t.getRoute()}</p>
                                <p>Ngày khởi hành: ${t.getStartDate()}</p>
                                <p>Ngày kết thúc: ${t.getEndDate()}</p>
                                <div class="start">
                                    <i class="fa-solid fa-star checked"></i>
                                    <i class="fa-solid fa-star checked"></i>
                                    <i class="fa-solid fa-star checked"></i>
                                    <i class="fa-solid fa-star checked"></i>
                                    <i class="fa-solid fa-star "></i>
                                </div>
                                <h6>Giá: <strong> ${String.format("%.0f", t.getPrice())} VND</strong></h6>
                                <a href="#book">Book Now</a>
                            </div>
                        </div>

                    </div>
                </c:forEach>

        </div>
        <div class="d-flex justify-content-end mt-2">
            <ul class="pagination">
                <c:if test="${pageable.getPage() > 1}">
                    <li class="page-item"><a class="page-link" href="/home?page=${pageable.page - 1}#packages">Previous</a></li>
                </c:if>

                <c:forEach begin="1" end="${pageable.getTotal()}" var="i">
                    <li class="page-item ${pageable.page == i ? 'active' : ''}"><a class="page-link" href="/home?page=${i}#packages">${i}</a></li>
                </c:forEach>
                <c:if test="${pageable.getPage() < pageable.getTotal()}">
                    <li class="page-item"><a class="page-link" href="/home?page=${pageable.page + 1}#packages">Next</a></li>
                </c:if>

            </ul>
        </div>
    </div>

</section>
<!-- Section packages End -->



<!-- Section Services Start -->
<section class="services" id="services">
    <div class="container">

        <div class="main-txt">
            <h1><span>D</span>ịch Vụ</h1>
        </div>

        <div class="row" style="margin-top: 30px;">

            <div class="col-md-4 py-3 py-md-0">

                <div class="card">
                    <i class="fas fa-hotel"></i>
                    <div class="card-body">
                        <h3>Khách sạn</h3>
                        <p>Khách sạn, Khu nghỉ dưỡng là chọn lựa tuyệt vời dành cho bạn, gia đình và tập thể muốn tận hưởng cảm
                            giác gần gũi và hòa mình với thiên nhiên</p>
                    </div>
                </div>

            </div>
            <div class="col-md-4 py-3 py-md-0">

                <div class="card">
                    <i class="fas fa-utensils"></i>
                    <div class="card-body">
                        <h3>Ẩm thực</h3>
                        <p>Các địa danh đều có ẩm thực đặc sắc. Với chương trình tour du lịch, khách sẽ có cơ hội thưởng thức
                            những món ăn ngon nhất</p>
                    </div>
                </div>

            </div>
            <div class="col-md-4 py-3 py-md-0">

                <div class="card">
                    <i class="fas fa-bullhorn"></i>
                    <div class="card-body">
                        <h3>Hướng dẫn an toàn</h3>
                        <p>Trải nghiệm du lịch với chế độ chăm sóc khách hàng tận tâm và nhiệt huyết cùng với đội ngũ chăm sóc
                            khách hàng được huấn luyện với nhiều kinh nghiệm</p>
                    </div>
                </div>

            </div>




        </div>

        <div class="row" style="margin-top: 30px;">

            <div class="col-md-4 py-3 py-md-0">

                <div class="card">
                    <i class="fas fa-globe-asia"></i>
                    <div class="card-body">
                        <h3>Khám phá các địa danh</h3>
                        <p>Hãng sẽ luôn đồng hành với khách hàng khám phá tất cả các địa danh nổi tiếng của các địa danh mà bạn
                            lựa chọn, thỏa sức khám phá.</p>
                    </div>
                </div>

            </div>
            <div class="col-md-4 py-3 py-md-0">

                <div class="card">
                    <i class="fas fa-plane"></i>
                    <div class="card-body">
                        <h3>Thủ tục nhanh chóng</h3>
                        <p>Hãng sẽ cung cấp cho khách hàng các thủ tục đặt tour nhanh chóng và thuận tiện, từ việc lựa chọn tour
                            đến xấc nhận tour và chuẩn bị cho chuyến du lịch </p>
                    </div>
                </div>

            </div>
            <div class="col-md-4 py-3 py-md-0">

                <div class="card">
                    <i class="fas fa-hiking"></i>
                    <div class="card-body">
                        <h3>Trải nghiệm phiêu lưu</h3>
                        <p>Với các trải nghiệm phiêu tuyệt vời các hoạt động thú vị, chuyến đi khám phá và trải nghiệm đáng nhớ
                            như: Trekking, leo núi, lặn biển, tham quan di sản...</p>
                    </div>
                </div>

            </div>




        </div>
    </div>

</section>
<!-- Section Services End -->




<!-- Section Gallary Start -->
<section class="gallary" id="gallary">
    <div class="container">

        <div class="main-txt">
            <h1><span>T</span>rải nghiệm</h1>
        </div>

        <div class="row" style="margin-top: 30px;">
            <div class="col-md-4 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\trekking.jpg" alt="" height="230px">
                </div>
            </div>
            <div class="col-md-4 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\lanbien.jpg" alt="" height="230px">
                </div>
            </div>
            <div class="col-md-4 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\disan.jpg" alt="" height="230px">
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 30px;">
            <div class="col-md-4 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\nauan.jpg" alt="" height="230px">
                </div>
            </div>
            <div class="col-md-4 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\choi1.webp" alt="" height="230px">
                </div>
            </div>
            <div class="col-md-4 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\halong2.jpg" alt="" height="230px">
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Section Gallary End -->



<!-- About Start -->
<section class="about" id="about">
    <div class="container">

        <div class="main-txt">
            <h1>Giới thiệu <span>Team-Travel</span></h1>
        </div>
        <div class="row" style="margin-top: 50px;">

            <div class="col-md-6 py-3 py-md-0">
                <div class="card">
                    <img src="frontend\assets\image\tourguid.jpg" alt="">
                </div>
            </div>

            <div class="col-md-6 py-3 py-md-0">
                <h2>Nâng tầm chuyến du lịch của công ty và đội nhóm của bạn! </h2>
                <p>Chào mừng bạn đến với Travel - đội ngũ chuyên viên tổ chức sự kiện đầy đam mê và tận tâm của iVIVU. Chúng
                    tôi cam kết mang đến cho bạn những trải nghiệm du lịch tuyệt vời nhất, bao gồm các chuyến tham quan,
                    retreats công ty, và team building theo nhu cầu và mục tiêu riêng của bạn. Chúng tôi tự hào về các chương
                    trình du lịch thú vị và những trải nghiệm đặc biệt mà chúng tôi tạo ra, từ việc cải thiện giao tiếp cho đến
                    xây dựng niềm tin, hoặc đơn giản chỉ là một kỳ nghỉ thuần túy. Cam kết của chúng tôi là mang lại những kết
                    quả tích cực, bao gồm làm mới bản thân, nâng cao tinh thần đoàn kết và ghi lại những kỷ niệm lâu dài. Hãy
                    liên hệ với chúng tôi ngay hôm nay để bắt đầu chuyến đi của bạn và đưa đội nhóm của bạn trên con đường để
                    trở nên mạnh mẽ và gắn kết hơn bao giờ hết!</p>
                <button id="about-btn">Đọc thêm...</button>
            </div>
        </div>
    </div>
</section>
<!-- About End -->



<!-- Footer Start -->
<footer id="footer">
    <h1><span>T</span>ravel</h1>
    <p>Huế: 26 Nguyễn Tri Phương, Phú Hội, Thành Phố Huế</p>
    <div class="social-links">
        <i class="fa-brands fa-twitter"></i>
        <i class="fa-brands fa-facebook"></i>
        <i class="fa-brands fa-instagram"></i>
        <i class="fa-brands fa-youtube"></i>
        <i class="fa-brands fa-pinterest-p"></i>
    </div>
    <div class="credit">
        <p>Liên hệ: <a href="#">1900898989</a></p>
    </div>
    <div class="copyright">
        <p>travelNN@gmail.com</p>
    </div>
</footer>
<!-- Footer End -->

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"--%>
<%--        crossorigin="anonymous"></script>--%>
<script>
    // Header
    let header = document.querySelector('header');
    window.addEventListener('scroll', () => {
        header.classList.toggle('shadow', window.scrollY > 0);
    });
    let clickButtons = document.querySelectorAll("#book-click");
    let clickCount = document.querySelector("#click-count");
    let count = 0;
    for (let i = 0; i < clickButtons.length; i++) {
        clickButtons[i].addEventListener("click", function () {
            count++;
            clickCount.innerHTML = count;
            clickCount.style.color = "#00B0B8";
        });
    }
    let aboutBtn = document.querySelector('#about-btn');
    let moreContent = document.querySelector('#more-content');
    aboutBtn.addEventListener('click', function () {
        event.preventDefault();
        moreContent.style.display = 'block';
        aboutBtn.style.display = 'none';
    });
    moreContent.addEventListener('click', function () {
        moreContent.style.display = 'none';
        aboutBtn.style.display = 'block';
    });
</script>
</body>



</html>