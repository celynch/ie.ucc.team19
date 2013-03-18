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
            <h2>Course Details</h2>
            <c:choose>
                <c:when test="${fn:length(courses) > 0}">
                    <c:forEach var="course" items="${courses}">
                        <h3>${course.courseTitle}</h3>
                        <c:choose>
                            <c:when test="${user!=null}">
	                            <form method="post" action="/team19/pages/enrollTest">
	                               <input type="hidden" name="enrollCourseId" value="${course.courseId}"/>
	                               <input type="submit" value="Enroll"/>
	                            </form>
                            </c:when>
                        </c:choose>
                        <h3>Fee:</h3>
                        <p>&euro;<fmt:formatNumber value="${course.fee}" minFractionDigits="2" maxFractionDigits="2"/></p>
                        <h3>Lecturer<c:choose><c:when test="${fn:length(lecturers) > 0}">s</c:when></c:choose>:</h3>
                        <c:forEach var="lecturer" items="${lecturers}">
                            <p>${lecturer.lecturerTitle} ${lecturer.firstName} ${lecturer.lastName}, ${lecturer.position}</p>
                        </c:forEach>
                        <h3>Venue<c:choose><c:when test="${fn:length(venues) > 0}">s</c:when></c:choose>:</h3>
                        <c:forEach var="venue" items="${venues}">
                            <p>${venue.venueRoom}, ${venue.venueBuilding}, ${venue.addressLine1}</p>
                        </c:forEach>
                        ${course.content}
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No Courses found.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>