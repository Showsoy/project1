<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
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
#gbutton {
	font-family:"Nanum Gothic";
	font-weight: 700;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 70px;
	border: 0;
	padding: 5px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}
#gbutton:hover, #gbutton:active, #gbutton:focus {
	background: #43A047;
}
#td_left{
	background-color:#F6F6F6;
	width:100px;
}
</style>
<script>
var chkCode = false;
function chkForm(f){
	var price = f.price.value;
	var sale = f.sale.value;
	
	var reg_price = /^[0-9a-zA-Z]+$/;
	var reg_sale = /^[0-9a-zA-Z]+$/;
	
	if(!chkCode){
		alert("코드를 생성해주세요!");
		return false;
	}
	if(f.item_name.value.trim()==""){
		alert("상품이름은 필수항목입니다.");
		f.item_name.focus();
		return false;
	}
	if (!reg_price.test(price)) {
		alert("가격은 숫자만 입력가능합니다.");
		f.price.focus();
		return false;
	}
	if (!reg_sale.test(sale)) {
		alert("할인은 숫자만 입력가능합니다.");
		f.sale.focus();
		return false;
	}else if(reg_sale.test(sale)){
		if(sale<0||sale>100){
			alert("할인은 1~100까지의 숫자만 가능합니다.");
			f.sale.focus();
			return false;
		}
	}
	document.joinform.submit();
}
function selCategory(sel) {
	chkCode = false;
	document.getElementById("item_code").value="";
}
</script>
</head>
<link rel="stylesheet" type="text/css" href="../style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>
<div class="pageform">
	<h3>&nbsp;&nbsp;상품등록</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<form action="itemRegistPro.im" method="post" enctype="multipart/form-data" name="itemnew" onsubmit="return chkForm(this)">
			<table>
				<tr>
					<td id="td_left">
						<label for="item_name">이름</label>
					</td>
					<td id="td_right">
						<input type="text" name="item_name" id="item_name" size="20"/>
					</td>
					<td id="td_left">
						<label for="category">분류</label>
					</td>
					<td id="td_right">
						<select name="category" id="category" onchange="selCategory(this)">
							<option value="" selected>선택</option>
							<option value="채소">채소</option>
							<option value="과일">과일</option>
							<option value="곡류">곡류</option>
							<option value="차">차</option>
							<option value="가공">가공</option>
						</select>
					</td>
				</tr>
				<tr>
					<td id="td_left">
						<label for="price">가격</label>
					</td>
					<td id="td_right">
						<input type="text" name="price" id="price" size="10"/>원
					</td>
					<td id="td_left">
						<label for="item_code">코드</label>
					</td>
					<td id="codegen">
						<input type="text" name="item_code" id="item_code" size="4"/>&nbsp;
						<button type="button" name="check" id="check" onclick="window.open('./codeGen.jsp?openInit=true','','width=370, height=220')" id="gbutton">자동생성</button>
					</td>
				</tr>
				<tr>
					<td id="td_left">
						<label for="origin">원산지</label>
					</td>
					<td id="td_right">
						<input type="text" name="origin" id="origin" />
					</td>
					<td id="td_left">
						<label for="sale">할인율</label>
					</td>
					<td id="td_right">
						<input type="text" name="sale" id="sale" value="0" size="10"/>%
					</td>
				</tr>
				<tr>
					<td id="td_left">
						<label for="content">내용</label>
					</td>	
					<td colspan="3">
						<textarea name="content" id="content" cols="60" rows="15"></textarea>
					</td>
				</tr>
				<tr>
					<td id="td_left">
						<label for="img_path">파일 첨부</label>
					</td>
					<td id="td_right" colspan="3">
						<input type="file" name="img_path" id="img_path"/>
					</td>
				</tr>
			</table>
			<br>
			<section id="commandCell">
				<button type="submit">등록</button>
				<button type="button" onclick="location.href='adminPage.jsp'" style="width:120px;">관리자 페이지</button>
			</section>
		</form>
	</div>	
</div>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>
