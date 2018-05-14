<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/table-style.css" />" rel="stylesheet"/>
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
                <table class="my-table">
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

            <div id="txt_2">
                <a href="${pageContext.request.contextPath}/createDepositAccount">+ Create new deposit account</a>
            </div>

            <div id="txt_3">
                <a href="${pageContext.request.contextPath}/createCreditAccount">+ Create new credit account</a>
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
