<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/form-style.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="../topMenu.jsp"%>
    <div class="main">
        <div class="form">
            <form action="creditAccountManagement" method="post">
                <input type="hidden" name="id" value="${param.id}" />
                <table class="table">
                    <tr>
                        <td>Account number</td>
                        <td>${requestScope.creditAccount.number}</td>
                    </tr>
                    <tr>
                        <td>Owner</td>
                        <td>
                            <a href="<%=request.getContextPath()%>/userInfo?userId=${requestScope.accountOwner.id}">
                                ${requestScope.accountOwner.lastName} ${requestScope.accountOwner.firstName}
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Limit</td>
                        <td>${requestScope.creditAccount.limit}</td>
                    </tr>
                    <tr>
                        <td>Credit rate</td>
                        <td>${requestScope.creditAccount.creditRate}</td>
                    </tr>

                    <tr>
                        <td>Expiration date</td>
                        <td>
                            ${requestScope.creditAccount.expirationDate}
                        </td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <select name="accountStatus">
                                <c:forEach var="status" items="${requestScope.statuses}">
                                    <option value="${status}" <c:if test="${status.equals(requestScope.creditAccount.status)}">selected</c:if>>
                                        ${status.value}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
</body>
</html>
