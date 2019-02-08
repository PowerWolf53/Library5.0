
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
      <%@ page import="java.util.ResourceBundle" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Pragma" content="no-cache" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/header.css">
<script src="js/head.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>

 

 <c:if test="${bundle==null}">
            <script>get_eng_locale()</script>
        </c:if>

 <c:if test="${error!=null}">
            <script>alert('${error}')</script>
        </c:if>
     
       <form id="logout"  action="MyServlet" method="POST">
              <input  type="hidden" name="command" value="logout">
               <c:if test="${id==null}">
                <input id="outbutton"  type="submit" name="" value="${bundle.getString('login')}">
               </c:if>
                <c:if test="${id!=null}">
                <input id="outbutton"  type="submit" name="" value="${bundle.getString('logout')}">
               </c:if>
              
          </form>
     
     <div class="container-fluid row" style="background:black;">
      <div class="col-md-10  container-fluid row" id="header">
            
            <div class="menu col-md-3 col-12" >
                <p class="nav-text"><a href="main.jsp">${bundle.getString('main_page')}</a></p>
            </div>
            
             <div class="menu col-md-3 col-12" >
                <p onclick="switch_sub()" style="cursor:pointer"  class="nav-text">${bundle.getString('reading_hall')}</p>
            </div>
            
         
             <c:if test="${id!=null}">
                 <div class="menu col-md-3 col-12" >
               <p class="nav-text"><a href="profile.jsp">${bundle.getString('profile')}</a></p>
                   </div> 
               </c:if>
                
         
           <c:if test="${status.toString() eq 'admin'}">
            <div class="menu col-md-3 col-12" >
                <p style="cursor:pointer" onclick="switcher()" class="nav-text"> <a >${bundle.getString('panel')}</a></p>
                
            </div> 
        </c:if>
    		
			
          </div>
          
           <div class="container-fluid row" id="subheader" style="display:none">
            
            <div style="width:50%;position:absolute;height:50px;" >
                <a style="cursor:pointer;color:white;" class="nav-text" href="bookcatalog.jsp">${bundle.getString('catalog_books')}</a>
               
            </div>
            
           
            
            </div>
            
            
            <div class="container-fluid row" id="sub" style="display:none">
            
            <div style="width:25%;position:absolute;height:50px;" >
                <a style="cursor:pointer;color:white;" class="nav-text" href="panel.jsp" >${bundle.getString('manage_books')}</a>
               
            </div>
            
            <div style="width:25%;position:absolute;height:50px;right:40%" >
                <a style="cursor:pointer;color:white;" href="orderpanel.jsp"  class="nav-text" >${bundle.getString('manage_orders')}</a>
            </div>
            
             <div style="width:25%;position:absolute;height:50px;right:0%" >
             
                <a style="cursor:pointer;color:white;"  class="nav-text" href="userbooks.jsp">${bundle.getString('private_books')}</a>
            </div>
            </div>
         </div>
         
         
          <div  style="position:fixed;width:70px;height:180px;bottom:10px;left:30px;z-index:5" >
           <img  onclick="get_eng_locale()" style="cursor:pointer;position:relative;width:100%;height:25%;" src="images/england.png">
           <img onclick="get_ger_locale()" style="cursor:pointer;position:relative;width:100%;height:25%;margin-top:20%;" src="images/germany.jpg">
           <img onclick="get_ru_locale()" style="cursor:pointer;position:relative;width:100%;height:25%;margin-top:20%;" src="images/russia.jpg">
    
       		 </div>
</body>
</html>