<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horiz_nav.jsp" />
    <jsp:include page="WEB-INF/views/verti_nav.jsp" />
        <div id="content">
            <form method="post" action="http://localhost:8080/team19/pages/resetPassword">
                <fieldset id="personalinformation">
                    <legend>Reset password?</legend>
                    <p>If you wish to reset your password, enter your email below and respond to the verification email sent.</p>
                    <dl>
                        <dd><input required="required" type="text" id="email" name="email" placeholder="email" size="12"/></dd>
                    </dl>
                    <input type="submit" name="loginVerify" value="Reset Password"/>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="clear"></div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>