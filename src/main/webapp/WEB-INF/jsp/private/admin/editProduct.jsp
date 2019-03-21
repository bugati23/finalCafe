<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.03.2019
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.editProduct"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/registrationpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.servletContext.contextPath}/cafe/product/editProductForm" method="post">
    <h1><fmt:message key="label.editProduct"/></h1>
    <fieldset id="inputs">
        <input id="title" name="title" type="text" value="<c:out value="${editProduct.title}"/>" placeholder="<fmt:message key="label.enterTitle"/>" minlength="1" maxlength="50" required>
        <label style="color:#721c24">${errorWrongTitle}</label><br>
        <textarea  name="description"  placeholder="<fmt:message key="label.enterDescription"/>" minlength="1" maxlength="200"><c:out value="${editProduct.description}" /></textarea>
        <label style="color:#721c24">${errorWrongDescription}</label><br>
        <input id="pict" name="pict" type="url" value="<c:out value="${editProduct.picture}"/>" placeholder="<fmt:message key="label.enterImage"/>" minlength="1" required>
        <label style="color:#721c24">${errorWrongImage}</label><br>
        <input id="price" name="price" type="number" value="<c:out value="${editProduct.price}"/>" placeholder="<fmt:message key="label.enterPrice"/>" min="0" step="0.01" required>
        <label style="color:#721c24">${errorWrongPrice}</label><br>
        <c:choose>
            <c:when test="${editProduct.availability == false}">
                <input type="radio" name="availabilityProduct" value="false" checked> <br> <fmt:message key="label.notAvailability"/><br>
                <input type="radio" name="availabilityProduct" value="true"> <br> <fmt:message key="label.availability"/><br>
                <br>
            </c:when>
            <c:otherwise>
                <input type="radio" name="availabilityProduct" value="false"> <br> <fmt:message key="label.notAvailability"/><br>
                <input type="radio" name="availabilityProduct" value="true" checked> <br> <fmt:message key="label.availability"/><br>
                <br>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${editProduct.category == 'FIRST_COURSE'}">
                <input type="radio" name="categoryProduct" value="1" checked> <br> <fmt:message key="label.firstCourse"/><br>
                <input type="radio" name="categoryProduct" value="2"> <br> <fmt:message key="label.mainCourse"/><br>
                <input type="radio" name="categoryProduct" value="3"> <br> <fmt:message key="label.drink"/><br>
            </c:when>
            <c:when test="${editProduct.category == 'MAIN_COURSE'}">
                <input type="radio" name="categoryProduct" value="1"> <br> <fmt:message key="label.firstCourse"/><br>
                <input type="radio" name="categoryProduct" value="2" checked> <br> <fmt:message key="label.mainCourse"/><br>
                <input type="radio" name="categoryProduct" value="3"> <br> <fmt:message key="label.drink"/><br>
            </c:when>
            <c:otherwise>
                <input type="radio" name="categoryProduct" value="1"> <br> <fmt:message key="label.firstCourse"/><br>
                <input type="radio" name="categoryProduct" value="2"> <br> <fmt:message key="label.mainCourse"/><br>
                <input type="radio" name="categoryProduct" value="3" checked> <br> <fmt:message key="label.drink"/><br>
            </c:otherwise>
        </c:choose>
        <input id="submit" type="submit" value="<fmt:message key="label.change"/>"/>
    </fieldset>
    <a href="${pageContext.servletContext.contextPath}/cafe/product/deleteProduct"><fmt:message key="label.delete"/> </a>
    <a href="${pageContext.servletContext.contextPath}/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
    <a href="${pageContext.servletContext.contextPath}/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
</form>
</body>
</html>
