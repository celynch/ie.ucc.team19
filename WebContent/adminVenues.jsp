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
            <section id="addVen">
                <form method="post" action="/team19/pages/adminVenues">
                    <fieldset>
                        <legend>Add Venue</legend>
                            <dl>
                                <dd><input type="text" name="venueRoom" placeholder="Venue Room" required="required"/></dd>
                                <dd><input type="text" name="venueBuilding" placeholder="Venue Building" required="required" /></dd>
                                <dd><input type="text" name="addressLine1" placeholder="Address Line1" required="required"/></dd>
                                <dd><input type="text" name="addressLine2" placeholder="Address Line2" required="required"/></dd>
                                <dd><input type="text" name="addressLine3" placeholder="Address Line3" required="required"/></dd>
                                <dd><input type="number" min="0" name="capacity" placeholder="Capacity" required="required"/></dd>
                                <dt><label for="onCampus">On Campus?</label></dt>
                                 <dd id="onCampus">
			                        <label>Yes<input type="radio" name="onCampus" value="true" required="required"/></label>
			                        <label>No<input type="radio" name="onCampus" value="false" required="required"/></label>
			                    </dd>
                            </dl>
                            <input type="submit" name="addVen" value="Add Venue" />
                    </fieldset>
                </form>
            </section>
            <section id="updateVen">
            </section>
            <section id="removeVen">
            </section>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
