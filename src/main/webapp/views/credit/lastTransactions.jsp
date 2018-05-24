<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}"/>
<fmt:setBundle basename="app_localization"/>

<html lang="${sessionScope['language']}">
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">
    <c:if test="${requestScope.transactions.isEmpty()}">
        No transactions
    </c:if>
    <c:if test="${!requestScope.transactions.isEmpty()}">
        <table class="table table-bordered table-striped table-hover">
            <tr>
                <th><fmt:message key="date"/></th>
                <th><fmt:message key="senderAccountNumber"/></th>
                <th><fmt:message key="receiverAccountNumber"/></th>
                <th><fmt:message key="amount"/></th>
            </tr>
            <c:forEach var="transaction" items="${requestScope.transactions}">
                <tr>
                    <td>${transaction.date}</td>
                    <td>
                        <c:if test="${transaction.senderAccountNumber.equals(requestScope.creditAccount.number)}">
                            <fmt:message key="me"/>
                        </c:if>
                        <c:if test="${!transaction.senderAccountNumber.equals(requestScope.creditAccount.number)}">
                            <fmt:formatNumber type="number" pattern="####,####,####,####"
                                              value="${transaction.senderAccountNumber}" />
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${transaction.receiverAccountNumber.equals(requestScope.creditAccount.number)}">
                            <fmt:message key="me"/>
                        </c:if>
                        <c:if test="${!transaction.receiverAccountNumber.equals(requestScope.creditAccount.number)}">
                            <fmt:formatNumber type="number" pattern="####,####,####,####"
                                              value="${transaction.receiverAccountNumber}" />
                        </c:if>
                    </td>
                    <td>${transaction.amount} $</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
