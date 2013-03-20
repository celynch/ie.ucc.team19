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
            <h2>Course Enrollment</h2>
            <div id="enrollNotice">
                <h3>Please Note: </h3>
	            <p>Review your selection before proceeding with enrollment.</p>
	            <p>A place on the course has been temporarily reserved for you. If payment of the deposit has not completed the within 30 minutes, the reservation is cancelled and you will have to reapply to enroll.</p>
            </div>
            <div id="schedule">
                <h3>Your current schedule:</h3>
                <div id="scheduleContainer">
                    <c:forEach var="i" begin="0" end="${fn:length(courses)-1}">
                        <div>
                            <p id="schedSideTitle">${courses[i].courseTitle}</p>
                            <div id="allCoursesRange">
                                <div id="courseRange" class="${conflicts[i]}"
                                     style="position:relative;left:${offsets[i]}%;width:${widths[i]}%;">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div id="cancelOrEnroll">
                <c:choose>
                    <c:when test="${conflictDetected}">
                        <p>The course you trying to enroll in has a scheduling conflict with another course you are already enrolled in.</p>
                        <p>Either cancel this enrollment or view your account to un-enroll from the other course</p>
                        <form method="post">
                            <input type="submit" value="Cancel" formaction="/team19/pages/viewCourse?courseId=${enrollCourse[0].courseId}" />
                            <input type="submit" value="Account" formaction="/team19/pages/account" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>The fee to enroll in this course is &euro;<fmt:formatNumber value="${enrollCourse[0].fee}" minFractionDigits="2" maxFractionDigits="2"/>.</p>
                        <p>A minimum deposit of &euro;<fmt:formatNumber value="${enrollCourse[0].fee * 0.2}" minFractionDigits="2" maxFractionDigits="2"/> is required to reserve a place in the course.</p>
                        <p>The total fee most be paid by ${enrollCourse[0].enrollEndDate}</p>
                        <form method="post">
                            <input type="submit" value="Cancel" formaction="/team19/pages/viewCourse?courseId=${enrollCourse[0].courseId}" />
                            <input type="hidden" name="paypalVoodoo" value="${enrollCourse[0].fee}"/>
                            <input type="submit" name="deposit" value="Deposit" formaction="/team19/pages/account" />
                            <input type="submit" name="fee" value="Full Fee" formaction="/team19/pages/account" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>