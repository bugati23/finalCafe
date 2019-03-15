<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
	<title>Reviews</title>
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
<section class="section-slide">
	<div class="wrap-slick1">
		<div class="slick1">
			<div class="item-slick1 item1-slick1" style="background-image: url(${pageContext.servletContext.contextPath}/assets/images/background1.jpg);">
				<div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
					<h2 class="caption2-slide1 tit1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
						<fmt:message key="label.reviews"/>
					</h2>

					<div class="wrap-btn-slide1 animated visible-false" data-appear="zoomIn">
						<!-- Button1 -->
						<a href="${pageContext.servletContext.contextPath}/cafe/feedback/addReview" class="btn1 flex-c-m size1 txt3 trans-0-4">
							<fmt:message key="label.leaveReview"/>
						</a>
					</div>
				</div>
			</div>
		</div>

		<div class="wrap-slick1-dots"></div>
	</div>
</section>
<%--<section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">--%>
	<%--<div class="container">--%>
		<%--<div class="row">--%>
			<%--<div class="col-md-10 col-lg-6 p-r-35 p-r-15-lg m-l-r-auto">--%>
				<%--<div class="wrap-item-mainmenu p-b-22">--%>
					<%--<c:forEach var="elem" items="${reviews}" varStatus="status">--%>
						<%--<div class="item-mainmenu m-b-36">--%>
							<%--<div class="flex-w flex-b m-b-3">--%>
								<%--<a  class="name-item-mainmenu txt36">--%>
									<%--<c:out value="${userFeedback[status.index].login}"/>--%>
								<%--</a>--%>
								<%--<div class="line-item-mainmenu bg3-pattern"></div>--%>
							<%--</div>--%>

							<%--<span class="info-item-mainmenu txt23">--%>
                            <%--<c:out value="${elem.review}"/>--%>
                        <%--</span>--%>
						<%--</div>--%>
					<%--</c:forEach>--%>
				<%--</div>--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</div>--%>
<%--</section>--%>


<section class="section-mainmenu p-t-110 p-b-70 bg1-pattern">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-lg-6 p-r-35 p-r-15-lg m-l-r-auto">
                <div class="wrap-item-mainmenu p-b-22">
                    <c:forEach var="elem" items="${reviews}" varStatus="status">
                        <div class="item-mainmenu m-b-36">
                            <div class="flex-w flex-b m-b-3">
                                <a  class="name-item-mainmenu txt36">
                                    <c:out value="${userFeedback[status.index].login}"/>
                                </a>
                                <div class="line-item-mainmenu bg3-pattern"></div>
                            </div>

                            <span class="info-item-mainmenu txt23">
                            <c:out value="${elem.review}"/>
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

	<!-- Container Selection1 -->
	<div id="dropDownSelect1"></div>



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
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
	<script src="${pageContext.servletContext.contextPath}/assets/js/map-custom.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.servletContext.contextPath}/assets/js/main.js"></script>
<jsp:include page="/jsp/include/footer.jsp" />
</body>
</html>
