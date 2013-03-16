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
				                    <dt>${sessionScope['user'].firstName}</dt>
				                    <dd><input type="text" id="firstName" name="firstName" /></dd>
				                    
				                    <dt><label for="lastName">Last Name:</label></dt>
				                    <dt>${sessionScope['user'].lastName}</dt>
				                    <dd><input type="text" id="lastName" name="lastName" /></dd>
				                    
				                    <dt><label for="address1">Address Line 1:</label></dt>
				                    <dt>${sessionScope['user'].addressLine1}</dt>
				                    <dd><input type="text" id="address1" name="addressLine1" /></dd>
				                    
				                    <dt><label for="address2">Address Line 2:</label></dt>
				                    <dt>${sessionScope['user'].addressLine2}</dt>
				                    <dd><input type="text" id="address2" name="addressLine2" /></dd>
				                    
				                    <dt><label for="address3">Address Line 3:</label></dt>
				                    <dt>${sessionScope['user'].addressLine3}</dt>
				                    <dd><input type="text" id="address3" name="addressLine3" /></dd>
				                    
				                    <dt><label for="country">Country:</label></dt>
				                    <dt>${sessionScope['user'].country}</dt>
				                    <dd><input type="text" id="country" name="country" /></dd>
				                    
				                    <dt><label for="gender">Gender</label></dt>
				                    <dt>
					                    <c:choose>
					                        <c:when test="${sessionScope['user'].gender == 'M'.charAt(0)}">Male</c:when>
					                        <c:otherwise>Female</c:otherwise>
					                    </c:choose>

				                    </dt>
				                    <dd id="gender">
				                        <label>M:<input type="radio" name="gender" value="M" /></label>
				                        <label>F:<input type="radio" name="gender" value="F" /></label>
				                    </dd>
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