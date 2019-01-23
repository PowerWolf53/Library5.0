function send()
{
	$.ajax({
		 type: "POST",
		  url: 'TestServlet',
		  data: { name: "John", location: "Boston" },
		  success: function(){
	    alert('Load was performed.');
	  }
	});

}


function reload()
{
	location.reload();
}