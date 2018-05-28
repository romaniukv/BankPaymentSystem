<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<nav>
    <div class="topMenuNav">

        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <a href="<%=request.getContextPath()%>/home"><fmt:message key="menu.home"/></a>
                <a href="<%=request.getContextPath()%>/login"><fmt:message key="menu.signIn"/></a>
                <a href="<%=request.getContextPath()%>/profile"><fmt:message key="menu.profile"/></a>
            </c:when>
            <c:when test="${sessionScope.user.isAdmin()}">
                <a href="<%=request.getContextPath()%>/home"><fmt:message key="menu.home"/></a>
                <a href="<%=request.getContextPath()%>/profile"><fmt:message key="menu.profile"/></a>
                <a href="<%=request.getContextPath()%>/adminPanel"><fmt:message key="menu.adminPanel"/></a>
            </c:when>
            <c:otherwise>
                <a href="<%=request.getContextPath()%>/home"><fmt:message key="menu.home"/></a>
                <a href="<%=request.getContextPath()%>/profile"><fmt:message key="menu.profile"/></a>
            </c:otherwise>
        </c:choose>

        <form action="changeLanguage" method="post">
            <select class="languagepicker roundborders" name="language" onchange="submit()">
                <option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>
                    <fmt:message key="menu.language.en"/>
                </option>
                <option value="uk" ${sessionScope.language == 'uk' ? 'selected' : ''}>
                    <fmt:message key="menu.language.uk"/>
                </option>
            </select>
        </form>
    </div>
</nav>
