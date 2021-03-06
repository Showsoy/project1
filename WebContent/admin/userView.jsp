<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:::관리자 페이지:::</title>
<style>
.mytable td{
	border: 1px solid #ddd;
}
.listtablepx td{
    border-bottom: 1px solid #ddd;
}
#td_info{
	text-align:center;
}
.orderbox{
	margin:0 auto;
}
</style>
<script>
	function deleteCheck(v){
		var flag = confirm('한 번 삭제한 아이디는 복구할 수 없습니다.\n그래도 삭제하시겠습니까?');
		if(flag){
			location.href=encodeURI("memberDelete.us?"+v);
		}
		
	}
</script>
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>
<jsp:include page="/common/adminbar.jsp" flush="false"/>
<div class="pageform">
	<h3>&nbsp;&nbsp;회원정보</h3>
	<hr color="#4CAF50" size="5">
	<div class="mypage">
	<div id="grade"><b id="grade_deco">|</b>${user.grade }</div>
	<section id="commandCell">
		<button id="wbutton" onclick="location.href=encodeURI('./memberModAdForm.us?page=${page }&uid=${user.user_id}&std=${std }&keyword=${keyword }')">수정</button> 
		<button id="wbutton" onclick="deleteCheck('page=${page }&uid=${user.user_id}&std=${std }&keyword=${keyword }');">삭제</button> 
	</section>
	<table class="mytable" cellspacing="0" cellpadding="0">
	<tr>
		<td id="td_left"><label for="userID">아이디</label> </td>
		<td>${user.user_id}</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">가입일</label> </td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.edate }" /></td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">포인트</label> </td>
		<td>${user.point}점 <button id="gbutton" style="width:80px" onclick="location.href=encodeURI('usPoint.us?upage=${page }&uid=${user.user_id}&std=${std }&keyword=${keyword }');">내역조회></button></td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">이름</label></td>
		<td>${user.name}</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">연락처</label></td>
		<td>${user.phone}</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">생년월일</label></td>
		<td>${user.birth}</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">성별</label></td>
		<td>${user.gender}</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">이메일</label></td>
		<td>${email1 }@${email2 }</td>
	</tr>
	<tr>
		<td id="td_left"><label for="userID">주소</label></td>
		<td rowspan="3">${user.postcode}<br>${user.address}<br>${user.address_second}</td>
	</tr>
</table>
<br><br>
	<div class="orderbox" id="order">
	<div id="grade"><b id="grade_deco">|</b>주문내역</div>
		<table class="listtablepx" cellspacing="0" cellpadding="0">
			<tr id="top_menug">
				<td>주문번호</td>
				<td>주문일시</td>
				<td>결제금액</td>
				<td>주문현황</td>
				<td>조회</td>
			</tr>
			<c:choose>
				<c:when test="${pageInfo.listCount>0 }">	
			<c:forEach var="orderList" items="${orderList }">
				<tr>
				<td>${orderList.order_id }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderList.dati }" /></td>
				<td>${orderList.pay }원</td>
				<td>${orderList.state }</td>
				<td><button type="button" id="wbutton" onclick="location.href=encodeURI('./admin/odView.od?order_id=${orderList.order_id}&upage=${page }&std=${std }&keyword=${keyword }');">조회</button></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="6" id="td_info">
					<c:if test="${pageInfo.page<=1 }">
						
					</c:if>
					<c:if test="${pageInfo.page>1 }">
						<a href="javascript:void(0);" onclick="location.href=encodeURI('userView.us?page=${page }&user_id=${user.user_id }&std=${std }&keyword=${keyword }&o_page=${pageInfo.page-1}&q_page=${q_page }#order');"><span id="pagebn"><</span></a>
					</c:if>
					
					<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
						<c:choose>
							<c:when test="${a==pageInfo.page }">
								<span id="nowpage">${a }</span>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="location.href=encodeURI('userView.us?page=${page }&user_id=${user.user_id }&std=${std }&keyword=${keyword }&o_page=${a }&q_page=${q_page }#order')">&nbsp;${a }&nbsp;</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${pageInfo.page>=pageInfo.maxPage }">
							
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" onclick="location.href=encodeURI('userView.us?page=${page }&user_id=${user.user_id }&std=${std }&keyword=${keyword }&o_page=${pageInfo.page+1 }&q_page=${q_page }#order')"><span id="pagebn">></span></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr><td colspan="6" style="width:390px;">조회 가능한 주문이 없습니다.</td></tr>
			</c:otherwise>
		</c:choose>
		</table>
	<br><br><br>
	</div>
	
	<div class="orderbox">
	<div id="grade"><b id="grade_deco">|</b>문의내역</div>
	<table class="listtablepx" cellspacing="0" cellpadding="0">
			<tr id="top_menug">
				<td style="width:150px;">상품</td>
				<td colspan="2" style="width:200px;">제목</td>
				<td style="width:150px;">작성일</td>
			</tr>
			<c:choose>
				<c:when test="${q_pageInfo.listCount>0 }">
					<c:forEach var="board" items="${boardList }">
					<tr><td><a href="uitemView.im?item_code=${board.code}">
						${board.content }</a>
						</td>
						<td colspan="2">
							<c:if test="${board.readcount>0 }">
								<img src="/FarmParm/images/lock.png"></c:if>
							<a href="./admin/qnaView.bo?bnum=${board.board_num }&item_code=${board.code }&page=${page }&turn=user&user_id=${user.user_id}">
							${board.subject }<c:if test="${board.has_re==1 }"> [1]</c:if>
							</a>
						</td>
						<td>${board.date }</td>
					</tr>
					</c:forEach>
			<tr>
				<td colspan="6" id="td_info">
					<c:if test="${q_pageInfo.page<=1 }">
						
					</c:if>
					<c:if test="${q_pageInfo.page>1 }">
						<a href="javascript:void(0);" onclick="location.href=encodeURI('userView.us?page=${page }&user_id=${user.user_id}&std=${std }&keyword=${keyword }&o_page=${o_page }&q_page=${q_pageInfo.page-1}')"><span id="pagebn"><</span></a>
					</c:if>
					
					<c:forEach var="a" begin="${q_pageInfo.startPage }" end="${q_pageInfo.endPage }" step="1">
						<c:choose>
							<c:when test="${a==q_pageInfo.page }">
								<span id="nowpage">${a }</span>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);" onclick="location.href=encodeURI('userView.us?page=${page }&user_id=${user.user_id}&std=${std }&keyword=${keyword }&o_page=${o_page }&q_page=${a }')">&nbsp;${a }&nbsp;</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${q_pageInfo.page>=q_pageInfo.maxPage }">
							
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" onclick="location.href=encodeURI('userView.us?page=${page }&user_id=${user.user_id}&std=${std }&keyword=${keyword }&o_page=${o_page }&q_page=${q_pageInfo.page+1 }')"><span id="pagebn">></span></a>
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
	<br><br><br>
	</div>
	
	<button type="button" id="bbutton" onclick="location.href='/FarmParm/admin/adminPage.jsp'" style="width:150px;">관리자페이지</button>
	<button type="button" id="bbutton" onclick="location.href=encodeURI('/FarmParm/memberList.us?page=${page }<c:out value="${std !=null ? '&std=' : '' }"/>${std}<c:out value="${keyword !=null ? '&keyword=' : '' }"/>${keyword}')" style="width:90px;">회원목록</button>
	</div>
</div>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>