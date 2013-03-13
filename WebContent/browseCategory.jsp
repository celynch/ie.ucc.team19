<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horiz_nav.jsp" />
    <jsp:include page="WEB-INF/views/verti_nav.jsp" />
        <div id="content">
            <h2>Course Details</h2>
            <c:choose>
                <c:when test="${fn:length(categoryCourses) > 0}">
                    <ul>
	                    <c:forEach var="course" items="${categoryCourses}">
	                        <li><a href="viewCourse?course_id=${course['courseId']}">${course['courseTitle']}</a></li>
	                    </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>No Courses open in ${param['category']}.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="clear"></div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>