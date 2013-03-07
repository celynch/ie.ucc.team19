<jsp:directive.page import= "ie.ucc.team19.dao.*" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="user" class="ie.ucc.team19.dao.StudentBean" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>${param['pageTitle']}</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/team19/dbtestcss.css" />
    </head>
    <body>
    <div id="wrapper" >
       <div id="header">
            <h1>${pageTitle}</h1>

                <c:choose>
                    <c:when test="${user.first_name != null}">
				        <p>
				            Welcome <c:out value="${user.first_name}"/>
				            <a href="/team19/pages/Logout">Logout</a>
			            </p>
		            </c:when>
		            <c:otherwise>
		                <form method="post" action="https://localhost:8443/team19/pages/Index">
                            <fieldset id="personalinformation">
                                <legend>Login</legend>
                                <dl>
                                    <dt><label for="email">E-mail: </label></dt>
                                    <dd><input required="required" type="text" id="email" name="email" /></dd>
                                    <dt><label for="password_hash">Password: </label></dt>
                                    <dd><input required="required" type="password" id="password_hash" name="password_hash" /></dd>
                                </dl>
                                <input type="submit" name="Login" value="Login"/>
                            </fieldset>
                        </form>
		            </c:otherwise>
				</c:choose>

        </div>