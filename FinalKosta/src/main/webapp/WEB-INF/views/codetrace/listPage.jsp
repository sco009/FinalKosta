<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<script src="/resources/dist/js/codetrace/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<style>
table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
  
li {display:inline;}

body{
	background-image: url('/resources/dist/img/codetrace/codetrace_bg1.jpg');
		-webkit-background-size: cover;
	        -moz-background-size: cover;
	        -o-background-size: cover;
	        /* background-size: cover; */
	        background-size: 1300px 600px;
}
</style>

<!-- Main content -->
<body>
<section class="content">
	<div class="row">
		<!-- left column -->
		
		<div class="col-md-12">
			<div class="col-md-offset-2 col-md-8">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>


				<div class='box-body'>
					<div class="col-md-offset-1 col-md-10">
					<select name="searchType">
						<option value="n"
							<c:out value="${cri.searchType == null?'selected':''}"/>>
							---</option>
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							Title</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							Content</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							Writer</option>
						<option value="tc"
							<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
							Title OR Content</option>
						<option value="cw"
							<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
							Content OR Writer</option>
						<option value="tcw"
							<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							Title OR Content OR Writer</option>
					</select> <input type="text" name='keyword' id="keywordInput"
						value='${cri.keyword }'>
					<button id='searchBtn'>Search</button>
					<button id='newBtn'>New Board</button>
					<button id='codeTraceBtn'>코디비교</button>
					</div>
				</div> 
			</div>

		
			<div class="box">
				<div class="box-header with-border"><br>
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th style="width: 100px">TITLE</th>
							<th style="width: 100px">WRITER</th>
							<th style="width: 100px">REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>

						<c:forEach items="${list}" var="CTBoardVO">

							<tr>
								<td align="center">${CTBoardVO.bno}</td>
								<td align="center"><a
									href='/codetrace/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${CTBoardVO.bno} '>
										${CTBoardVO.title} <strong>[ ${CTBoardVO.replycnt} ]</strong>
								</a></td>
								<td align="center">${CTBoardVO.writer}</td>
								<td align="center"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
										value="${CTBoardVO.regdate}" /></td>
								<td align="center"><span class="badge bg-red">${CTBoardVO.viewcnt}</span></td>
							</tr>

						</c:forEach>

					</table>
				</div>
				<!-- /.box-body -->


				<div class="box-footer">

					<div class="text-center" align="center" >
						<ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a
									href="listPage${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="listPage${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="listPage${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
							</c:if>

						</ul>
					</div>

				</div> 
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>

<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
		location.replace(self.location);
	}
</script>

<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "listPage"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});

				$('#newBtn').on("click", function(evt) {

					self.location = "register_form";

				});
				
				$('#codeTraceBtn').on("click", function(evt) {

					self.location = "codetrace";

				});

			});
</script> 
</body>

