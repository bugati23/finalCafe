<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.02.2019
  Time: 20:23
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
    <title><fmt:message key="label.registration"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/registrationpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.servletContext.contextPath}/cafe/user/signupForm" method="post">
    <h1><fmt:message key="label.registration"/></h1>
    <fieldset id="inputs">
        <input name="login" type="text" value="<c:out value="${formUser.login}"/>" placeholder="<fmt:message key="label.enterLogin"/>" minlength="4" maxlength="40" autofocus required>
        <label style="color:#721c24">${errorWrongLogin}</label>
        <input id="password" name="password" type="password" value="<c:out value="${formUser.password}"/>" placeholder="<fmt:message key="label.enterPassword"/>" minlength="8" maxlength="60" required>
        <label style="color:#721c24">${errorWrongPassword}</label>
        <input id="email" name="email" type="email" value="<c:out value="${formUser.email}"/>" placeholder="<fmt:message key="label.enterEmail"/>" autofocus required>
        <label style="color:#721c24">${errorWrongEmail}</label>
        <input name="firstName" type="text" value="<c:out value="${formUser.firstName}"/>" placeholder="<fmt:message key="label.enterFirstName"/>" minlength="4" maxlength="50" required/>
        <label style="color:#721c24">${errorWrongUserName}</label>
        <input name="lastName" type="text" value="<c:out value="${formUser.lastName}"/>" placeholder="<fmt:message key="label.enterLastName"/>" minlength="4" maxlength="50" required/>
        <label style="color:#721c24">${errorWrongUserName}</label>
        <input id="submit" type="submit" value="<fmt:message key="label.register"/>"/>
        <a href="${pageContext.servletContext.contextPath}/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
        <a href="${pageContext.servletContext.contextPath}/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
    </fieldset>
</form>
</body>
</html>
