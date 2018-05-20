<c:if test="${requestScope.creditAccount == null}">
    <a href="${pageContext.request.contextPath}/createCreditAccount">+ Create new credit account</a>
</c:if>
<c:if test="${requestScope.creditAccount != null}">
    <div class="my-table">
        <table class="table">
            <tr>
                <td>Number:</td>
                <td>
                    <fmt:formatNumber type="number" pattern="####,####,####,####"
                                      value="${requestScope.creditAccount.number}" />
                </td>
            </tr>
            <tr>
                <td>Balance:</td>
                <td>${requestScope.creditAccount.balance}</td>
            </tr>
            <tr>
                <td>Expiration date:</td>
                <td>${requestScope.creditAccount.expirationDate}</td>
            </tr>
            <tr>
                <td>Limit:</td>
                <td>${requestScope.creditAccount.limit}</td>
            </tr>
            <tr>
                <td>Indebtedness:</td>
                <td>${requestScope.creditAccount.indebtedness}</td>
            </tr>
            <tr>
                <td>Accrued interest:</td>
                <td>${requestScope.creditAccount.accruedInterest}</td>
            </tr>
            <tr>
                <td>Rate:</td>
                <td>${requestScope.creditAccount.creditRate} %</td>
            </tr>
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/transferMoney">
                        Transfer money
                    </a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/payBill">
                        Pay a bill
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <a class="history" href="lastTransactions">
        View last transactions
    </a>
    <a class="history" href="lastPayments">
        View last payments
    </a>
</c:if>
