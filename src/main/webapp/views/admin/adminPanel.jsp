<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}"/>
<fmt:setBundle basename="app_localization"/>

<html lang="${sessionScope['language']}">
<head>
    <title>Banking System | Administration</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/tabs.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="../topMenu.jsp"%>
    <div class="main">
        <div class="tabs">
            <input type="radio" name="inset" value="" id="tab_1" checked>
            <label for="tab_1"><fmt:message key="adminPanel.depositCatalog"/></label>

            <input type="radio" name="inset" value="" id="tab_2">
            <label for="tab_2"><fmt:message key="adminPanel.manage"/></label>

            <div id="txt_1">
                <a href="${pageContext.request.contextPath}/addNewDeposit">
                    <fmt:message key="adminPanel.addNewDeposit"/>
                </a>
                <table class="table table-striped table-hover">
                    <tr>
                        <th><fmt:message key="deposit.name"/></th>
                        <th><fmt:message key="deposit.term"/></th>
                        <th><fmt:message key="deposit.rate"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="deposit" items="${requestScope.availableDeposits}">
                        <tr>
                            <td>${deposit.name}</td>
                            <td>${deposit.term}</td>
                            <td>${deposit.rate} %</td>
                            <td>
                                <a href="<%=request.getContextPath()%>/removeDeposit?id=${deposit.id}">
                                    <fmt:message key="adminPanel.remove"/>
                                </a>
                            </td>
                            <td>
                                <a href="<%=request.getContextPath()%>/editDeposit?id=${deposit.id}">
                                    <fmt:message key="adminPanel.edit"/>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>

            <div id="txt_2">
                <c:if test="${requestScope.newCreditAccounts.isEmpty()}">
                    <fmt:message key="adminPanel.nothingToManage"/>
                </c:if>
                <c:if test="${!requestScope.newCreditAccounts.isEmpty()}">
                    <table class="table table-striped table-hover">
                        <tr>
                            <th><fmt:message key="account.number"/></th>
                            <th><fmt:message key="creditAccount.limit"/></th>
                            <th><fmt:message key="creditAccount.rate"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="account" items="${requestScope.newCreditAccounts}">
                            <tr>
                                <td>${account.number}</td>
                                <td>${account.limit}</td>
                                <td>${account.creditRate}</td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/creditAccountManagement?id=${account.id}">
                                        <fmt:message key="creditAccount.manage"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </div>

</body>
</html>
