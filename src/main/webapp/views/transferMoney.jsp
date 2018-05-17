<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/transfer-money-form.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="topMenu.jsp"%>
<div class="main">

    <div class="transfer-money-form">
        <form action="transferMoney" method="post">
            <h4>Money transfer</h4>
            <h5>From: <fmt:formatNumber type="number" pattern="####,####,####,####"
                                  value="${requestScope.creditAccount.number}" />
                (Balance: <c:out value="${requestScope.creditAccount.balance}"/>)</h5>
            <input type="hidden"  name="fromAccount" value="${requestScope.creditAccount.number}"/>
            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>
            <label for="to">To:</label>
            <input id="to" type="text" placeholder="account number" name="toAccount" required/>
            <label for="amount">Amount:</label>
            <input id="amount" type="text" placeholder="amount" name="amount" required/>
            <button type="submit">Transfer</button>
        </form>
    </div>
</div>
</body>
</html>
