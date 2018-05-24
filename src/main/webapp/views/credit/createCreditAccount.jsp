<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}"/>
<fmt:setBundle basename="app_localization"/>

<html lang="${sessionScope['language']}">
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/credit-from.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">

    <div class="credit-form">
        <form action="createCreditAccount" method="post">
            <h1 id="selectCL"><fmt:message key="selectCreditLimit"/></h1>
            <c:forEach var="limit" items="${sessionScope.creditLimits}">
                <label class="container">${limit.key}
                    <input type="radio" checked="checked" name="creditLimit" value="${limit.key}">
                    <p class="message"><fmt:message key="creditRateIs"/> : ${limit.value} %</p>
                    <span class="checkmark"></span>
                </label>
            </c:forEach>
            <button type="submit"><fmt:message key="create"/></button>
        </form>
    </div>
</div>
</body>
</html>
