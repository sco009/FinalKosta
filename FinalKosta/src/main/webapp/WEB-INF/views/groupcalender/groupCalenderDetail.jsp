<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
	function inertboard(yy,mm,dd){
		
		location.href="/groupcalender/groupCalenderinsert?yy="+yy+"&mm="+mm+"&dd="+dd;
		
	}
	function rework(calenderNo,yy,mm,dd){
		
		location.href="/groupcalender/updatepage?calenderNo="+calenderNo+"&yy="+yy+"&mm="+mm+"&dd="+dd;
	}
	function deletes(calenderNo,yy,mm,dd){
		location.href="/groupcalender/calenderdelete?calenderNo="+calenderNo+"&yy="+yy+"&mm="+mm+"&dd="+dd;
		
	}

</script>



<style type="text/css">
table {
	text-align: center;
}

.color {
	background: #FFC19E;
}
</style>
</head>
<body>
	<table border="1px" width="100%" bordercolor="#F29661">
		<c:if test="${list eq '[]' }">
		<td class="color" colspan="3">선택 일자</td>
				<td colspan="3">${yy }- ${mm } -${dd }</td>
		<tr>
			
			<td> 저장되어있는 값이 없습니다.</td>
			<td colspan="3"><input type="button" value="추가하기"
			onclick="inertboard(${yy }, ${mm },${dd })"></td>
		</tr>
		</c:if>
	
		<c:forEach items="${list }" var="list">
			<tr>
				<td class="color" colspan="3">선택 일자</td>
				<td colspan="3">${list.yy }- ${list.mm } -${list.dd }</td>
			</tr>
			<tr>
				<td class="color" colspan="3">제목</td>
				<td colspan="3">${list.title }</td>
			</tr>
			<tr>
				<td colspan="6" class="color">내용</td>
			</tr>
			<tr>
				<td colspan="6" style="width:100%; height: 100px" >${list.contexts }</td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="추가하기"
					onclick="inertboard(${list.yy }, ${list.mm },${list.dd })"></td>
				<td colspan="2"><input type="button" value="수정하기"
					onclick="rework(${list.calenderNo},${list.yy }, ${list.mm },${list.dd })"></td>
				<td colspan="2"><input type="button" value="삭제하기"
					onclick="deletes(${list.calenderNo},${list.yy }, ${list.mm },${list.dd })"></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>