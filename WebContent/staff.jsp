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
            <h2>Summer Courses: Staff</h2>
            <p>For contact details see <a href="/team19/pages/contactUs">here.</a></p>
                <ul>
                    <c:forEach var="lecturer" items="${lecturers}">
                        <li class="lecturerCard">
                            <img class="profilePic" src="/team19/images/lecturers/lecturer${lecturer.lecturerId}.jpg" title="" />
                            <h3>${lecturer.lecturerTitle} ${lecturer.firstName} ${lecturer.lastName}</h3>
                            <p>${lecturer.position}</p>
                            <p>Email: <a href="mailto:${lecturer.email}">${lecturer.email}</a></p>
                            <p>Tele: ${lecturer.telephone}</p>
                            <div class="clear"></div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="clear"></div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>