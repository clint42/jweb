<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8" import="model.News, java.util.ArrayList" %>
<%
ArrayList<News> news = new ArrayList<News>();
if (request.getAttribute("news") instanceof ArrayList<?>) {
	news = (ArrayList<News>)request.getAttribute("news");
}
%>
<!DOCTYPE HTML>
<html>
<head>
<title>Lacob | News</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.min.js"></script>
<!-- start menu -->
<link href="${pageContext.request.contextPath}/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<!--start slider -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fwslider.css" media="all">
    <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/css3-mediaqueries.js"></script>
    <script src="${pageContext.request.contextPath}/js/fwslider.js"></script>
<!--end slider -->
<script src="${pageContext.request.contextPath}/js/jquery.easydropdown.js"></script>
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
  
<div class="main">
	<div class="wrap">
		<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">News</h3>
		</div>
		<div class="panel-body">
		  	<%
		  	for (News newsUnit : news) {
		  		%>
		  		<jsp:include page="/fragments/newsLine.jsp">
		  			<jsp:param name="newsTitle" value="<%= newsUnit.getTitle() %>"/>
		  			<jsp:param name="newsContent" value="<%= newsUnit.getContent() %>"/>
		  			<jsp:param name="newsDate" value="<%= newsUnit.getCreatedDate() %>"/>
		  		</jsp:include>
		  		<%
		  	}
		  	%>
		</div>
		</div>
	</div>
	</div>
<jsp:include page="/fragments/footer.jsp" />
</body>
 <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
