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
            <section id="addCourse">
                <form method="post" action="/team19/pages/adminCourses">
                    <fieldset>
                        <legend>Add Course</legend>
                            <dl>
                                <dd><input type="text" name="courseTitle" placeholder="Course Title" /></dd>
                                <dd><input type="number" min="0" name="fee" placeholder="Fee: &euro;" /></dd>
                                <dd><input type="text" name="spaces" placeholder="Spaces" /></dd>
                                <dd><input type="text" name="courseCategory" placeholder="Course Category" /></dd>
                                <dt><label style="font-size:0.6em;margin-left:4em;">Date Format: YYYY-MM-DD</label></dt>
                                <dd><input type="text" name="enrollStartDate" placeholder="Enroll Start Date" /></dd>
                                <dd><input type="text" name="enrollEndDate" placeholder="Enroll End Date" /></dd>
                                <dd><input type="text" name="courseStartDate" placeholder="Course Start Date" /></dd>
                                <dd><input type="text" name="courseEndDate" placeholder="Course End Date" /></dd>
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
                                <dd><textarea name="content" cols="50" rows="15" ></textarea></dd>
                            </dl>
                            <input type="submit" name="addCourse" value="Add Course">
                    </fieldset>
                </form>
            </section>
            <!-- <div>
	            ${param['content']}
	        </div>
            <form method="post" action="/team19/pages/admin.jsp">  
		        <textarea name="content" cols="50" rows="15" >${content}</textarea>
		        <input type="submit" value="submit"/>
	        </form>
             -->
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>