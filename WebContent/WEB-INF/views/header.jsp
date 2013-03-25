<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <base href="http://${serverName}" />
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>${pageTitle} | UCC&nbsp;Summer&nbsp;Courses</title>
        <link rel="stylesheet" type="text/css" href="/team19/css/reset.css" media="all" />
        <link rel="stylesheet" type="text/css" href="/team19/css/style.css" media="all" />
        <link rel="stylesheet" type="text/css" href="/team19/css/structural.css" media="all" />
        <link rel="stylesheet" type="text/css" href="/team19/css/fontsizes.css" media="all" />
        <link rel="stylesheet" type="text/css" href="/team19/css/print.css" media="print" />
        <link rel="stylesheet" type="text/css" href="/team19/css/mobile.css" media="mobile" />
        <!-- <link rel="shortcut icon" href="/favicon.ico" type="image/vnd.microsoft.icon" />  -->
        <c:if test="${includeEditor!=null}">
            <jsp:include page="loadTinyMce.jsp" />
        </c:if>
    </head>
    <body>
    <div id="wrapper" >
       <div id="header">
            <div class="header_logo">
                <h1>${pageTitle} | UCC&nbsp;Summer&nbsp;Courses</h1>
            </div>
            <div id="loginLogout">
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
			</div>
            <div class="clear"></div>
        </div><!-- close header -->