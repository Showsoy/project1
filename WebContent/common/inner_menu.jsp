<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="right">
	<c:choose>
		<c:when test="${id == null}">
			<a href="javascript:void(0);" onclick="location.href='<%=request.getContextPath()%>/member/memberLogin.us?returnURI='+encodeURIComponent(location);">로그인</a>&nbsp;
			<a href="<%=request.getContextPath()%>/member/joinForm.jsp">회원가입</a>&nbsp;
		</c:when>
		<c:otherwise>
			<a href="<%=request.getContextPath()%>/memberLogout.us?url=<%=request.getRequestURI()%>">로그아웃</a>&nbsp;
		</c:otherwise>
		</c:choose>  
		    <a href="<%=request.getContextPath()%>/myPage.us">마이페이지</a>&nbsp;
			<a href="<%=request.getContextPath()%>/order/myodList.od">주문/배송</a>&nbsp;

			<a href="<%=request.getContextPath()%>/common/csList.bo">고객센터</a>&nbsp;
			<a href="<%=request.getContextPath()%>/item/reList.bo">상품후기</a>&nbsp;
			<a href="<%=request.getContextPath()%>/item/cartList.ct">장바구니</a>&nbsp;
			<c:if test="${id == 'admin'}">
				<a href="<%=request.getContextPath()%>/admin/adminPage.jsp">관리자페이지</a>&nbsp;
			</c:if>
	
</div>
</body>
</html>