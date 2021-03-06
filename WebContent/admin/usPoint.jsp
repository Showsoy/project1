<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:::관리자 페이지:::</title>
<style>
td{
	text-align:center;
    border-bottom: 1px solid #ddd;
}
td p{
	text-height:100%;
	float:left;
}
</style>
<script>
function selectSDay(sel) {
	var year = document.getElementById("sYear").options[document.getElementById("sYear").selectedIndex].value;
	var month = sel.options[sel.selectedIndex].value;
	
	var lastDay = (new Date(year,month,0)).getDate();
	var string = "";
	for(var i=1; i<=lastDay; i++){
		if(i<10) string += "<option value = '0"+i+"'>"+i+"일</option>";
		else string += "<option value = '"+i+"'>"+i+"일</option>";
	}
	document.getElementById("sDay").innerHTML = string;
}
function selectEDay(sel) {
	var year = document.getElementById("eYear").options[document.getElementById("eYear").selectedIndex].value;
	var month = sel.options[sel.selectedIndex].value;
	
	var lastDay = (new Date(year,month,0)).getDate();
	var string = "";
	for(var i=1; i<=lastDay; i++){
		if(i<10) string += "<option value = '0"+i+"'>"+i+"일</option>";
		else string += "<option value = '"+i+"'>"+i+"일</option>";
	}
	document.getElementById("eDay").innerHTML = string;
}
</script>
</head>
<link rel="stylesheet" type="text/css" href="./style/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<body>
<jsp:include page="/common/top_menu.jsp" flush="false"/>
<jsp:include page="/common/adminbar.jsp" flush="false"/>
<div class="pageform">
	<h3>&nbsp;&nbsp;적립금 내역</h3>
	<hr color="#4CAF50" size="5">
	<form action="usPoint.us" method="post" name="pointList">
	<input type="hidden" id="upage" name="upage" value="${upage }">
	<input type="hidden" name="uid" id="uid" value="${user_id }"/>
	<input type="hidden" name="std" id="std" value="${std }"/>
	<input type="hidden" name="keyword" id="keyword" value="${keyword }"/>
	
	<div class="mypage">
		<br><br>
		<div id="sales-keyword" style="width:700px">
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear" />
		<%
		String start = (String)request.getAttribute("start");
		String end = (String)request.getAttribute("end");
		HashMap<String, String> dayMap = new HashMap<String, String>();
		dayMap.put("syear", start.substring(0, 4));
		dayMap.put("smonth", start.substring(5, 7));
		dayMap.put("sday", start.substring(8, 10));
		
		dayMap.put("eyear", end.substring(0, 4));
		dayMap.put("emonth", end.substring(5, 7));
		dayMap.put("eday", end.substring(8, 10));

		pageContext.setAttribute("dayMap", dayMap);
		%>
        <div id="datesel">
		<span id="monthseldel1">
		<select id="sYear" name="sYear">
		  <c:forEach var="year" begin="${nowYear - 2}" end="${nowYear}">
		   <option value="${year}" <c:out value="${dayMap['syear'] eq year ? 'selected=\"selected\"' : '' }"/>>${year}년 </option>
		  </c:forEach>
		</select><select id="sMonth" name="sMonth" onchange="selectSDay(this)">
		 <c:forEach var="month" begin="1" end="12">
		 <fmt:formatNumber var="datePattern" value="${month}" pattern="00"/>
		   <option value="${datePattern}" <c:out value="${dayMap['smonth'] eq datePattern ? 'selected=\"selected\"' : '' }"/>> ${month}월 </option>
		 </c:forEach>
		</select><select id="sDay" name="sDay">
		 <c:forEach var="day" begin="1" end="31">
		 <fmt:formatNumber var="datePattern" value="${day}" pattern="00"/>
		   <option value="${datePattern}" <c:out value="${dayMap['sday'] eq datePattern ? 'selected=\"selected\"' : '' }"/>> ${day}일 </option>
		 </c:forEach>
		</select>
		</span>
		<span id="monthseldel2" style="display:block;">
		&nbsp;-
		<select id="eYear" name="eYear">
		  <c:forEach var="year" begin="${nowYear - 2}" end="${nowYear}">
		   <option value="${year}" <c:out value="${dayMap['eyear'] eq year ? 'selected=\"selected\"' : '' }"/>>${year}년 </option>
		  </c:forEach>
		</select><select id="eMonth" name="eMonth" onchange="selectEDay(this)">
		 <c:forEach var="month" begin="1" end="12">
		 <fmt:formatNumber var="datePattern" value="${month}" pattern="00"/>
		   <option value="${datePattern}" <c:out value="${dayMap['emonth'] eq datePattern ? 'selected=\"selected\"' : '' }"/>> ${month}월 </option>
		 </c:forEach>
		</select><select id="eDay" name="eDay">
		 <c:forEach var="day" begin="1" end="31">
		 <fmt:formatNumber var="datePattern" value="${day}" pattern="00"/>
		   <option value="${datePattern}" <c:out value="${dayMap['eday'] eq datePattern ? 'selected=\"selected\"' : '' }"/>> ${day}일 </option>
		 </c:forEach>
		</select>
		</span>&nbsp;
		<span id="monthseldel3">
		<button type="submit" id="wbutton">&nbsp;검색&nbsp;</button>
		</span>
		</div>
		<br>
		<button type="button" id="wbutton" onclick="location.href='usPoint.us?uid=${user_id}&period=1&upage=${upage }&std=${std }&keyword=${keyword }'">1개월</button>
		<button type="button" id="wbutton" onclick="location.href='usPoint.us?uid=${user_id}&period=3&upage=${upage }&std=${std }&keyword=${keyword }'">3개월</button>
		<button type="button" id="wbutton" onclick="location.href='usPoint.us?uid=${user_id}&period=6&upage=${upage }&std=${std }&keyword=${keyword }'">6개월</button>
		<br><br>
		<b>${start } - ${end }</b> 기간 검색
		</div>
		<br>
		<br>
		<table class="listtablepx" cellspacing="0" cellpadding="0">	
			<tr id="top_menu">
				<td>주문번호</td>
				<td>적립내역</td>
				<td>구분</td>
				<td>적립금</td>
				<td>적립잔액</td>
				<td>적립일자</td>
			</tr>
			<c:choose>
				<c:when test="${pointList!=null && pageInfo.listCount>0 }">
			<c:forEach var="pointList" items="${pointList }">
			<tr>
				<td>${pointList.order_id }</td>
				<td>${pointList.state }</td>
				<c:choose>
				<c:when test="${pointList.plminus>0 }">
				<td><font color="green">적립</font></td></c:when>
				<c:otherwise><td><font color="red">차감</font></td></c:otherwise>
				</c:choose>
				<td>${pointList.plminus * pointList.point }</td>
				<td>${pointList.balance }점</td>
				<td>${pointList.pdate }</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="8" id="td_info">
					<c:if test="${pageInfo.page<=1 }">
						
					</c:if>
					<c:if test="${pageInfo.page>1 }">
						<a href="usPoint.us?uid=${user_id}&page=${pageInfo.page-1}&start=${start}&end=${end}&upage=${upage }&std=${std }&keyword=${keyword }"><span id="pagebn"><</span></a>&nbsp;
					</c:if>

					<c:forEach var="a" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
						<c:choose>
							<c:when test="${a==pageInfo.page }">
								<span id="nowpage">${a }</span>
							</c:when>
							<c:otherwise>
								<a href="usPoint.us?uid=${user_id}&page=${a }&start=${start}&end=${end}&upage=${upage }&std=${std }&keyword=${keyword }">&nbsp;${a }&nbsp;</a>&nbsp;
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${pageInfo.page>=pageInfo.maxPage }">
							
						</c:when>
						<c:otherwise>
							<a href="usPoint.us?uid=${user_id}&page=${pageInfo.page+1 }&start=${start}&end=${end}&upage=${upage }&std=${std }&keyword=${keyword }"><span id="pagebn">></span></a>&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
				<tr><td colspan="8">등록된 정보가 없습니다.</td></tr>
			</c:otherwise>
		</c:choose>
		</table>
	<br><br><br>
	<button type="button" id="bbutton" onclick="location.href='userView.us?page=${upage}&user_id=${user_id }&std=${std }&keyword=${keyword }'" style="width:100px;">회원정보</button>
	</div>
	</form>
</div>
 <jsp:include page="/common/footer.jsp" flush="false"/>
</body>
</html>