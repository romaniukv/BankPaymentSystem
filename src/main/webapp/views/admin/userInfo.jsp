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
    <link href="<c:url value="/css/tabs.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
<%@ include file="../topMenu.jsp"%>
<div class="main">
    <div class="tabs">
        <input type="radio" name="inset" value="" id="tab_1" checked>
        <label for="tab_1"><fmt:message key="info"/></label>

        <input type="radio" name="inset" value="" id="tab_2">
        <label for="tab_2"><fmt:message key="depositAccounts"/></label>

        <div id="txt_1">
            <div class="my-table">
                <table class="table">
                    <tr>
                        <td><fmt:message key="user.firstName"/>:</td>
                        <td>${requestScope.userInfo.firstName}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.lastName"/>:</td>
                        <td>${requestScope.userInfo.lastName}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.email"/>:</td>
                        <td>${requestScope.userInfo.email}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="user.username"/>:</td>
                        <td>${requestScope.userInfo.username}</td>
                    </tr>
                </table>
            </div>
        </div>

        <div id="txt_2">
            <c:if test="${requestScope.depositAccounts != null}">
                <hr>
                <h4><fmt:message key="depositAccounts"/></h4>
                <hr>
                <c:forEach var="depositAccount" items="${requestScope.depositAccounts}">
                    <div class="my-table">
                        <table class="table">
                            <tr>
                                <td><fmt:message key="account.number"/></td>
                                <td>
                                    <fmt:formatNumber type="number" pattern="####,####,####,####"
                                                      value="${depositAccount.number}" />
                                </td>
                            </tr>
                            <tr>
                                <td><fmt:message key="account.balance"/></td>
                                <td>${depositAccount.balance}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="account.expirationDate"/></td>
                                <td>${depositAccount.expirationDate}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="deposit.amount"/></td>
                                <td>${depositAccount.amount}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="deposit.rate"/></td>
                                <td>${depositAccount.rate} %</td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/historyOfReplenishments?id=${depositAccount.id}">
                                        <fmt:message key="deposit.history"/>
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <hr>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
