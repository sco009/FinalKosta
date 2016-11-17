function Next() {
	
	location.href= "/subjective"
	if (subjectiveReply !== subjectiveAnswer) {
		location.href = "SubjectiveMain.jsp?solveFailId=" + solveSelectId + "&nextCheck=0";	//&nextCheck=0 <- 다음으로 넘겼을 때만 카운트가 증가
	} else {																			    //할 수 있도록 확인값을 넘겨준다.
		location.href = "SubjectiveMain.jsp?solveSuccessId=" + solveSelectId + "&nextCheck=0";
	}
}

function subjectiveCheck() {
	var subjectiveReply = $(":input:hidden[name=result]").val();
	var subjectiveAnswer = $(":input:hidden[name=subjectiveAnswer]").val();
	var subjectiveSelect = $(":input:hidden[name=subjectiveSelect]").val();
	var check = "";
	
	
	if(subjectiveAnswer===subjectiveReply){
		$.ajax({
			url:"deleteFile",
			type:"post",
			data: {fileName:$(this).attr("data-src")},
		})
		check = "<img src='/resources/dist/img/quest/subjective/ok.gif'>";
	}else{
		check = "<img src='/resources/dist/img/quest/subjective/x.gif'>";
	}
	document.getElementById("checkAnswer").innerHTML = check;
}

function resultMultiple() {
	var subjectiveReply = $(":input:radio[name=subjectiveReply]").val(); // 마지막 값은 바로 resultMultiple.jsp로 넘겨준다.
	var subjectiveAnswer = $(":input:hidden[name=subjectiveAnswer]").val();
	var solveSelectId = $(":input:hidden[name=solveSelectId]").val();
	if (subjectiveReply !== subjectiveAnswer) {
		location.href = "resultSubjective.jsp?solveFailId=" + solveSelectId;
	} else {
		location.href = "resultSubjective.jsp?solveSuccessId=" + solveSelectId;
	}
}

$(document).ready(function() {
	$('[data-toggle="popover"]').popover({
		container : "body"
	});
});

$(function() {
	$('.dropdown-el').click(function(e) {

		e.preventDefault();
		e.stopPropagation();
		var el = $(this);
		$(el).toggleClass('expanded');
		$('#' + $(e.target).attr('for')).prop('checked', true);
	});

	$('.dropdown-els').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		var els = $(this);
		$(els).toggleClass('expanded');
		$('#' + $(e.target).attr('for')).prop('checked', true);
	});
	$('.dropdown-e').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		var els = $(this);
		$(els).toggleClass('expanded');
		$('#' + $(e.target).attr('for')).prop('checked', true);
	});
});


//웹컴파일러 js
$(function(){
	$("#complieFrom").submit(function(event){
		event.preventDefault();
		
		$.ajax({
			url : "compile",
			type : "post",
			dataType:"text",
			data : $(this).serialize(),
			success : successHandler,
			error : function(){
				alert("실패");
			}
		});
	});
	
	function successHandler(data){
		var str = data.replace(/^\s+/, "");
		$("#wc_result").empty();
		$("#wc_result").text(str);
		document.getElementById("confirm").style.display = "block";//<- 컴파일버튼을 눌렀을 때 답확인 버튼 활성화
		document.getElementById("hiddenResult").value=str.trim();
		
	}
	
	$(document).ajaxStart(function(){
	      $("#loading").fadeIn();
	   }).ajaxStop(function(){
		   $("#loading").fadeOut();
	   });
});