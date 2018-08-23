<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = (String)session.getAttribute("id");

	if(id==null) {
		out.println("<script>");
		out.println("alert('로그인 한 회원만 쓸 수 있습니다.');");
		out.println("location.href='../member/memberLogin.us?turn=ok';");
		out.println("</script>");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
td, tr{
	border: 1px solid #ddd;
}
</style>
<script>
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
    if(doubleSubmitCheck()) return;
    
	alert("등록되었습니다.");
    document.csboard.submit();
}
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
	<form action="csWrite.bo" method="post" enctype="multipart/form-data" name="csboard" onsubmit="return chkForm(this)">
	<input type="hidden" id="user_id" name="user_id" value="${id }"/>
			<table class="mytable">
				<tr>
					<td id="td_left">
						<label for="item_name">제목</label>
					</td>
					<td id="td_right">
						<input type="text" name="subject" id="subject" size="40" required/>
					</td>
					<td id="td_left">
						<input type="radio" name="hide" value="HIDE">&nbsp;숨김글
					</td>
				</tr>
				<tr>
					<td id="td_left">
						<label for="content">내용</label>
					</td>	
					<td colspan="2">
						<textarea name="content" id="content" cols="60" rows="15" onkeyup="fc_chk_byte(this);" onkeypress="fc_chk2();"></textarea>
					</td>
				</tr>
				<tr>
					<td id="td_left">
						<label for="img_path">파일 첨부</label>
					</td>
					<td id="td_right" colspan="2">
						<input type="file" name="img_path" id="img_path"/>
					</td>
				</tr>
			</table>
			<br>
			<section>
				<button id="bbutton" type="submit">등록</button>
				<button id="bbutton" type="button" onclick="location.href='csList.bo?page=${page}'">목록</button>
			</section>
		</form>
	</div>	
</div>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>