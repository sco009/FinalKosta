<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script type="text/javascript">
window.onload= function() {
	var resetPage = setInterval(function() {
		$.ajax({
			url : "fightList",
			dataType : 'json',
			success : successhandler
		});
	}, 2000);

	function successhandler(data) {

		var $cat = $("#cat");
		
		$cat.empty();

		var html = "";
		$.each(data, function(index, entry) {
			html += "<tr>";
			html += "<td>" + entry.memberId + "</td>";
			html += "<td>" + entry.memberName + "</td>"; 
			html += "</tr>";
		});

		$cat.append(html);
	}
}
	
</script>
<title>Insert title here</title>
</head>
<body>
		<h2>현재 접속자 목록</h2>
		<table border="1" id="cat">
			<tr>
				<td>아이디</td>
				<td>이름</td>
			</tr>
		</table>
	
	<input type="text"></text>
	<input type="button" value="초대하기"></button>
</body>
</html>