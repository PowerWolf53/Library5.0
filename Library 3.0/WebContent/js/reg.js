
function switcher()
{
   var box1=document.getElementById("box1");
   var box2=document.getElementById("box2");
   if(box1.style.display=="block")
       {
           box1.style.display="none";
           box2.style.display="block";
       }else{
   if(box2.style.display=="block")
       {
           box2.style.display="none";
           box1.style.display="block";
       } 
       }
}

function verification()
{

if(document.getElementById("password").value==document.getElementById("confirmpassword").value)
{
	document.getElementById("subm").disabled=false;
}else{
	document.getElementById("subm").disabled=true;
}

}