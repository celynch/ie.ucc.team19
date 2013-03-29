<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<jsp:include page="WEB-INF/views/header.jsp" >
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="serverName" value="${serverName}" />
    <jsp:param name="secureServerName" value="${secureServerName}" />
</jsp:include>

    <div id="main">
        <jsp:include page="WEB-INF/views/horizNav.jsp" />
        <jsp:include page="WEB-INF/views/vertiNav.jsp" />
        <div id="content">
        <p class="feedback">${registerError}</p>
        <form method="post" action="https://${secureServerName}/team19/pages/RegisterComplete">
            <fieldset id="personalInformation">
                <legend>Personal information</legend>
                <dl>
	                <dt><label for="firstName">First Name: *</label></dt>
	                <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="firstName" name="firstName" value="${retry.firstName[0]}" /></dd>
	                <dt><label for="lastName">Last Name: *</label></dt>
	                <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="lastName" name="lastName" value="${retry.lastName[0]}" /></dd>
	                <dt><label for="dobD">Date of Birth *</label></dt>
		            <dd>
		                <input x-webkit-speech="x-webkit-speech" required="required" type="number" min="1" max="31" name="dobD" maxlength="2" size="2" id="dobD"
			                value="DD"
			                onfocus="if(this.value=='DD')this.value='';"
			                onblur="if(this.value=='')this.value='DD';" />
		                <select name="dobM" id="dobM">
			                <option>Month</option>
			                <option value="01">January</option>
			                <option value="02">February</option>
			                <option value="03">March</option>
			                <option value="04">April</option>
							<option value="05">May</option>
							<option value="06">June</option>
							<option value="07">July</option>
							<option value="08">August</option>
							<option value="09">September</option>
							<option value="10">October</option>
							<option value="11">November</option>
							<option value="12">December</option>
	                    </select>
	                    <input x-webkit-speech="x-webkit-speech" required="required" type="number" min="1900" max="${year - 18}" name="dobY" maxlength="4" size="4" id="dobY"
			                value="YYYY"
			                onfocus="if(this.value=='YYYY')this.value='';"
			                onblur="if(this.value=='')this.value='YYYY';" />
	                </dd>
	                <dt><label for="gender">Gender *</label></dt>
	                <dd id="gender">
                        <label>Male:<input type="radio" name="gender" value="M" required="required"/></label>
                        <label>Female:<input type="radio" name="gender" value="F" required="required"/></label>
                    </dd>
                </dl>
            </fieldset>

            <fieldset id="regAddress">
                <legend>Address</legend>
                <dl>
		            <dt><label for="address1">Address: *</label></dt>
		            <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="address1" name="addressLine1" value="${retry.addressLine1[0]}" /></dd>
		            <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="address2" name="addressLine2" value="${retry.addressLine2[0]}" /></dd>
		            <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="address3" name="addressLine3" value="${retry.addressLine3[0]}" /></dd>
		            <dt><label for="country" >Country: *</label></dt>
		            <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="country" name="country" value="${retry.country[0]}" /></dd>
	            </dl>
            </fieldset>
            <fieldset id="contactInformation">
                <legend>Contact Information</legend>
                <dl>
                    <dt><label for="telephone">Telephone:</label></dt>
                    <dd><input x-webkit-speech="x-webkit-speech" type="tel" id="telephone" name="telephone" value="${retry.telephone[0]}" /></dd>
		            <dt><label for="email">Email: *</label></dt>
		            <dd><input x-webkit-speech="x-webkit-speech" required="required" type="email" id="email" name="email" placeholder="email" value="${retry.email[0]}" /></dd>
		            <dt><label for="email2">Verify Email: *</label></dt>
                    <dd><input x-webkit-speech="x-webkit-speech" required="required" type="text" id="email2" name="email2" placeholder="verify email" value="${retry.email2[0]}" /></dd>
                </dl>
            </fieldset>
            <fieldset id="PasswordInformation">
                <legend>Contact Information</legend>
                <p>Please enter a password for your student account. Passwords should be between 6 and 20 characters and contain at least 1 letter and 1 number.</p>
                <script>
                    function checkPasswordLength() {
	                    if(document.getElementById('password').value.length<6) {
	                    	document.getElementById('passwordLabel1').innerHTML='Password: * <span class=\"feedback\">At least 6 characters</span>';
	                	} else {
	                		document.getElementById('passwordLabel1').innerHTML='Password: *';
	              		}
                    }
                </script>
                <script>
                    function checkPasswordMatch() {
                    	if(document.getElementById('password2').value != document.getElementById('password').value) {
                    		document.getElementById('passwordLabel2').innerHTML='Re-enter Password: * <span class=\"feedback\">Does not match</span>';
                   		} else {
                   			document.getElementById('passwordLabel2').innerHTML='Re-enter Password: *';
                   		}
                    }
                </script>
                <dl>
		            <dt><label for="password" id="passwordLabel1">Password: *</label></dt>
		            <dd><input required="required" type="password" id="password" name="password" onblur="checkPasswordLength()"/><dd>
		            <dt><label for="password2" id="passwordLabel2">Re-enter Password: *</label></dt>
		            <dd><input required="required" type="password" id="password2" name="password2" onblur="checkPasswordMatch()" /></dd>
	            </dl>
            </fieldset>
            <input type="submit" name="cancel" value="Cancel" formaction="/tema19/pages/"/>
            <input type="submit" name="Proceed" value="Proceed"/>
        </form>
        </div>
    </div>
<jsp:include page="WEB-INF/views/footer.jsp"></jsp:include>