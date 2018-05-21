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
        <form action="payBill" method="post">
            <h4>Pay bill</h4>

            <h5>From: <fmt:formatNumber type="number" pattern="####,####,####,####"
                                  value="${requestScope.creditAccount.number}" />
                (Balance: <c:out value="${requestScope.creditAccount.balance}"/>)</h5>

            <input type="hidden"  name="senderAccount" value="${requestScope.creditAccount.number}"/>

            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>

            <label for="receiver">Receiver:</label>
            <input id="receiver" type="text" placeholder="full name" name="receiverName" required/>

            <label for="acc_number">Receiver's account number:</label>
            <input id="acc_number" type="number" placeholder="account number" name="receiverAccount"
                   min="1000" max="9999999999999999" required/>

            <label for="amount">Amount:</label>
            <input id="amount" type="number" step="0.01" placeholder="amount" name="amount" required/>

            <label for="purpose">Purpose of payment:</label>
            <input id="purpose" type="text" placeholder="purpose of payment..." name="purpose" required/>

            <button type="submit">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
