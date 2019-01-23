function order(userId,bookId){
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { id: userId, book_id: bookId,command:"order_book" },
		  success: function(){
			  location.reload();
			  alert("book ordered");
	  }
	});
	
}