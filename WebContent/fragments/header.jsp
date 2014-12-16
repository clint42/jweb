<%@ page import="model.*" %>
<div class="cssmenu">
	<ul>
		<%
		if ((session = (HttpSession)(request.getAttribute("session"))) != null && session.getAttribute("user") != null) {
			User user = (User)(session.getAttribute("user"));
			%>
			<li class="active"><a href="/jweb/User/Account"><%= user.getFirstName() %> <%= user.getLastName() %></a></li>
			<li><a href="/jweb/User/Account?action=logout">Logout</a></li>
			<li><a href="checkout.html">Wishlist</a></li>
			<li><a href="checkout.html">Checkout</a></li>
			<%
		}
		else {
			%>
			<li class="active"><a href="/jweb/User/Login">Sign in</a>
			<%
		}
		%>
	</ul>
</div>

