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
            <section id="addAdmin">
                <c:if test="${adminAdded!=null}"><p class="feedback">${adminAdded}</p></c:if>
                <form method="post" action="/team19/pages/adminManage">
                    <fieldset>
                        <legend>Add Admin</legend>
                            <dl>
                                <dd><input required="required" type="text" name="adminName" placeholder="Admin Name" /></dd>
                                <dd><input required="required" type="text" name="adminPassword" placeholder="Passord" /></dd>
                                <dd><input required="required" type="email" name="email" placeholder="Email" /></dd>
                            </dl>
                            <input type="submit" name="addAdmin" value="Add Admin">
                    </fieldset>
                </form>
            </section>
        </div>
    </div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>
