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
            <p id="breadcrumbs">
                <a href="http://${serverName}/team19/pages/">Summer Courses &gt;</a>
                <a href="http://${serverName}/team19/pages/browseCategory?category=${param.category}">${param.category} &gt;</a>
            </p>
            <c:choose>
                <c:when test="${fn:length(categoryCourses) > 0}">
                    <ul>
	                    <c:forEach var="course" items="${categoryCourses}">
	                        <li><a href="/team19/pages/viewCourse?courseId=${course['courseId']}">${course['courseTitle']}</a></li>
	                    </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <p>No Courses open in ${param['category']}.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>