<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panelTitle"><%= request.getParameter("newsTitle") %></h3>
	</div>
	<div class="panel-body">
		<%= request.getParameter("newsContent") %>
	</div>
	<div class="panel-footer">
		Posted: <%= request.getParameter("newsDate") %>
	</div>
</div>