<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8" import="model.Review" %>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title"><%= request.getParameter("userName") %> - <%= request.getParameter("reviewTitle") %></h3>
	</div>
	<div class="panel-body">
		Rank: <%= request.getParameter("reviewRank") %> / 5<br>
		<%= request.getParameter("reviewText") %>
	</div>
	<div class="panel-footer">
		Posted: <%= request.getParameter("reviewDate") %>
	</div>
</div>
