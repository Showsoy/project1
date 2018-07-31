﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
</style>
</head>
<link rel="stylesheet" type="text/css" href="../style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<script type="text/javascript" src="path/to/jQuery.js"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>
<section>
  <img class="mySlides" src="../images/slide1.jpg" style="width:100%">
  <img class="mySlides" src="../images/slide2.jpg" style="width:100%">
</section>
<script>
// Automatic Slideshow - change image every 3 seconds
var myIndex = 0;
carousel();
function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}
    x[myIndex-1].style.display = "block";
    setTimeout(carousel, 3000);
}
</script>
<br><br>
<!-- 베스트 상품 리스트 -->
<section class="w3-container w3-center" style="max-width:500px">
  <h2 class="w3-wide">BEST</h2>
  <p class="w3-opacity"><i>가장 사랑받은 제품들</i></p>
</section>

	<div id="container">
		<ul class="prod-list" align="center">
			<li><img src="../images/apple.jpg" alt="1">
			<p>dd</p>
				<div class="caption">
					<h1>사과</h1>
					<p>
						<br><a href="../items/detail.jsp">상품보러가기</a><br><a href="#">장바구니</a>
					</p>
				</div> 
			</li>
			<li><img src="../images/apple.jpg" alt="2">
			<p>dd</p>
			<div class="caption">
					<h1>ㅎㅎ</h1>
					<p>
						<br>견종 : 포메라니안<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						20만원<br>기타 사항 : 슬개골이 약함
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="3">
			<p>dd</p>
			<div class="caption">
					<h1>3번</h1>
					<p>
						<br>견종 : 말티즈<br>성별 : 여아<br>나이 : 2개월<br>분양가 :
						5만원<br>기타 사항 : 난폭함
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="4">
			<p>dd</p>
			<div class="caption">
					<h1>4번</h1>
					<p>
						<br>견종 : 믹스<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						5만원<br>기타 사항 : 말티즈+푸들 믹스
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="5">
			<div class="caption">
					<h1>5번</h1>
					<p>
						<br>견종 : 푸들<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						10만원<br>기타 사항 : 블랙 푸들
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="6">
			<div class="caption">
					<h1>6번</h1>
					<p>
						<br>견종 : 비숑프리제<br>성별 : 여아<br>나이 : 3개월<br>분양가 :
						25만원<br>기타 사항 : 활발함
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="7">
			<div class="caption">
					<h1>7번</h1>
					<p>
						<br>견종 : 크림 푸들<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						30만원<br>기타 사항 : 없음
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="8">
			<div class="caption">
					<h1>8번</h1>
					<p>
						<br>견종 : 크림 푸들<br>성별 : 여아<br>나이 : 1개월<br>분양가 :
						30만원<br>기타 사항 : 없음
					</p>
				</div></li>
		</ul>
	</div>


	<!-- 신메뉴 리스트 -->
<section class="w3-container w3-center" style="max-width:500px">
  <h2 class="w3-wide">NEW</h2>
  <p class="w3-opacity"><i>야심찬 새 상품</i></p>
</section>
<div id="container">
		<ul class="prod-list" align="center">
			<li><img src="../images/apple.jpg" alt="1">
				<div class="caption">
					<h1>1번</h1>
					<p>
						<br>견종 : 푸들<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						10만원<br>기타 사항 : 브라운 푸들
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="2">
			<div class="caption">
					<h1>2번</h1>
					<p>
						<br>견종 : 포메라니안<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						20만원<br>기타 사항 : 슬개골이 약함
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="3">
			<div class="caption">
					<h1>3번</h1>
					<p>
						<br>견종 : 말티즈<br>성별 : 여아<br>나이 : 2개월<br>분양가 :
						5만원<br>기타 사항 : 난폭함
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="4">
			<div class="caption">
					<h1>4번</h1>
					<p>
						<br>견종 : 믹스<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						5만원<br>기타 사항 : 말티즈+푸들 믹스
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="5">
			<div class="caption">
					<h1>5번</h1>
					<p>
						<br>견종 : 푸들<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						10만원<br>기타 사항 : 블랙 푸들
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="6">
			<div class="caption">
					<h1>6번</h1>
					<p>
						<br>견종 : 비숑프리제<br>성별 : 여아<br>나이 : 3개월<br>분양가 :
						25만원<br>기타 사항 : 활발함
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="7">
			<div class="caption">
					<h1>7번</h1>
					<p>
						<br>견종 : 크림 푸들<br>성별 : 남아<br>나이 : 2개월<br>분양가 :
						30만원<br>기타 사항 : 없음
					</p>
				</div></li>
			<li><img src="../images/apple.jpg" alt="8">
			<div class="caption">
					<h1>8번</h1>
					<p>
						<br>견종 : 크림 푸들<br>성별 : 여아<br>나이 : 1개월<br>분양가 :
						30만원<br>기타 사항 : 없음
					</p>
				</div></li>
		</ul>
	</div>

<br><br><br>
<!-- 농부 소개 -->
<section class="w3-row-padding w3-center w3-light-grey">
  <article class="w3-third">
    <p>슬기농부</p>
    <img src="../images/common.jpg" alt="Random Name">
    <p>슰기슬기</p>
  </article>
  <article class="w3-third">
    <p>Paul</p>
    <img src="../images/common1.jpg" alt="Random Name">
    <p>조이농부</p>
  </article>
  <article class="w3-third">
    <p>윤아농부</p>
    <img src="../images/common2.jpg" alt="Random Name">
    <p>유나우나</p>
  </article>
</section>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>