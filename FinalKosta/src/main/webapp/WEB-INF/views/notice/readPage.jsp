<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="/resources/dist/js/codetrace/jquery-1.11.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<!-- Main content -->
<style type="text/css">
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
     
     li {display:inline;}
     
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
	
	.box-footer {
	padding-top: 50px;
	}
}
</style>

<body>
    <div class='popup back' style="display:none;"></div>
    <div id="popup_front" class='popup front' style="display:none;">
     <img id="popup_img">
    </div>

	<section class="content">
	   <div class="row">
	      <!-- left column -->
	      <div class="col-md-12">
	      	<div class="col-md-offset-3 col-md-6">
	         <!-- general form elements -->
	         <div class="box box-primary">
	            <div class="box-header">
	               <h3 class="box-title">상세글 보기</h3>
	            </div>
	            <!-- /.box-header -->
				
	            <form role="form" action="modifyPage" method="post">
	
	               <input type='hidden' name='bno' value="${NoticeVO.bno}"> <input
	                  type='hidden' name='page' value="${cri.page}"> <input
	                  type='hidden' name='perPageNum' value="${cri.perPageNum}">
	               <input type='hidden' name='searchType' value="${cri.searchType}">
	               <input type='hidden' name='keyword' value="${cri.keyword}">
	
	            </form>
	
	            <table>
	               <tr>
	                  <td>Title</td> 
	                  <td><input type="text"
	                     name='title' class="form-control" value="${NoticeVO.title}"
	                     readonly="readonly"></td>
	               </tr>
	               <tr>
	                  <td>Content</td>
	                  <td><textarea class="form-control" name="content" rows="3"
	                     readonly="readonly">${NoticeVO.content}</textarea></td>
	               </tr>
	               <tr>
	                  <td>Writer</td> 
	                  <td><input type="text"
	                     name="writer" class="form-control" value="${NoticeVO.writer}"
	                     readonly="readonly"></td>
	               </tr>
	            </table>
	            
		<ul class="mailbox-attachments clearfix uploadedList"></ul>
		
		<div class="col-md-offset-3 col-md-6">
			<div class="box-footer">
			   <c:if test="${login.memberName == NoticeVO.writer}">
			   	<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
			   	<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
			  </c:if>
			   	<button type="submit" class="btn btn-primary" id="goListBtn">GO LIST </button>
			</div>
	 	</div>
	 </div>
	
	
	
	         </div>
	         <!-- /.box -->
	      </div>
	      <!--/.col (left) -->
	
	   </div>
	   <!-- /.row -->

	
	   
	</section>
	<!-- /.content -->


<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
   <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
   </span>
  </div>
</li>                
</script>  



<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
<i class="fa fa-comments bg-blue"></i>
 <div class="timeline-item" >
  <span class="time">
    <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
  </span>
  <h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
  <div class="timeline-body">{{replytext}} </div>
    <div class="timeline-footer">
     <a class="btn btn-primary btn-xs" 
       data-toggle="modal" data-target="#modifyModal">Modify</a>
    </div>
  </div>         
</li>
{{/each}}
</script>

