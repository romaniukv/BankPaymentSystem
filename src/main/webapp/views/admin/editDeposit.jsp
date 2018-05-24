<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}"/>
<fmt:setBundle basename="app_localization"/>

<html lang="${sessionScope['language']}">
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/my-form.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
    <script src="<c:url value="/js/validation.js" />"></script>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">
    <div class="my-form">
        <form action="editDeposit" method="post">
            <div class="errorMsg">
                <c:out value="${requestScope.errorMsg}"/>
                <span id="error"></span>
            </div>
            <input type="hidden" name="id" value="${requestScope.deposit.id}"/>

            <label for="name"><fmt:message key="deposit.name"/></label>
            <input id="name" type="text" value="${requestScope.deposit.name}"  name="name" required/>

            <label for="term"><fmt:message key="deposit.term"/></label>
            <input id="term" type="number" value="${requestScope.deposit.term}"  name="term" required/>

            <label for="rate"><fmt:message key="deposit.rate"/></label>
            <input id="rate" type="number" value="${requestScope.deposit.rate}" step="0.01" name="rate" required/>

            <button type="submit">
                <fmt:message key="save"/>
            </button>
        </form>
    </div>
</div>

</body>
</html>