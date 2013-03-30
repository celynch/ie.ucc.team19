<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
    <jsp:param name="admin" value="${admin}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horizNav.jsp" />
    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        
        <div id="content">
            <section id="editCourse">
                <form method="post" action="/team19/pages/adminCourseUpdate">
                    <fieldset>
                        <legend>Edit Course</legend>
                            <dl>
                                <dt><label for="courseTitle">Title:</label></dt>
                                <dd><input id="courseTitle" type="text" name="courseTitle" value="${course.courseTitle}" required/></dd>
                                <dt><label for="fee">Fee: &euro;</label></dt>
                                <dd><input id="fee" type="number" min="0" name="fee" value="${course.fee}" required /></dd>
                                <dt><label for="spaces">Spaces: </label></dt>
                                <dd><input id="spaces" type="text" name="spaces" value="${course.spaces}" required /></dd>
                                <dt><label for="courseCategory">Course Category: </label></dt>
                                <dd><input id="courseCategory" type="text" name="courseCategory" value="${course.courseCategory}" required /></dd>
                                <dt><label for="enrollStartDate" >Enroll Start Date</label></dt>
                                <dd><input id="enrollStartDate" type="text" name="enrollStartDate" value="${course.enrollStartDate}" required /></dd>
                                <dt><label for="enrollEndDate" >Enroll Start Date</label></dt>
                                <dd><input id="enrollEndDate" type="text" name="enrollEndDate" value="${course.enrollEndDate}" required /></dd>
                                <dt><label for="courseStartDate" >Enroll Start Date</label></dt>
                                <dd><input id="courseStartDate" type="text" name="courseStartDate" value="${course.courseStartDate}" required /></dd>
                                <dt><label for="courseEndDate" >Enroll Start Date</label></dt>
                                <dd><input id="courseEndDate" type="text" name="courseEndDate" value="${course.courseEndDate}" required /></dd>
                                <dd>
                                    <select name="lecturerId">
                                        <option value="-1">Lecturer</option>
                                        <c:forEach var="lecturer" items="${lecturers}">
                                            <option value="${lecturer.lecturerId}">${lecturer.lecturerTitle} ${lecturer.firstName} ${lecturer.lastName}</option>
                                        </c:forEach>
                                    </select>
                                </dd>
                                <dd>
                                    <select name="venueId">
                                        <option value="-1">Venue</option>
                                        <c:forEach var="venue" items="${venues}">
                                            <option value="${venue.venueId}">${venue.venueRoom} ${venue.venueBuilding}</option>
                                        </c:forEach>
                                    </select>
                                </dd>
                                <dd><textarea name="content" cols="50" rows="15" >${course.content}</textarea></dd>
                            </dl>
                            <input type="hidden" name="courseId" value="${course.courseId}" />
                            <input type="submit" name="updateCourse" value="Submit Changes">
                    </fieldset>
                </form>
            </section>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
