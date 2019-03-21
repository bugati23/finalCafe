<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.02.2019
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
    <title>NavBar</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<body>
<!-- Header -->
<header>
    <!-- Header desktop -->
    <div class="wrap-menu-header gradient1 trans-0-4">
        <div class="container h-full">
            <div class="wrap_header trans-0-3">
                <!-- Logo -->
                <div class="logo">
                    <a href="${pageContext.servletContext.contextPath}/cafe/user/home">
                        <img src="${pageContext.servletContext.contextPath}/assets/images/icons/logo.png" alt="IMG-LOGO" data-logofixed="${pageContext.servletContext.contextPath}/assets/images/icons/logo2.png">
                    </a>
                </div>

                <!-- Menu -->
                <div class="wrap_menu p-l-45 p-l-0-xl">
                    <nav class="menu">
                        <ul class="main_menu">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/cafe/user/home"><fmt:message key="label.home"/></a>
                            </li>

                            <li>
                                <a href="${pageContext.servletContext.contextPath}/cafe/product/menu"><fmt:message key="label.menu"/></a>
                            </li>

                            <li>
                                <a href="${pageContext.servletContext.contextPath}/cafe/feedback/reviews"><fmt:message key="label.reviews"/></a>
                            </li>
                        </ul>
                    </nav>
                </div>

                <!-- Bar -->
                <div class="bar flex-w flex-l-m p-r-20">
                    <a href="${pageContext.servletContext.contextPath}/cafe/user/profile"><i class="fa fa-user fa-lg" aria-hidden="true"></i></a>
                    <c:if test="${user!=null}">
                        <h6 class="m-l-18 fa-lg" aria-hidden="true"> <fmt:message key="label.account"/>: ${user.account} </h6>
                        <h6 class="m-l-18 fa-lg" aria-hidden="true"> <fmt:message key="label.pointsLoyalty"/>: ${user.pointsLoyalty} </h6>
                        <a href="${pageContext.servletContext.contextPath}/cafe/order/cart"><i class="fa fa-shopping-cart m-l-21 fa-lg" aria-hidden="true"></i></a>
                        <a href="${pageContext.servletContext.contextPath}/cafe/user/signout"><i class="fa fa-sign-out m-l-21 fa-lg" aria-hidden="true"></i></a>
                    </c:if>
                    <a href="${pageContext.servletContext.contextPath}/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
                    <a href="${pageContext.servletContext.contextPath}/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>