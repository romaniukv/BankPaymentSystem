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
    <c:if test="${requestScope.payments.isEmpty()}">
        No payments
    </c:if>
    <c:if test="${!requestScope.payments.isEmpty()}">
        <table class="table table-bordered table-striped table-hover">
            <tr>
                <th>Date</th>
                <th>Receiver</th>
                <th>Receiver account number</th>
                <th>Amount</th>
                <th>Purpose of payment</th>
            </tr>
            <c:forEach var="payment" items="${requestScope.payments}">
                <tr>
                    <td>${payment.date}</td>
                    <td>${payment.receiverFullName}</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="####,####,####,####"
                                          value="${payment.receiverAccountNumber}" />
                    </td>
                    <td>${payment.amount} $</td>
                    <td>${payment.purpose}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
