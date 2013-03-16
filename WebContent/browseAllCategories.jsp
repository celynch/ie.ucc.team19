<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>

    <div id="main" >
    <jsp:include page="WEB-INF/views/horizNav.jsp" />
    <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        <div id="content">
            <h2>Course Categories</h2>
            <p>Select from the list below to see the range of courses of that category.</p>
                <ul>
	                <c:forEach var="category" items="${categories.categoryTitles}">
	                    <li><a href="browseCategory.jsp?category=${category}">${category}</a></li>
	                </c:forEach>
                </ul>
        </div>
    </div>
    <div class="clear"></div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>