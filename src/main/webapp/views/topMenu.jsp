<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<nav>
    <div class="topMenuNav">
        <a href="<%=request.getContextPath()%>/home"><fmt:message key="menu.home"/></a>
        <c:if test="${sessionScope.user == null}">
            <a href="<%=request.getContextPath()%>/login"><fmt:message key="menu.signIn"/></a>
        </c:if>
        <a href="<%=request.getContextPath()%>/profile"><fmt:message key="menu.profile"/></a>
        <c:if test="${sessionScope.user != null && sessionScope.user.isAdmin()}">
            <a href="<%=request.getContextPath()%>/adminPanel"><fmt:message key="menu.adminPanel"/></a>
        </c:if>

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
