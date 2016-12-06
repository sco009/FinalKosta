<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>COSMOS MAIN</title>

<link rel="stylesheet" type="text/css"
   href="/resources/dist/css/login/mainMenu1.css">
<link rel="stylesheet" type="text/css"
   href="/resources/dist/css/login/mainMenu2.css">
<link rel="stylesheet" type="text/css"
   href="/resources/dist/css/login/mainMenu3.css">
<link href="/resources/dist/css/login/notify.css" rel="stylesheet">
<!-- 부트스트랩 시작  -->

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/resources/bootstrap-3.3.2/css/bootstrap.min.css"
   rel="stylesheet">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript"
   src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<!-- 부트스트랩 끝 -->

<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="description"
   content="Custom Drop-Down List Styling with CSS3" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css"
   href="/resources/dist/css/login/drop_style.css" />
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700'
   rel='stylesheet' type='text/css' />
<script type="text/javascript"
   src="/resources/dist/js/login/modernizr.custom.79639.js"></script>
</head>


<!-- jQuery if needed -->
<script type="text/javascript">
   function DropDown(el) {
      this.dd = el;
      this.initEvents();
   }
   DropDown.prototype = {
      initEvents : function() {
         var obj = this;

         obj.dd.on('click', function(event) {
            $(this).toggleClass('active');
            event.stopPropagation();
         });
      }
   }

   $(function() {

      var dd = new DropDown($('#dd'));

      $(document).click(function() {
         // all dropdowns
         $('.wrapper-dropdown-5').removeClass('active');
      });

   });
</script>

<script type="text/javascript">
   var icount;
   $(document).ready(function() {
      $.ajax({
         url : "/login/invite",
         success : successHandler,
         dataType : "json"
      });

      setInterval(function() {
         $.ajax({
            url : "/login/invite",
            success : successHandler,
            dataType : "text"
         });
      }, 3000);

   });

   function successHandler(data) {
      $('#notification').html(data);
   };
   

   function successHandler2(data) {
      var $table = $('.table');
      $table.find('tbody').empty();

      $
            .each(
                  data,
                  function(index, entry) {
                     var html = '<tr id='+entry.inviteID+'><td>'
                           + entry.sendPerson
                           + '</td><td>'
                           + entry.contents
                           + '</td><td id='+entry.groupID+'><input type="checkbox" name="subCheck" value='+entry.inviteID+'></td></tr>'
                     $table.find('tbody').append(html);
               });
   }

   function check() {
	   $.ajax({
           url : "/login/invitelist",
           success : successHandler2,
           dataType : "json"
        });
	   
      setInterval(function() {
         $.ajax({
            url : "/login/invitelist",
            success : successHandler2,
            dataType : "json"
         });
      }, 5000);
   }

   $(function() {
      $('#checkedAll').click(function() {
         if ($('#checkedAll').prop('checked')) {
            $('input[name=subCheck]:checkbox').each(function() {
               $(this).prop('checked', true);
            });
         } else {
            $('input[name=subCheck]:checkbox').each(function() {
               $(this).prop('checked', false);
            });
         }
      })
   });

   function reject() { // 초대 거절
      var tbody = document.getElementById("inviteList");
      console.log(tbody.innerHTML);

      // 체크박스 전부 찾기
      var boxes = document.getElementsByName("subCheck");
      for (var i = boxes.length; i > 0; i--) {
         // 체크박스를 순서대로 하나씩 꺼낸다.
         var checkbox = boxes[i - 1];

         if(checkbox.checked == true) {
            // 체크박스의 값을 읽어온다. ---> 삭제할 tr의 id를 얻음
            var rowid = checkbox.value;//inviteID
            console.log(rowid);
            
            rejectInvite(rowid);
            
            // 삭제할 tr 찾기
            var tr = document.getElementById(rowid);
            // tbody에서 tr지우기
            tbody.removeChild(tr);
         }
      }
   }
   
   function accept1() { // 초대 수락
      var tbody = document.getElementById("inviteList");
      console.log(tbody.innerHTML);

      // 체크박스 전부 찾기
      var $boxes = document.getElementsByName("subCheck");
      for (var i = $boxes.length; i > 0; i--) {
         // 체크박스를 순서대로 하나씩 꺼낸다.
         var checkbox = $boxes[i - 1];

         if(checkbox.checked == true) {
            // 체크박스의 값을 읽어온다. ---> 삭제할 tr의 id를 얻음
            var rowid = checkbox.value;//inviteID
            /* var groupid = checkbox.parent().parent().attr('id'); */
            var groupid = $('input').parents('td').attr('id');
            
            joinGroup(rowid, groupid); // 수락한 내 ID를 DB에서 해당 그룹에 넣어줌
            
            // 삭제할 tr 찾기
            var tr = document.getElementById(rowid);
            // tbody에서 tr지우기
            tbody.removeChild(tr);
         }
      }
   }

   function joinGroup(rowid, groupid){
      $.ajax({
         url : "joinGroup",
         data : "inviteID="+rowid+"&"+"groupID="+groupid
         
      });
   }
   
   function rejectInvite(rowid){
      $.ajax({
         url : "rejectInvite",
         data : "inviteID="+rowid
      });
   }
   
