<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/dist/js/quest/subjective/subjectiveResult.js"></script>

<link href="/resources/dist/css/quest/subjective/subjectiveMenu.css" rel="stylesheet" type="text/css">
<link href="/resources/dist/css/quest/subjective/subjectiveRadioButton.css" rel="stylesheet" type="text/css">
<link href="/resources/dist/css/quest/subjective/resultSubjective.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function returnSucjective() {
	location.href= "initialization";
}
</script>
</head>
<body id="resultBody">

      
   <div class="col-md-12">
      <c:if test="${successProgress !=100 }">
      <div class="progress">
         <c:if test="${successProgress!=0 }">
         <div class="progress-bar" role="progressbar"
            aria-valuenow=${successProgress } aria-valuemin="0"
            aria-valuemax="100" style="width:${successProgress }%;">
            Success : ${successProgress }%
         </div>
         </c:if>
         <c:if test="${successProgress==0 }">
         <div class="progress-bar" role="progressbar"
            aria-valuenow=100 aria-valuemin="0"
            aria-valuemax="100" style="width:100%;">
            Success : 0
         </c:if>
      </div>
      </c:if>
      <c:if test="${successProgress==100 }">
      		<div class="totalImg">
            <img src='/resources/dist/img/quest/subjective/total100.gif'>
            </div>
      </c:if>
   </div>
   
 	<div class="col-md-12">
      <c:forEach var="failList" items="${failList }" begin="0"
         end="${failList.size() }">
         <c:if test="${failList !=null }">
               <label class="row-md">틀린문제 : ${failList.subj_Content }</label>
            <button type="button" class="btn btn-default"
               title="문제내용 : ${failList.subj_Content }" data-container="body"
               data-toggle="popover" data-placement="right"
               data-content="답 : ${failList.subj_Answer }">틀린문제 상세보기</button>
            <!-- 틀린문제 정보 보여주기 -->
            <br>
         </c:if>
      </c:forEach>
   </div>
	
	<%-- <button type="button" class="btn btn-default"
					title="문제내용 : ${failList.subj_Content }" data-container="body"
					data-toggle="popover" data-placement="right"
					data-content="답 : ${failList.subj_Answer }">틀린문제 상세보기</button> --%>
				<!-- 틀린문제 정보 보여주기 -->
   <br>
   <br>
   <div class="col-md-12">
   <c:if test="${successList.size()>0 }">      <!-- 회원이 1문제 이상 맞췄을 때 생기는 버튼 -->
      <form action="finishSubjective" method="POST">         
         <input type="hidden" name = "memberId" value=${memberId }></input>
         <input type="hidden" name = "successPoint" value=${successPoint }></input>
         <input type="submit" class = "subjectiveSelect-css returnButton" value="돌아가기" ></input>
      </form>
   </c:if>
   <c:if test="${successList.size() ==0 }">   <!-- 회원이 0문제 맞췃을 때 생기는 버튼 -->
      <input type=button class = "subjectiveSelect-css returnButton" value="돌아가기" onclick=returnSucjective() ></input>
   </c:if>
   </div>
</body>
</html>