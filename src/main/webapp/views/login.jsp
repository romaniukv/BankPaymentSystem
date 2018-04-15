<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
</head>
<body>
    <%@ include file="topMenu.jsp"%>
    <div class="main">
        <form action="login" method="post">
            Email:<input type="text" name="username"/><br/><br/>
            Password:<input type="password" name="password"/><br/><br/>
            <input type="submit" value="Sign in"/>
        </form>
    </div>
</body>
</html>
