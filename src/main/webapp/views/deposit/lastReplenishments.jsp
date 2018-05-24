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
    <c:if test="${requestScope.replenishments.isEmpty()}">
        <fmt:message key="emptyReplenishments"/>
    </c:if>
    <c:if test="${!requestScope.replenishments.isEmpty()}">
        <table class="table table-bordered table-striped table-hover">
            <tr>
                <th><fmt:message key="date"/></th>
                <th><fmt:message key="sender"/></th>
                <th><fmt:message key="receiver"/></th>
                <th><fmt:message key="amount"/></th>
            </tr>
            <c:forEach var="replenishment" items="${requestScope.replenishments}">
                <tr>
                    <td>${replenishment.date}</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="####,####,####,####"
                                          value="${replenishment.senderAccountNumber}" />
                    </td>
                    <td>
                        <fmt:formatNumber type="number" pattern="####,####,####,####"
                                          value="${replenishment.receiverAccountNumber}" />
                    </td>
                    <td>${replenishment.amount} $</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
