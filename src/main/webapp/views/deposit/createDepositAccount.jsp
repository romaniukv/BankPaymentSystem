<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/login-form.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">

    <div class="my-form">
        <form action="createDepositAccount" method="post">
            <input type="hidden" name="id" value="${param.id}" />
            <table class="table">
                <tr>
                    <td>Deposit: </td>
                    <td><c:out value="${requestScope.account.name}"/></td>
                </tr>
                <tr>
                    <td>Term: </td>
                    <td><c:out value="${requestScope.account.term}"/> months</td>
                </tr>
                <tr>
                    <td>Rate: </td>
                    <td><c:out value="${requestScope.account.rate}"/> %</td>
                </tr>
                <tr>
                    <td>Input amount:</td>
                    <td><input id="input-amount" type="number" step="0.01" placeholder="amount" name="amount" required/> ₴</td>
                </tr>
            </table>
            <button type="submit">Create</button>
        </form>
    </div>
</div>
</body>
</html>
