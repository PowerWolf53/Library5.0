function switch_books()
{
	document.getElementById("books").style.display="block";
	document.getElementById("bookorders").style.display="none";

}

function switch_book_orders()
{
	document.getElementById("books").style.display="none";
	document.getElementById("bookorders").style.display="block";

}

function se()
{
	
	document.forms["Form"].submit();
}

function cancel_order(id){
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { command:"cancel_order",order_id:id},
		  success: function(){
			  location.reload();
	  }
	});
}

function say(){
	alert("hello");
}

function switchProfile(){
	document.getElementById("profilearea").style.display="block";	
	document.getElementById("editarea").style.display="none";
}

function switchEditProfile(){
	document.getElementById("profilearea").style.display="none";	
	document.getElementById("editarea").style.display="block";
}
