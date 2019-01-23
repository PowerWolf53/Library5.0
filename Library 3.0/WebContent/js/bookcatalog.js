


	function send(){
        $.ajax
        ({
            type: "GET",//Метод передачи
            url: 'TestServlet',//Название сервлета
            data: { name: "John", location: "Boston" }  
        });
	}
  
	function se()
	{
		
		document.forms["Form"].submit();
	}
	
	function subm(form)
	{
		document.forms[form].submit();
		
	}

