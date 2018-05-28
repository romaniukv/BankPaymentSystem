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

            <input type="radio" name="inset" value="" id="tab_3">
            <label for="tab_3"><fmt:message key="creditAccount"/></label>

            <input type="radio" name="inset" value="" id="tab_4">
            <label for="tab_4"><fmt:message key="settings"/></label>

            <div id="txt_1">
                <div class="my-table">
                    <table class="table">
                        <tr>
                            <td><fmt:message key="user.firstName"/></td>
                            <td>${sessionScope.user.firstName}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="user.lastName"/></td>
                            <td>${sessionScope.user.lastName}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="user.email"/></td>
                            <td>${sessionScope.user.email}</td>
                        </tr>
                        <tr>
                            <td><fmt:message key="user.username"/></td>
                            <td>${sessionScope.user.username}</td>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="txt_2">
                <%@ include file="depositAccounts.jsp"%>
            </div>

            <div id="txt_3">
                <%@ include file="creditAccount.jsp"%>
            </div>

            <div id="txt_4">
                <form action="logOut" method="post">
                    <button type="submit"><fmt:message key="logOut"/></button>
                </form>
            </div>

        </div>
    </div>
</body>
</html>
