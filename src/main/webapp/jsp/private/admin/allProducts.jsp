<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.03.2019
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
    <title><fmt:message key="label.allDishes"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/assets/images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/fonts/themify/themify-icons.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/vendor/lightbox2/css/lightbox.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/css/main.css">
    <!--===============================================================================================-->
</head>
<body class="animsition">
<jsp:include page="/jsp/include/navbar.jsp" />
<!-- Title Page -->
<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background2.jpg);">
    <h2 class="tit6 t-center">
        <fmt:message key="label.allDishes"/>
    </h2>
</section>


<section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-lg-6 p-r-35 p-r-15-lg m-l-r-auto">
                <div class="wrap-item-mainmenu p-b-22">
                    <h3 class="tit-mainmenu tit10 p-b-25">
                        <fmt:message key="label.dishes"/>:
                    </h3>
                    <c:forEach var="elem" items="${products}" varStatus="status">
                        <div class="item-mainmenu m-b-36">
                            <div class="flex-w flex-b m-b-3">
                                <label class="name-item-mainmenu txt36">
                                    <c:out value="${elem.title}"/>
                                </label>

                                <div class="line-item-mainmenu bg3-pattern"></div>
                                <form action="${pageContext.servletContext.contextPath}/cafe/product/editProduct">
                                    <button type="submit" class="btn3 flex-c-m size18 txt11 trans-0-4 m-10">
                                        <fmt:message key="label.change"/>
                                        <input type="hidden" name="editProduct" value="${elem.id}">
                                    </button>
                                </form>
                            </div>

                            <span class="info-item-mainmenu txt23">
                            <fmt:message key="label.description"/>: <c:out value="${elem.description}"/><br>
                            <fmt:message key="label.image"/>:<br>
                            <p><img src="${elem.picture}" width="200" height="100"></p>
                            <fmt:message key="label.price"/>: <c:out value="${elem.price}"/><br>
                            <fmt:message key="label.status"/>:
                            <c:choose>
                                <c:when test="${elem.availability == false}">
                                    <fmt:message key="label.notAvailability"/>
                                    <br>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="label.availability"/>
                                    <br>
                                </c:otherwise>
                            </c:choose>
                                <fmt:message key="label.category"/>:
                            <c:choose>
                                <c:when test="${elem.category == 'FIRST_COURSE'}">
                                    <fmt:message key="label.firstCourse"/>
                                    <br>
                                </c:when>
                                <c:when test="${elem.category == 'MAIN_COURSE'}">
                                    <fmt:message key="label.mainCourse"/>
                                    <br>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="label.drink"/>
                                    <br>
                                </c:otherwise>
                            </c:choose>
                        </span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>


<!-- Back to top -->
<div class="btn-back-to-top bg0-hov" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
</div>


<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/bootstrap/js/popper.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/slick/slick.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/js/slick-custom.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/parallax100/parallax100.js"></script>
<script type="text/javascript">
    $('.parallax100').parallax100();
</script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/assets/vendor/lightbox2/js/lightbox.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>
