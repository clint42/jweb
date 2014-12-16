<%@ page import="java.util.ArrayList" %>
<% 
	ArrayList<String> errors = null;
	ArrayList<String> successes = null;
	if (session.getAttribute("errorsMsg") instanceof ArrayList<?>) {
		//@SuppressWarnings("unchecked")
		errors = (ArrayList<String>)(session.getAttribute("errorsMsg"));
	}
	if (session.getAttribute("successMsg") instanceof ArrayList<?>) {
		successes = (ArrayList<String>)(session.getAttribute("successMsg"));
	}
	%>
	<div style="text-align:center">
		<p style="color:red">
		<%
		if (errors != null) {
			for (String error : errors) {
				out.println(error + "<br>");
			}
			errors.clear();
		}
		%>
		</p>
		<p style="color:green">
		<%
		if (successes != null) {
			for (String success : successes) {
				out.println(success + "<br>");
			}
			if (successes != null) {
				successes.clear();
			}
		}
		%>
		</p>
	</div>