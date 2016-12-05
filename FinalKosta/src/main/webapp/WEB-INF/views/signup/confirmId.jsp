<%@page import="cosmos.signup.persistence.SignUpDAO"%>
<%@page import="cosmos.signup.domain.SignUpVO"%>
<%@page import="cosmos.signup.service.SignUpService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/dist/css/signupcss/confirmId.css" type="text/css"
   media="screen" /><title>ID 중복확인</title>
</head>
<body>
	<%-- <%
		request.setCharacterEncoding("UTF-8");
	%>
	<%
		String memberID = request.getParameter("memberID");

		MemberService service = MemberService.getInstance();
		Member member = service.selectMemberService(memberID);

		if (member != null) {
	%> --%>
	<c:if test="${result != null }">
		<form class="form" name="checkForm" method="GET" action="/main/signup/confirmId?memberID=${memberID}">
				<b><span class="strong">${memberID }</span>는  
			이미 사용중인 아이디입니다.<br><b>다른 아이디를 선택하세요.</b><br> <br> <input type="text"
					name="memberID"> <input type="submit" value="중복확인"> </b>
		</form>
	</c:if>
	
	<c:if test="${result == null }">
		<center>
			<b><span class="font">입력하신 <span class="strong">${memberID }</span>는 사용하실 수 있는 ID입니다.
			</span><br> </b><br> <br> <input type="button" value="닫기"
				onclick="setid()">
		</center>
	</c:if>
	
			
	<%-- <%
		} else {
	%> --%>
	
	<%-- <%
		}
	%> --%>

	<script language="javascript">
	function setid() {
		opener.document.userForm.memberID.value="${memberID }";
			opener.document.userForm.chkID.value = "Y";
			self.close(); 
		}
	</script>
</body>
</html>