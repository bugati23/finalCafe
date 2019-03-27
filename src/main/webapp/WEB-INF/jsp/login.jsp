<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.02.2019
  Time: 18:39
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
    <title><fmt:message key="label.login"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/loginpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.request.contextPath}/cafe/user/signinForm" method="post">
    <h1><fmt:message key="label.welcome"/> </h1>
    <label style="color:#721c24">${errorWrongLoginOrPassword}</label>
    <fieldset id="inputs">
        <input id="log" name="login" type="text" value="<c:out value="${formUser.login}"/>" placeholder="<fmt:message key="label.enterLogin"/>" minlength="4" maxlength="40"  autofocus required>
        <input id="password" name="password" type="password" value="<c:out value="${formUser.password}"/>" placeholder="<fmt:message key="label.enterPassword"/>" minlength="8" maxlength="60" required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="<fmt:message key="label.signIn"/>"/>
        <input type="hidden" name="formId" value="${formId}">
        <a href="${pageContext.servletContext.contextPath}/cafe/user/forgotPassword"><fmt:message key="label.forgotPassword"/> </a>
        <a href="${pageContext.servletContext.contextPath}/cafe/user/signup"><fmt:message key="label.signUp"/></a>
        <a href="${pageContext.servletContext.contextPath}/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
        <a href="${pageContext.servletContext.contextPath}/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
    </fieldset>
    <br>
</form>
</body>
</html>
