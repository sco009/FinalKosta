<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description"
	content="Examples of Pseudo-Elements Animations and Transitions " />
<meta name="keywords"
	content="pseudo-elements, before, after, animation, transition, css3" />
<meta name="author" content="Marco Barria for Codrops" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="/resources/plugins/education/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/dist/css/education/education_style2.css" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="/resources/plugins/education/js/modernizr.custom.js"></script>
<title>Insert title here</title>
<style type="text/css">
#body{
	background-image: url("/resources/dist/img/education/education_background.jpg");
	background-size:cover;
}
</style>
<script type="text/javascript">
 function Frameset(page) { 
	framecode = "<frameset rows='1*'>" 
	+ "<frame name=main src='" + page + "'>" 
	+ "</frameset>"; 

	page = window.open(""); 
	page.document.open(); 
	page.document.write(framecode); 
	page.document.close(); 
	}  
</script>
</head>
<body id="body">
		
	<div class="container">
		<div class="main clearfix">
			<div class="col-md-4">
				<a
					href="javascript:Frameset('educations?dataClassify=education_Introduce&memberID=aaa&pages=1')"><div
						class="circle">
						<h1>자바의 소개</h1>
					</div></a>
			</div>
			<div class="col-md-4">
				<a
					href="javascript:Frameset('educations?dataClassify=education_Data&memberID=aaa&pages=1')"><div
						class="circle">
						<h1>데이터형과 연산자</h1>
					</div></a>
			</div>
			<div class="col-md-4">
				<a
					href="javascript:Frameset('educations?dataClassify=education_Array&memberID=aaa&pages=1')"><div
						class="circle">
						<h1>제어문과 배열</h1>
					</div></a>
			</div>
		</div>
	</div>
	
	
	

</body>
</html>