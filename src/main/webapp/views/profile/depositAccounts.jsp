<a href="${pageContext.request.contextPath}/home">+ Create new deposit account</a>
<c:if test="${requestScope.depositAccounts != null}">
    <hr>
    <h4><fmt:message key="depositAccounts"/>:</h4>
    <hr>
    <c:forEach var="depositAccount" items="${requestScope.depositAccounts}">
        <div class="my-table">
            <table class="table">
                <tr>
                    <td><fmt:message key="account.number"/>:</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="####,####,####,####"
                                          value="${depositAccount.number}" />
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="account.balance"/>:</td>
                    <td>${depositAccount.balance} $</td>
                </tr>
                <tr>
                    <td><fmt:message key="account.expirationDate"/>:</td>
                    <td>${depositAccount.expirationDate}</td>
                </tr>
                <tr>
                    <td><fmt:message key="deposit.amount"/>:</td>
                    <td>${depositAccount.amount} $</td>
                </tr>
                <tr>
                    <td><fmt:message key="deposit.rate"/>:</td>
                    <td>${depositAccount.rate} %</td>
                </tr>
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/replenishDeposit?id=${depositAccount.id}">
                            <fmt:message key="replenishDeposit"/>
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/historyOfReplenishments?id=${depositAccount.id}">
                            <fmt:message key="lastReplenishments"/>
                        </a>
                    </td>
                </tr>
            </table>
        </div>
        <hr>
    </c:forEach>
</c:if>
