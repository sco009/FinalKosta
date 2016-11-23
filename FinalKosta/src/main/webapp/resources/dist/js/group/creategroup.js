/*$(function(){
	$('body').on("contextmenu",'.list-group-item',function(event) { 
	    event.preventDefault();
	    $("<span class='custom-menu'>초대하기</span>").appendTo(".list-group-item").css({top: event.pageY + "px", left: event.pageX + "px"}).on("click", function(event){
	    	 var id = event.target.getAttribute('id');
	    	 console.log(id);
	    });
	}).on("click", function(event) {
	    $("div.custom-menu").remove();
	});
	mousedown(function(event){
	   if(event.button==2){
		  
		  
	   }else{
		   $("div.custom-menu").remove();
	   }
	 });
	

});*/


$(document).ready(function(e){
    		$(".img-check").click(function(){
				$(this).toggleClass("check");
			});
	});