<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />

<jsp:include page="WEB-INF/views/header.jsp">
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
</jsp:include>

    <div id="main">
        <p>
            Thank you for registering, to activate your account please check you email and click the link therein.
            <a href="/team19/pages/Index">Return to front page</a>
        </p>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>