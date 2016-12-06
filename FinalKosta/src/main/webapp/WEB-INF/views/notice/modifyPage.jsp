<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<script src="/resources/dist/js/codetrace/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>

<style>
body{
	background-image: url('/resources/dist/img/notice/notice.jpg');
		-webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
}

table{
	width: 500px;
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
	      	<div class="col-md-offset-3 col-md-6">
	         <!-- general form elements -->
	         <div class="box box-primary">
	            <div class="box-header">
	               <h3 class="box-title">공지사항 수정</h3>
	            </div>
	            <!-- /.box-header -->
	            
	<table>
		<form role="form" action="modifyPage" method="post">
		
		   <input type='hidden' name='page' value="${cri.page}"> 
		   <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
		   <input type='hidden' name='searchType' value="${cri.searchType}">
		   <input type='hidden' name='keyword' value="${cri.keyword}">
						<table>
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
		                     <td><label for="exampleInputPassword1">Content</td>
		                     <td><textarea class="form-control" name="content" rows="3">${NoticeVO.content}</textarea></td>
		                  </tr>
		                  <tr>
		                     <td><label for="exampleInputEmail1">Writer</td> 
		                     <td><input type="text" name="writer" class="form-control"
		                        value="${NoticeVO.writer}" readonly="readonly"></td>
		                  </tr>
		                  
		               </table>
		               <!-- /.box-body -->
		
								<div class="box-footer">
									<div>
										<hr>
									</div>
		
									<ul class="mailbox-attachments clearfix uploadedList">
									</ul>
									<div class="col-md-offset-4 col-md-6">
									<button type="submit" class="btn btn-primary">SAVE</button>
									<button type="submit" class="btn btn-warning">CANCEL</button>
		
								</div>
		</form>
	</table>
	</div>
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
	$(document).ready(function(){
	      
	   var formObj = $("form[role='form']");
	   
	   formObj.submit(function(event){
	      event.preventDefault();
	      
	      var that = $(this);
	      
	      var str ="";
	      $(".uploadedList .delbtn").each(function(index){
	          str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
	      });
	      
	      that.append(str);
	
	      console.log(str);
	      
	      that.get(0).submit();
	   });
	   
	   
	   $(".btn-warning").on("click", function(){
	     self.location = "/codetracce/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}"+
	           "&searchType=${cri.searchType}&keyword=${cri.keyword}";
	   });
	   
	});
	
	
	
	
	var template = Handlebars.compile($("#template").html());
	
	
	$(".fileDrop").on("dragenter dragover", function(event){
	   event.preventDefault();
	});
	
	
	$(".fileDrop").on("drop", function(event){
	   event.preventDefault();
	   
	   var files = event.originalEvent.dataTransfer.files;
	   
	   var file = files[0];
	
	   //console.log(file);
	   
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
	
	
	$(".uploadedList").on("click", ".delbtn", function(event){
	   
	   event.preventDefault();
	   
	   var that = $(this);
	    
	   $.ajax({
	      url:"/deleteFile",
	      type:"post",
	      data: {fileName:$(this).attr("href")},
	      dataType:"text",
	      success:function(result){
	         if(result == 'deleted'){
	            that.closest("li").remove();
	         }
	      }
	   });
	});
	
	
	var bno = ${NoticeVO.bno};
	var template = Handlebars.compile($("#template").html());
	
	$.getJSON("/codetrace/getAttach/"+bno,function(list){
	   $(list).each(function(){
	      
	      var fileInfo = getFileInfo(this);
	      
	      var html = template(fileInfo);
	      
	       $(".uploadedList").append(html);
	      
	   });
	});
	
	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
	   
	   var fileLink = $(this).attr("href");
	   
	   if(checkImageType(fileLink)){
	      
	      event.preventDefault();
	            
	      var imgTag = $("#popup_img");
	      imgTag.attr("src", fileLink);
	      
	      console.log(imgTag.attr("src"));
	            
	      $(".popup").show('slow');
	      imgTag.addClass("show");      
	   }   
	});
	
	$("#popup_img").on("click", function(){
	   
	   $(".popup").hide('slow');
	   
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

	<!-- /.content-wrapper -->
</body>

