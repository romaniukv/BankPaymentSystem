<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}"/>
<fmt:setBundle basename="app_localization"/>

<html lang="${sessionScope['language']}">
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
        <form action="replenishDeposit" method="post">
            <h4><fmt:message key="depositReplenishment"/></h4>
            <h5><fmt:message key="from"/>: <fmt:formatNumber type="number" pattern="####,####,####,####"
                                        value="${requestScope.depositAccount.number}" />
                (<fmt:message key="account.balance"/>: <c:out value="${requestScope.depositAccount.balance}"/>)</h5>
            <input type="hidden"  name="receiverAccountNumber" value="${requestScope.depositAccount.number}"/>
            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>

            <label for="sender"><fmt:message key="senderAccountNumber"/>:</label>
            <input id="sender" type="number" name="senderAccountNumber"
                    min="1000" max="9999999999999999" required/>

            <label for="input-amount"><fmt:message key="amount"/>:</label>
            <input id="input-amount" type="number" step="0.01" placeholder="amount" name="amount" required/> ₴
            <br>
            <button type="submit"><fmt:message key="replenish"/></button>
        </form>
    </div>
</div>
</body>
</html>
