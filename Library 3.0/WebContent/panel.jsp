<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
     <%@include file="WEB-INF/header.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="css/panel.css">
  <script src="js/panel.js" charset="utf-8"></script>
</head>
<body>

            
                
        <img src="images/desk.jpg"></img>        
     
            
        <div id="core" style="background-image: url(images/desk.jpg)">
                <div id="box1" class="box" style="display: block">
            <h2>${bundle.getString('book_addition')}</h2>
            <form  enctype="multipart/form-data"  action="LibraryServlet" method="POST">
                
                <div class="inputBox">
                    <input type="text" name="name" required="true" >
                    <label >${bundle.getString('book_name')}</label>
                </div>
                
                <div class="inputBox">
                    <input type="text" name="author" required="" >
                    <label >${bundle.getString('author_name')}</label>
                </div>
                
                
                
                <div class="inputBox">
                    <input type="text" name="specification" required="" >
                    <label >${bundle.getString('specification')}</label>
                </div>
                
                <div class="inputBox">
                    <input id="ammount" type="text" name="ammount" required="" onchange="verification()" >
                    <label >${bundle.getString('amount')}<div id="not"><a >${bundle.getString('wrong_number')} </a></div></label>
                </div>
                
                
                
                <div class="inputBox">
                <textarea name="description" cols="45" rows="4" placeholder="${bundle.getString('description')}"></textarea>
                </div>
                
                <div id="file">
                <input type="file" name="bookfile" required="">
                </div>
                
                 <input id="subm"  type="submit" name="" value="Submit" >
                 <input  type="hidden" name="command" value="bookaddition">
                 <a onclick="switcher2()" style="cursor:pointer;position:relative;left:30%;color:white;">${bundle.getString('amount_addition')}</a>
                 <p onclick="switcher3()" style="cursor:pointer;position:relative;left:58%;top:10%;color:white;">${bundle.getString('book_refund')}</p>
            </form>
           
        </div>
        
        <div id="box2" class="box" style="display: none">
            <h2>${bundle.getString('book_increment')}</h2>
            <form   action="LibraryServlet" method="POST">
                
                <div class="inputBox">
                    <input type="text" name="name" required="" >
                    <label >${bundle.getString('book_name')}</label>
                    
                </div>
 
   
                <div class="inputBox">
                    <input id="ammount2" type="text" name="ammount" required="" onchange="second_verification()" >
                    <label >${bundle.getString('amount')} <div id="not2"><a >${bundle.getString('wrong_number')} </a></div></label>
                </div>
                

                
                 <input id="subm2"  type="submit" name="" value="Submit" disabled >
                 <input  type="hidden" name="command" value="bookincrement">
				 <a onclick="switcher1()" style="cursor:pointer;position:relative;left:30%;color:white;">${bundle.getString('book_addition')}</a>
				 <p onclick="switcher3()" style="cursor:pointer;position:relative;left:58%;top:10%;color:white;">${bundle.getString('book_refund')}</p>
            </form>
        </div>
        
         <div id="box3" class="box" style="display: none">
            <h2>${bundle.getString('book_refund')}</h2>
            <form    action="LibraryServlet" method="POST">
                
                <div class="inputBox">
                    <input type="text" name="private_book_id" required="true" >
                    <label >${bundle.getString('order_id')}</label>
                </div>
                
                 <input id="subm"  type="submit" name="" value="Submit" >
                 <input  type="hidden" name="command" value="refund_book">
                 <a onclick="switcher2()" style="cursor:pointer;position:relative;left:30%;color:white;">${bundle.getString('amount_addition')}</a>
                  <p onclick="switcher1()" style="cursor:pointer;position:relative;left:58%;top:10%;color:white;">${bundle.getString('book_addition')}</p>
            </form>
           
        </div>
        
        
        
        
        
        
        
        </div>
</body>
</html>