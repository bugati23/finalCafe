<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.03.2019
  Time: 1:56
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
    <title><fmt:message key="label.changeStatus"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/registrationpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.servletContext.contextPath}/cafe/order/editOrderForm" method="post">
    <h1><fmt:message key="label.changeStatus"/></h1>
    <fieldset id="inputs">
        <c:choose>
            <c:when test="${editOrder.status == 'EXPECTS'}">
                <input type="radio" name="statusOrder" value="1" checked> <br> <fmt:message key="label.expects"/><br>
                <input type="radio" name="statusOrder" value="2"> <br> <fmt:message key="label.closed"/><br>
                <input type="radio" name="statusOrder" value="3"> <br> <fmt:message key="label.expired"/><br>
            </c:when>
            <c:when test="${editOrder.status == 'CLOSED'}">
                <input type="radio" name="statusOrder" value="1"> <br> <fmt:message key="label.expects"/><br>
                <input type="radio" name="statusOrder" value="2" checked> <br> <fmt:message key="label.closed"/><br>
                <input type="radio" name="statusOrder" value="3"> <br> <fmt:message key="label.expired"/><br>
            </c:when>
            <c:otherwise>
                <input type="radio" name="statusOrder" value="1"> <br> <fmt:message key="label.expects"/><br>
                <input type="radio" name="statusOrder" value="2"> <br> <fmt:message key="label.closed"/><br>
                <input type="radio" name="statusOrder" value="3" checked> <br> <fmt:message key="label.expired"/><br>
            </c:otherwise>
        </c:choose>
        <input id="submit" type="submit" value="<fmt:message key="label.change"/>"/>
        <input type="hidden" name="formId" value="${formId}">
        <a href="${pageContext.servletContext.contextPath}/cafe/order/allOrders"><fmt:message key="label.back"/> </a>
    </fieldset>
</form>
</body>
</html>
