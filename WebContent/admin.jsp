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
            <div>
	            ${param['content']}
	        </div>
            <form method="post" action="/team19/pages/admin.jsp">  
		        <textarea name="content" cols="50" rows="15" >${content}</textarea>
		        <input type="submit" value="submit"/>
	        </form>
            
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>