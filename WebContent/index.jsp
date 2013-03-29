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
            <h2>Course Categories</h2>
            <p>Select from the list below to see the range of courses of that category.</p>
                <ul>
                    <c:if test="${fn:length(categories.categoryTitles)>0}">
	                <c:forEach var="i" begin="0" end="${fn:length(categories.categoryTitles)-1}">
	                    <li class="categoryPanel<c:if test="${i % 2 == 1}"> categoryPanelRight</c:if>">
	                       <a href="/team19/pages/browseCategory.jsp?category=${categories.categoryTitles[i]}">${categories.categoryTitles[i]}</a>
                        </li>
	                </c:forEach>
	                </c:if>
                </ul>
                <div class="clear"></div>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>