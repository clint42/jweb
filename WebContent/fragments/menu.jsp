<%@ page import="model.*" %>
<div class="header-bottom-left">
	<div class="logo">
		<a href="/jweb/"><img src="${pageContext.request.contextPath}/images/logo.png" alt=""/></a>
	</div>
	<div class="menu">
		<ul class="megamenu skyblue">
			<li class="active grid"><a href="/jweb/">Home</a></li>
			<li><a class="color4" href="#">Hard Drive</a></li>				
			<li><a class="color5" href="#">Software</a></li>
			<li><a class="color6" href="other.html">Who are we ?</a></li>
		</ul>
	</div>
</div>
<div class="header-bottom-right">
	<div class="search">	  
			<input type="text" name="s" class="textbox" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
			<input type="submit" value="Subscribe" id="submit" name="submit">
			<div id="response"> </div>
	</div>
  	<div class="tag-list">
    	<ul class="icon1 sub-icon1 profile_img">
			<li><a class="active-icon c1" href="#"> </a>
				<ul class="sub-icon1 list">
					<li><h3>sed diam nonummy</h3><a href=""></a></li>
					<li><p>Lorem ipsum dolor sit amet, consectetuer  <a href="">adipiscing elit, sed diam</a></p></li>
				</ul>
			</li>
		</ul>
		<ul class="icon1 sub-icon1 profile_img">
			<li><a class="active-icon c2" href="#"> </a>
				<ul class="sub-icon1 list">
					<li><h3>No Products</h3><a href=""></a></li>
					<li><p>Lorem ipsum dolor sit amet, consectetuer  <a href="">adipiscing elit, sed diam</a></p></li>
				</ul>
			</li>
		</ul>
    	<ul class="last"><li><a href="#">Cart(0)</a></li></ul>
	</div>
</div>
