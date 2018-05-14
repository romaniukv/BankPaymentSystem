<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/form-style.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="topMenu.jsp"%>
    <div class="main">

        <div class="form">
            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>
            <form action="login" method="post">
                <input type="hidden" name="redirectId" value="${param.redirectId}" />
                <input type="text" placeholder="username" name="username" required/>
                <input type="password" placeholder="password" name="password" required/>
                <button type="submit">Login</button>
                <p class="message">
                    Not registered?
                    <a href="${pageContext.request.contextPath}/join">Create an account</a>
                </p>
            </form>
        </div>
    </div>
</body>
</html>
