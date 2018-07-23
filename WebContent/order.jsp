<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#order_info{
	margin:0 auto;
	width:700px;
}
.mypage table{
	width:700px;
	margin:0 auto;
	text-align:left;
	padding : 15px;
	border-radius:10px;
	border-collapse: collapse;
}
.mypage tr{
	padding-top: 12px;
    padding-bottom: 12px;
}
.mypage td{
	padding:5px 5px 5px 10px;
}
.mypage table td label{
	font-weight:700;
	font-family:"Nanum Gothic";
	color : #43A047;
	font-size:14px;
}
td, tr{
	border: 1px solid #ddd;
}
.mypage button {
	font-family:"Nanum Gothic";
	font-weight: 700;
	text-transform: uppercase;
	outline: 0;
	background: black;
	width: 100px;
	border: 0;
	padding: 10px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}
.mypage button:hover, .mypage button:active, .mypage button:focus {
	background: #191919;
}
#td_left{
	background-color:#F6F6F6;
	width:100px;
}
#order_top_menu{
	background-color:#F6F6F6;
	text-weight:700;
}
</style>
</head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="top_menu.jsp" flush="false"/>
<div class="pageform">
	<h3>&nbsp;&nbsp;결제</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<div id="order_info">
		<div id="grade"><b id="grade_deco">|</b>결제상품</div>
		<table cellspacing="0" cellpadding="0">
	<tr id="order_top_menu">
		<td>상품명</td>
		<td>가격</td>
		<td>수량</td>
		<td>할인</td>
	</tr>
	<tr>
		<td>청송사과 1kg</td>
		<td>1111</td>
		<td>2</td>
		<td>-22</td>
	</tr>
	<tr>
		<td>밀양 얼음골 사과 2kg</td>
		<td>2222</td>
		<td>1</td>
		<td>0</td>
	</tr>
	</table>
	<p class="right"><font size="3em">총 &nbsp;4444 원</font><br>
	<font size="2em">- 할인 금액 <font color="red">22</font>원</font><br><br>
	<font size="4em"><b>총 결제금액 <b id="grade_deco">4422</b>원</b></font></p>
	</div>
	<br>
	<div id="grade"><b id="grade_deco">|</b>배송지 정보</div>
	<table cellspacing="0" cellpadding="0">
	<tr>
		<td id="td_left"><label for="userID">이름</label></td>
		<td>\</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">연락처</label></td>
		<td>\</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">이메일</label></td>
		<td>\</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">주소</label></td>
		<td rowspan="3">\<br>/<br>/</td>
	</tr>
</table>
<br><br>
			<button onclick="location.href='error.jsp'">결제</button> 
	
			<button onclick="location.href='main.jsp'">돌아가기</button> 
		
	</div>
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
