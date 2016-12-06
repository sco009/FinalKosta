<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">


<link href="https://fonts.googleapis.com/css?family=Architects+Daughter" rel="stylesheet" type="text/css">
<link href="/resources/dist/css/rankingcss/Ranking.css" rel="stylesheet">


<script src="https://cdnjs.cloudflare.com/ajax/libs/i18next/3.1.0/i18next.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/i18next-locize-backend/0.1.1/i18nextLocizeBackend.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-i18next/0.0.14/i18next-jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/i18next-browser-languagedetector/0.3.0/i18nextBrowserLanguageDetector.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>Insert title here</title>
</head>
<body id='body'>


	<div id="main">
		<div class="col-md-6">
			<h2 align="center">객관식 문제 랭킹</h2>

			<div class="wrapper">

				<div class="table">

					<div class="row header">
						<div class="cell">순위</div>
						<div class="cell">이름</div>
						<div class="cell">포인트</div>
					</div>


					<c:forEach var="list" items="${multiList}"  begin="0" end="9">
						<div class="row">
							<div class="cell">${list.multiple_ranking }</div>
							<div class="cell">${list.memberName }</div>
							<div class="cell">${list.multiple_point }</div>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
		<div class="col-md-6">
			<h2 align="center">주관식 문제 랭킹</h2>

			<div class="wrapper">

				<div class="table">

					<div class="row header">
						<div class="cell">순위</div>
						<div class="cell">이름</div>
						<div class="cell">포인트</div>
					</div>


					<c:forEach var="list" items="${subList}" begin="0" end="9">
						<div class="row">
							<div class="cell">${list.subject_ranking }</div>
							<div class="cell">${list.memberName }</div>
							<div class="cell">${list.subject_point }</div>
						</div>
					</c:forEach>

				</div>

			</div>
		</div><br><br>
		<div class="col-md-12" align="center">
			<h3>${ranking.memberID}의 주관식 포인트는 ${ranking.subject_point}, ${ranking.subject_ranking}위 입니다.</h3>
			<h3>${ranking.memberID}의 객관식 포인트는 ${rankig.multiple_point}, ${ranking.multiple_ranking}위 입니다.</h3>
		</div>
		
	</div>





</body>
</html>