<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/transaction-form.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">

    <div class="transaction-form">
        <form action="transferMoney" method="post">
            <h4>Money transfer</h4>
            <h5>From: <fmt:formatNumber type="number" pattern="####,####,####,####"
                                  value="${requestScope.creditAccount.number}" />
                (Balance: <c:out value="${requestScope.creditAccount.balance}"/>)</h5>
            <input type="hidden"  name="fromAccount" value="${requestScope.creditAccount.number}"/>
            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>
            <label for="to">To:</label>
            <input id="to" type="number" placeholder="account number" name="toAccount" required/>
            <label for="input-amount">Amount:</label>
            <br>
            <input id="input-amount" type="number" placeholder="amount" name="amount" required/> ₴
            <br>
            <button type="submit">Transfer</button>
        </form>
    </div>
</div>
</body>
</html>
