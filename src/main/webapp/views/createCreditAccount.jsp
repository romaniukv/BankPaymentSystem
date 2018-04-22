<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <link href="<c:url value="/css/form-style.css" />" rel="stylesheet">
</head>
<body>
<%@ include file="topMenu.jsp"%>
<div class="main">

    <div class="form">
        <form action="createCreditAccount" method="post">
            <label for="limit">Select credit limit</label>
            <select id="limit" name="creditLimit">
                <option value="500">500</option>
                <option value="1000">1000</option>
                <option value="5000">5000</option>
                <option value="10000">10000</option>
                <option value="25000">25000</option>
                <option value="50000">50000</option>
            </select>
            <p class="message">Credit rate is : 4.5%</p>
            <button type="submit">Create</button>
        </form>
    </div>
</div>
</body>
</html>
