<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../include/quest/multiple/multiple_body.jsp"%>
<title>Cosmos</title>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../js/MultipleJs/MultipleJavaScript.js"></script>
<link href="../css/MultipleCss/MultipleMenu.css" rel="stylesheet"
	type="text/css">
<link href="../../../css/quest/multiple/MultipleRadioButton.css" rel="stylesheet"
	type="text/css">
</head>
<body id="body">

	<div class="row-fluid">
		<div class="col-md-12">
    		<div class="row-fluid side">
     			<label id="toplabel"><b>카테고리:</b> ${multipleSelect.mulquestCategori}</label> 
     			 <label id="toplabel"><b>난이도 :</b>${multipleSelect.mulquestLevel}</label>
         	</div>
         	
  		 <div class="col-md-3 col-md-offset-1* side">
  		 	<form action="multiple" method="POST">
   	 			<div class="row-fluid">
   
   					<span class="dropdown-el " id="drop">
   						<input type="radio" name="mulquestCategori" checked="checked" value="sort-list" ><label for="sort-relevance">카테고리</label>
    					<input type="radio" name="mulquestCategori" value="for문" id="sort-1"><label for="sort-1">for문</label>
   						<input type="radio" name="mulquestCategori" value="if문" id="sort-2"><label for="sort-2">if문</label>
    					<input type="radio" name="mulquestCategori" value="while문" id="sort-3"><label for="sort-3">while문</label>
    					<input type="radio" name="mulquestCategori" value="정렬" id="sort-4"><label for="sort-4">정렬</label>
    					<input type="radio" name="mulquestCategori" value="객체지향" id="sort-5"><label for="sort-5">객체지향</label>
  					</span>
  	 			</div><!-- 1번 col row end -->
   	 	
   	 	<div class="row-fluid">
   	 		<span class="dropdown-els " id="drop">
    			<input type="radio" name="mulquestLevel" checked="checked" value="sort-list" ><label for="sort_level">난이도</label>
    			<input type="radio" name="mulquestLevel" value="상" id="sort_h"><label for="sort_h">상</label>
    			<input type="radio" name="mulquestLevel" value="중" id="sort_m"><label for="sort_m">중</label>
    			<input type="radio" name="mulquestLevel" value="하" id="sort_d"><label for="sort_d">하</label>
  	 		</span>
  	 	</div>
     
  	 	<div clsass="row-fluid">
   	 		<br>
     			<input class = "multipleSelect_css" type="submit" value="선택">             
  	 	</div>
  	 </form>
  </div><!--1번 col row end -->

			<div class="col-md-3 col-md-offset-1* side">
				<form action="multiple" method="POST">
					<div class="row-fluid">

						<span class="dropdown-el " id="drop"> <input type="radio"
							name="mulquestCategori" checked="checked" value="sort-list"><label
							for="sort-relevance">카테고리</label> <input type="radio"
							name="mulquestCategori" value="for문" id="sort-1"><label
							for="sort-1">for문</label> <input type="radio"
							name="mulquestCategori" value="if문" id="sort-2"><label
							for="sort-2">if문</label> <input type="radio"
							name="mulquestCategori" value="while문" id="sort-3"><label
							for="sort-3">while문</label> <input type="radio"
							name="mulquestCategori" value="정렬" id="sort-4"><label
							for="sort-4">정렬</label> <input type="radio"
							name="mulquestCategori" value="객체지향" id="sort-5"><label
							for="sort-5">객체지향</label>
						</span>
					</div>
					<!-- 1번 col row end -->

					<div class="row-fluid">
						<span class="dropdown-els " id="drop"> <input type="radio"
							name="mulquestLevel" checked="checked" value="sort-list"><label
							for="sort_level">난이도</label> <input type="radio"
							name="mulquestLevel" value="상" id="sort_h"><label
							for="sort_h">상</label> <input type="radio" name="mulquestLevel"
							value="중" id="sort_m"><label for="sort_m">중</label> <input
							type="radio" name="mulquestLevel" value="하" id="sort_d"><label
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

			<div class="col-md-4">
				<div class="well well-large problem">
					<pre>
			 <c:if test="${multipleSelect==null }">
			 	부정행위 방지를 위해 이 페이지는 F5키와 뒤로가기 버튼이 먹히지 않습니다.
			 </c:if>
			 <c:if test="${multipleSelect!=null }">
			 ${multipleSelect.mulquestContent }
			 </c:if>
			</pre>
					<br>
					<c:if test="${multipleSelect!=null }">
						<br>
						<div id="Examiner">
							<small><cite title="Examiner">출제자 :</cite>${multipleSelect.mulquestExaminer }</small>
						</div>
					</c:if>
				</div>

			</div>
			<!--2단 end  -->

			<div class="col-md-4 checkd side">
				<div class="well well-large">
					<!-- 유형이 선택되야만 보기가 보여짐 -->
					<c:if test="${multipleSelect != null }">
						<!-- 보기 시작 -->
						<div>
							<input type="hidden" name="multipleChoiceSelectAnswer"
								value=${multipleSelect.multipleAnswer }></input> <input
								type="radio" name="multipleChoiceSelect" id="radio-option-1"
								value=${multipleChoiceSelect.multipleChoiceOne
								}
								onclick=choiceButton()></input> <label for="radio-option-1">
								${multipleChoiceSelect.multipleChoiceOne } </label>
						</div>
						<div>
							<input type="radio" name="multipleChoiceSelect"
								id="radio-option-2"
								value=${multipleChoiceSelect.multipleChoiceTwo
								}
								onclick=choiceButton()></input> <label for="radio-option-2">
								${multipleChoiceSelect.multipleChoiceTwo } </label>
						</div>
						<div>
							<input type="radio" name="multipleChoiceSelect"
								id="radio-option-3"
								value=${multipleChoiceSelect.multipleChoiceThree
								}
								onclick=choiceButton()></input> <label for="radio-option-3">
								${multipleChoiceSelect.multipleChoiceThree} </label>
						</div>
						<div>
							<input type="radio" name="multipleChoiceSelect"
								id="radio-option-4"
								value=${multipleChoiceSelect.multipleChoiceFour
								}
								onclick=choiceButton()></input> <label for="radio-option-4">
								${multipleChoiceSelect.multipleChoiceFour} </label>
						</div>

						<div id="myCheckButton" style="display: none">

							<input type="button" class="multipleSelect_css" value="답 Check"
								onclick=selectMutlpleCheck()></input>
						</div>
						<!-- 보기 끝 -->
					</c:if>
					<!-- 유형이 선택되야만 보기가 보여짐 end -->
				</div>
				<div class="imgs" id="checkAnswer"></div>

				<br>
				<div id="solveButton" style="display: none">
					<!-- <form action="test" method="POST"> -->
						<c:if test="${signal==null}">
							<input type="hidden" name="solveSelectId"
								value=${multipleSelect.mulquestId }></input>
							<input type="button" name="nextButton" value="다음"
								class="multipleSelect_css" onclick=Next()></input>
					<!-- </form> -->
					<!-- 							<input type="button" name="nextButton" value="다음"
								class="multipleSelect_css" onclick=Next()></input> -->
					</c:if>
					<form action="result" method="POST">
						<c:if test="${signal!=null }">
							<input type="hidden" name="LastsolveSelectId"
								value=${multipleSelect.mulquestId }></input>
							<!-- <input type="submit" value="결과보기" class="multipleSelect_css"></input> -->
							<input type="button" value="결과보기" class="multipleSelect_css"
								onclick=resultMultiple()></input>
						</c:if>
					</form>

				</div>
				<!-- solveButton end  -->
			</div>
			<!-- 3단 col end  -->
		</div>
	
	</div><!--2단 end  -->
	
	 <div class="col-md-4 checkd side">
      <div class="well well-large">
         <!-- 유형이 선택되야만 보기가 보여짐 -->
         <c:if test="${multipleSelect != null }">
         <!-- 보기 시작 -->
            <div>
               <input type="hidden" name = "multipleChoiceSelectAnswer" value=${multipleSelect.multipleAnswer }></input>
                <input type="radio" name="multipleChoiceSelect" id="radio-option-1" value=${multipleChoiceSelect.multipleChoiceOne} onclick = choiceButton()></input>
                 <label for="radio-option-1">  ${multipleChoiceSelect.multipleChoiceOne } </label>
            </div>
            <div>
                <input type="radio" name="multipleChoiceSelect" id="radio-option-2" value=${multipleChoiceSelect.multipleChoiceTwo} onclick = choiceButton()></input>
                 <label for="radio-option-2">  ${multipleChoiceSelect.multipleChoiceTwo } </label>
            </div>
            <div>
                <input type="radio" name="multipleChoiceSelect" id="radio-option-3" value=${multipleChoiceSelect.multipleChoiceThree} onclick = choiceButton()></input>
                 <label for="radio-option-3">  ${multipleChoiceSelect.multipleChoiceThree} </label>
            </div>
            <div>
                <input type="radio" name="multipleChoiceSelect" id="radio-option-4" value=${multipleChoiceSelect.multipleChoiceFour} onclick = choiceButton()></input>
                 <label for="radio-option-4">  ${multipleChoiceSelect.multipleChoiceFour} </label>
            </div>
            
            <div id="myCheckButton" style= "display:none" >
               
               <input type="button" class="multipleSelect_css" value="답 Check"  onclick=selectMutlpleCheck()></input>
            </div>
         <!-- 보기 끝 -->
          </c:if><!-- 유형이 선택되야만 보기가 보여짐 end -->
      </div>
         <div class="imgs" id="checkAnswer"></div>
         
         <br>
         <div id= "solveButton" style= "display:none" >
            <c:if test="${checkCount<multipleSelectCount}">
               <input type="hidden" name= "solveSelectId" value=${multipleSelect.mulquestId } ></input>
               <input type="button" name="nextButton" value="다음" class="multipleSelect_css" onclick=Next()></input>
            </c:if>
            <c:if test="${checkCount==multipleSelectCount && checkCount!=null }">
               <input type="hidden" name= "LastsolveSelectId" value=${multipleSelect.mulquestId } ></input>
               <input type="button" value="결과보기" class="multipleSelect_css" onclick=resultMultiple()></input>
            </c:if>
         </div><!-- solveButton end  -->
      </div><!-- 3단 col end  --> 
	</div><!-- col 12 end  -->
</div><!-- main row -->
</body>
</html>