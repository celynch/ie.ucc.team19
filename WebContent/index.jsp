<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" />
<jsp:directive.page import= "ie.ucc.team19.service.*" />
<jsp:directive.page import= "java.util.*" />
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Welcome to UCC Summer Courses" />
</jsp:include>
<%
    String tablequery = "SELECT * FROM Courses WHERE course_id = \'1\'";
    ArrayList<String[]> details = new ArrayList<String[]>();
    details = new TableFetcher().getCourseDetails("1");
    ArrayList<String[]> lecturers = new ArrayList<String[]>();
    lecturers = new TableFetcher().getCourseLecturers("1");
    ArrayList<String[]> venues = new ArrayList<String[]>();
    venues = new TableFetcher().getCourseVenues("1");
%>
    <div id="main" >
        <h2>Course Details</h2>
        <%
            if(details.size() != 0) {
                out.print("<h3>" + details.get(0)[1] + "</h3>");
                out.print("<h3>Fee:</h3>");
                out.print("<p>&euro;" + details.get(0)[2] + "</p>");
                out.print("<h3>Lecturer");
                out.print(lecturers.size() > 1 ? "s:</h3>" : ":</h3>");
                System.out.println(lecturers.size());
                for(String[] lecturer : lecturers) {
                    out.print("<p>" + lecturer[4] + " " + lecturer[1] + " " + lecturer[2] + ", ");
                    out.print(lecturer[5] +"</p>");
                }
                out.print("<h3>Venue");
                out.print(venues.size() > 1 ? "s:</h3>" : ":</h3>");
                for(String[] venue : venues) {
                    out.print("<p>" + venue[1] + ", " + venue[2] + ", " + venue[3] + "</p>");
                }
                out.print(details.get(0)[5]);
            } else {
                out.println("<p>No Courses found.</p>");
            }%>
    </div>
  

<jsp:include page="footer.jsp"></jsp:include>