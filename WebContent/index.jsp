<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.ArrayList, model.Product"%>
<%
	ArrayList<Product> products = new ArrayList<Product>();
if (request.getAttribute("products") instanceof ArrayList<?>) {
	products = (ArrayList<Product>)(request.getAttribute("products"));
}
%>
<!DOCTYPE HTML>
<html>
<head>
<title>Lacob | Home</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
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
<!--start slider -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/fwslider.css" media="all">
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/css3-mediaqueries.js"></script>
<script src="${pageContext.request.contextPath}/js/fwslider.js"></script>
<!--end slider -->
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
	<!-- start slider -->
	<div id="fwslider">
		<div class="slider_container">
			<div class="slide">
				<!-- Slide image -->
				<img src="${pageContext.request.contextPath}/images/banner1.png"
					alt="" />
				<!-- /Slide image -->
				<!-- Texts container -->
				<div class="slide_content">
					<div class="slide_content_wrap">
						<!-- Text title -->
						<h4 class="title">It's not a hardware problem</h4>
						<!-- /Text title -->

						<!-- Text description -->
						<p class="description">It's a bocal problem</p>
						<!-- /Text description -->
					</div>
				</div>
				<!-- /Texts container -->
			</div>
			<!-- /Duplicate to create more slides -->
			<div class="slide">
				<img src="${pageContext.request.contextPath}/images/banner2.png"
					alt="" />
				<div class="slide_content">
					<div class="slide_content_wrap">
						<h4 class="title">Want more than garbage ?</h4>
						<p class="description">Choose Lacob !</p>
					</div>
				</div>
			</div>
			<div class="slide">
				<img src="${pageContext.request.contextPath}/images/banner3.png"
					alt="" />
				<div class="slide_content">
					<div class="slide_content_wrap">
						<h4 class="title">Epitech compliant</h4>
						<p class="description">2 openSuse partitions + 1 windows 8
							partition</p>
					</div>
				</div>
			</div>
			<!--/slide -->
		</div>
		<div class="timers"></div>
		<div class="slidePrev">
			<span></span>
		</div>
		<div class="slideNext">
			<span></span>
		</div>
	</div>
	<!--/slider -->
	<div class="main">
		<div class="wrap">
			<div class="row">
					<%
						for (Product product: products) {
					%>
					<jsp:include page="/fragments/productBox.jsp">
						<jsp:param name="productId" value="<%=product.getId()%>" />
						<jsp:param name="productName" value="<%=product.getName()%>" />
						<jsp:param name="productPrice" value="<%=product.getPrice()%>" />
						<jsp:param name="productCreationDate"
							value="<%=product.getCreationDate()%>" />
						<jsp:param name="productNew" value="<%=product.isNew()%>" />
					</jsp:include>
					<%
						}
					%>
			</div>
			<!-- <div class="section group">
		  <div class="cont span_2_of_3">
		  	<h2 class="head">Hard Drive Products</h2>
			<div class="top-box">
			 <div class="col_1_of_3 span_1_of_3"> 
			   <a href="single.html">
				<div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/images/pic1.jpg" alt=""/>
					</div>
                    <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Hard Drive 7200 rpm</p>
							<div class="price1">
							  <span class="actual">59.99 &euro;</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                 </a>
				</div>
			   <div class="col_1_of_3 span_1_of_3">
			   	 <a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/images/pic2.jpg" alt=""/>
					</div>
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Hard Drive 7200 rpm</p>
							<div class="price1">
							  <span class="actual">69.99 &euro;</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
				  <div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/images/pic3.jpg" alt=""/>
					</div>
                    <div class="sale-box1"><span class="on_sale title_shop">Sale</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">Hard Drive 7200 rpm</p>
							<div class="price1">
							  <span class="reducedfrom">74.99 &euro;</span>
							  <span class="actual">70.00 &euro;</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="clear"></div>
			</div>	
		  <h2 class="head">SSD Products</h2>
		  <div class="top-box1">
			  <div class="col_1_of_3 span_1_of_3">
			  	 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/images/pic4.jpg" alt=""/>
					</div>
                     <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">SSD 128Go</p>
							<div class="price1">
							  <span class="actual">55.99 &euro;</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
					 <a href="single.html">
					<div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/images/pic5.jpg" alt=""/>
					</div>
				    <div class="price">
					   <div class="cart-left">
							<p class="title">SSD 256Go</p>
							<div class="price1">
							  <span class="actual">105.99 &euro;</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="col_1_of_3 span_1_of_3">
				 <a href="single.html">
				 <div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/images/pic6.jpg" alt=""/>
					</div>
                   	 <div class="sale-box"><span class="on_sale title_shop">New</span></div>	
                    <div class="price">
					   <div class="cart-left">
							<p class="title">SSD 512Go</p>
							<div class="price1">
							  <span class="actual">189.99 &euro;</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>				
                   </div>
                   </a>
				</div>
				<div class="clear"></div>
			</div>			 						 			    
		  </div>
		  -->
			<div class="rsidebar span_1_of_left">
				<div class="top-border"></div>
				<div class="top-border"></div>
				<div class="sidebar-bottom">
					<h2 class="m_1">
						Newsletters<br> Signup
					</h2>
					<p class="m_text">Enter your email address here to subscribe to our newsletters</p>
					<div class="subscribe">
						<form method="post" action="/jweb/Newsletter?action=subscribe">
							<input name="mail" type="text" class="textbox"> <input
								type="submit" value="Subscribe">
						</form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<jsp:include page="/fragments/footer.jsp" />
</body>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
