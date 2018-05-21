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
        <form action="editDeposit" method="post">
            <div class="errorMsg">
                <c:out value="${requestScope.errorMsg}"/>
                <span id="error"></span>
            </div>
            <input type="hidden" name="id" value="${requestScope.deposit.id}"/>

            <input type="text" value="${requestScope.deposit.name}"  name="name" required/>

            <input type="number" value="${requestScope.deposit.term}"  name="term" required/>

            <input type="number" value="${requestScope.deposit.rate}" step="0.01" name="rate" required/>

            <button type="submit" onclick="return validate(this.form);">Save</button>
        </form>
    </div>
</div>

</body>
</html>