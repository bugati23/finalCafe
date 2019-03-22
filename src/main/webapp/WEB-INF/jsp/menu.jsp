<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
	<title><fmt:message key="label.menu"/> </title>
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
<jsp:include page="/WEB-INF/jsp/include/navbar.jsp" />
	<!-- Title Page -->
	<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background1.jpg);">
		<h2 class="tit6 t-center">
			<fmt:message key="label.menu"/>
		</h2>
	</section>

	<section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">

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
							<label><img src="${elem.picture}" alt="IMG-MENU"></label>
						</div>

						<div class="text-blo3 size21 flex-col-l-m">
							<label class="txt21 m-b-3">
								<c:out value="${elem.title}"/>
							</label>

							<span class="txt23">
								<c:out value="${elem.description}"/>
							</span>

							<span class="txt22 m-t-20">
								<c:out value="${elem.price}"/>
							</span>
							<form action="${pageContext.servletContext.contextPath}/cafe/order/addToCart" method="post">
								<p><fmt:message key="label.amount"/>:</p>
								<input id="amount" name="amount" type="number" value="1" placeholder="<fmt:message key="label.enterAmount"/>" min="-12" step="1" required>
								<label style="color:#721c24">${errorWrongAmount}</label>
								<button type="submit" class="btn3 flex-c-m size18 txt11 trans-0-4 m-10">
									<fmt:message key="label.addToCart"/>
									<input type="hidden" name="ProductToCart" value="${elem.id}">
								</button>
							</form>
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
										<label><img src="${elem.picture}" alt="IMG-MENU"></label>
                                    </div>

                                    <div class="text-blo3 size21 flex-col-l-m">
                                        <label class="txt21 m-b-3">
                                            <c:out value="${elem.title}"/>
                                        </label>
                                        <span class="txt23">
											<c:out value="${elem.description}"/>
										 </span>

                                        <span class="txt22 m-t-20">
								           <c:out value="${elem.price}"/>
										 </span>
										<form action="${pageContext.servletContext.contextPath}/cafe/order/addToCart" method="post">
											<p><fmt:message key="label.amount"/>:</p>
											<input id="amount2" name="amount" type="number" value="1" placeholder="<fmt:message key="label.enterAmount"/>" min="1" step="1" required>
											<button type="submit" class="btn3 flex-c-m size18 txt11 trans-0-4 m-10">
												<fmt:message key="label.addToCart"/>
												<input type="hidden" name="ProductToCart" value="${elem.id}">
											</button>
										</form>
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
										<label><img src="${elem.picture}" alt="IMG-MENU"></label>
                                    </div>

                                    <div class="text-blo3 size21 flex-col-l-m">
                                        <label class="txt21 m-b-3">
                                            <c:out value="${elem.title}"/>
										</label>

                                        <span class="txt23">
								             <c:out value="${elem.description}"/>
							             </span>

                                        <span class="txt22 m-t-20">
								             <c:out value="${elem.price}"/>
							             </span>
										<form action="${pageContext.servletContext.contextPath}/cafe/order/addToCart" method="post">
											<p><fmt:message key="label.amount"/>:</p>
											<input id="amount3" name="amount" type="number" value="1" placeholder="<fmt:message key="label.enterAmount"/>" min="1" step="1" required>
											<button type="submit" class="btn3 flex-c-m size18 txt11 trans-0-4 m-10">
												<fmt:message key="label.addToCart"/>
												<input type="hidden" name="ProductToCart" value="${elem.id}">
											</button>
										</form>
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
<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
</body>
</html>
