<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/form-style.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
    <script src="<c:url value="/js/joinValidating.js" />"></script>
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
            <input type="text" placeholder="first name" name="firstName" required/>

            <input type="text" placeholder="last name" name="lastName" required/>

            <input type="text" placeholder="e-mail" name="email" required/>

            <input type="text" placeholder="username" name="username" required/>

            <input type="password" placeholder="password" name="password" required/>

            <input type="password" placeholder="confirm password" name="password2" required/>

            <button type="submit" onclick="return validate(this.form);">Create</button>
            <p class="message">
                Already registered?
                <a href="${pageContext.request.contextPath}/login">Sign In</a>
            </p>
        </form>
    </div>
</div>

</body>
</html>