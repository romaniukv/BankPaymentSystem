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
        <form action="payBill" method="post">
            <h4><fmt:message key="payBill"/></h4>

            <h5><fmt:message key="sender"/> <fmt:formatNumber type="number" pattern="####,####,####,####"
                                  value="${requestScope.creditAccount.number}" />
                (<fmt:message key="account.balance"/>: <c:out value="${requestScope.creditAccount.balance}"/>)</h5>

            <input type="hidden"  name="senderAccount" value="${requestScope.creditAccount.number}"/>

            <div class="errorMsg"><c:out value="${requestScope.errorMsg}"/></div>

            <label for="receiver"><fmt:message key="receiver"/>:</label>
            <input id="receiver" type="text" name="receiverName" required/>

            <label for="acc_number"><fmt:message key="receiverAccountNumber"/>:</label>
            <input id="acc_number" type="number" name="receiverAccount"
                   min="1000" max="9999999999999999" required/>

            <label for="input-amount"><fmt:message key="amount"/></label>
            <input id="input-amount" type="number" step="0.01" name="amount" required/> $ <br>

            <label for="purpose"><fmt:message key="purpose"/>:</label>
            <input id="purpose" type="text" name="purpose" required/>

            <button type="submit"><fmt:message key="pay"/></button>
        </form>
    </div>
</div>
</body>
</html>