</script>

<body>
   <div class="drop_container">

      <section class="drop_main">

      <div class="wrapper-demo">
         <div id="dd" class="wrapper-dropdown-5" tabindex="1">
            ${login.memberName }
            <ul class="dropdown">
               <li><a href="TestAr.jsp"><i class="icon-user"></i>접속기록 확인</a></li>
               <li><a href="updateInfo.jsp"><i class="icon-cog"></i>회원정보수정</a></li>
               <li><a href="/login/logout"><i class="icon-remove"></i>로그아웃</a></li>
            </ul>
         </div>
         ​
      </div>

      <div id='center'>
         <div id='top'>
            <div id='orange' data-toggle="modal" data-target="#myModal"
               onclick="check()">
               <img src='https://az31353.vo.msecnd.net/c04/qmcr.png'>
            </div>
            <div id='notification'>${icount}</div>
         </div>
      </div>

      <div class="modal" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">
                     <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                  </button>
                  <h4 class="modal-title" id="myModalLabel" align="center">초대
                     내역</h4>
               </div>

               <div class="container2">
                  <table class="table table-striped">
                     <thead>
                        <tr>
                           <th>초대한 회원</th>
                           <th>초대 내용</th>
                           <th><input type="checkbox" id="checkedAll"></th>
                        </tr>
                     </thead>
                     <tbody id="inviteList">

                     </tbody>
                  </table>
               </div>
               <div class="modal-body">
                  <div align="center">
                     <input type="button" value="수락" class="btn btn-success" onclick="accept1()"> 
                     <input type="button" value="거절" class="btn btn-fail" onclick="reject()">
                  </div>
               </div>
               <br> <br>
               <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
               </div>
            </div>
            <!-- 모달 콘텐츠 -->
         </div>
         <!-- 모달 다이얼로그 -->
      </div>
      <!-- 모달 전체 윈도우 -->
   </div>
   </section>

   </div>

   <div class="container">
      <div class="row-fluid">
         <div class="col-md-12">
            <div class="row-fluid">
               <div class="col-md-3 col-md-offset-1">
                  <input id='check' type='checkbox'> <label class='main'
                     for='check'>
                     <div class='title'>
                        <i class="fa fa-graduation-cap" aria-hidden="true"></i>
                     </div>
                  </label>
                  <div class="ma">
                     <a class="plate" href="/education?memberID= ${login.memberID }"> <i
                        class="fa fa-book" aria-hidden="true"></i>
                     </a> <a class="plate" href="/multiple/multiple?memberID= ${login.memberID }">
                        <i class="fa fa-pencil" aria-hidden="true"></i>
                     </a> <a class="plate" href="/codetrace/register_form?memberID=${login.memberID }&"> <i
                        class="fa fa-balance-scale" aria-hidden="true"></i>
                     </a> <a class="plate" href="/subjective/main?memberId=${login.memberID }"> <i
                        class="fa fa-keyboard-o" aria-hidden="true"></i>
                     </a> <a class="plate" href="/ranking_main"></i>
                     </a>
                  </div>
               </div>

               <div class="col-md-4">
                  <input id='check2' type='checkbox'></input> <label class='main2'
                     for='check2'>
                     <div class='title2'>
                        <i class="fa fa-users" aria-hidden="true"></i>
                     </div>
                  </label>
                  <div class="ma2">
                     <a class="plate2" href="/group/creategroup"> <i
                        class="fa fa-user-plus" aria-hidden="true"></i>
                     </a> <a class="plate2" href="enterGroup.jsp"> <i
                        class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>
                     </a> <a class="plate2" href="/webcompile/main">
                        <i class="fa fa-file-code-o" aria-hidden="true"></i>
                     </a>
                  </div>
               </div>

               <div class="col-md-3 col-md-offset-1*">
                  <input id='check3' type='checkbox'> <label class='main3'
                     for='check3'>
                     <div class='title3'>
                        <i class="fa fa-pencil-square-o" aria-hidden="true"><a
                           class="plate3" href="#"></a></i>
                     </div>
                  </label>
               </div>
            </div>
         </div>
      </div>
   </div>
</body>
</html>