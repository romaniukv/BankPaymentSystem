<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
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
                <th>Name</th>
                <th>Term</th>
                <th>Rate</th>
                <th></th>
            </tr>
            <c:forEach var="account" items="${requestScope.depositAccounts}">
                <tr>
                    <td>${account.name}</td>
                    <td>${account.term}</td>
                    <td>${account.rate}</td>
                    <td><a id="link" href="<%=request.getContextPath()%>/createDepositAccount?id=${account.id}">Open</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
