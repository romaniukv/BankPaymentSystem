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
</head>
<body>
    <%@ include file="topMenu.jsp"%>
    <div class="main">

        <div class="my-form">
            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>
            <form action="login" method="post">
                <input type="hidden" name="redirectId" value="${param.redirectId}" />

                <label for="username"><fmt:message key="user.username"/> </label>
                <input id="username" type="text" name="username" required/>

                <label for="pass"><fmt:message key="password"/> </label>
                <input id="pass" type="password" name="password" required/>

                <button type="submit"><fmt:message key="menu.signIn"/></button>
                <p class="message">
                    <fmt:message key="notRegistered"/>
                    <a href="${pageContext.request.contextPath}/join"><fmt:message key="createAcc"/></a>
                </p>
            </form>
        </div>
    </div>
</body>
</html>
