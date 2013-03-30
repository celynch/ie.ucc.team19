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
            <h2>Confirm Enrollment</h2>
            <div id="enrollNotice">
                <h3>Please Note: </h3>
	            <p>Review your selection before proceeding with enrollment.</p>
	            <p>A place on the course has been temporarily reserved for you. If payment of the deposit has not completed the within 30 minutes, the reservation is cancelled and you will have to reapply to enroll.</p>
	            <p>Selected Course: <span>${enrollCourse.courseTitle}</span></p>
	            <p>Deposit Required: <span>${enrollCourse.fee * 0.2}</span></p>
	            <p>Rest of Fee: <span>${enrollCourse.fee * 0.8}</span></p>
                <p>Start Date: <fmt:formatDate type="date" value="${enrollCourse.courseStartDate}" pattern="MMM-dd" /></p>
                <p>End Date: <fmt:formatDate type="date" value="${enrollCourse.courseEndDate}" pattern="MMM-dd" /></p>
            </div>
            <div id="schedule">
                <h3>Your current schedule:</h3>
                <table id="scheduleContainer">
                    <thead>
                        <tr>
                            <th class="sched_CourseDetails">Course Title</th>
                            <th class="sched_CourseDetails">Start Date</th>
                            <th class="sched_CourseDetails">End Date</th>
                            <th>Dates</th>
                            <th>Deposit</th>
                            <th>Fee</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:if test="${fn:length(courses)>0}">
                    <c:forEach var="i" begin="0" end="${fn:length(courses)-1}">
                        <tr class="sched_CourseDetail">
                            <td class="sched_CourseDetails sched_SideTitle">${courses[i].courseTitle}</td>
                            <td class="sched_CourseDetails sched_StartDate"><fmt:formatDate type="date" value="${courses[i].courseStartDate}" pattern="MMM-dd" /></td>
                            <td class="sched_CourseDetails sched_EndDate" ><fmt:formatDate type="date" value="${courses[i].courseEndDate}" pattern="MMM-dd"/></td>
                            <td class="sched_CourseDateBar">
	                            <div class="sched_AllCoursesRange">
	                                <div class="sched_CourseRange ${conflicts[i]}"
	                                    style="position:relative;left:${offsets[i]}%;width:${widths[i]}%;">
	                                </div>
	                            </div>
                            </td>
                            <td class="sched_Status">
                                <c:choose>
                                    <c:when test ="${enrollments[i].paidDeposit}"><span class="sched_Paid"> </span></c:when>
                                    <c:otherwise><span class="sched_Unpaid"> </span></c:otherwise>
                                </c:choose>
                            </td>
                            <td class="schedStatus">
                                <c:choose>
                                    <c:when test ="${enrollments[i].paidFee}"><span class="sched_Paid"> </span></c:when>
                                    <c:otherwise><span class="sched_Unpaid"> </span></c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div id="cancelOrEnroll">
                <c:choose>
                    <c:when test="${conflictDetected}">
                        <p>The course you trying to enroll in has a scheduling conflict with another course you are already enrolled in.</p>
                        <p>Either cancel this enrollment or view your account to un-enroll from the other course</p>
                        <form method="post">
                            <input type="submit" value="Cancel" formaction="/team19/pages/viewCourse?courseId=${enrollCourse.courseId}" />
                            <input type="submit" value="Account" formaction="/team19/pages/account" />
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>The fee to enroll in this course is &euro;<fmt:formatNumber value="${enrollCourse.fee}" minFractionDigits="2" maxFractionDigits="2"/>.</p>
                        <p>A minimum deposit of &euro;<fmt:formatNumber value="${enrollCourse.fee * 0.2}" minFractionDigits="2" maxFractionDigits="2"/> is required to reserve a place in the course.</p>
                        <p>The total fee most be paid by <fmt:formatDate type="date" value="${enrollCourse.enrollEndDate}" /></p>
                        <form method="post">
                            <input type="submit" value="Cancel" formaction="/team19/pages/viewCourse?courseId=${enrollCourse.courseId}" />
                        </form>
                        <form method="post" action="https://www.sandbox.paypal.com/cgi-bin/webscr">
                            <input type="hidden" name="business" value="celynch@gmail.com" />
						    <input type="hidden" name="cmd" value="_xclick" />
						    <input type="hidden" name="item_name" value="Deposit ${enrollCourse.courseTitle}" />
						    <input type="hidden" name="amount" value="${enrollCourse.fee * 0.2}" />
						    <input type="hidden" name="currency_code" value="EUR" />
						    <input type="hidden" name="custom" value="${user.studentId}-${enrollCourse.courseId}-1">
						    <input type="hidden" name="notify_url" value="http://${serverName}/team19/ipn/" />
						    <input type="hidden" name="cancel_return" value="http://${serverName}/team19/pages/">
						    <input type="hidden" name="return" value="http://${serverName}/team19/pages/">
						    <label for="deposit">Deposit: &euro;${enrollCourse.fee * 0.2}</label>
						    <input id="deposit" type="image" name="submit" style="border:none;" src="http://images.paypal.com/images/x-click-but2.gif" alt="PayPal - The safer, easier way to pay online">
						    <img alt="" border="0" width="1" height="1" src="https://www.paypal.com/en_US/i/scr/pixel.gif" />
                        </form>
                        <form method="post" action="https://www.sandbox.paypal.com/cgi-bin/webscr">
                            <input type="hidden" name="business" value="celynch@gmail.com">
                            <input type="hidden" name="cmd" value="_xclick">
                            <input type="hidden" name="item_name" value="Fee ${enrollCourse.courseTitle}">
                            <input type="hidden" name="amount" value="${enrollCourse.fee * 0.8}">
                            <input type="hidden" name="currency_code" value="EUR">
                            <input type="hidden" name="custom" value="${user.studentId}-${enrollCourse.courseId}-2">
                            <input type="hidden" name="notify_url" value="http://${serverName}/team19/ipn/" />
                            <input type="hidden" name="cancel_return" value="http://${serverName}/team19/pages/">
                            <input type="hidden" name="return" value="http://${serverName}/team19/pages/">
                            <label for="fee">Fee: &euro;${enrollCourse.fee * 0.8}</label>
                            <input id="fee" type="image" name="submit" style="border:none;" src="http://images.paypal.com/images/x-click-but2.gif" alt="PayPal - The safer, easier way to pay online">
                            <img alt="" border="0" width="1" height="1" src="https://www.paypal.com/en_US/i/scr/pixel.gif" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>