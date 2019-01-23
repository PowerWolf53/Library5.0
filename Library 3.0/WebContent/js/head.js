function say(){
	alert("hello");
}

function switch_sub()
{
	var sub=document.getElementById("subheader");
	
	if(sub.style.display=="none")
		{
		sub.style.display="block";
		}else{
			sub.style.display="none";
		}
}

function switcher()
{
var sub=document.getElementById("sub");
	
	if(sub.style.display=="none")
		{
		sub.style.display="block";
		}else{
			sub.style.display="none";
		}
}

function get_eng_locale()
{
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { locale: "eng",command:"getlocale" },
		
	});
	location.reload();
}

function get_ger_locale()
{
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { locale: "ger",command:"getlocale" },
		
	});
	location.reload();
}

function get_ru_locale()
{
	$.ajax({
		 type: "POST",
		  url: 'AjaxServlet',
		  data: { locale: "ru",command:"getlocale" },
		
	});
	location.reload();
}

