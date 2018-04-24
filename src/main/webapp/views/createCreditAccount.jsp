<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/form-style.css" />" rel="stylesheet">
    <link href="<c:url value="/css/radio-buttons-style.css" />" rel="stylesheet">
</head>
<body>
<%@ include file="topMenu.jsp"%>
<div class="main">

    <div class="form">
        <form action="createCreditAccount" method="post">
            <h1>Select credit limit</h1>
            <c:forEach var="limit" items="${sessionScope.creditLimits}">
                <label class="container">${limit.key}
                    <input type="radio" checked="checked" name="creditLimit" value="${limit.key}">
                    <p class="message">Credit rate is : ${limit.value} %</p>
                    <span class="checkmark"></span>
                </label>
            </c:forEach>
            <button type="submit">Create</button>
        </form>
    </div>
</div>
</body>
</html>