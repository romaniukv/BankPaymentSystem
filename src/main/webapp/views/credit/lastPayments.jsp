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
    <c:if test="${requestScope.payments.isEmpty()}">
        <fmt:message key="emptyPayments"/>
    </c:if>
    <c:if test="${!requestScope.payments.isEmpty()}">
        <table class="table table-bordered table-striped table-hover">
            <tr>
                <th><fmt:message key="date"/></th>
                <th><fmt:message key="receiver"/></th>
                <th><fmt:message key="receiverAccountNumber"/></th>
                <th><fmt:message key="amount"/></th>
                <th><fmt:message key="purpose"/></th>
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
