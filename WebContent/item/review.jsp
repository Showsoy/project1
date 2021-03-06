<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
</style>
<script>
var count1 = 0;
	
	function show_content1(v){
		var name = "re_content"+v;
		var name2 = "rere_content"+v;
		if(document.getElementById(name).style.display=="none"){
			document.getElementById(name).style.display = "table-row";
			if(document.getElementById(name2)!=null) document.getElementById(name2).style.display = "table-row";
			count1++;
			return;
		}else if(document.getElementById(name).style.display=="table-row"){
			document.getElementById(name).style.display = "none";
			if(document.getElementById(name2)!=null) document.getElementById(name2).style.display = "none";
			count1--;
			return;
		}
		
	}
</script>
</head>
<body>
<div class="review" id="reboard">
<h3>&nbsp;&nbsp;상품후기</h3>
	<hr color="#4CAF50" size="5">
		<div id="commandCell">
			<button type="button" id="wbutton" onclick="window.open('./item/reForm.bo?item_code=${item.item_code}','','width=500, height=400')">후기쓰기</button>
		</div>
		<table cellspacing="0" cellpadding="0" id="detail_board">
			<tr id="top_menu20" height="20px">
				<td id="td_check">번호</td>
				<td colspan="2" width="200px">제목</td>
				<td width="100px">작성자</td>
				<td width="100px">작성일</td>
			</tr>
			<c:choose>
				<c:when test="${reviewList!=null&&r_pageInfo.listCount>0 }">	
				<c:set var="num" value="${r_pageInfo.listCount-(r_pageInfo.page-1)*10 }"/>
				<c:forEach var="review" items="${reviewList }">
					<c:if test="${review.rstep==1 }">
					<tr height="30px">
						<td>${num }</td><c:set var="num" value="${num-1 }"/>
						<td id="leftalign" colspan="2" onclick="show_content1(${review.rgroup})" style="cursor:pointer;">${review.subject }<c:if test="${review.has_re==1 }"> [1]</c:if></td>
						<td>${review.user_id }***</td>
						<td>${review.date }</td>
					</tr>
					</c:if>
					<tr id="re_content${review.rgroup }" style="display:none;background-color: #f2f2f2;">
						<td colspan="5">
						<div id="parent" style="width:700px;">
						<c:if test="${review.rstep==1 }">
							<c:choose>
							<c:when test="${review.img_path != null }">
								<div id="parent_img">
								<img src="./images/${review.img_path }" style="width:50px;height:50px;">
								<div id="zoom">
								<a onclick="doImgPop('./images/${review.img_path}')" style="cursor:pointer;" title="클릭하시면 원본크기로 보실 수 있습니다."><img src="./images/zoom-inb.png"></a>
								</div>
								</div>
								<div id="parent_cont" style="width:470px;padding:10px;text-align:left">
									<span id="idnamespan">주문번호 : ${review.readcount }</span><br>
									${review.content }
								</div>
							</c:when>
							<c:otherwise>
								<div id="parent_cont" style="width:600px;padding:10px 10px 10px 50px;text-align:left">
									<span id="idnamespan">주문번호 : ${review.readcount }</span><br>
									${review.content }
								</div>
							</c:otherwise>
							</c:choose>
							
							
							<div id="parent_command" style="width:80px;">
							<c:if test="${review.has_re==0 && id eq 'admin'}">
								<a href="javascript:void(0);" onclick="window.open('./item/rereForm.jsp?item_code=${review.code}&bnum=${review.board_num}','','width=500, height=400')" style="cursor:pointer;">답글</a>
							</c:if>
							<c:if test="${id eq review.user_id || id eq 'admin' }">
								<a href="./reRemove.bo?item_code=${review.code}&bnum=${review.board_num}&page=${page }&r_page=${r_page}&q_page=${q_page }">삭제</a>
							</c:if>
							</div>
						</c:if>
						</div>
						</td>
					</tr>
					<c:if test="${review.rstep==2 }">
					<tr id="rere_content${review.rgroup }" style="display:none;background-color: #f2f2f2;">
						<td colspan="5" id="child_content">
							<div style="width:600px;padding:10px 10px 10px 50px;text-align:left">
							<p><b>팜팜농원</b></p>
							${review.content }
							</div>
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</c:when>
				<c:otherwise>
					<td colspan="5">
						조회 가능한 상품후기가 없습니다.
					</td>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="5" style="height:50px;">
					<c:if test="${r_pageInfo.page<=1 }">
						
					</c:if>
					<c:if test="${r_pageInfo.page>1 }">
						<a href="uitemView.im?item_code=${item.item_code }&page=${page }&r_page=${r_pageInfo.page-1}&q_page=${q_page }#reboard"><span id="pagebn"><</span></a>
					</c:if>
					
					<c:forEach var="a" begin="${r_pageInfo.startPage }" end="${r_pageInfo.endPage }" step="1">
						<c:choose>
							<c:when test="${a==r_pageInfo.page }">
								<span id="nowpage">${a }</span>
							</c:when>
							<c:otherwise>
								<a href="uitemView.im?item_code=${item.item_code }&page=${page }&r_page=${a }&q_page=${q_page }#reboard">&nbsp;${a }&nbsp;</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${r_pageInfo.page>=r_pageInfo.maxPage }">
							
						</c:when>
						<c:otherwise>
							<a href="uitemView.im?item_code=${item.item_code }&page=${page }&r_page=${r_pageInfo.page+1 }&q_page=${q_page }#reboard"><span id="pagebn">></span></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>