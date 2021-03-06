<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:::상품후기 | 팜팜농원:::</title>
<style>
tr{
	height:40px;
}
tr:nth-child(even) {background-color: #f2f2f2;}
td{
    border-bottom: 1px solid #ddd;
}
td p{
	text-height:100%;
	float:left;
	padding: 0 0 0 10px;
	border:0;
	margin:0;
}
img{
	padding:0 0 3px 0;
}
</style>
<script>

function viewnonreply(){
	if(document.getElementById("nonreply").checked){
		location.href="reList.bo?std=reply";
	}else{
		location.href="reList.bo";
	}
}
function viewmyre(){
	if(document.getElementById("myre").checked){
		location.href="reList.bo?std=myre";
	}else{
		location.href="reList.bo";
	}
}
</script>
</head>
<link rel="stylesheet" type="text/css" href="../style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>
<div class="pageform">
	<p style="float:right;font-size:12px;margin-right:110px;">홈>상품후기</p>
	<h3>&nbsp;&nbsp;상품후기</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
		<c:if test="${keyword !=null }">
			<div id="search_info">
				"${keyword }" 검색결과... <a href="reList.bo">전체보기</a>
			</div>
		</c:if>
		<br>
		<div id="leftalign" style="width:75%;margin:0 auto;padding:10px;">
		<c:choose>
		<c:when test="${id eq 'admin' }">
		<span id="pcheck">&nbsp;<input type="checkbox" id="nonreply" name="nonreply" onchange="viewnonreply()" <c:out value="${std eq 'reply' ? 'checked' : '' }"/>> <label for="nonreply">미답변글 보기</label>&nbsp;</span>
		</c:when>
		<c:when test="${id != null}">
		<span id="pcheck">&nbsp;<input type="checkbox" id="myre" name="myre" onchange="viewmyre()" <c:out value="${std eq 'myre' ? 'checked' : '' }"/>> <label for="myre">내가 쓴 글 보기</label>&nbsp;</span>
		</c:when>
		</c:choose>
		</div>
		<table class="listtable" cellspacing="0" cellpadding="0">
			<tr id="top_menu35">
				<td id="td_check">번호</td>
				<td style="width:150px;">상품</td>
				<td colspan="2" style="width:200px;">제목</td>
				<td style="width:100px;">작성자</td>
				<td style="width:150px;">작성일</td>
			</tr>
			<c:choose>
				<c:when test="${pageInfo.listCount>0 }">	
				<c:set var="num" value="${pageInfo.listCount-(pageInfo.page-1)*10 }"/>
					<c:forEach var="board" items="${boardList }">
					<tr>
						<td>${num }</td>
						<c:set var="num" value="${num-1 }"/>
						<td>${board.content }</td>
						<td colspan="2">
							<p>
							<a href="javascript:void(0);" onclick="location.href=encodeURI('reView.bo?bnum=${board.board_num }&item_code=${board.code }&page=${pageInfo.page}<c:out value="${std !=null ? '&std=' : '' }"/>${std}<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')">
							${board.subject }
							<c:if test="${board.has_re>0 }">[1]</c:if>
							</a></p>
						</td>
						<td>${board.user_id }***</td>
						<td>${board.date }</td>
					</tr>
					</c:forEach>
			<tr>
				<td colspan="6" id="td_info">
					<c:if test="${pageInfo.page<=1 }">
						
					</c:if>
					<c:if test="${pageInfo.page>1 }">
						<a href="javascript:void(0);" onclick="location.href=encodeURI('reList.bo?page=${pageInfo.page-1}<c:out value="${std !=null ? '&std=' : '' }"/>${std}<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')"><span id="pagebn"><</span></a>
					</c:if>
					
					<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
						<c:choose>
							<c:when test="${a==pageInfo.page }">
								<span id="nowpage">${a }</span>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="location.href=encodeURI('reList.bo?page=${a}<c:out value="${std !=null ? '&std=' : '' }"/>${std}<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')">&nbsp;${a }&nbsp;</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${pageInfo.page>=pageInfo.maxPage }">
							
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" onclick="location.href=encodeURI('reList.bo?page=${pageInfo.page+1}<c:out value="${std !=null ? '&std=' : '' }"/>${std}<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')"><span id="pagebn">></span></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr><td colspan="6">등록된 글이 없습니다.</td></tr>
			</c:otherwise>
		</c:choose>
		</table>
		<br>
		<section id="commandCellp">
			<form action="reList.bo" name="research" method="post">
				<select name="std" id="std">
					<option value="item_name" <c:out value="${std eq 'item_name' ? 'selected' : '' }"/>>상품명</option>
					<option value="subject" <c:out value="${std eq 'subject' ? 'selected' : '' }"/>>제목</option>
					<option value="content" <c:out value="${std eq 'content' ? 'selected' : '' }"/>>제목+내용</option>
				</select>
				<input type="text" id="keyword" name="keyword" size="15" value="${keyword }"/>
				<button type="submit" id="wbutton">검색</button>
			</form>
			
		</section>
	</div>
</div>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>