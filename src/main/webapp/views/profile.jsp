<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/profile-style.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="topMenu.jsp"%>
    <div class="main">
        <div class="tabs">
            <input type="radio" name="inset" value="" id="tab_1" checked>
            <label for="tab_1">Info</label>

            <input type="radio" name="inset" value="" id="tab_2">
            <label for="tab_2">Deposit Accounts</label>

            <input type="radio" name="inset" value="" id="tab_3">
            <label for="tab_3">Credit Account</label>

            <input type="radio" name="inset" value="" id="tab_4">
            <label for="tab_4">Settings</label>

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
                <a href="${pageContext.request.contextPath}/home">+ Create new deposit account</a>
                <c:if test="${requestScope.depositAccounts != null}">
                    <hr>
                    <h4>My deposit accounts:</h4>
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
                                    <td></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/depositAccountInfo?id=${depositAccount.id}">
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

            <div id="txt_3">
                <c:if test="${requestScope.creditAccount == null}">
                    <a href="${pageContext.request.contextPath}/createCreditAccount">+ Create new credit account</a>
                </c:if>
                <c:if test="${requestScope.creditAccount != null}">
                <div class="my-table">
                    <table class="table">
                        <tr>
                            <td>Number:</td>
                            <td>
                                <fmt:formatNumber type="number" pattern="####,####,####,####"
                                                  value="${requestScope.creditAccount.number}" />
                            </td>
                        </tr>
                        <tr>
                            <td>Balance:</td>
                            <td>${requestScope.creditAccount.balance}</td>
                        </tr>
                        <tr>
                            <td>Expiration date:</td>
                            <td>${requestScope.creditAccount.expirationDate}</td>
                        </tr>
                        <tr>
                            <td>Limit:</td>
                            <td>${requestScope.creditAccount.limit}</td>
                        </tr>
                        <tr>
                            <td>Indebtedness:</td>
                            <td>${requestScope.creditAccount.indebtedness}</td>
                        </tr>
                        <tr>
                            <td>Accrued interest:</td>
                            <td>${requestScope.creditAccount.accruedInterest}</td>
                        </tr>
                        <tr>
                            <td>Rate:</td>
                            <td>${requestScope.creditAccount.creditRate} %</td>
                        </tr>
                    </table>
                </div>
                </c:if>
            </div>

            <div id="txt_4">
                <form action="logOut" method="post">
                    <button type="submit">Log out</button>
                </form>
            </div>

        </div>
    </div>
</body>
</html>
