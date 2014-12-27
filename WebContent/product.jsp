<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8" import="model.Product, model.Review, java.util.ArrayList" %>
<%
Product product = new Product();
if (request.getAttribute("product") instanceof Product) {
	product = (Product)(request.getAttribute("product"));
}
%>
<!DOCTYPE HTML>
<html>
<head>
<title>Lacob | <%= product.getName() %></title>
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
			<h3 class="panel-title" style="text-transform: uppercase;"><%= product.getName() %></h3><br>
		</div>
		<div class="panel-body">
			<div style="text-align:center">
				<img src="${pageContext.request.contextPath}/images/pic1.jpg"/>
			</div>
			<p>
			<%= product.getDescription() %>
			</p>
			<p>
			Items left: <%= product.getQuantity() %>
			</p>
			<p>Product added to our list on : <%= product.getCreationDate() %></p>
		</div>
	</div>
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">Users reviews</h3>
		</div>
		<div class="panel-body">
		<% 
		for (Review review: product.getReviews()) { %>
			<jsp:include page="/fragments/reviewLine.jsp">
				<jsp:param name="userName" value="<%= review.getUserFirstName() + \" \" + review.getUserLastName() %>"/>
				<jsp:param name="reviewTitle" value="<%= review.getTitle() %>"/>
				<jsp:param name="reviewText" value="<%= review.getText() %>"/>
				<jsp:param name="reviewRank" value="<%= review.getRank() %>"/>
				<jsp:param name="reviewDate" value="<%= review.getCreationDate() %>"/>
			</jsp:include>
		<% 
		}
		%>
		<%
		if (session.getAttribute("user") != null) {
			%>
			<a href="/jweb/User/Review/<%= product.getId() %>" class="btn btn-info">Post a review</a>
			<%
		}
		else {
		%>
		<a href="/jweb/User/Login" class="btn btn-default">Sign in to post a review</a>
		<%
		}
		%>
		</div>
		</div>
	</div>
   <div class="footer">
		<div class="footer-top">
			<div class="wrap">
			  <div class="section group example">
				<div class="col_1_of_2 span_1_of_2">
					<ul class="f-list">
					  <li><img src="${pageContext.request.contextPath}/images/2.png"><span class="f-text">Free Shipping on orders over $ 100</span><div class="clear"></div></li>
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
						<script>(function(d, s, id) {
						  var js, fjs = d.getElementsByTagName(s)[0];
						  if (d.getElementById(id)) return;
						  js = d.createElement(s); js.id = id;
						  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
						  fjs.parentNode.insertBefore(js, fjs);
						}(document, 'script', 'facebook-jssdk'));</script>
						<div class="like_box">	
							<div class="fb-like-box" data-href="http://www.facebook.com/w3layouts" data-colorscheme="light" data-show-faces="true" data-header="true" data-stream="false" data-show-border="true"></div>
						</div>
 				  </div>
				  <div class="col_1_of_f_2 span_1_of_f_2">
						<h3>From Twitter</h3>
				       <div class="recent-tweet">
							<div class="recent-tweet-icon">
								<span> </span>
							</div>
							<div class="recent-tweet-info">
								<p>Ds which don't look even slightly believable. If you are <a href="#">going to use nibh euismod</a> tincidunt ut laoreet adipisicing</p>
							</div>
							<div class="clear"> </div>
					   </div>
					   <div class="recent-tweet">
							<div class="recent-tweet-icon">
								<span> </span>
							</div>
							<div class="recent-tweet-info">
								<p>Ds which don't look even slightly believable. If you are <a href="#">going to use nibh euismod</a> tincidunt ut laoreet adipisicing</p>
							</div>
							<div class="clear"> </div>
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
							   		<p>Rue Jean Jaures - Villejuif</p>
							   		<p>France</p>
					   		<p>Phone: 06 18 01 83 96</p>
					 	 	<p>Email: <span>aurelien.prieur@epitech.eu</span></p>
					   		
					   </div>
					   <div class="social-media">
						     <ul>
						        <li> <span class="simptip-position-bottom simptip-movable" data-tooltip="Google"><a href="#" target="_blank"> </a></span></li>
						        <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Linked in"><a href="#" target="_blank"> </a> </span></li>
						        <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Rss"><a href="#" target="_blank"> </a></span></li>
						        <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Facebook"><a href="#" target="_blank"> </a></span></li>
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
			        <p>ÃÂ© 2014 Template by <a href="http://w3layouts.com" target="_blank">w3layouts</a></p>
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
 <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
