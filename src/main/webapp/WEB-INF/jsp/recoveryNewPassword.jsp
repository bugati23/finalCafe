<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.03.2019
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.recoveryPassword"/></title>
    <link href="${pageContext.servletContext.contextPath}/assets/css/loginpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="${pageContext.servletContext.contextPath}/cafe/cafe/user/recoveryNewPasswordForm" method="post">
    <h1><fmt:message key="label.recoveryPassword"/> </h1>
    <label style="color:#721c24">${errorWrongPassword}</label>
    <fieldset id="inputs">
        <input id="password" name="password" type="password" value="<c:out value="${formUser.password}"/>" placeholder="<fmt:message key="label.enterNewPassword"/>" minlength="8" maxlength="60" required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="<fmt:message key="label.save"/>"/>
    </fieldset>
    <br>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langen"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-great-britain-48.png"></a>
    <a href="${pageContext.servletContext.contextPath}/cafe/cafe/user/langru"><img src="${pageContext.servletContext.contextPath}/assets/images/icons/icons8-russian-federation-48.png"></a>
</form>
</body>
</html>
