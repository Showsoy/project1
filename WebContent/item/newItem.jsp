<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table{
	border:0;
}
th, td {
    border-bottom: 1px solid #ddd;
    height:50px;
}
</style>
</head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>

<c:if test="${todayImageMap!=null }">
<div class="recent_view">
		<h4>&nbsp;&nbsp;최근 본 상품</h4>
		<table>
		<tr>
			<c:forEach var="todayImage" items="${todayImageMap }" varStatus="status" begin="0" end="5" step="1">
				<td><a href="uitemView.im?item_code=${todayImage.key }"><img src="images/${todayImage.value }" id="todayImage"/></a></td>
			</c:forEach>
		</tr>
		</table>
</div>
</c:if>
<div class="pageform">
	<h3>&nbsp;&nbsp;NEW</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<div id="goods_container">
		<ul class="prod-list" align="center">
		<c:forEach var="newList" items="${newList }">
		<c:set var="price" value="${newList.price }"/>
		<c:set var="sale" value="${newList.sale }"/>
		<%
		int price = (int)pageContext.getAttribute("price");
		int sale = (int)pageContext.getAttribute("sale");
		double saled = 1-(double)sale/100;
		int uprice = (int)(price*saled);
		pageContext.setAttribute("uprice", uprice);
		%>
			<li>
				<a href="uitemView.im?item_code=${newList.item_code }">
				<img id="prod_image" src="images/${newList.img_path }"/></a>
				<div class="caption">
				<a href="uitemView.im?item_code=${newList.item_code }">
					<b>${newList.item_name }</b></a><br>
				<c:choose>
					<c:when test="${newList.sale==0 }">
						<fmt:formatNumber value="${newList.price }" type="number"/>원<br>
					</c:when>
					<c:otherwise>
						<span id="or_price"><fmt:formatNumber value="${newList.price }" type="number"/>원</span><br>
						<img src="images/sale.png" style="width:24px;height:15px;border:none;padding:0;margin:0;">
						<span id="new_price" style="line-height:5px;margin:0 auto;font-size:14px;">
						<fmt:formatNumber value="${uprice }" type="number"/>원(-${newList.sale }%)</span><br>
					</c:otherwise>
				</c:choose>	
				<c:choose>
				<c:when test="${newList.stock<=0 }">
					<font style="font-size:14px;color:red;">매진</font>
				</c:when>
				<c:otherwise>
					<a href="./item/addCart.ct?item_code=${newList.item_code }" style="color:black;paddin:0;font-size:13px;"><img src="images/cart.png"/> 장바구니</a>
				</c:otherwise>
				</c:choose>
				</div>
			</li>
		</c:forEach>
		</ul>
	
	</div>
</div>
</div>
<jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>