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
            <section class="dashBoard">
                <h2><a href="team19/pages/admin/adminManage">Admin Management</a></h2>
                <p>Add new Admin</p>
                <p>Update Admin Details</p>
                <p>Remove Admin</p>
            </section>
            <section class="dashBoard">
                <h2><a href="team19/pages/admin/adminCourses">Course Management</a></h2>
                <div class="glance">
                    <p>Students: ${numberOfCourses}</p>
                </div>
                <p>View Course</p>
                <p>Add Course</p>
                <p>Update Course</p>
                <p>Remove Course</p>
            </section>
            <section class="dashBoard">
                <h2>Student Management</h2>
                <div class="glance">
                    <p>Students: ${numberOfStudents}</p>
                </div>
                <p>View Student</p>
                <p>Update St</p>
                <p>Remove Course</p>
            </section>
            <section class="dashBoard">
                <h2><a href="team19/pages/admin/adminVenues">Venue Management</a></h2>
                <div class="glance">
                    <p>Venues: ${numberOfVenues}</p>
                </div>
                <p>Add Venue</p>
                <p>Update Venue</p>
                <p>Remove Venue</p>
            </section>
            <section class="dashBoard">
                <h2><a href="team19/pages/admin/adminLecturers">Lecturer Management</a></h2>
                <div class="glance">
                    <p>Lecturers: ${numberOfLecturers}</p>
                </div>
                <p>Add Lecturer</p>
                <p>Update Lecturer</p>
                <p>Remove Lecturer</p>
            </section>
            <section class="dashBoard">
                <h2><a href="team19/pages/admin/adminComments">Review Comments</a></h2>
                <div class="glance">
                    <p>Unreviewed Comments: ${fn:length(comments)}</p>
                </div>
            </section>
            <div class="clear"></div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
