<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/bootstrap-3.3.2/css/kfonts2.css" rel="stylesheet">
<link href="/resources/bootstrap-3.3.2/css/custom1.css" rel="stylesheet">
<link href="/resources/bootstrap-3.3.2/css/custom2.css" rel="stylesheet">
<link href="/resources/dist/css/group/creategroup.css" rel="stylesheet">
<link href="/resources/dist/css/group/room.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="//rawgithub.com/stidges/jquery-searchable/master/dist/jquery.searchable-1.0.0.min.js"></script>
<script src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">


var text;


   $(document).ready(function(){
      $('#contact-list-search').keyup(function() {
         $.ajax({
            url : "currentMember",
            success : successHandler,
            dataType : "json",
            data : "value=" + $(this).val(),
            processData : false,
            contentType : false
         });

      });
      
      
      $(document).on('click', '.img-check', function() {
         $(this).toggleClass("check");
         text = $(this).attr('alt');
      });

      function successHandler(data) {
         var $searchList = $('.searchList');
         var html;
         $searchList.empty();
         $.each(data,function(index, entry){
            html = '<div class="col-md-3"><label class="btn btn-primary">'
                  + '<img src="/resources/dist/img/group/z.jpg" alt="'+entry.memberName+'" class="img-thumbnail img-check">'
                  + '<input type="checkbox" name="chk1" id="item4" value="val1" class="hidden" autocomplete="off">'
                  + '<h1 id="h1name">'+ entry.memberName+ '</h1></label></div>';

            $searchList.append(html);
         });

      }
      
      $("#searchedListForm").submit(function(event){
         event.preventDefault();
         $.ajax({
            url : "currentMember",
            success : addMemberList,
            dataType : "json",
            data : "value=" + text,
            processData : false,
            contentType : false
         });
      });
      function addMemberList(data){
         var $addMember = $('.addMember');
         var html;
         
         //$addMember.empty();
         $.each(data,function(index, entry){
            html =  '<div class="my-box">'+
                  '<div class="checkbox pull-left" border: 1px solid green>'+
                     '<label> <input type="checkbox" value="'+entry.memberID+'" name="check"></label></div>'+
                     '<div class="pull-left form-control-inline">'+
                     '<h4 class="list-group-item-heading">'+entry.memberName+'</h4></div>'+
                     '<img src="/resources/dist/img/group/delete.png" alt ="" style="width: 30px; height: 30px;" onclick="remove()">'+
                     '<div class="clearfix"></div>'+
                  '</div>';
            $addMember.append(html);
         });
      }
      
   });
   
   function invite(){
      var $length = $(":checkbox[name='check']:checked").length;
      var checkArr = [];
      var $groupName= $('#groupName').val();
      var $contents = $('#TA1').val();
      var userId = 'asdf';
      
      
      
      if($length != 0){
         $(":checkbox[name='check']:checked").each(function(index){   
            checkArr.push($(this).val()); 
          });
      }
      var allData = {
            "userId": userId,
            "checkArray": checkArr,
            "contents": $contents,
            "groupName": $groupName
      }
      
      $.ajax({
         url : "inviteMember",
         type: "POST",
         data :allData,
         success : function(){
            console.log('insert success');
         }
      });
      
      createRoom(allData);
      
   }
   
/*    function remove(){
      $(this).parents().remove();
   } */
   
   function createRoom(allData){
      var $container = $('.container');
      var html;
      
      html = 
            '<div class="card">'+
            '<div class="tr"></div>'+
             '<div class="tl"></div>'+
              '<div class="br"></div>'+
             '<div class="bl"></div>'+'sdf'+
            '</div>';
            
      $container.append(html);
   }
   

   
</script>
</head>
<body>
   <div class="container">

      <button class="btn btn-primary btn-lg" data-toggle="modal"
         data-target="#myModal">그룹 생성</button>

      <div class="modal" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">
                     <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                  </button>
                  <h4 class="modal-title" id="myModalLabel" align="center">팀원 생성</h4>
               </div>
               <div class="modal-body">

                  <form role="form" class="form-inline">
                     <div class="form-group">
                        <span class="lead">그룹명 입력 :&nbsp;&nbsp;</span><label for="Name"
                           class="sr-only">그룹명 입력</label> <input type="text" id="groupName"
                           class="form-control" placeholder="그룹명 입력">
                     </div>
                  </form>
                  <br> <br> <br>
                  <form id="demo-2">
                     <span class="lead">팀원 검색 :&nbsp;&nbsp;</span><input type="search"
                        placeholder="Search" id="contact-list-search">
                  </form>
                  <br> <br> <br> <br>


                  <div class="row">
                     <form id= 'searchedListForm' method="get">
                        <div class="form-group searchList">
                           
                           
                        </div>
                        <div align="center">
                           <input type="submit" value="Add member" class="btn btn-success">
                        </div>

                     </form>
                  </div>
                  <br><br>
               
               
                  <div class="list-group addMember">

                  </div>
            
                  
                  <br><br>

                  <TEXTAREA ROWS="7" COLS="30" NAME="TextArea1" ID="TA1">
                     Default text area
                  </TEXTAREA>

                  <br><br><br>

                  <div class="modal-footer">
                  <button type="button" class="btn btn-primary" onClick="invite()">완료</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
               </div>
            </div>
            <!-- 모달 콘텐츠 -->
         </div>
         <!-- 모달 다이얼로그 -->
      </div>
      <!-- 모달 전체 윈도우 -->
     
      </div>
         

</body>
</html>