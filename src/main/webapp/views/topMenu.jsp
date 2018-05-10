<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
    <nav>
        <div class="navbar">
            <a href="<%=request.getContextPath()%>/">Home</a>
            <c:if test="${sessionScope.user == null}">
                <a href="<%=request.getContextPath()%>/login">Sign In</a>
            </c:if>
            <a href="<%=request.getContextPath()%>/profile">My Profile</a>
            <c:if test="${sessionScope.user != null && sessionScope.user.isAdmin()}">
                <a href="<%=request.getContextPath()%>/adminPanel">Admin Panel</a>
            </c:if>
        </div>
    </nav>
