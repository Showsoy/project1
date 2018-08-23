﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.mypage td{
    border-bottom: 3px solid #fff;
}
#top_td{
	font-size:12px;
}
.re{
	margin:0 0 0 200px;
}
</style>
<script>
function fc_chk_byte(memo) { 
	var ari_max=600;
	var ls_str = memo.value;
	var li_str_len = ls_str.length;
	
	var li_max = ari_max;
	var i = 0;
	var li_byte = 0;
	var li_len = 0;
	var ls_one_char = "";
	var ls_str2 = "";
	
	for(i=0; i< li_str_len; i++) { 
		ls_one_char = ls_str.charAt(i); 
		
		if (escape(ls_one_char).length > 4) { 
		   li_byte += 2; 
		}else{
		   li_byte++; 
		} 
		if(li_byte <= li_max){ 
		   li_len = i + 1; 
		} 
	} 
	
	if(li_byte > li_max){ 
		alert("300글자 초과. \n 초과된 내용은 삭제 됩니다."); 
		ls_str2 = ls_str.substr(0, li_len); 
		memo.value = ls_str2; 
	} 
	memo.focus(); 
}

function fc_chk2() { 
	if(event.keyCode == 13) 
	event.returnValue=false; 
}
function goto_url(act) {
	document.csview.action = act;
	document.csview.submit();
}
</script>
</head>
<link rel="stylesheet" type="text/css" href="../style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>
<div class="pageform">
	<h3>&nbsp;&nbsp;고객센터</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<form method="post" name="csview">
			<table class="board_table" cellspacing="0" cellpadding="0">
				<tr>
					<td id="td_left" colspan="2" style="text-align:right;">
						<label>작성일</label> <span id="top_td">${board.date }</span>
						&nbsp;&nbsp;<label>답변상태</label>
						<span id="top_td"><c:choose>
							<c:when test="${board.has_re == 1 }">
									완료
							</c:when>
							<c:otherwise>
								미완
							</c:otherwise>
						</c:choose></span>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td id="td_parent" style="width:100px;">
						<label for="subject">제목</label>
					</td>
					<td id="td_parent">
						${board.subject }
					</td>
				</tr>
				<tr>
					<td id="td_parent">
						<label for="content">내용</label>
					</td>	
					<td id="td_parent">
						<c:if test="${board.img_path!=null }">
						<img src="../images/${board.img_path }"/>
						</c:if>
						<p>${board.content }</p>
					</td>
				</tr>
				<!-- id eq 'admin' 추가 -->
			<c:if test="${board.has_re == 1}">
					<tr>
						<td id="td_child">
							<label for="content">답변</label>
						</td>	
						<td id="td_child">
							${rboard.content }
						</td>
					</tr>
			</c:if>
			</table>
			<br>
			<section id="commandCell">
			<c:if test="${board.user_id eq id || id eq 'admin'}">
				<button type="button" id="wbutton" onclick="goto_url('csRemove.bo?bnum=${board.board_num}&page=${page }<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')">삭제</button>
			</c:if>
				<button type="button" id="wbutton" onclick="location.href='csList.bo?page=${page}<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}'">목록</button>
			</section>
			<br>
			<div class="re">
			<c:if test="${board.has_re == 0 && id eq 'admin'}">
				<table>
					<tr>
						<td id="td_left" colspan="2">
							<label for="subject">답변 글쓰기</label>
						</td>
					</tr>
					<tr>
						<td id="td_left">
							<label for="content">답변</label>
						</td>	
						<td>
							<textarea name="content" id="content" cols="60" rows="15" onkeyup="fc_chk_byte(this);" onkeypress="fc_chk2();"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span style="padding:0 0 0 610px;"><button type="button" id="wbutton" onclick="goto_url('csReply.bo?bnum=${board.board_num}&page=${page }<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')" style="width:80px;">답변하기</button></span>
						</td>
					</tr>
				</table>
			</c:if>
			</div>
			<br>
		</form>
	</div>	
</div>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>
