<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" />
<jsp:directive.page import= "ie.ucc.team19.service.*" />
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Account Registration | UCC Summer Courses" />
</jsp:include>
    <div id="main">
        <form method="post" action="registered.jsp">
            <fieldset id="personalinformation">
                <legend>Personal information</legend>
                <dl>
	                <dt><label for="first_name">First Name: *</label></dt>
	                <dd><input required="required" type="text" id="first_name" name="first_name" /></dd>
	                <dt><label for="last_name">Last Name: *</label></dt>
	                <dd><input required="required" type="text" id="last_name" name="last_name" /></dd>
	                <dt><label for="dobD">Date of Birth *</label></dt>
		            <dd>
		                <input required="required" type="text" name="dobD" maxlength="2" size="2" id="dobD"
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
	                    <input required="required" type="text" name="dobY" maxlength="4" size="4" id="dobY"
			                value="YYYY"
			                onfocus="if(this.value=='YYYY')this.value='';"
			                onblur="if(this.value=='')this.value='YYYY';" />
	                </dd>
                </dl>
            </fieldset>

            <fieldset id="address">
                <legend>Address</legend>
                <dl>
		            <dt><label for="address1">Address: *</label></dt>
		            <dd><input required="required" type="text" id="address1" name="address_line1" /></dd>
		            <dd><input required="required" type="text" id="address2" name="address_line2" /></dd>
		            <dd><input required="required" type="text" id="address3" name="address_line3" /></dd>
		            <dt><label for="country">Country: *</label></dt>
		            <dd><input required="required" type="text" id="country" name="country" /></dd>
		            <dt><label for="gender">Gender *</label></dt>
		            <dd id="gender">
		                <label>Male:<input type="radio" name="gender" value="M"/></label>
		                <label>Female:<input type="radio" name="gender" value="F"/></label>
	                </dd>
	            </dl>
            </fieldset>
            <fieldset id="contact information">
                <legend>Contact Information</legend>
                <dl>
                    <dt><label for="telephone">Telephone:</label></dt>
                    <dd><input type="text" id="telephone" name="telephone" /></dd>
		            <dt><label for="email">Email: *</label></dt>
		            <dd><input required="required" type="text" id="email" name="email" /></dd>
		            <dt><label for="email2">Verify Email: *</label></dt>
                    <dd><input required="required" type="text" id="email2" name="email" /></dd>
                </dl>
            </fieldset>
            <fieldset id="Password information">
                <legend>Contact Information</legend>
                <p>Please enter a password for your student account. Passwords should be between 6 and 20 characters and contain at least 1 letter and 1 number.</p>
                <dl>
		            <dt><label for="password">Password: *</label></dt>
		            <dd><input required="required" type="password" id="password" name="password" /><dd>
		            <dt><label for="password2">Re-enter Password: *</label></dt>
		            <dd><input required="required" type="password" id="password2" name="password2" /></dd>
	            </dl>
            </fieldset>
            <input type="submit" name="Proceed" value="Cancel"/> <input type="submit" name="Proceed" value="Proceed"/>
        </form>
        <div style="clear:both;"></div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>