<script>
   Handlebars.registerHelper("prettifyDate", function(timeValue) {
      var dateObj = new Date(timeValue);
      var year = dateObj.getFullYear();
      var month = dateObj.getMonth() + 1;
      var date = dateObj.getDate();
      return year + "/" + month + "/" + date;
   });

   var printData = function(replyArr, target, templateObject) {

      var template = Handlebars.compile(templateObject.html());

      var html = template(replyArr);
      $(".replyLi").remove();
      target.after(html);

   }

   var bno = ${NoticeVO.bno};
   
   var replyPage = 1;

   function getPage(pageInfo){
      
      $.getJSON(pageInfo,function(data){
         printData(data.list, $("#repliesDiv") ,$('#template'));
         printPaging(data.pageMaker, $(".pagination"));
         
         $("#modifyModal").modal('hide');
         $("#replycntSmall").html("[ " + data.pageMaker.totalCount +" ]");
         
      });
   }



   var printPaging = function(pageMaker, target) {

      var str = "";

      if (pageMaker.prev) {
         str += "<li><a href='" + (pageMaker.startPage - 1)
               + "'> << </a></li>";
      }

      for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
         var strClass = pageMaker.cri.page == i ? 'class=active' : '';
         str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
      }

      if (pageMaker.next) {
         str += "<li><a href='" + (pageMaker.endPage + 1)
               + "'> >> </a></li>";
      }

      target.html(str);
   };

   $("#repliesDiv").on("click", function() {

      if ($(".timeline li").size() > 1) {
         return;
      }
      getPage("/notice/replies/" + bno + "/1");

   });
   

   $(".pagination").on("click", "li a", function(event){
      
      event.preventDefault();
      
      replyPage = $(this).attr("href");
      
      getPage("/notice/replies/"+bno+"/"+replyPage);
      
   });
   

   $("#replyAddBtn").on("click",function(){
       
       var replyerObj = $("#newReplyWriter");
       var replytextObj = $("#newReplyText");
       var replyer = replyerObj.val();
       var replytext = replytextObj.val();
      
        
        $.ajax({
            type:'post',
            url:'/codetrace/replies/',
            headers: { 
                  "Content-Type": "application/json",
                  "X-HTTP-Method-Override": "POST" },
            dataType:'text',
            data: JSON.stringify({bno:bno, replyer:replyer, replytext:replytext}),
            success:function(result){
               console.log("result: " + result);
               if(result == 'SUCCESS'){
                  alert("등록 되었습니다.");
                  replyPage = 1;
                  getPage("/replies/"+bno+"/"+replyPage );
                  replyerObj.val("");
                  replytextObj.val("");
               }
         }});
   });


   $(".timeline").on("click", ".replyLi", function(event){
      
      var reply = $(this);
      
      $("#replytext").val(reply.find('.timeline-body').text());
      $(".modal-title").html(reply.attr("data-rno"));
      
   });
   
   

   $("#replyModBtn").on("click",function(){
        
        var rno = $(".modal-title").html();
        var replytext = $("#replytext").val();
        
        $.ajax({
            type:'patch',
            url:'/codetrace/replies/'+rno,
            headers: { 
                  "Content-Type": "application/json",
                  "X-HTTP-Method-Override": "PATCH" },
            data:JSON.stringify({replytext:replytext}), 
            dataType:'text', 
            success:function(result){
               console.log("result: " + result);
               if(result == 'SUCCESS'){
                  alert("수정 되었습니다.");
                  getPage("/replies/"+bno+"/"+replyPage );
               }
         }});
   });

   $("#replyDelBtn").on("click",function(){
        
        var rno = $(".modal-title").html();
        var replytext = $("#replytext").val();
        
        $.ajax({
            type:'delete',
            url:'/codetrace/replies/'+rno,
            headers: { 
                  "Content-Type": "application/json",
                  "X-HTTP-Method-Override": "DELETE" },
            dataType:'text', 
            success:function(result){
               console.log("result: " + result);
               if(result == 'SUCCESS'){
                  alert("삭제 되었습니다.");
                  getPage("/codetrace/replies/"+bno+"/"+replyPage );
               }
         }});
   });
   
</script>


<script>
$(document).ready(function(){
   
   var formObj = $("form[role='form']");
   
   console.log(formObj);
   
   $("#modifyBtn").on("click", function(){
      formObj.attr("action", "/notice/modifyPage");
      formObj.attr("method", "get");      
      formObj.submit();
   });
   
   $("#removeBtn").on("click", function(){
      formObj.attr("action", "/notice/removePage");
      formObj.submit();
   }); 

   
   $("#removeBtn").on("click", function(){
      
      var replyCnt =  $("#replycntSmall").html();
      
      if(replyCnt > 0 ){
         alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
         return;
      }   
      
      var arr = [];
      $(".uploadedList li").each(function(index){
          arr.push($(this).attr("data-src"));
      });
      
      /* if(arr.length > 0){
         $.post("/deleteAllFiles",{files:arr}, function(){
            
         });
      } */
      
      formObj.attr("action", "/notice/removePage");
      formObj.submit();
   });   
   
   $("#goListBtn ").on("click", function(){
      formObj.attr("method", "get");
      formObj.attr("action", "/notice/listPage");
      formObj.submit();
   });
   
   var bno = ${NoticeVO.bno};
   var template = Handlebars.compile($("#templateAttach").html());
   
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
   
      
   
});
</script>
</body>





