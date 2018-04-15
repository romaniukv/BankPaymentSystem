<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Banking System</title>
    <link href="<c:url value="/css/topMenu.css" />" rel="stylesheet">
    <script src="<c:url value="/js/joinValidating.js" />"></script>
</head>
<body>
<%@ include file="topMenu.jsp"%>
<div class="main">

    <form action="join" method="post">
        <div class="box">
            <table>
                <tr>
                    <td>First name</td>
                    <td>
                        <input id="firstName"  name="firstName" type="text">
                    </td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td>
                        <input name="lastName" type="text">
                    </td>
                </tr>
                <tr>
                    <td>e-mail</td>
                    <td>
                        <input name="email" type="text">
                    </td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td>
                        <input name="username" type="text">
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <input name="password" type="password">
                    </td>
                </tr>
                <tr>
                    <td>Confirm password</td>
                    <td>
                        <input name="password2" type="password">
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <input type="button" onclick="validate(this.form)" value="Проверить">
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>

</body>
</html>