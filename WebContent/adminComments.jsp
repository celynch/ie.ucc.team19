<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horizNav.jsp" />
    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        
        <div id="content">
            <h2>Reviewed Comments</h2>
                <c:choose>
                    <c:when test="${param['respond'] != null }">
                        <p>Response to ${param['email']} sent.</p>
                    </c:when>
                    <c:otherwise><p>No comment updates.</p></c:otherwise>
                </c:choose>
            <h2>Unreviewed Comments</h2>
            <c:forEach var="comment" items="${comments}">
                <h4>Email:</h4><p>${comment.email}</p>
                <h4>Subject:</h4><p>${comment.subject}</p>
                <h4>Message:</h4><p>${comment.messageText}</p>
	            <form method="post" action="/team19/pages/adminComments">
	                <input type="hidden" name="commentId" value="${comment.commentId}" />
	                <input type="hidden" name="email" value="${comment.email}" />
	                <input type="hidden" name="subject" value="${comment.subject}" />
	                <textarea name="mailMessage" cols="50" rows="15" >Hi ${comment.firstName}, about your message concerning ${comment.subject}:</textarea>
	                <input type="submit" name="respond" value="respond" />
	            </form>
            </c:forEach>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>