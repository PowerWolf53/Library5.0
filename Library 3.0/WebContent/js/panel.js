function verification()
{
	  var note=document.getElementById("not");
	if(!isNaN(document.getElementById("ammount").value)&&document.getElementById("ammount").value>0){
   document.getElementById("subm").disabled=false;
 
		 note.style.display="none";
   
	}else{
		document.getElementById("subm").disabled=true;
		 note.style.display="block";
	}

}

function second_verification()
{
 var note=document.getElementById("not2");
	if(!isNaN(document.getElementById("ammount2").value)&&document.getElementById("ammount2").value>0){
   document.getElementById("subm2").disabled=false;
 
		 note.style.display="none";
   
	}else{
		document.getElementById("subm2").disabled=true;
		 note.style.display="block";
	}
}	





function switcher1(){
	document.getElementById("box1").style.display="block";
	document.getElementById("box2").style.display="none";
	document.getElementById("box3").style.display="none";
}

function switcher2(){
	document.getElementById("box2").style.display="block";
	document.getElementById("box1").style.display="none";
	document.getElementById("box3").style.display="none";
}

function switcher3(){
	document.getElementById("box3").style.display="block";
	document.getElementById("box2").style.display="none";
	document.getElementById("box1").style.display="none";
}