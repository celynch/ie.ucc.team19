<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${user.firstName != null}">
        <form method="post" action="/team19/pages/Logout">
            <fieldset>
                <legend><a href="/team19/pages/account">Welcome <c:out value="${user.firstName}"/></a></legend>
                <input type="submit" name="logout" value="Logout"/>
            </fieldset>
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" action="https://${secureServerName}/team19/pages/Index">
            <fieldset class="login">
                <legend>Login</legend>
                <dl>
                    <dd><input required="required" type="text" name="email" placeholder="email" size="12"/></dd>
                    <dd><input required="required" type="password" name="passwordHash"  placeholder="password" size="12"/></dd>
                    <dt><label for="rememberMe">Remember&nbsp;me</label></dt>
                    <dd><input type="checkbox" id="rememberMe" name="rememberMe" value="true"/></dd>
                </dl>
                <a href="/team19/pages/register.jsp">Register</a>
                <a href="/team19/pages/passwordResetRequest.jsp">Forgot password?</a>
                <input type="submit" name="studentLogin" value="Login"/>
            </fieldset>
        </form>
    </c:otherwise>
</c:choose>