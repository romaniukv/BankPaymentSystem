<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
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
        <label for="tab_1">Info</label>

        <input type="radio" name="inset" value="" id="tab_2">
        <label for="tab_2">Deposit Accounts</label>

        <div id="txt_1">
            <div class="my-table">
                <table class="table">
                    <tr>
                        <td>Имя:</td>
                        <td>${sessionScope.user.firstName}</td>
                    </tr>
                    <tr>
                        <td>Фамилия:</td>
                        <td>${sessionScope.user.lastName}</td>
                    </tr>
                    <tr>
                        <td>e-mail:</td>
                        <td>${sessionScope.user.email}</td>
                    </tr>
                    <tr>
                        <td>username:</td>
                        <td>${sessionScope.user.username}</td>
                    </tr>
                </table>
            </div>
        </div>

        <div id="txt_2">
            <c:if test="${requestScope.depositAccounts != null}">
                <hr>
                <h4>Deposit accounts:</h4>
                <hr>
                <c:forEach var="depositAccount" items="${requestScope.depositAccounts}">
                    <div class="my-table">
                        <table class="table">
                            <tr>
                                <td>Number:</td>
                                <td>
                                    <fmt:formatNumber type="number" pattern="####,####,####,####"
                                                      value="${depositAccount.number}" />
                                </td>
                            </tr>
                            <tr>
                                <td>Balance:</td>
                                <td>${depositAccount.balance}</td>
                            </tr>
                            <tr>
                                <td>Expiration date:</td>
                                <td>${depositAccount.expirationDate}</td>
                            </tr>
                            <tr>
                                <td>Deposit amount:</td>
                                <td>${depositAccount.amount}</td>
                            </tr>
                            <tr>
                                <td>Deposit rate:</td>
                                <td>${depositAccount.rate} %</td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/historyOfReplenishments?id=${depositAccount.id}">
                                        More info
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
