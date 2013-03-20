<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />

<jsp:include page="WEB-INF/views/header.jsp">
    <jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>

    <div id="main">
        <p>You have now logged out. <a href="team19/pages/">Return to front page</a></p>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>