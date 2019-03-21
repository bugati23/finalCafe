<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.03.2019
  Time: 16:41
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
    <title><fmt:message key="label.editUser"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/registrationpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.servletContext.contextPath}/cafe/cafe/user/editUserForm" method="post">
    <h1><fmt:message key="label.editUser"/></h1>
    <fieldset id="inputs">
        <label><fmt:message key="label.changeAccount"/></label>
        <input name="account" type="number" value="<c:out value="${editUser.account}"/>" placeholder="<fmt:message key="label.enterAccount"/>" min="0" step="0.01">
        <label style="color:#721c24">${errorWrongAccount}</label><br>
        <label><fmt:message key="label.changePointsLoyalty"/></label>
        <input id="pointsLoyalty" name="pointsLoyalty" type="number" value="<c:out value="${editUser.pointsLoyalty}"/>" placeholder="<fmt:message key="label.enterPointsLoyalty"/>" min="0">
        <label style="color:#721c24">${errorWrongPointsLoyalty}</label><br>
        <c:choose>
            <c:when test="${editUser.blocked == false}">
                <input type="radio" name="statusUser" value="false" checked> <fmt:message key="label.notBlocked"/>
                <input type="radio" name="statusUser" value="true"> <fmt:message key="label.blocked"/>
                <br>
            </c:when>
            <c:otherwise>
                <input type="radio" name="statusUser" value="false"> <fmt:message key="label.notBlocked"/>
                <input type="radio" name="statusUser" value="true" checked> <fmt:message key="label.blocked"/>
                <br>
            </c:otherwise>
        </c:choose>
        <input id="submit" type="submit" value="<fmt:message key="label.change"/>"/>
    </fieldset>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/deleteUser"><fmt:message key="label.delete"/> </a>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
</form>
</body>
</html>
