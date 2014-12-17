<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList, model.News" %>
<%
ArrayList<News> newsArray = new ArrayList<News>();
if (request.getAttribute("newsArray") != null && request.getAttribute("newsArray") instanceof ArrayList<?>) {
	newsArray = (ArrayList<News>)(request.getAttribute("newsArray"));
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Lacob - Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/css/plugins/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div id="wrapper">
		<jsp:include page="/admin/fragments/nav.jsp"/>
		
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<jsp:include page="/fragments/flashMessenger.jsp"/>
				</div>
			</div>
			<div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">All news</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<div class="row">
                <div class="col-lg-12">
					<%
					if (newsArray.size() > 0) {
					%>
					<table class="table">
						<thead>
						<tr>
							<th>Title</th>
							<th>Content</th>
							<th>Created By</th>
							<th>Created Date</th>
							<th>Actions</th>
						</tr>
						</thead>
						
						<tbody>
							<%
							for (News news : newsArray) {
								%>
								<tr>
								<td><%= news.getTitle() %></td>
								<td><%= news.getContent() %></td>
								<td><%= news.getCreatedBy() %></td>
								<td><%= news.getCreatedDate() %></td>
								<td>
								<div class="btn-group" role="group">
								<a href="/jweb/Admin/News/Edit/<%= news.getId() %>" title="Edit this news" class="btn btn-small btn-primary"><span class="fa fa-edit"></span></a>
								<a href="/jweb/Admin/News/Delete/<%= news.getId() %>" title="Delete this news" class="btn btn-small btn-danger"><span class="fa fa-remove"></span></a>
								</div>
								</td>
								</tr>
								<%
							}
							%>
						</tbody>
					</table>
					<%
					}
					else {
					%>
					<p style="color:red">No news</p>
					<%
					}
					%>
			</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/js/plugins/metisMenu/metisMenu.min.js"></script>
    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.js"></script>

</body>

</html>