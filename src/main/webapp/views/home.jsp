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
    <%@ include file="topMenu.jsp"%>
    <div class="main">
        <table class="table table-striped table-hover">
            <tr>
                <th><fmt:message key="deposit.name"/></th>
                <th><fmt:message key="deposit.term"/></th>
                <th><fmt:message key="deposit.rate"/></th>
                <th></th>
            </tr>
            <c:forEach var="account" items="${requestScope.depositAccounts}">
                <tr>
                    <td>${account.name}</td>
                    <td>${account.term} months</td>
                    <td>${account.rate} %</td>
                    <td>
                        <a id="link" href="<%=request.getContextPath()%>/createDepositAccount?id=${account.id}">
                            <fmt:message key="open"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
