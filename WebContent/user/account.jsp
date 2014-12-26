<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.User"%>

<%
	User user_info = (User) (session.getAttribute("user"));
%>

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
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<script
	src="${pageContext.request.contextPath}/js/jquery.easydropdown.js"></script>
</head>
<body>
	<div class="header-top">
		<div class="wrap">
			<jsp:include page="/fragments/header.jsp" />
			<div class="clear"></div>
		</div>
	</div>
	<jsp:include page="/fragments/flashMessenger.jsp" />
	<div class="header-bottom">
		<div class="wrap">
			<jsp:include page="/fragments/menu.jsp" />
			<div class="clear"></div>
		</div>
	</div>
	<div class="register_account">
		<div class="wrap">
			<h4 class="title">Account information</h4>
			<%--     	      <p style="color:red"><%= request.getAttribute("error") %></p> --%>
			<form action="/jweb/User/Account?action=information"
				class="account_form" method="post">
				<div class="col_1_of_2 span_1_of_2">
					<h5 class="title">First name</h5>
					<div>
						<input type="text" name="First Name"
							value="<%=user_info.getFirstName()%>" disabled>
					</div>
					<h5 class="title">Last name</h5>
					<div>
						<input type="text" name="Last Name"
							value="<%=user_info.getLastName()%>" disabled>
					</div>
					<h5 class="title">Username</h5>
					<div>
						<input type="text" name="Username"
							value="<%=user_info.getUsername()%>" disabled>
					</div>
					<h5 class="title">E-Mail</h5>
					<div>
						<input type="text" name="E-Mail" value="<%=user_info.getMail()%>"
							disabled>
					</div>
					<h5 class="title">Address</h5>
					<div>
						<input type="text" name="Address"
							value="<%=user_info.getAddress()%>"
							onblur="if (this.value == '') {this.value = '<%=user_info.getAddress()%>';}">
					</div>
					<h5 class="title">Country</h5>
					<div>
						<div>
							<select id="country" name="country"
								onchange="change_country(this.value)" class="frm-field required">
								<option value="null">Select a Country</option>
								<option value="BE">Belgium</option>
								<option value="CA">Canada</option>
								<option value="CN">China</option>
								<option value="HR">Croatia (Hrvatska)</option>
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
							<script>
								var temp = "<%=user_info.getCountry()%>";
								var mySelect = document
										.getElementById('country');

								for (var i, j = 0; i = mySelect.options[j]; j++) {
									if (i.value == temp) {
										mySelect.selectedIndex = j;
										break;
									}
								}
							</script>
						</div>
					</div>
					<h5 class="title">City</h5>
					<div>
						<input type="text" name="City" value="<%=user_info.getCity()%>"
							onblur="if (this.value == '') {this.value = '<%=user_info.getCity()%>';}">
					</div>
					<h5 class="title">Zip Code</h5>
					<div>
						<input type="text" name="Zip Code"
							value="<%=user_info.getZipcode()%>"
							onblur="if (this.value == '') {this.value = '<%=user_info.getZipcode()%>';}">
					</div>
					<button class="grey">Submit</button>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<div class="register_account">
		<div class="wrap">
			<h4 class="title">Change Password</h4>
			<form action="/jweb/User/Account?action=password"
				class="account_form" method="post">
				<div class="col_1_of_2 span_1_of_2">
					<h5 class="title">Actual Password</h5>
					<div>
						<input type="text" name="Actual Password" value="">
					</div>
					<h5 class="title">New Password</h5>
					<div>
						<input type="text" name="New Password" value="">
					</div>
					<h5 class="title">Confirm New Password</h5>
					<div>
						<input type="text" name="Confirm New Password" value="">
					</div>
					<button class="grey">Submit</button>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<div class="register_account">
		<div class="wrap">
			<h4 class="title">Delete Account</h4>
			<form action="/jweb/User/Account?action=delete" class="account_form"
				method="post">
				<div class="col_1_of_2 span_1_of_2">
					<h5 class="title">Password</h5>
					<div>
						<input type="text" name="Password" value="">
					</div>
					<button class="grey">Submit</button>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<jsp:include page="/fragments/footer.jsp" />
</body>
</html>