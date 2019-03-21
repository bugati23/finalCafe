<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.03.2019
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="pageContent"/>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/assets/css/error.css" type="text/css" rel="stylesheet"/>
    <title><fmt:message key="label.timeReciept"/></title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
</body>
</html>