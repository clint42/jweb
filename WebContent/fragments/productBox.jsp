<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Product" %>

	<div class="col_1_of_3 span_1_of_3"> 
		<a href="/jweb/Product/<%= request.getParameter("productId") %>">
		<div class="inner_content clearfix">
			<div class="product-image">
				<img src="${pageContext.request.contextPath}/images/pic1.jpg" alt=""/>
			</div>
	 		<div class="sale-box">
		 		<% if (request.getParameter("productNew").toLowerCase().equals("true")) { %>
			 		<span class="on_sale title_shop">New</span>
		 		<% } %>
			</div>	
			<div class="price">
		   		<div class="cart-left">
					<p class="title"><%= request.getParameter("productName") %></p>
					<div class="price1">
				  		<span class="actual"><%= request.getParameter("productPrice") %> &euro;</span>
					</div>
				</div>
				<div class="cart-right"> </div>
			</div>
		</div>
		</a>
	</div>
