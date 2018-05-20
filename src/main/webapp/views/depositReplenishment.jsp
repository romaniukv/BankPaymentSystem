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
<%@ include file="topMenu.jsp"%>
<div class="main">

    <div class="transaction-form">
        <form action="replenishDeposit" method="post">
            <h4>Deposit Replenishment</h4>
            <h5>From: <fmt:formatNumber type="number" pattern="####,####,####,####"
                                        value="${requestScope.depositAccount.number}" />
                (Amount: <c:out value="${requestScope.depositAccount.amount}"/>)</h5>
            <input type="hidden"  name="receiverAccountNumber" value="${requestScope.depositAccount.number}"/>
            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>
            <label for="input-amount">Amount:</label>
            <br>
            <label for="sender">Sender account number:</label>
            <input id="sender" type="number" placeholder="account number" name="senderAccountNumber" required/>
            <input id="input-amount" type="number" placeholder="amount" name="amount" required/> ₴
            <br>
            <button type="submit">Replenish</button>
        </form>
    </div>
</div>
</body>
</html>
