<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horizNav.jsp" />
    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        <div id="content">
            <p id="breadcrumbs">
                <a href="/team19/pages/">&gt; Summer Courses</a>
                <a href="/team19/pages/browseCategory?category=${courses[0].courseCategory}"
                     >&gt; ${courses[0].courseCategory}</a>
                <a href="/team19/pages/viewCourse?courseId=${courses[0].courseId}"
                     >&gt; ${courses[0].courseTitle}</a>
            </p>
            <h2>Course Details</h2>
            <c:choose>
                <c:when test="${fn:length(courses) > 0}">
                    <c:forEach var="course" items="${courses}">
                        <h3>${course.courseTitle}</h3>
                        <c:choose>
                            <c:when test="${user!=null}">
	                            <form method="get" action="https://${secureServerName}/team19/pages/enrollConfirm">
	                               <input type="hidden" name="enrollCourseId" value="${course.courseId}"/>
	                               <input type="submit" value="Enroll"/>
	                            </form>
                            </c:when>
                            <c:otherwise>
                                <form method="post" action="/team19/pages/login">
                                   <label for="loginChallenge">You must login before enrolling</label>
                                   <input id="loginChallenge" type="submit" value="Login"/>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        <h3>Dates:</h3>
                        <p>Start Date: <fmt:formatDate type="date" value="${course.courseStartDate}" pattern="MMM-dd" /></p>
                        <p>End Date: <fmt:formatDate type="date" value="${course.courseEndDate}" pattern="MMM-dd" /></p>
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