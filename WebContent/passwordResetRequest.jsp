<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horizNav.jsp" />
    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        <div id="content">
            <form method="post" action="http://localhost:8080/team19/pages/passwordResetDispatch">
                <fieldset id="passwordReset">
                    <legend>Reset password?</legend>
                    <c:choose>
                        <c:when test="${emailError}">
                            <p>"We are unable to find an account matching the email address you entered."</p>
                        </c:when>
                    </c:choose>
                    <p>If you wish to reset your password, enter your email below and respond to the verification email sent.</p>
                    <dl>
                        <dd><input required="required" type="email" id="email" name="email" placeholder="email" size="20"/></dd>
                    </dl>
                    <input type="submit" name="loginVerify" value="Reset Password"/>
                </fieldset>
            </form>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>