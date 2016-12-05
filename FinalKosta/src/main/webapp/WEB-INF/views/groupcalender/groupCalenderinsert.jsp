<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

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
	<form action="/groupcalender/newinsert">
		<input type="hidden" name="yy" value="${yy }">
		<input type="hidden" name="groupid" value="${groupid }"> <input
			type="hidden" name="mm" value="${mm }"> <input type="hidden"
			name="dd" value="${dd }">
		<table border="1px" width="100%" bordercolor="#F29661">
			<tr>

				<td class="color" colspan="3">선택 일자</td>
				<td colspan="3" name="date">${yy }-${mm }-${dd }</td>
			</tr>
			<tr>
				<td class="color" colspan="3">제목</td>
				<td colspan="3"><input type="text" name="title"></td>
			</tr>
			<tr>
				<td colspan="6" class="color">내용</td>
			</tr>
			<tr>
				<td colspan="6"><input type="text" name="contexts"
					style="width: 100%; height: 100px"></td>
			</tr>
			<tr>
				<td colspan="6"><input type="submit" value="추가하기" ></td>
			</tr>

		</table>
	</form>
</body>
</html>