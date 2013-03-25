<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp">
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
</jsp:include>

    <div id="main">
	    <jsp:include page="WEB-INF/views/horizNav.jsp" />
	    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        <div id="content">
	        <h3>Contact us - Main address &amp; phone number</h3>
	        <p>
	            The Department's primary location is at "The Laurels" UCC on campus, and contact details are below.
	        </p>
	        <div id="address">
		        <address>Centre for Adult Continuing Education</address>
		        <address>The Laurels, UCC</address>
		        <address>Western Road</address>
		        <address>Cork</address>
		        <address>Ireland</address>
		        <address>Phone: 00 353 21 490 4700</address>
		        <address><a href="mailto:ace@ucc.ie">ace@ucc.ie</a></address>
	        </div>

	        <div id="mapContainer">
		        <iframe width="425" height="350" style="frameborder:0;scrolling:no;marginheight:0;marginwidth:0;"
		            src="http://maps.google.co.uk/maps?q=The+Laurels,+UCC&sll=51.893126,-8.50565&hq=The+Laurels,+UCC&iwloc=A&output=embed">
		        </iframe>
	            <br />
		        <a href="http://maps.google.co.uk/maps?q=The+Laurels,+UCC&sll=51.893126,-8.50565&hq=The+Laurels,+UCC&iwloc=A"
		            style="color:#0000FF;text-align:left">View Larger Map</a>
	        </div>
	        <div class="clear"></div>
	        <div id="contactForm">
	            <h3>If you wish to sumbit a query to the courses co-ordinator, see below.</h3>
	            <c:choose>
	                <c:when test="${user != null}">
	                    <c:if test="${param['submit'] != null}">
	                        <p>Your message has been sent, the courses co-ordinator will respond via email when the message has been reviewed.</p>
	                    </c:if>
			            <form method="post" action="/team19/pages/contactUs">
			                <fieldset>
			                    <legend>Comment:</legend>
			                    <input type="hidden" name="studentId" value="${user.studentId}" />
			                    <select name="concerning" id="dobM" onClick="if(this.value!='')getElementById('subject').value=this.value;">
		                            <option>Concerning:</option>
		                            <option value="General Enquery">General Enquery</option>
		                            <option value="Site Error">Site Error</option>
		                            <option value="Course Feedback">Course Feedback</option>
		                        </select>
		                        <input required="required" name="subject" type="text" id="subject" placeholder="subject" />
		                        <textarea name="messageText" rows="20" cols="50"></textarea>
		                        <input type="submit" name="submit" value="Submit Query" />
			                </fieldset>
			            </form>
		            </c:when>
		            <c:otherwise>
		                <p>You must be <a href="/team19/pages/login">login</a> to submit comments to the course co-ordinator.</p>
		            </c:otherwise>
	            </c:choose>
	        </div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>