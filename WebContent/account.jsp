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
            <div id="details">

	            <c:if test="${editError!=null}">
	                <p class="feedback">${editError}</p>
	            </c:if>
	            <c:if test="${edited}">
	                <p class="feedback">Details updated.</p>
	            </c:if>
	            <c:if test="${unenrolled}">
	                <p class="feedback">Un-Enrolled from course.</p>
	            </c:if>
	            <form method="post" action="https://${secureServerName}/team19/pages/account">
	                <fieldset id="personalInformation">
	                    <legend>Account Details</legend>
	                    <dl>
				            <dt><label for="firstName">First Name:</label></dt>
				            <dd><input type="text" class="editable" id="firstName" name="firstName" disabled="disabled"
				                value="${sessionScope['user'].firstName}" placeholder="${sessionScope['user'].firstName}"/></dd>
				            <dt><label for="lastName">Last Name:</label></dt>
				            <dd><input type="text" class="editable" id="lastName" name="lastName" disabled="disabled"
				                            value="${sessionScope['user'].lastName}" placeholder="${sessionScope['user'].lastName}"/></dd>
				            <dt><label for="address1">Address Line 1:</label></dt>
				            <dd><input type="text" class="editable" id="address1" name="addressLine1" disabled="disabled"
				                value="${sessionScope['user'].addressLine1}" placeholder="${sessionScope['user'].addressLine1}"/></dd>
				            <dt><label for="address2">Address Line 2:</label></dt>
				            <dd><input type="text" class="editable" id="address2" name="addressLine2" disabled="disabled"
				                value="${sessionScope['user'].addressLine2}" placeholder="${sessionScope['user'].addressLine2}"/></dd>
				            <dt><label for="address3">Address Line 3:</label></dt>
				            <dd><input type="text" class="editable" id="address3" name="addressLine3" disabled="disabled"
				                value="${sessionScope['user'].addressLine3}" placeholder="${sessionScope['user'].addressLine3}"/></dd>
				            <dt><label for="country">Country:</label></dt>
				            <dd><input type="text" class="editable" id="country" name="country" disabled="disabled"
				                value="${sessionScope['user'].country}" placeholder="${sessionScope['user'].country}"/></dd>
				        </dl>
				        <script>function enableEdit() {
	                        var inputs = document.getElementsByClassName("editable");
	                        for (var i = 0; i < inputs.length; i++) {
	                            inputs[i].disabled=!inputs[i].disabled;
	                        }
	  	                }</script>
				        <input type="submit" class="editable" name="editStudent" value="Submit Changes" disabled="disabled" />
				        <input type="button" value="Edit" onclick="enableEdit()" />
	                </fieldset>
	            </form>
	            <form method="post" action="https://${secureServerName}/team19/pages/account">
	                <fieldset id="personalInformation">
	                    <legend>Courses Enrolled</legend>
	                    <dl>
	                        <dd>
	                            <select name="courseId">
	                                <c:forEach var="course" items="${courses}">
	                                    <option value="${course.courseId}">${course.courseTitle}</option>
	                                </c:forEach>
	                            </select>
	                        </dd>
	                    </dl>
	                    <input type="submit" name="unenroll" value="Un-Enroll?" />
	                    <input type="submit" name="view" value="View Course" formaction="/team19/pages/viewCourse" />
	                </fieldset>
	            </form>
            </div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>