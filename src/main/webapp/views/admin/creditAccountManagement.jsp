<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}"/>
<fmt:setBundle basename="app_localization"/>

<html lang="${sessionScope['language']}">
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/my-form.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="../topMenu.jsp"%>
    <div class="main">
        <div class="my-form">
            <form action="creditAccountManagement" method="post">
                <input type="hidden" name="id" value="${param.id}" />
                <table class="table">
                    <tr>
                        <td><fmt:message key="account.number"/></td>
                        <td>${requestScope.creditAccount.number}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account.owner"/></td>
                        <td>
                            <a href="<%=request.getContextPath()%>/userInfo?id=${requestScope.accountOwner.id}">
                                ${requestScope.accountOwner.lastName} ${requestScope.accountOwner.firstName}
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="creditAccount.limit"/></td>
                        <td>${requestScope.creditAccount.limit} $</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="creditAccount.rate"/></td>
                        <td>${requestScope.creditAccount.creditRate}</td>
                    </tr>

                    <tr>
                        <td><fmt:message key="account.expirationDate"/></td>
                        <td>
                            ${requestScope.creditAccount.expirationDate}
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="creditAccount.status"/></td>
                        <td>
                            <select name="accountStatus">
                                <c:forEach var="status" items="${requestScope.statuses}">
                                    <option value="${status}" ${status.equals(requestScope.creditAccount.status)? 'selected' : ''}>
                                        ${status.value}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button type="submit"><fmt:message key="save"/></button>
            </form>
        </div>
    </div>
</body>
</html>
