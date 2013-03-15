<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>${param['pageTitle']}</title>
        <link rel="stylesheet" type="text/css" href="/team19/css/dbtestcss.css" />
    </head>
    <body>
    <div id="wrapper" >
       <div id="header">
            <h1>${pageTitle}</h1>

                <c:choose>
                    <c:when test="${user.firstName != null}">
				        <p>
				            Welcome <c:out value="${user.firstName}"/>
				            <a href="/team19/pages/Logout">Logout</a>
			            </p>
		            </c:when>
		            <c:otherwise>
		                <form method="post" action="https://localhost:8443/team19/pages/Index">
                            <fieldset id="personalinformation">
                                <legend>Login</legend>
                                <dl>
                                    <dd><input required="required" type="text" id="email" name="email" placeholder="email" size="12"/></dd>
                                    <dd><input required="required" type="password" id="passwordHash" name="passwordHash"  placeholder="password" size="12"/></dd>
                                    <dt><label for="rememberMe">Remember&nbsp;me</label></dt>
                                    <dd><input type="checkbox" id="rememberMe" name="rememberMe" value="true"/></dd>
                                </dl>
                                <a href="/team19/pages/register.jsp">Register</a>
                                <a href="/team19/pages/passwordResetRequest.jsp">Forgot password?</a>
                                <input type="submit" name="login" value="Login"/>
                            </fieldset>
                        </form>
		            </c:otherwise>
				</c:choose>

        </div><!-- close header -->