<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">


<link href="https://fonts.googleapis.com/css?family=Architects+Daughter" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/resources/dist/css/webcompile/style.css" media="screen" />


<script src="https://cdnjs.cloudflare.com/ajax/libs/i18next/3.1.0/i18next.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/i18next-locize-backend/0.1.1/i18nextLocizeBackend.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-i18next/0.0.14/i18next-jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/i18next-browser-languagedetector/0.3.0/i18nextBrowserLanguageDetector.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<!-- 컴파일러 자동완성 라이브러리 -->
<script type='text/javascript' src="/resources/dist/js/webcompile/behave.js"></script>

<!-- Bootstrap -->
<link href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">

	$(function(){
		$("#complieFrom").submit(function(event){
			event.preventDefault();
			
			$.ajax({
				url : "compile",
				type : "post",
				dataType:"text",
				data : $(this).serialize(),
				success : successHandler,
				error : function(){
					alert("실패");
				}
			});
		});
		
		function successHandler(data){
			console.log(data);
			var str = data.replace(/^\s+/, "");
			$("#wc_result").empty();
			$("#wc_result").text(str);
		}
		
		$(document).ajaxStart(function(){
		      $("#loading").fadeIn();
		   }).ajaxStop(function(){
			   $("#loading").fadeOut();
		   });
		
	});

</script>
		<br><br><br>

<title>Insert title here</title>
</head>
<body id="body">

<div id="main">

	<div class="row" id="demo1">

      <div class="col-md-4">
      	<form id="complieFrom">
			<textarea rows="30" cols="50" name="wc_code" onkeydown="useTab(this)" id="demo"></textarea>
			
			<div id="loading" style="width: 100%; text-align: center" >
				<img alt="" src="/resources/dist/img/webcompile/compileLoading.gif"style="width: 100%; max-width: 150px; vertical-align: middle">
			</div><br>
			
			<input type="submit" value="Compile"><br>

			<textarea rows="10" cols="50" type="disabled" id="wc_result"></textarea>
		</form>
      </div>
      
</body>
</html>