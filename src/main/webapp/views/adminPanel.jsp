<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System | Administration</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/profile-style.css" />" rel="stylesheet"/>
    <link href="<c:url value="/bootstrap/css/bootstrap.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="topMenu.jsp"%>
    <div class="main">
        <div class="tabs">
            <input type="radio" name="inset" value="" id="tab_1" checked>
            <label for="tab_1">New credit accounts</label>

            <input type="radio" name="inset" value="" id="tab_2">
            <label for="tab_2">New deposit accounts</label>

            <div id="txt_1">
                <table class="table table-striped table-hover">
                    <tr>
                        <th>Number</th>
                        <th>Limit</th>
                        <th>Credit rate</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="account" items="${requestScope.newCreditAccounts}">
                        <tr>
                            <td>${account.number}</td>
                            <td>${account.limit}</td>
                            <td>${account.creditRate}</td>
                            <td><a href="<%=request.getContextPath()%>/creditAccountManagement?id=${account.id}">Manage</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div id="txt_2">
            </div>
        </div>
    </div>

</body>
</html>
