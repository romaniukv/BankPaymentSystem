<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">
    <table class="table table-bordered table-striped table-hover">
        <tr>
            <th>Date</th>
            <th>Sender</th>
            <th>Receiver</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="transaction" items="${requestScope.transactions}">
            <tr>
                <td>${transaction.date}</td>
                <td>
                    <c:if test="${transaction.senderAccountNumber.equals(requestScope.creditAccount.number)}">
                        Me
                    </c:if>
                    <c:if test="${!transaction.senderAccountNumber.equals(requestScope.creditAccount.number)}">
                        <fmt:formatNumber type="number" pattern="####,####,####,####"
                                          value="${transaction.senderAccountNumber}" />
                    </c:if>
                </td>
                <td>
                    <c:if test="${transaction.receiverAccountNumber.equals(requestScope.creditAccount.number)}">
                        Me
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
</div>
</body>
</html>
