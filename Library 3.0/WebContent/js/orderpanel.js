function sayHello(){
	alert("hello");
}

function validate_year(id){
	
var inp=document.getElementById(id).value;

if(inp<2019||isNaN(inp)||inp==null){
	alert("wrong year");
	return false;
}else{
	return true;
}

		
}

function validate_month(id){
	
var inp=document.getElementById(id).value;

if(inp<0||isNaN(inp)||inp>12||inp==null||inp==""){
	alert("wrong month");
	return false;
}else{
	return true;
}

		
}


function validate_day(id){
	
var inp=document.getElementById(id).value;

if(inp<0||isNaN(inp)||inp>31||inp==null||inp==""){
	alert("wrong day");
	return false;
}else{
	return true;
}

		
}

function sendRequest(userId,bookId){
	$.ajax({
		 type: "POST",
		  url: 'LibraryServlet',
		  data: { command:"get_orders" },
		  success: function(){
			  location.reload();
	  }
	});
	
}

function validate(year,month,day) {
	if(validate_year(year)==false||validate_month(month)==false||validate_day(day)==false){
		return false;
		}else{
			return true;
		}
}

function send()
{
	
	document.forms["orders"].submit();
}

function cancel(id){
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { command:"cancel_order",order_id:id},
		  success: function(){
			  location.reload();
	  }
	});
}

function submit(orderId,bookId,year,month,day,userId){
		if(validate(year,month,day)==true){
		var yearDate=document.getElementById(year).value;
		var monthDate=document.getElementById(month).value;
		var dayDate=document.getElementById(day).value;
		var fullDate="";
		fullDate=fullDate.concat(yearDate,".",monthDate,".",dayDate);
		sendSubmitRequest(orderId,bookId,fullDate,userId)
	
		}else{
		alert("wrong");
		}
		
}

function sendSubmitRequest(oderId,bookId,fullDate,userId){
	
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { command:"submit_order",order_id:oderId,book_id:bookId,expire_date:fullDate,user_id:userId},
		  success: function(){
			  location.reload();
	  }
	});
}
