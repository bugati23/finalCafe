<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.03.2019
  Time: 16:00
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
    <title><fmt:message key="label.editProfile"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/registrationpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.servletContext.contextPath}/cafe/cafe/user/editprofileForm" method="post">
    <h1><fmt:message key="label.editProfile"/></h1>
    <fieldset id="inputs">
        <input name="login" type="text" value="<c:out value="${user.login}"/>" placeholder="<fmt:message key="label.enterLogin"/>" minlength="4" maxlength="40">
        <label style="color:#721c24">${errorWrongLogin}</label>
        <input id="password" name="password" type="password" value="<c:out value="${formUser.password}"/>" placeholder="<fmt:message key="label.enterPassword"/>" minlength="8" maxlength="60" >
        <label style="color:#721c24">${errorWrongPassword}</label>
        <input name="firstName" type="text" value="<c:out value="${user.firstName}"/>" placeholder="<fmt:message key="label.enterFirstName"/>" minlength="4" maxlength="50" />
        <label style="color:#721c24">${errorWrongUserName}</label>
        <input name="lastName" type="text" value="<c:out value="${user.lastName}"/>" placeholder="<fmt:message key="label.enterLastName"/>" minlength="4" maxlength="50" />
        <label style="color:#721c24">${errorWrongUserName}</label>
        <input id="submit" type="submit" value="<fmt:message key="label.change"/>"/>
    </fieldset>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
</form>
</body>
</html>