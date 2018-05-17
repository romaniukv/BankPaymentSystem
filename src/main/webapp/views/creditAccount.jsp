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
    <input type="checkbox" class="read-more-state" id="post-1" />
    <label for="post-1" class="read-more-trigger"></label>
    <div class="read-more-wrap">
        <div class="read-more-target">
            <table class="table table-striped table-hover">
                <tr>
                    <th>Date</th>
                    <th>Destination</th>
                    <th>Amount</th>
                </tr>
                <c:forEach var="transaction" items="${requestScope.transactions}">
                    <tr>
                        <td>${transaction.date}</td>
                        <td>${transaction.toNumber}</td>
                        <td>${transaction.amount} $</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
