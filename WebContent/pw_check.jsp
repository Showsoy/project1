<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.mypage table{
	width:300px;
	margin:0 auto;
	text-align:center;
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
</style>
</head>
<script>
var chkId = false;
function chkForm(f){
	if(f.userPass.value.trim()==""){
		alert("비밀번호는 필수항목입니다.");
		f.userPass.focus();
		return false;
	}
	//비밀번호 db와 비교하기
	if(f.userPass.value.trim()!=""){
		if(f.userPass.value!=f.userPassre.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.userPassre.value="";
			f.userPassre.focus();
			return false;
		}
	}
	document.joinform.submit();
}
</script>
<link rel="stylesheet" type="text/css" href="style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<div class="header">
   <a href="main.jsp"><img src="images/farm_logo.png" width="150px"></a>
  <div>A website created by me.</div>
  <div class="right">
	  <a href="login.jsp">로그인</a>&nbsp;
	  <a href="joinForm.jsp">회원가입</a>&nbsp;
	  <a href="myPage.jsp">마이페이지</a>&nbsp;
	  <a href="myOrders.jsp">주문/배송</a>&nbsp;
	  <a href="cs_center.jsp">고객센터</a>&nbsp;
	  <a href="cart.jsp">장바구니</a>&nbsp;
  </div>
  
</div>   
<div class="navbar">
	<ul>
  <li><a href="#" id="menu"><img src="images/menu.png" width="24px" height="27px"/></a></li>
  <li class="dropdown">
    <a href="javascript:void(0)" class="dropbtn">&nbsp;&nbsp;팜팜소개&nbsp;&nbsp;</a>
    <div class="dropdown-content">
      <a href="introduction.jsp">농원소개</a>
      <a href="location.jsp">오시는 길</a>
      <a href="notice.jsp">안내사항</a>
    </div>
  </li>
  <li><a href="vegetable.jsp">&nbsp;&nbsp;&nbsp;&nbsp;채소&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
  <li><a href="fruit.jsp">&nbsp;&nbsp;&nbsp;&nbsp;과일&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
  <li><a href="grains.jsp">&nbsp;&nbsp;쌀/잡곡&nbsp;&nbsp;</a></li>
  <li><a href="tea.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;차&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
  <li><a href="mf.jsp">&nbsp;&nbsp;&nbsp;가공류&nbsp;&nbsp;&nbsp;</a></li>
  <li><a href="market.jsp" id="market" target="_blank">&nbsp;&nbsp;팜팜마켓&nbsp;&nbsp;</a></li>
  
</ul>
</div>

<div class="pageform">
	<h3>&nbsp;&nbsp;개인정보수정</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<form action="#" name="pwcheck" method="post" onsubmit="return chkForm(this)">
	<table cellspacing="0" cellpadding="0">
	<tr>
		<td id="td_left"><label for="userID">아이디</label> </td>
		<td></td>
	</tr>
	<tr>
		<td id="td_left"><label for="userPass">비밀번호</label> </td>
		<td><input type="password" id="userPass" name="userPass"></td>
	</tr>
</table>
<br><br>
			<button type="submit">확인</button> 
	
			<button onclick="location.href='main.jsp'">돌아가기</button> 
		</form>
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