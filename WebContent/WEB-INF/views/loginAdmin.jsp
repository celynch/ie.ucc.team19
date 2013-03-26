<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${user != null}">
        <form method="post" action="/team19/pages/Logout">
            <fieldset>
                <legend><a href="/team19/pages/account">Welcome <c:out value="${user.adminName}"/></a></legend>
                <input type="submit" name="logout" value="Logout"/>
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" action="https://${secureServerName}/team19/pages/adminDashBoard">
            <fieldset class="login">
                <legend>Login</legend>
                <dl>
                    <dd><input required="required" type="text" name="adminName" placeholder="Admin Name" size="12"/></dd>
                    <dd><input required="required" type="password" name="adminPassword"  placeholder="Admin Password" size="12"/></dd>
                </dl>
                <input type="submit" name="adminLogin" value="Admin Login"/>
            </fieldset>
        </form>
    </c:otherwise>
</c:choose>