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
    <script src="<c:url value="/js/validation.js" />"></script>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">

    <div class="transaction-form">
        <form action="transferMoney" method="post">
            <span class="errorMsg" id="error"></span>
            <h4><fmt:message key="transferMoney"/></h4>
            <h5><fmt:message key="from"/>: <fmt:formatNumber type="number" pattern="####,####,####,####"
                                  value="${requestScope.creditAccount.number}" />
                (<fmt:message key="account.balance"/>: <c:out value="${requestScope.creditAccount.balance}"/>)</h5>
            <input type="hidden"  name="fromAccount" value="${requestScope.creditAccount.number}"/>

            <label for="to"><fmt:message key="receiverAccountNumber"/>:</label>
            <input id="to" type="number" name="toAccount"
                   min="1000" max="9999999999999999" required/>

            <label for="input-amount"><fmt:message key="amount"/>:</label><br>
            <input id="input-amount" type="number" step="0.01" name="amount" required/> $ <br>

            <button type="submit" onclick="return validateTransferMoneyFrom(this.form, ${requestScope.creditAccount.balance});">
                <fmt:message key="submit"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
