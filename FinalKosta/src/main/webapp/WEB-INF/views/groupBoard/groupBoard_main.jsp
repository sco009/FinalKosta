<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Draggable - Default functionality</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="/resources/dist/css/groupBoard/groupstyle.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/dist/js/groupBoard/groupBoardJavaScript.js"></script>
<script>
	$(function() {
		$("#draggable").draggable({
			containment : ".well",
			scroll : false
		});
	});

	   
	   function test(){
	        var con = document.getElementById("#chat");
	          if(con.style.display=='none'){
	              con.style.display = 'block';
	          }else{
	              con.style.display = 'none';
	          }
	   }
	   
	   function openNav() {
		      document.getElementById("mySidenav").style.width = "250px";
		   }

		   function closeNav() {
		      document.getElementById("mySidenav").style.width = "0";
		   }
		   
</script>
<script type="text/javascript">
window.onload= function() {
	var resetPage = setInterval(function() {
		$.ajax({
			url : "realTimeScrum",
			dataType : "json",
			success : successhandler
			});
	}, 10000);
	
	function successhandler(data) {
		var $writerText = $("#writerText");
		var $memberList = $("#memberList");
		$memberList.empty();
		$writerText.empty();
		
		var html = "";
		var html2 = "";
		$.each(data, function(index, entry) {
			html2 += "<option value=" + entry.leaderId + ">" + entry.leaderId + "</option>";
			html2 += "<option value=" + entry.team1 + ">" + entry.team1 + "</option>";
			html2 += "<option value=" + entry.team2 + ">" + entry.team2 + "</option>";
			html2 += "<option value=" + entry.team3 + ">" + entry.team3 + "</option>";
			html2 += "<option value=" + entry.team4 + ">" + entry.team4 + "</option>";
			html2 += "<option value=" + entry.team5 + ">" + entry.team5 + "</option>";
			html2 += "<option value=" + entry.team6 + ">" + entry.team6 + "</option>";
		});
		$writerText.append(html2);
		
		$.each(data, function(index, entry){
			html += "<dd>" + entry.leaderId + "</dd>";
			html += "<dd>" + entry.team1 + "</dd>";
			html += "<dd>" + entry.team2 + "</dd>";
			html += "<dd>" + entry.team3 + "</dd>";
			html += "<dd>" + entry.team4 + "</dd>";
			html += "<dd>" + entry.team5 + "</dd>";
			html += "<dd>" + entry.team6 + "</dd>";
		});
		$memberList.append(html);
	}
}
	
