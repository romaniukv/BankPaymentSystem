<c:if test="${requestScope.creditAccount == null}">
    <a href="${pageContext.request.contextPath}/createCreditAccount">+ Create new credit account</a>
</c:if>
<c:if test="${requestScope.creditAccount != null}">
    <div class="my-table">
        <table class="table">
            <tr>
                <td><fmt:message key="account.number"/>:</td>
                <td>
                    <fmt:formatNumber type="number" pattern="####,####,####,####"
                                      value="${requestScope.creditAccount.number}" />
                </td>
            </tr>
            <tr>
                <td><fmt:message key="account.balance"/>:</td>
                <td>${requestScope.creditAccount.balance}</td>
            </tr>
            <tr>
                <td><fmt:message key="account.expirationDate"/>:</td>
                <td>${requestScope.creditAccount.expirationDate}</td>
            </tr>
            <tr>
                <td><fmt:message key="creditAccount.limit"/>:</td>
                <td>${requestScope.creditAccount.limit}</td>
            </tr>
            <tr>
                <td><fmt:message key="indebtedness"/>:</td>
                <td>${requestScope.creditAccount.indebtedness}</td>
            </tr>
            <tr>
                <td><fmt:message key="accruedInterest"/>:</td>
                <td>${requestScope.creditAccount.accruedInterest}</td>
            </tr>
            <tr>
                <td><fmt:message key="deposit.rate"/>:</td>
                <td>${requestScope.creditAccount.creditRate} %</td>
            </tr>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/transferMoney">
                        <fmt:message key="transferMoney"/>
                    </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/payBill">
                        <fmt:message key="payBill"/>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <a class="history" href="lastTransactions">
        <fmt:message key="lastTransactions"/>
    </a>
    <a class="history" href="lastPayments">
        <fmt:message key="lastPayments"/>
    </a>
</c:if>
