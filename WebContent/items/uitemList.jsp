<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*
String cookie = request.getHeader("Cookie");
String key="";
String value="";

if(cookie!=null){
	Cookie cookies[] = request.getCookies();
	
	for(int i=0;i<cookies.length;i++){
		key = cookies[i].getName();
		value=cookies[i].getValue();
		System.out.println("<h2>쿠키 이름 : "+key+"</h2>");
		System.out.println("<h2>쿠키 값 : "+value+"</h2>");
	}
}*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
footer {
    padding: 24px; /* some padding */
    text-align:center;
    background: #333; /* green background */
    color:white;/* white text color */
}
table{
	border:0;
}
th, td {
    border-bottom: 1px solid #ddd;
    height:50px;
}
.page{
	margin:0 auto;
	text-align:center;
}
#orderby{
	width:800px;
	text-align:right;
	font-size:14px;
}
</style>
</head>
<link rel="stylesheet" type="text/css" href="../style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>

<c:if test="${todayImageList!=null }">
<div class="recent_view">
		<h4>&nbsp;&nbsp;최근 본 상품</h4>
		<table>
		<tr>
			<c:forEach var="todayImage" items="${todayImageList }" varStatus="status" begin="0" end="5" step="1">
				<td><img src="../images/${todayImage }" id="todayImage"/></td>
			</c:forEach>
		</tr>
		</table>
</div>
</c:if>
<div class="pageform">
	<h3>&nbsp;&nbsp;${category }</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<c:choose>
		<c:when test="${pageInfo.listCount>0 }">
		<span id="orderby">
			<a href="uitemList.im?category=${category }&std=new">신상품순</a>
			<a href="uitemList.im?category=${category }&std=high">가격높은순</a>
			<a href="uitemList.im?category=${category }&std=low">가격낮은순</a>
			<a href="uitemList.im?category=${category }&std=pc">판매량순</a>
			<a href="uitemList.im?category=${category }&std=rc">인기순</a>
		</span>
	<div id="goods_container">
		<ul class="prod-list" align="center">
		<c:forEach var="itemList" items="${itemList }">
		<c:set var="price" value="${itemList.price }"/>
		<c:set var="sale" value="${itemList.sale }"/>
		<%
		int price = (int)pageContext.getAttribute("price");
		int sale = (int)pageContext.getAttribute("sale");
		double saled = 1-(double)sale/100;
		int uprice = (int)(price*saled);
		pageContext.setAttribute("uprice", uprice);
		%>
			<li>
				<a href="uitemView.im?item_code=${itemList.item_code }">
				<img src="../images/${itemList.img_path }"/></a>
				<div class="caption">
					${itemList.item_name }<br>
					${itemList.price }원<br>
					${itemList.sale }% ${uprice }원
				</div>
			</li>
		</c:forEach>
		</ul>
	
	</div>
	<div class="page">
		<c:if test="${pageInfo.page<=1 }">
			[이전]&nbsp;
			</c:if>
			<c:if test="${pageInfo.page>1 }">
				<a href="itemList.im?page=${pageInfo.page-1}">[이전]</a>&nbsp;
			</c:if>
					
			<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
				<c:choose>
					<c:when test="${a==pageInfo.page }">
						[${a }]
					</c:when>
					<c:otherwise>
						<a href="itemList.im?page=${a }">[${a }]</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
				<c:choose>
					<c:when test="${pageInfo.page>=pageInfo.maxPage }">
						[다음]
					</c:when>
					<c:otherwise>
						<a href="itemList.im?page=${pageInfo.page+1 }">[다음]</a>
					</c:otherwise>
				</c:choose>
	</div>
		</c:when>
		<c:otherwise>
			상품이 존재하지 않습니다.
		</c:otherwise>
	</c:choose>
</div>
</div>
<footer>
  <a href="#"><i class="fa fa-facebook-official"></i></a>
  <a href="#"><i class="fa fa-pinterest-p"></i></a>
  <a href="#"><i class="fa fa-twitter"></i></a>
  <a href="#"><i class="fa fa-flickr"></i></a>
  <a href="#"><i class="fa fa-linkedin"></i></a>
  <p class="w3-medium">
    Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a>
  </p>
</footer>
</body>
</html>