</script>
<jsp:include page="/WEB-INF/views/module/header.jsp" />
</head>
<body>
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-1">
            <span style="font-size: 300%; cursor: pointer" onclick="openNav()">&#9776;</span>
            <div id="mySidenav" class="sidenav">
               <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
               <a href="main">애자일 스크럼</a> <a href="/groupcalender/groupCalenderMain">일정보기</a> <a href="../notice/listPage">공지사항</a> 
               <a href="/webcompile/main">웹컴파일</a>
            </div>
         </div>
         
			<div class="col-md-2" id="ToDo" ondrop="drop(event)"
				ondragover="allowDrop(event)">
				<div class="well" id="ToDoDiv">
					<h3>ToDo</h3>

					<c:if test="${list!=null}">
						<c:forEach var="boardList" items="${list }" begin="0"
							end="${list.size()}">
							<c:if test="${boardList.gBoardCategori eq 'ToDo' }">
								<div id="draggable${boardList.groupBoardId }"
									class="ui-widget-content" draggable="true"
									ondragstart="drag(event)">
									<div id="ToDoElementA${boardList.groupBoardId }">■요구사항명■
										${boardList.gBoardTitle }</div>
									<div id="ToDoElementB${boardList.groupBoardId }">■요구사항■
										${boardList.gBoardContent }</div>
									<div id="ToDoElementC${boardList.groupBoardId }">■담당자■
										${boardList.gBoardWriter }</div>
									<div id="ToDoElementD${boardList.groupBoardId }">■기간■
										${boardList.gBoardPeriod }</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				</div>
			</div>

			<div class="col-md-2" id="ING" ondrop="drop(event)"
				ondragover="allowDrop(event)">
				<div class="well" id="INGDiv">
					<h3>~ING</h3>

					<c:if test="${list!=null}">
						<c:forEach var="boardList" items="${list }" begin="0"
							end="${list.size()}">
							<c:if test="${boardList.gBoardCategori eq 'ING' }">
								<div id="draggable${boardList.groupBoardId }"
									class="ui-widget-content" draggable="true"
									ondragstart="drag(event)">
									<div id="INGelementA${boardList.groupBoardId }">■요구사항명■
										${boardList.gBoardTitle }</div>
									<div id="INGelementB${boardList.groupBoardId }">■요구사항■
										${boardList.gBoardContent }</div>
									<div id="INGelementC${boardList.groupBoardId }">■담당자■
										${boardList.gBoardWriter }</div>
									<div id="INGelementD${boardList.groupBoardId }">■기간■
										${boardList.gBoardPeriod }</div>
									<div id="INGelementE${boardList.groupBoardId }" class="startDay">■시작일■
										${boardList.gBoardRegdate }</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>

				</div>
			</div>
			<div class="col-md-2" id="END" ondrop="drop(event)"
				ondragover="allowDrop(event)">
				<div class="well" id="ENDDiv">
					<h3>END</h3>

					<c:if test="${list!=null}">
						<c:forEach var="boardList" items="${list }" begin="0"
							end="${list.size()}">
							<c:if test="${boardList.gBoardCategori eq 'END' }">
								<div id="draggable${boardList.groupBoardId }"
									class="ui-widget-content" draggable="true"
									ondragstart="drag(event)">
									<div id="ENDelementA${boardList.groupBoardId }">■요구사항명■
										${boardList.gBoardTitle }</div>
									<div id="ENDelementB${boardList.groupBoardId }">■요구사항■
										${boardList.gBoardContent }</div>
									<div id="ENDelementC${boardList.groupBoardId }">■담당자■
										${boardList.gBoardWriter }</div>
									<div id="ENDelementD${boardList.groupBoardId }">■기간■
										${boardList.gBoardPeriod }</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>

				</div>
			</div>
			<div class="col-md-1" id="Issue" ondrop="drop(event)"
				ondragover="allowDrop(event)">
				<div class="well" id="IssueDiv">
					<h3>Issue and RISK</h3>

					<c:if test="${list!=null}">
						<c:forEach var="boardList" items="${list }" begin="0"
							end="${list.size()}">
							<c:if test="${boardList.gBoardCategori eq 'Issue' }">
								<div id="draggable${boardList.groupBoardId }"
									class="ui-widget-content" draggable="true"
									ondragstart="drag(event)">
									<div id="IssueElementA${boardList.groupBoardId }">■요구사항명■
										${boardList.gBoardTitle }</div>
									<div id="IssueElementB${boardList.groupBoardId }">■요구사항■
										${boardList.gBoardContent }</div>
									<div id="IssueElementC${boardList.groupBoardId }">■담당자■
										${boardList.gBoardWriter }</div>
									<div id="IssueElementD${boardList.groupBoardId }">■기간■
										${boardList.gBoardPeriod }</div>

								</div>
							</c:if>
						</c:forEach>
					</c:if>

				</div>
			</div>
			<div class="col-md-2">
				<div class="well">
					<h3>ADD</h3>
					<form id="addForm" action="saveData">
						<input type="hidden" name="groupId" value=${groupId }></input> 
						<input type="hidden" name="gBoardCategori" value="ToDo"></input>

						<input type="text" id="titleText" name="gBoardTitle"
							placeholder="요구사항명"></input>
						<textarea type="text" id="contentText" name="gBoardContent"
							placeholder="요구사항내용"></textarea>

						<br> <label>담당자</label> <select class="form-control" id="writerText" name="gBoardWriter">
							<option value="${member.leaderId}">${member.leaderId}</option>
							<option value="${member.team1}">${member.team1}</option>
							<option value="${member.team2}">${member.team2}</option>
							<option value="${member.team3}">${member.team3}</option>
							<option value="${member.team4}">${member.team4}</option>
							<option value="${member.team5}">${member.team5}</option>
							<option value="${member.team6}">${member.team6}</option>
						</select> <br> <label>기한</label> <select class="form-control" id="periodText" name="gBoardPeriod">
							<option value="4H">4H</option>
							<option value="6H">6H</option>
							<option value="8H">8H</option>
							<option value="1D">1D</option>
							<option value="3D">3D</option>
							<option value="1W">1W</option>
						</select> <br> <br> <input type="submit" class="btn btn-info"
							value="ADD"></input>
					</form>
					<div id="deletes" ondrop="drop(event)"
						ondragover="allowDrop(event)">
						휴 지 통 <span class="glyphicon glyphicon-heart"></span>
					</div>
				</div>
			</div >
			<div class="col-md-1">
			<a onclick="test()"><img src="/resources/dist/img/chat/chatimg.png"></a>   
   <div  id="#chat"> 
   <iframe  src="http://localhost:3000/?id=${memberId}&roomid=${groupId}"  width="300px" height="600px"></iframe>
   </div>
   </div>
			

		</div>
		<div class="row">
			<div class="col-md-offset-1 col-md-2">
				<div class="well">
					<dl id="memberList">
					<dt><h3>그룹원</h3></dt>
						<dd>${member.leaderId}</dd>
						<dd>${member.team1}</dd>
						<dd>${member.team2}</dd>
						<dd>${member.team3}</dd>
						<dd>${member.team4}</dd>
						<dd>${member.team5}</dd>
						<dd>${member.team6}</dd>
					</dl>
				</div>
			</div>

			<div class="col-md-4">
				<div class="well">
					<table class="table table-bordered" border="1">
						<h2>scrum 현황 !</h2>
						<tr class="active info">
							<th>ToDo</th>
							<th>ING</th>
							<th>END</th>
						</tr>
						<tr id="scrumTable">
							<td>${scrumMap.get("ToDo") }</td>
							<td>${scrumMap.get("ING") }</td>
							<td>${scrumMap.get("END") }</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</div>
	
	<!-- col-md-12 end -->
</body>
</html>