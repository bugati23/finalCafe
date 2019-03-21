<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.03.2019
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
    <title><fmt:message key="label.profile"/></title>
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
        <%--<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/assets/css/profile.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        a {
            color: #2e3133; /* Цвет обычной ссылки */
            text-decoration: none; /* Убираем подчеркивание у ссылок */
        }
        a:hover {
            color: #007fff; /* Цвет ссылки при наведении на нее курсора мыши */
            text-decoration: underline; /* Добавляем подчеркивание */
        }
    </style>
</head>
<body>
<div class="container emp-profile">
    <form method="post">
        <div class="row">
            <div class="col-md-6">
                <div class="profile-head">
                    <h5><c:out value="${user.login}"/></h5>
                    <h6><c:out value="${user.role}"/></h6>
                    <p class="proile-rating"><fmt:message key="label.account"/> : <span><c:out value="${user.account}"/></span></p>
                </div>
            </div>
            <div style="float: right">
                <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/editProfile"><fmt:message key="label.editProfile"/> </a>
                <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
                <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
                <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/home"><i class="fa fa-times" aria-hidden="true"></i></a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="profile-work">
                    <c:choose>
                        <c:when test="${user.role == 'USER'}">
                            <a href="${pageContext.servletContext.contextPath}/cafe/cafe/order/myOrders"><fmt:message key="label.myOrders"/> </a><br/>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.servletContext.contextPath}/cafe/cafe/order/myOrders"><fmt:message key="label.myOrders"/> </a><br/>
                            <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/allUsers"><fmt:message key="label.allUsers"/></a><br/>
                            <a href="${pageContext.servletContext.contextPath}/cafe/cafe/product/allProducts"><fmt:message key="label.allProducts"/></a><br/>
                            <a href="${pageContext.servletContext.contextPath}/cafe/cafe/product/addProduct"><fmt:message key="label.addProduct"/></a><br/>
                            <a href="${pageContext.servletContext.contextPath}/cafe/cafe/order/allOrders"><fmt:message key="label.allOrders"/></a><br/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-md-8">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message key="label.login"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out value="${user.login}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message key="label.firstName"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out value="${user.firstName}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message key="label.lastName"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out value="${user.lastName}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message key="label.email"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out value="${user.email}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label><fmt:message key="label.pointsLoyalty"/></label>
                            </div>
                            <div class="col-md-6">
                                <p><c:out value="${user.pointsLoyalty}"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
