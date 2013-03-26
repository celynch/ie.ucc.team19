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
                    <c:when test="${admin}">
                        <jsp:include page="loginAdmin.jsp" />
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="loginStudent.jsp" />
                    </c:otherwise>
				</c:choose>
			</div>
            <div class="clear"></div>
        </div><!-- close header -->
        