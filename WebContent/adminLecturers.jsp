<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
    <jsp:param name="admin" value="${true}" />
</jsp:include>

    <div id="main" >
	    <jsp:include page="WEB-INF/views/horizNav.jsp" />
	    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
    
        <div id="content">
            <section id="addLec">
                <form method="post" action="/team19/pages/adminLecturers">
                    <fieldset>
                        <legend>Add Lecturer</legend>
                            <dl>
                                <dd><input type="text" name="firstName" placeholder="First Name" /></dd>
                                <dd><input type="text" name="lastName" placeholder="Last Name" /></dd>
                                <dd><input type="email" name="email" placeholder="Email" /></dd>
                                <dd><input type="text" name="lecturerTitle" placeholder="Title" /></dd>
                                <dd><input type="text" name="position" placeholder="Position" /></dd>
                                <dd><input type="text" name="addressLine1" placeholder="Address Line1" /></dd>
                                <dd><input type="text" name="addressLine2" placeholder="Address Line2" /></dd>
                                <dd><input type="text" name="addressLine3" placeholder="Address Line3" /></dd>
                                <dd><input type="text" name="country" placeholder="Country" /></dd>
                                <dd><input type="text" name="telephone" placeholder="Telephone" /></dd>
                            </dl>
                            <input type="submit" name="addLec" value="Add Lecturer">
                    </fieldset>
                </form>
            </section>
            <section id="updateLec">
            </section>
            <section id="removeLec">
            </section>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
