<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System | Administration</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet"/>
    <link href="<c:url value="/css/profile-style.css" />" rel="stylesheet"/>
</head>
<body>
    <%@ include file="topMenu.jsp"%>
    <div class="main">
        <div class="tabs">
            <input type="radio" name="inset" value="" id="tab_1" checked>
            <label for="tab_1">Info</label>

            <input type="radio" name="inset" value="" id="tab_2">
            <label for="tab_2">Accounts</label>

            <input type="radio" name="inset" value="" id="tab_3">
            <label for="tab_3">Settings</label>

            <div id="txt_1">

            </div>

            <div id="txt_2">

            </div>

            <div id="txt_3">

            </div>

        </div>
    </div>

</body>
</html>
