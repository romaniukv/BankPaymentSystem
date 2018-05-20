<a href="${pageContext.request.contextPath}/home">+ Create new deposit account</a>
<c:if test="${requestScope.depositAccounts != null}">
    <hr>
    <h4>My deposit accounts:</h4>
    <hr>
    <c:forEach var="depositAccount" items="${requestScope.depositAccounts}">
        <div class="my-table">
            <table class="table">
                <tr>
                    <td>Number:</td>
                    <td>
                        <fmt:formatNumber type="number" pattern="####,####,####,####"
                                          value="${depositAccount.number}" />
                    </td>
                </tr>
                <tr>
                    <td>Balance:</td>
                    <td>${depositAccount.balance}</td>
                </tr>
                <tr>
                    <td>Expiration date:</td>
                    <td>${depositAccount.expirationDate}</td>
                </tr>
                <tr>
                    <td>Deposit amount:</td>
                    <td>${depositAccount.amount}</td>
                </tr>
                <tr>
                    <td>Deposit rate:</td>
                    <td>${depositAccount.rate} %</td>
                </tr>
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/replenishDeposit?id=${depositAccount.id}">
                            Replenish deposit
                        </a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/historyOfReplenishments?id=${depositAccount.id}">
                            More info
                        </a>
                    </td>
                </tr>
            </table>
        </div>
        <hr>
    </c:forEach>
</c:if>
