<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../../include/codefight/codefight_body.jsp"%>
</head>
<body id="body">

	<div class="row-fluid">
		<div class="row">
			<div class="col-md-offset-6 col-md-2">
				<panel class="glyphicon glyphicon-star btn-lg"" aria-hidden="true"></panel>
				<panel class="glyphicon glyphicon-star btn-lg" aria-hidden="true"></panel>
				<panel class="glyphicon glyphicon-star-empty btn-lg" aria-hidden="true"></panel>
			</div>
			<div class="col-md-2">
				<label class="btn btn-default btn-lg">뚜뚜뚜님</label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-2 col-md-4">
				<div class="well wel-large">문제나올 판</div>
			</div>
			<div class="col-md-3">
				<form id="complieFrom">
					<textarea rows="20" cols="50" name="wc_code"
						onkeydown="useTab(this)" id="demo" class="well"></textarea>
					<br> <input type="submit" value="Compile"><br>
					<textarea rows="10" cols="50" type="disabled" id="wc_result"
						class="well"></textarea>
				</form>
			</div>
		</div>
	</div>

</body>
</html>