<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.HashMap" %>
<%
HashMap<String, String> formValues = new HashMap<String, String>();
if (request.getAttribute("formValues") != null && request.getAttribute("formValues") instanceof HashMap<?, ?>) {
	formValues = (HashMap<String, String>)(request.getAttribute("formValues"));
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
                    <h1 class="page-header">User informations</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
			<div class="row">
                <div class="col-lg-12">
					<form method="POST" role="form">
					<input type="hidden" name="edit" value="edit">
					<input type="hidden" name="id" value=<%= formValues.get("id") %>>
					<div class="form-group">
						<label for="username">Username</label>
						<input name="username" type="text" class="form-control" id="username" placeholder="username" value="<%= formValues.get("username") %>">
					</div>
					<div class="form-group">
						<label for="mail">Mail</label>
						<input name="mail" type="text" class="form-control" id="mail" placeholder="mail" value="<%= formValues.get("mail")%>">
					</div>
					<div class="form-group">
						<label for="firstName">Mail</label>
						<input name="firstName" type="text" class="form-control" id="firstName" placeholder="firstName" value="<%= formValues.get("firstName")%>">
					</div>
					<div class="form-group">
						<label for="lastName">Mail</label>
						<input name="lastName" type="text" class="form-control" id="lastName" placeholder="lastName" value="<%= formValues.get("lastName")%>">
					</div>
					<div class="form-group">
						<label for="address">Address</label>
						<input name="address" type="text" class="form-control" id="address" placeholder="address" value="<%= formValues.get("address")%>">
					</div>
					<div class="form-group">
						<label for="city">City</label>
						<input name="city" type="text" class="form-control" id="city" placeholder="city" value="<%= formValues.get("city")%>">
					</div>
					<div class="form-group">
						<label for="country">Country</label>
						<input name="country" type="text" class="form-control" id="country" placeholder="country" value="<%= formValues.get("country")%>">
					</div>
					<div class="form-group">
						<label for="zipcode">Zipcode</label>
						<input name="zipcode" type="text" class="form-control" id="zipcode" placeholder="zipcode" value="<%= formValues.get("zipcode")%>">
					</div>
					<input type="submit" name="publish" value="Save" class="btn btn-default">
					<a href="/jweb/Admin/Users/" class="btn btn-primary">Cancel</a>
				</form>
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