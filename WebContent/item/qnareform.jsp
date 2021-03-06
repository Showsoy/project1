<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::답변 작성:::</title>
<style>
</style>
<script>
function fc_chk_byte(memo) { 
	var ari_max=400;
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
		alert("200글자 초과. \n 초과된 내용은 삭제 됩니다."); 
		ls_str2 = ls_str.substr(0, li_len); 
		memo.value = ls_str2; 
	} 
	memo.focus(); 
}

function fc_chk2() { 
	if(event.keyCode == 13) 
	event.returnValue=false; 
}
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}
function chkForm(f){

	if(f.subject.value.trim()==""){
		alert("제목을 입력해주세요.");
		f.subject.focus();
		return false;
	}
	if(doubleSubmitCheck()) return false;
	document.reviewform.submit();
}
function fclose(f){
	opener.location.reload();
	window.close();
}
</script>
</head>
<link rel="stylesheet" type="text/css" href="../style/style.css">
<body class="popup_body">
<div id="grade"><b id="grade_deco">|</b> 문의답글</div>
<form action="qnaReply.bo" name="reviewform" method="post" onsubmit="return chkForm(this)">
<c:choose>
	<c:when test="${act != null && act eq 'ok' }">
		<img src="../images/celebration.png">
		<p>답글이 정상적으로 등록되었습니다.</p>
		<button type="button" onclick="fclose(this)" id="wbutton">닫기</button>
	</c:when>
	<c:when test="${id == null || (act != null && act eq 'login')}">
		<script>
			alert('로그인이 필요합니다.');
			opener.location.href="/FarmParm/member/memberLogin.us?returnURI="+encodeURIComponent(opener.location);
			window.close();
		</script>
	</c:when>
	<c:when test="${id != null && id != 'admin' }">
		<p>권한이 없습니다.</p>
		<button type="button" onclick="fclose(this)" id="wbutton">닫기</button>
	</c:when>
	<c:when test="${act != null && act eq 'dupl' }">
		<p>이미 작성하셨습니다.</p>
		<button type="button" onclick="fclose(this)" id="wbutton">닫기</button>
	</c:when>
	<c:otherwise>
<% 
	String code = request.getParameter("item_code");
    String bnum = request.getParameter("bnum");
%>
<input type="hidden" name="item_code" id="item_code" value="<%=code%>">
<input type="hidden" name="rgroup" id="rgroup" value="<%=bnum%>">
<table>
	<tr>
		<td id="td_left">
			<label for="content">내용</label>
		</td>
		<td>
			<textarea name="content" id="content" cols="40" rows="10" onkeyup="fc_chk_byte(this);" onkeypress="fc_chk2();" placeholder="200자까지 입력하실 수 있습니다."></textarea>
		</td>
	</tr>
</table>
<br>
<section>
	<button type="submit" id="wbutton">등록</button>
	<button type="button" onclick="fclose(this)" id="wbutton">닫기</button>
</section>
</c:otherwise>
</c:choose>
</form>
</body>
</html>