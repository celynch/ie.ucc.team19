<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" />
<jsp:directive.page import= "ie.ucc.team19.service.*" />
<jsp:directive.page import= "java.sql.Date" />
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registration Complete | UCC Summer Courses" />
</jsp:include>
<jsp:useBean id="user" class="ie.ucc.team19.dao.RegisterBean" scope="session" />
<jsp:setProperty name="user" property="*"/>
<jsp:setProperty name="user" property="date_of_birth" value="<%= request.getParameter(\"dobY\") + \"-\" + request.getParameter(\"dobM\") + \"-\" + request.getParameter(\"dobD\") %>" />
<jsp:scriptlet> new InsertUser().insertStudent(user); </jsp:scriptlet>
    <div id="main">
        <p>Thank you for registering, to activate your account please check you email and click the link therein.</p>
    </div>
<jsp:include page="footer.jsp"></jsp:include>