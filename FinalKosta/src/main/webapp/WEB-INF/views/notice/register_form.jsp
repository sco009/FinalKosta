<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resources/dist/js/codetrace/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<style>
table{
	width: 500px;
}

body{
	background-image: url('/resources/dist/img/notice/notice.jpg');
		-webkit-background-size: cover;
	        -moz-background-size: cover;
	        -o-background-size: cover;
	        background-size: cover;
}

.box-title {
	padding-left: 150px;
	font-size: 35px;
	padding-bottom: 80px;
}

.box-primary {
	padding-left: 80px;
}

</style>

<body>
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- general form elements -->
				<div class="col-md-offset-3 col-md-6">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">공지사항 작성</h3>
					</div>
					<!-- /.box-header -->
	
		<table summary="글쓰기 테이블">
		<form id='registerForm' role="form" method="post">
			
			 <table summary="테이블 구성">
			 		<tr>
					<td align="center">Title</td> 
					<td align="center"><input type="text"
						name='title' class="form-control" placeholder="Enter Title"></td>
					</tr>
					<tr>
					<td align="center">Content</td>
					<td align="center"><textarea class="form-control" name="content" rows="3"
						placeholder="Enter ..."></textarea></td>
					</tr>
					<tr>
					<td align="center">Writer</td> 
					<td align="center"><input type="text"
						name="writer" class="form-control" value="${login.memberName }" readonly="readonly"></td>
					</tr>
				
			</table>
			<br>
			<!-- /.box-body -->
				<div class="col-md-offset-5 col-md-3">
					<button type="submit" class="btn btn-primary">작성완료</button>
				</div>
			
		</form> 
		</table>
	
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
	
	<script type="text/javascript" src="/resources/js/upload.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	
	<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
	</script>    
	
	<script>
	
	var template = Handlebars.compile($("#template").html());
	
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});
	
	
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];
	
		var formData = new FormData();
		
		formData.append("file", file);	
		
		
		$.ajax({
			  url: '/uploadAjax',
			  data: formData,
			  dataType:'text',
			  processData: false,
			  contentType: false,
			  type: 'POST',
			  success: function(data){
				  
				  var fileInfo = getFileInfo(data);
				  
				  var html = template(fileInfo);
				  
				  $(".uploadedList").append(html);
			  }
			});	
	});
	
	$(".uploadedList").on("click","a",function(event){
		   event.preventDefault();
		   var that = $(this);
		   $.ajax({
		      url : "/deleteFile",
		      type : "post",
		      data : {fileName:$(this).attr("data-src")},
		      dataType : "text",
		      success : function(result){
		         if(result == "deleted"){
		        	that.remove();
		            alert("deleted");
		         }
		      }
		   })
	});
	
	
	$("#registerForm").submit(function(event){
		event.preventDefault();
		
		var that = $(this);
		
		var str ="";
		$(".uploadedList .delbtn").each(function(index){
			 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
		});
		
		that.append(str);
	
		that.get(0).submit();
	});
	
	
	
	</script>
</body>
 


