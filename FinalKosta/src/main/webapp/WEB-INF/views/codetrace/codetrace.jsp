<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/module/header.jsp" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="/resources/dist/js/codetrace/jquery-1.11.0.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-3.3.2/css/bootstrap.min.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="/resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> 
<script>
//텝이 메모장처럼 되야하는데... 이부분 왜 안되니
function useTab(event, obj){
   if(event.keyCode == 9){
      var tab = '\t';
      obj.selection = document.selection.createRange();

      obj.selection.text = tab;

      event.returnValue = false;

   }
}
</script>
<style type="text/css">
span{
   color: red;
} 
#result{
   color: black;
}
b{
   color: green;
}
body{
   background-image: url('/resources/dist/img/codetrace/codetrace_bg1.jpg');
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        /* background-size: cover;  */
        background-size: 1300px 600px; 
}

#my_demo {
	border: 2px solid #765942;
	border-radius: 10px;
	height: 250px;
	width: 300px;
}

#your_demo {
	border: 2px solid #765942;
	border-radius: 10px;
	height: 250px;
	width: 300px;
}


</style>

</head>
<body>
<h1>&nbsp;&nbsp;&nbsp;코드트레이스</h1><br>
	<div class="col-md-12">
		<div class="col-md-offset-1 col-md-3">
				<form id="my_code">
					<textarea rows="10" cols="50" name="my_code" onkeydown="useTab(this)" id="my_demo"></textarea>
					<!-- <br> <input type="button" value="mine" id="preview"><br> -->
					<div id="mcode_result"></div> 
				</form>
		</div>

	<div class="col-md-offset-2 col-md-3">
	
		<form id="reply_code">
				<textarea rows="10" cols="50" name="reply_code" onkeydown="useTab(this)" id="your_demo"></textarea>
				<!-- <br> <input type="button" value="reply" id="preview2"><br> -->
				<div id="ycode_result"></div> 
		</form>
	</div>
</div>
	<div class="col-md-12">
	<div class="col-md-offset-4 col-md-5"><br><br><br>
			<div class="container show-grid">
				<br><input class="btn btn-success" type="button" value="Compare" id="preview">
    			<button class="btn btn-primary" id="listBtn">Go List</button>
			</div>
	</div> 
	</div>
</body> 

<head>
<title>Recipe</title>

<script>

$(document).ready(function() {
    /* document.getElementById("preview").onclick=processText;
    document.getElementById("preview2").onclick=processText2; */
    document.getElementById("preview").onclick=processText3;
   });

//내 코드를 출력하는 소스
/* function processText() {
 var txtBox = document.getElementById("my_demo");
 var lines = txtBox.value.split("\n");

 // generate HTML version of text
 var resultString  = "<p>";
 for (var i = 0; i < lines.length; i++) {
   resultString += lines[i] + "<br />";
 
 }
 
 resultString += "</p>";
 
 // print out to page
 var   blk   = document.getElementById("result");
 blk.innerHTML  =  resultString;  
 
} */

//다른 사람 코드를 출력하는 소스
/* function processText2() {
    var txtBox2 = document.getElementById("your_demo");
    var lines2 = txtBox2.value.split("\n");

    // generate HTML version of text
    var resultString2  = "<p>";
    for (var i = 0; i < lines2.length; i++) {
      resultString2 += lines2[i] + "<br />";
    
    }
    
    resultString2 += "</p>";
    
    // print out to page
    var   blk2   = document.getElementById("result2");
    blk2.innerHTML  =  resultString2; 
    
} */
   
function processText3() {
    var my_txtBox = document.getElementById("my_demo"); //my_demo textarea에 있는 글들을 my_txtBox에 넣고
    var my_code = my_txtBox.value.split("\n"); //enter를 기준으로 잘라서 my_code에 저장한다.
    
    var your_txtBox = document.getElementById("your_demo"); //your_demo textarea에 있는 글들을 your_txtBox에 넣고
    var your_code = your_txtBox.value.split("\n"); //enter를 기준으로 잘라서 your_code에 저장한다.

    // generate HTML version of text
    var resultString  = "<p>";
    for (var i = 0; i < my_code.length; i++) {
      resultString += my_code[i] + "<br />";
    
    }
    
    resultString += "</p>";
    
    var resultString2  = "<p>";
    for (var i = 0; i < your_code.length; i++) {
      resultString2 += your_code[i] + "<br />";
    
    }
    
    resultString2 += "</p>";
    
   //배열 교집합 꺼내오는 함수 -> 굳이 필요가없...
    /*  jQuery.arrayIntersect = function(a, b)
    {
       return $.grep(a, function(i){
          return $.inArray(i, b)>-1;
       });   
    }; */
    
   // var isExist = (lines.indexOf(lines2)== -1 )
    
   // document.write(lines.indexOf(lines2));

    //document.write($.arrayIntersect(lines, lines2));
    
    //'/n'으로 잘려 들어간 배열을 index별로 비교
    
      /* var resultString3 = "";
      //var resultIntersect = $.arrayIntersect(lines, lines2);
                
          for(var i=0; i<lines.length;i++){
             
             if(lines[i]===lines2[i]){
                resultString3 += "<p>";
                resultString3 += lines[i] + "<br />";
                resultString3 += "</p>";
                alert("??");
                
             }else{
                resultString3 += "<p>" + "<span>";
                resultString3 += lines[i] + "<br />";
                resultString3 += "</span>" + "</p>";
                alert("들리니 내말이 들리니");
             
          }
       }  */ 
       
       var resultString3 = "";
         //var resultIntersect = $.arrayIntersect(lines, lines2);
                   
             for(var i=0; i<my_code.length;i++){
                
                if(my_code[i]===your_code[i]){
                   //resultString4 += lines[i] + "<br />";
                   //re_split = resultString3.split('');
                   resultString3 += "<p>";
                   resultString3 += my_code[i] + "<br />";
                   resultString3 += "</p>";
                   
                }else{
                   //resultString4 += lines[i] + "<br />";
                   //alert("들리니 내말이 들리니");
                  resultString3 += "<p>" + "<span>";
                   resultString3 += my_code[i] + "<br />";
                   resultString3 += "</span>" + "</p>";
                
             }
          }  
                
       var   blk3   = document.getElementById("mcode_result");
       blk3.innerHTML  = resultString3; 
       
       
       
       
       var re_split = my_txtBox.value.split('');
       var re_spl = your_txtBox.value.split('');
      
       alert(re_split);
       alert(re_spl);
      
      var resultString5 = "<p>";
      for(var i=0; i<re_spl.length;i++){
      
          if(re_split[i]===re_spl[i]){
             //resultString5 += "<br />";
             resultString5 += re_spl[i];
             
          }else{
             //resultString5 += "<br />";
             resultString5 += "<b>";
             resultString5 += re_spl[i];
             resultString5 += "</b>"; 
          }
          
       }
      resultString3 += "</p>"+"<br />";
       
       var   blk4   = document.getElementById("ycode_result");
       blk4.innerHTML  = resultString5; 

	}
			 
</script>

<script>
$(document).ready(
		function() {
			$('#listBtn').on("click", function(evt) {
				self.location = "listPage";

			});
			
		});
</script>

</head>


</html>