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
			<form action="/jweb/User/Account?action=information" class="account_form" method="post">
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
							onblur="if (this.value == '') {this.value = '<%=user_info.getMail()%>';}">
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
			<form action="/jweb/User/Account?action=password" class="account_form" method="post">
				<div class="col_1_of_2 span_1_of_2">
					<h5 class="title">Actual Password</h5>
					<div>
						<input type="text" name="Actual Password"
							value="" >
					</div>
					<h5 class="title">New Password</h5>
					<div>
						<input type="text" name="New Password"
							value="" >
					</div>
					<h5 class="title">Confirm New Password</h5>
					<div>
						<input type="text" name="Confirm New Password"
							value="" >					</div>
					<button class="grey">Submit</button>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<div class="register_account">
		<div class="wrap">
			<h4 class="title">Delete Account</h4>
			<form action="/jweb/User/Account?action=delete" class="account_form" method="post">
				<div class="col_1_of_2 span_1_of_2">
					<h5 class="title">Password</h5>
					<div>
						<input type="text" name="Actual Password"
							value=""
							onfocus="if (this.value == 'Password') {this.value = ''; this.type='password'}; "
							onblur="if (this.value == '') {this.type='text'; this.value = 'Password';}">
					</div>
					<button class="grey">Submit</button>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<div class="footer">
		<div class="footer-top">
			<div class="wrap">
				<div class="section group example">
					<div class="col_1_of_2 span_1_of_2">
						<ul class="f-list">
							<li><img src="images/2.png"><span class="f-text">Free
									Shipping on orders over $ 100</span>
								<div class="clear"></div></li>
						</ul>
					</div>
					<div class="col_1_of_2 span_1_of_2">
						<ul class="f-list">
							<li><img src="images/3.png"><span class="f-text">Call
									us! toll free-01 40 01 08 44 </span>
								<div class="clear"></div></li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="footer-middle">
			<div class="wrap">
				<div class="section group example">
					<div class="col_1_of_f_1 span_1_of_f_1">
						<div class="section group example">
							<div class="col_1_of_f_2 span_1_of_f_2">
								<h3>Facebook</h3>
								<script>
									(function(d, s, id) {
										var js, fjs = d.getElementsByTagName(s)[0];
										if (d.getElementById(id))
											return;
										js = d.createElement(s);
										js.id = id;
										js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
										fjs.parentNode.insertBefore(js, fjs);
									}(document, 'script', 'facebook-jssdk'));
								</script>
								<div class="like_box">
									<div class="fb-like-box"
										data-href="http://www.facebook.com/w3layouts"
										data-colorscheme="light" data-show-faces="true"
										data-header="true" data-stream="false" data-show-border="true"></div>
								</div>
							</div>
							<div class="col_1_of_f_2 span_1_of_f_2">
								<h3>From Twitter</h3>
								<div class="recent-tweet">
									<div class="recent-tweet-icon">
										<span> </span>
									</div>
									<div class="recent-tweet-info">
										<p>
											Ds which don't look even slightly believable. If you are <a
												href="#">going to use nibh euismod</a> tincidunt ut laoreet
											adipisicing
										</p>
									</div>
									<div class="clear"></div>
								</div>
								<div class="recent-tweet">
									<div class="recent-tweet-icon">
										<span> </span>
									</div>
									<div class="recent-tweet-info">
										<p>
											Ds which don't look even slightly believable. If you are <a
												href="#">going to use nibh euismod</a> tincidunt ut laoreet
											adipisicing
										</p>
									</div>
									<div class="clear"></div>
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="col_1_of_f_1 span_1_of_f_1">
						<div class="section group example">
							<div class="col_1_of_f_2 span_1_of_f_2">
								<h3>Information</h3>
								<ul class="f-list1">
									<li><a href="#">Duis autem vel eum iriure </a></li>
									<li><a href="#">anteposuerit litterarum formas </a></li>
									<li><a href="#">Tduis dolore te feugait nulla</a></li>
									<li><a href="#">Duis autem vel eum iriure </a></li>
									<li><a href="#">anteposuerit litterarum formas </a></li>
									<li><a href="#">Tduis dolore te feugait nulla</a></li>
								</ul>
							</div>
							<div class="col_1_of_f_2 span_1_of_f_2">
								<h3>Contact us</h3>
								<div class="company_address">
									<p>500 Lorem Ipsum Dolor Sit,</p>
									<p>22-56-2-9 Sit Amet, Lorem,</p>
									<p>USA</p>
									<p>Phone:(00) 222 666 444</p>
									<p>Fax: (000) 000 00 00 0</p>
									<p>
										Email: <span>mail[at]leoshop.com</span>
									</p>

								</div>
								<div class="social-media">
									<ul>
										<li><span class="simptip-position-bottom simptip-movable"
											data-tooltip="Google"><a href="#" target="_blank">
											</a></span></li>
										<li><span class="simptip-position-bottom simptip-movable"
											data-tooltip="Linked in"><a href="#" target="_blank">
											</a> </span></li>
										<li><span class="simptip-position-bottom simptip-movable"
											data-tooltip="Rss"><a href="#" target="_blank"> </a></span></li>
										<li><span class="simptip-position-bottom simptip-movable"
											data-tooltip="Facebook"><a href="#" target="_blank">
											</a></span></li>
									</ul>
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="footer-bottom">
			<div class="wrap">
				<div class="copy">
					<p>
						© 2014 Template by <a href="http://w3layouts.com" target="_blank">w3layouts</a>
					</p>
				</div>
				<div class="f-list2">
					<ul>
						<li class="active"><a href="about.html">About Us</a></li> |
						<li><a href="delivery.html">Delivery & Returns</a></li> |
						<li><a href="delivery.html">Terms & Conditions</a></li> |
						<li><a href="contact.html">Contact Us</a></li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>