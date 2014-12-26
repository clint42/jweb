<!DOCTYPE HTML>
<html>
<head>
<title>Lacob | Register</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Exo+2'
	rel='stylesheet' type='text/css'>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery1.min.js"></script>
<!-- start menu -->
<link href="${pageContext.request.contextPath}/css/megamenu.css"
	rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script
	src="${pageContext.request.contextPath}/js/jquery.easydropdown.js"></script>
</head>
<body> 
     <div class="header-top">
	   <div class="wrap">
			<jsp:include page="/fragments/header.jsp"/>
			<div class="clear"></div>
 		</div>
	</div>
	<jsp:include page="/fragments/flashMessenger.jsp"/>
	 <div class="header-bottom">
	    <div class="wrap">
	    <jsp:include page="/fragments/menu.jsp"/>
     <div class="clear"></div>
     </div>
	</div>
          <div class="register_account">
          	<div class="wrap">
    	      <h4 class="title">Create an Account</h4>
<%--     	      <p style="color:red"><%= request.getAttribute("error") %></p> --%>
			<form method="post">
				<div class="col_1_of_2 span_1_of_2">
					<div><input type="text" name="First Name" value="First Name" onfocus="if (this.value == 'First Name') {this.value = '';}" onblur="if (this.value == '') {this.value = 'First Name';}"></div>
					<div><input type="text" name="Last Name" value="Last Name" onfocus="if (this.value == 'Last Name') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Last Name';}"></div>
					<div><input type="text" name="Username" value="Username" onfocus="if (this.value == 'Username') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Username';}"></div>
					<div><input type="text" name="Password" value="Password" onfocus="if (this.value == 'Password') {this.value = ''; this.type='password'}; "  onblur="if (this.value == '') {this.type='text'; this.value = 'Password';}"></div>
					<div><input type="text" name="Confirm Password" value="Confirm Password" onfocus="if (this.value == 'Confirm Password') {this.value = ''; this.type='password'}; "  onblur="if (this.value == '') {this.type='text'; this.value = 'Confirm Password';}"></div>
				</div>
				<div class="col_1_of_2 span_1_of_2">	
		    		<div><input type="text" name="E-Mail" value="E-Mail" onfocus="if (this.value == 'E-Mail') {this.value = '';}" onblur="if (this.value == '') {this.value = 'E-Mail';}"></div>
		    		<div><input type="text" name="Address" value="Address" onfocus="if (this.value == 'Address') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Address';}"></div>
		    		<div><select id="country" name="country" onchange="change_country(this.value)" class="frm-field required">
		            <option value="null">Select a Country</option>         
		            <option value="BE">Belgium</option>
		            <option value="CA">Canada</option>
		            <option value="CN">China</option>
		            <option value="HR">Croatia (Hrvatska)</option>
		            <option value="CU">Cuba</option>
		            <option value="CY">Cyprus</option>
		            <option value="CZ">Czech Republic</option>
		            <option value="DK">Denmark</option>
		            <option value="DO">Dominican Republic</option>
		            <option value="EG">Egypt</option>
		            <option value="FI">Finland</option>
		            <option value="FR">France</option>
		            <option value="FX">France, Metropolitan</option>
		            <option value="GF">French Guiana</option>
		            <option value="PF">French Polynesia</option>
		            <option value="TF">French Southern Territories</option>
		            <option value="DE">Germany</option>
		            <option value="GR">Greece</option>
		            <option value="GP">Guadeloupe</option>
		            <option value="HU">Hungary</option>
		            <option value="IS">Iceland</option>
		            <option value="IN">India</option>
		            <option value="ID">Indonesia</option>
		            <option value="IE">Ireland</option>
		            <option value="IL">Israel</option>
		            <option value="IT">Italy</option>
		            <option value="JM">Jamaica</option>
		            <option value="JP">Japan</option>
		            <option value="KR">Korea</option>
		            <option value="KP">Korea, North</option>
		            <option value="LY">Libya</option>
		            <option value="LI">Liechtenstein</option>
		            <option value="LT">Lithuania</option>
		            <option value="LU">Luxembourg</option>
		            <option value="MG">Madagascar</option>
		            <option value="MQ">Martinique</option>
		            <option value="MR">Mauritania</option>
		            <option value="MC">Monaco</option>
		            <option value="ME">Montenegro</option>
		            <option value="MS">Montserrat</option>
		            <option value="MA">Morocco</option>
		            <option value="NL">Netherlands</option>
		            <option value="AN">Netherlands Antilles</option>
		            <option value="NC">New Caledonia</option>
		            <option value="NZ">New Zealand</option>
		            <option value="NO">Norway</option>
		            <option value="PT">Portugal</option>
		            <option value="PR">Puerto Rico</option>
		            <option value="QA">Qatar</option>
		            <option value="RE">Reunion</option>
		            <option value="RO">Romania</option>
		            <option value="RU">Russia</option>
		            <option value="SN">Senegal</option>
		         </select>
		         </div>		        
		          <div><input type="text" name="City" value="City" onfocus="if (this.value == 'City') {this.value = '';}" onblur="if (this.value == '') {this.value = 'City';}"></div>
		          <div><input type="text" name="Zip Code" value="Zip Code" onfocus="if (this.value == 'Zip Code') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Zip Code';}"></div>
		          </div>
		      <button class="grey">Submit</button>
		    <div class="clear"></div>
		    </form>
    	</div>
    </div>
    <jsp:include page="/fragments/footer.jsp" />
</body>
</html>