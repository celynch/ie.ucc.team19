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
            <div id="details">
                <c:choose>
                    <c:when test="${sessionScope['user']!=null}">
                        <form>
                            <fieldset>
                                <legend>Account Details</legend>
                                <dl>
				                    <dt><label for="firstName">First Name:</label></dt>
				                    <dd><input type="text" id="firstName" name="firstName" disabled="disabled" placeholder="${sessionScope['user'].firstName}"/></dd>
				                    
				                    <dt><label for="lastName">Last Name:</label></dt>
				                    <dd><input type="text" id="lastName" name="lastName" disabled="disabled" placeholder="${sessionScope['user'].lastName}"/></dd>
				                    
				                    <dt><label for="address1">Address Line 1:</label></dt>
				                    <dd><input type="text" id="address1" name="addressLine1" disabled="disabled" placeholder="${sessionScope['user'].addressLine1}"/></dd>
				                    
				                    <dt><label for="address2">Address Line 2:</label></dt>
				                    <dd><input type="text" id="address2" name="addressLine2" disabled="disabled" placeholder="${sessionScope['user'].addressLine2}"/></dd>
				                    
				                    <dt><label for="address3">Address Line 3:</label></dt>
				                    <dd><input type="text" id="address3" name="addressLine3" disabled="disabled" placeholder="${sessionScope['user'].addressLine3}"/></dd>
				                    
				                    <dt><label for="country">Country:</label></dt>
				                    <dd><input type="text" id="country" name="country" disabled="disabled" placeholder="${sessionScope['user'].country}"/></dd>
				                </dl>
                            </fieldset>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p>You must Log in to your account to view your details</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="clear"></div>

<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>