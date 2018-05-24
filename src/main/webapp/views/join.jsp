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
<%@ include file="topMenu.jsp"%>
<div class="main">
    <div class="my-form">
        <form action="join" method="post">
            <div class="errorMsg">
                <c:out value="${requestScope.errorMsg}"/>
                <span id="error"></span>
            </div>
            <label for="firstName"><fmt:message key="user.firstName"/> </label>
            <input id="firstName" type="text" placeholder="first name" name="firstName" required/><br>

            <label for="lastName"><fmt:message key="user.lastName"/> </label>
            <input id="lastName" type="text" placeholder="last name" name="lastName" required/><br>

            <label for="email"><fmt:message key="user.email"/> </label>
            <input id="email" type="text" placeholder="e-mail" name="email" required/><br>

            <label for="username"><fmt:message key="user.username"/> </label>
            <input id="username" type="text" placeholder="username" name="username" required/><br>

            <label for="pass"><fmt:message key="password"/> </label>
            <input id="pass" type="password" placeholder="password" name="password" required/><br>

            <label for="confPass"><fmt:message key="confPassword"/> </label>
            <input id="confPass" type="password" placeholder="confirm password" name="password2" required/><br>

            <button type="submit" onclick="return validateJoinFrom(this.form);">
                <fmt:message key="signUp"/>
            </button>
            <p class="message">
                <fmt:message key="alreadyRegistered"/>
                <a href="${pageContext.request.contextPath}/login"><fmt:message key="menu.signIn"/></a>
            </p>
        </form>
    </div>
</div>

</body>
</html>