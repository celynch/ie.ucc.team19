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
            <form method="post" action="${returnURL}">
                <fieldset id="personalinformation">
                    <legend>Login to verify Account</legend>
                    <dl>
                        <dd><input required="required" type="text" id="email" name="email" placeholder="email" size="12"/></dd>
                        <dd><input required="required" type="password" id="passwordHash" name="passwordHash"  placeholder="password" size="12"/></dd>
                        <dt><label for="rememberMe">Remember&nbsp;me</label></dt>
                        <dd><input type="checkbox" id="rememberMe" name="rememberMe" value="true"/></dd>
                    </dl>
                    <a href="/pages/register.jsp">Register</a>
                    <input type="hidden" name="authString" value="${param['authString']}"/>
                    <input type="submit" name="loginVerify" value="loginVerify"/>
                </fieldset>
            </form>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>