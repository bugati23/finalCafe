<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
	<title>Menu</title>
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
	<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background1.jpg);">
		<h2 class="tit6 t-center">
			<fmt:message key="label.menu"/>
		</h2>
	</section>


	<!-- Main menu -->
	<section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">
		<%--<div class="container">--%>
			<%--<div class="row">--%>
				<%--<div class="col-md-10 col-lg-6 p-r-35 p-r-15-lg m-l-r-auto">--%>
					<%--<div class="wrap-item-mainmenu p-b-22">--%>
						<%--<h3 class="tit-mainmenu tit10 p-b-25">--%>
							<%--STARTERS--%>
						<%--</h3>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Pine nut sbrisalona--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$29.79--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Sed fermentum eros vitae eros--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Aenean eu--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$19.35--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Feugiat maximus neque pharetra--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Sed feugiat--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$12.19--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Proin lacinia nisl ut ultricies posuere nulla--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Consectetur--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$21.89--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Etiam cursus facilisis tortor--%>
							<%--</span>--%>
						<%--</div>--%>
					<%--</div>--%>

					<%--<div class="wrap-item-mainmenu p-b-22">--%>
						<%--<h3 class="tit-mainmenu tit10 p-b-25">--%>
							<%--Drinks--%>
						<%--</h3>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Vivamus pretium--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$29.79--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Sed fermentum eros vitae eros--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Duis pharetra ligula--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$19.35--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Feugiat maximus neque pharetra--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--In eu dolor--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$53.34--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Proin lacinia nisl ut ultricies posuere nulla--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Feugiat maximus--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$62.45--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Sed fermentum eros vitae eros--%>
							<%--</span>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>

				<%--<div class="col-md-10 col-lg-6 p-l-35 p-l-15-lg m-l-r-auto">--%>
					<%--<div class="wrap-item-mainmenu p-b-22">--%>
						<%--<h3 class="tit-mainmenu tit10 p-b-25">--%>
							<%--Main--%>
						<%--</h3>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Duis sed aliquet--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$31.18--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Proin lacinia nisl ut ultricies posuere nulla--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Suspendisse--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$70.25--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Feugiat maximus neque pharetra--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Scelerisque sed--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$36.19--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Etiam cursus facilisis tortor--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Mollis nulla--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$19.50--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Proin lacinia nisl ut ultricies posuere nulla--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Convallis augue--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$29.15--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Sed fermentum eros vitae eros--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Maecenas tristique--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$29.79--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Feugiat maximus neque pharetra--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Duis tincidunt--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$19.35--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Proin lacinia nisl ut ultricies posuere nulla--%>
							<%--</span>--%>
						<%--</div>--%>
					<%--</div>--%>

					<%--<div class="wrap-item-mainmenu p-b-22">--%>
						<%--<h3 class="tit-mainmenu tit10 p-b-25">--%>
							<%--Dessert--%>
						<%--</h3>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--tempus aliquet--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$9.79--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Proin lacinia nisl ut ultricies posuere nulla--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--scelerisque--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$19.35--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Sed fermentum eros vitae eros--%>
							<%--</span>--%>
						<%--</div>--%>

						<%--<!-- Item mainmenu -->--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a href="#" class="name-item-mainmenu txt21">--%>
									<%--Cras maximus--%>
								<%--</a>--%>

								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>

								<%--<div class="price-item-mainmenu txt22">--%>
									<%--$5.79--%>
								<%--</div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
								<%--Duis pharetra ligula at urna dignissim--%>
							<%--</span>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
	</section>


	<!-- First -->
	<section class="section-lunch bgwhite">
		<div class="header-lunch parallax0 parallax100" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background2.jpg);">
			<div class="bg1-overlay t-center p-t-170 p-b-165">
				<h2 class="tit4 t-center">
					<fmt:message key="label.firstCourse"/>
				</h2>
			</div>
		</div>

		<div class="container">
			<div class="row p-t-108 p-b-70">
				<div class="col-md-8 col-lg-6 m-l-r-auto">
                    <c:forEach var="elem" items="${products}" varStatus="status">
					<c:if test="${elem.category == 'FIRST_COURSE'}">
					<c:if test="${elem.availability == true}">
					<div class="blo3 flex-w flex-col-l-sm m-b-30">
						<div class="pic-blo3 size20 bo-rad-10 hov-img-zoom m-r-28">
							<a href="#"><img src="${elem.picture}" alt="IMG-MENU"></a>
						</div>

						<div class="text-blo3 size21 flex-col-l-m">
							<a href="#" class="txt21 m-b-3">
								<c:out value="${elem.title}"/>
							</a>

							<span class="txt23">
								<c:out value="${elem.description}"/>
							</span>

							<span class="txt22 m-t-20">
								<c:out value="${elem.price}"/>
							</span>
						</div>
					</div>
					</c:if>
					</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>


	<!-- Main -->
	<section class="section-dinner bgwhite">
		<div class="header-dinner parallax0 parallax100" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background2.jpg);">
			<div class="bg1-overlay t-center p-t-170 p-b-165">
				<h2 class="tit4 t-center">
					<fmt:message key="label.mainCourse"/>
				</h2>
			</div>
		</div>

		<div class="container">
			<div class="row p-t-108 p-b-70">
				<div class="col-md-8 col-lg-6 m-l-r-auto">
                    <c:forEach var="elem" items="${products}" varStatus="status">
                        <c:if test="${elem.category == 'MAIN_COURSE'}">
                            <c:if test="${elem.availability == true}">
                                <div class="blo3 flex-w flex-col-l-sm m-b-30">
                                    <div class="pic-blo3 size20 bo-rad-10 hov-img-zoom m-r-28">
                                        <a href="#"><img src="${elem.picture}" alt="IMG-MENU"></a>
                                    </div>

                                    <div class="text-blo3 size21 flex-col-l-m">
                                        <a href="#" class="txt21 m-b-3">
                                            <c:out value="${elem.title}"/>
                                        </a>

                                        <span class="txt23">
								<c:out value="${elem.description}"/>
							</span>

                                        <span class="txt22 m-t-20">
								<c:out value="${elem.price}"/>
							</span>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                    </c:forEach>
				</div>
			</div>
		</div>
	</section>

	<!-- Drink -->
	<section class="section-dinner bgwhite">
		<div class="header-dinner parallax0 parallax100" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background2.jpg);">
			<div class="bg1-overlay t-center p-t-170 p-b-165">
				<h2 class="tit4 t-center">
					<fmt:message key="label.drink"/>
				</h2>
		    </div>
	    </div>

	    <div class="container">
		    <div class="row p-t-108 p-b-70">
			    <div class="col-md-8 col-lg-6 m-l-r-auto">
                    <c:forEach var="elem" items="${products}" varStatus="status">
                        <c:if test="${elem.category == 'DRINK'}">
                            <c:if test="${elem.availability == true}">
                                <div class="blo3 flex-w flex-col-l-sm m-b-30">
                                    <div class="pic-blo3 size20 bo-rad-10 hov-img-zoom m-r-28">
                                        <a href="#"><img src="${elem.picture}" alt="IMG-MENU"></a>
                                    </div>

                                    <div class="text-blo3 size21 flex-col-l-m">
                                        <a href="#" class="txt21 m-b-3">
                                            <c:out value="${elem.title}"/>
                                        </a>

                                        <span class="txt23">
								<c:out value="${elem.description}"/>
							</span>

                                        <span class="txt22 m-t-20">
								<c:out value="${elem.price}"/>
							</span>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                    </c:forEach>
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
