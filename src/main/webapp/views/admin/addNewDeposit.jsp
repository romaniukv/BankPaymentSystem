<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/login-form.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
    <script src="<c:url value="/js/joinValidating.js" />"></script>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">
    <div class="my-form">
        <form action="addNewDeposit" method="post">
            <div class="errorMsg">
                <c:out value="${requestScope.errorMsg}"/>
                <span id="error"></span>
            </div>
            <input type="text" placeholder="name" name="name" required/>

            <input type="number" placeholder="term(months)" name="term" required/>

            <input type="number" placeholder="rate" name="rate" required/>

            <button type="submit" onclick="return validate(this.form);">Add</button>
        </form>
    </div>
</div>

</body>
</html>