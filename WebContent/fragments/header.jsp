<%@ page import="model.*, java.util.ArrayList" %>
<div class="cssmenu">
	<ul>
		<%
		if (session != null && session.getAttribute("user") != null) {
			User user = (User)(session.getAttribute("user"));
			%>
			<li class="active"><a href="/jweb/User/Account"><%= user.getFirstName() %> <%= user.getLastName() %></a></li>
			<li><a href="/jweb/User/Account?action=logout">Logout</a></li>
			<%
			if (user.getRole().equals("A")) {
				%>
					<li><a href="/jweb/Admin/Home">Administration</a>
				<%
			}
			%>
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

