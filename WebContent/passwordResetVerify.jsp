<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horizNav.jsp" />
    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        <div id="content">
            <form method="post" action="https://${secureServerName}/team19/pages/passwordReset">
                <fieldset id="passwordReset">
                    <legend>Complete password Reset</legend>
                    <c:choose>
                        <c:when test="${emailError}">
                            <p>"We are unable to find an account matching the email address you entered."</p>
                        </c:when>
                        <c:when test="${passwordError}">
                            <p>"Please ensure passwords match"</p>
                        </c:when>
                    </c:choose>
                    <p>To complete resetting your password enter your email and <span class="bold">new</span> password below</p>
                    <dl>
                        <dd><input required="required" type="email" id="email" name="email" value="${param['email']}" placeholder="email" size="20"/></dd>
                        <dd><input required="required" type="password" id="passwordHash" name="passwordHash" placeholder="new password" size="20"/></dd>
                        <dd><input required="required" type="password" id="passwordHash2" name="passwordHash2" placeholder="re-enter new password" size="20"/></dd>
                        <dt><label for="rememberMe">Remember&nbsp;me</label></dt>
                        <dd><input type="checkbox" id="rememberMe" name="rememberMe" value="true"/></dd>
                    </dl>
                    <input type="hidden" value="${param['authString']}" />
                    <input type="submit" name="loginReset" value="Reset Password"/>
                </fieldset>
            </form>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
