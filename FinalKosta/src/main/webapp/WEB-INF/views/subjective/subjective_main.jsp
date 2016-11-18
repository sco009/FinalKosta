<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>

<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/dist/js/quest/subjective/SubjectiveJavaScript.js"></script>
<link href="/resources/dist/css/quest/subjective/subjectiveMenu.css" rel="stylesheet" type="text/css">
<link href="/resources/dist/css/quest/subjective/subjectiveRadioButton.css" rel="stylesheet" type="text/css">
<link href="/resources/dist/css/webcompile/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">


</head>
<body id="body">

	<div class="row-fluid">
		<div class="col-md-12">
			<div class="row-fluid side">
				<label id="toplabel">
				<b>카테고리:</b>${subjectiveSelect.subj_Categori }
				</label> 
				
				<label id="toplabel">
				<b>난이도 :</b>${subjectiveSelect.subj_Level }
				</label>
			</div>
<!-- dd -->
			<div class="col-md-3 col-md-offset-1* side">
				<form action="subjectiveSelect" method="GET">
					<div class="row-fluid">

						<span class="dropdown-el " id="drop"> <input type="radio"
							name="subj_Categori" checked="checked" value="sort-list"><label
							for="sort-relevance">카테고리</label> <input type="radio"
							name="subj_Categori" value="for문" id="sort-1"><label
							for="sort-1">for문</label> <input type="radio"
							name="subj_Categori" value="if문" id="sort-2"><label
							for="sort-2">if문</label> <input type="radio" name="subj_Categori"
							value="while문" id="sort-3"><label for="sort-3">while문</label>
							<input type="radio" name="subj_Categori" value="정렬" id="sort-4"><label
							for="sort-4">정렬</label> <input type="radio" name="subj_Categori"
							value="객체지향" id="sort-5"><label for="sort-5">객체지향</label>
						</span>
					</div>
					<!-- 1번 col row end -->

					<div class="row-fluid">
						<span class="dropdown-els " id="drop"> <input type="radio"
							name="subj_Level" checked="checked" value="sort-list"><label
							for="sort_level">난이도</label> <input type="radio"
							name="subj_Level" value="상" id="sort_h"><label
							for="sort_h">상</label> <input type="radio" name="subj_Level"
							value="중" id="sort_m"><label for="sort_m">중</label> <input
							type="radio" name="subj_Level" value="하" id="sort_d"><label
							for="sort_d">하</label>
						</span>
					</div>

					<div clsass="row-fluid">
						<br> <input class="multipleSelect_css" type="submit"
							value="선택">
					</div>
				</form>
			</div>
			<!--1번 col row end -->

			<div class="col-md-3">
				<div class="well well-large problem" >
					<pre><c:if test="${subjectiveSelect==null }">
					부정행위 방지를 위해 이 페이지는 F5키와 뒤로가기 버튼이 먹히지 않습니다.
			 			 </c:if>
			 		<c:if test="${subjectiveSelect!=null }">
			 			${subjectiveSelect.subj_Content }
			 		</c:if>
			 		</pre>
					<br>
					<c:if test="${subjectiveSelect!=null }">
						<br>
						<div id="Examiner">
							<small><cite title="Examiner">출제자 :</cite>${subjectiveSelect.subj_Examiner }</small>
						</div>
					</c:if>
				</div>

			</div>
			<div class="col-md-3">
				<form id="complieFrom">
					<textarea rows="30" cols="50" name="wc_code" onkeydown="useTab(this)" id="demo"></textarea>
						<div id="loading" style="width: 100%; text-align: center">
						<img alt=""src="/resources/dist/img/webcompile/compileLoading.gif" style="width: 100%; max-width: 150px; vertical-align: middle">
					</div>
					<br> <input type="submit" value="Compile"><br>
					<textarea rows="10" cols="50" type="disabled" id="wc_result"></textarea>
				</form>
			</div>
			
			<div class="col-md-1" id="confirmSubjective">
					<input type="hidden" id = "hiddenResult" name ="result"></input>
					<input type="hidden" name = "subjectiveAnswer" value=${subjectiveSelect.subj_Answer }></input>
					<input type="hidden" name = "subjectiveSelect" value=${subjectiveSelect.subj_QuestId }></input>
					<input type="button" id="confirm" value="답체크" onclick= subjectiveCheck() style="display:none;"></input>
					<p id="checkAnswer"></p> 
			</div>
			
			<c:if test="${signal==null }">
				<form action="subjectiveNext" method="GET">
					<div class="col-md-1">
						<input type="submit" id="nextButton1" name="nextButton" value="다음"
							class="multipleSelect_css" style="display:none;"></input>
					</div>
				</form>
			</c:if>
			
			<c:if test="${signal!=null }">
               		<input type="button" id="nextButton1" name="nextButton" value="결과보기" 
               			class="multipleSelect_css" style="display:none" onclick= resultPage()></input>
			</c:if>
			
			<!--2단 end  -->

		</div>
		<!-- col 12 end  -->
	</div>
	<!-- main row -->
</body>
</html>