<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
    <nav>
        <div class="navbar">
            <a href="<%=request.getContextPath()%>/">Home</a>

            <c:if test="${sessionScope.user == null}">
                <a href="<%=request.getContextPath()%>/login">Sign In</a>
                <a href="<%=request.getContextPath()%>/join">Sign Up</a>
            </c:if>

                <a href="<%=request.getContextPath()%>/profile">My Profile</a>
        </div>
    </nav>
