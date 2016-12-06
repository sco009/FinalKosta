<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="/resources/dist/js/codetrace/jquery-1.11.0.min.js"></script>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->
				
<table>
<form role="form" method="post">

	

		<tr>
			<td>BNO</td> 
			<td><input type="text"
				name='bno' class="form-control" value="${NoticeVO.bno}"
				readonly="readonly"></td>
		</tr>

		<tr>
			<td>Title</td> 
			<td><input type="text"
				name='title' class="form-control" value="${NoticeVO.title}"></td>
		</tr>
		<tr>
			<td>Content</td>
			<td><textarea class="form-control" name="content" rows="3">${NoticeVO.content}</textarea></td>
		</tr>
		<tr>
			<td>Writer</td> 
			<td><input type="text" name="writer" class="form-control" value="${NoticeVO.writer}"></td>
		</tr>
	
	<!-- /.box-body -->
</form>
</table>

<div class="box-footer">
	<button type="submit" class="btn btn-primary">SAVE</button>
	<button type="submit" class="btn btn-warning">CANCEL</button>
</div>

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/notice/listAll";
		});

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});

	});
</script>




			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

