<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />

<jsp:include page="WEB-INF/views/header.jsp">
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
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
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>