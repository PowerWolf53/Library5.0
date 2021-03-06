<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="WEB-INF/header.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Login</title>
         <link rel="stylesheet" type="text/css" href="css/profile.css">
		<script src="js/profile.js" charset="utf-8"></script>
    </head>
    <body>


<div style="display:none">
<form name="Form" action="LibraryServlet" method="POST">
<input type="text" name="command" value="get_profile_info">
</form>
</div>

 <c:if test="${profile_info==null}">
            <script>se()</script>
        </c:if>
<div class="d-none d-md-block container-fluid  row " id="block1" >

<div class="col-md-6" id="profilearea" style="display:block;">
	
	<div id="imgdiv" style="background-image:url(images/${profile_info.getImage()})">
	
	</div>
	
	
	
	<a id="username">${profile_info.getName()} &#160;&#160;${profile_info.getSurname()}</a>

	
	<div onclick="switch_books()"  style="cursor:pointer;height:30px;width:120px;position:relative;background-color:rgba(182, 255, 10, 0.5);top:120px;left:0%;border-radius:15px;">
	 <a style="font-family:Philosopher;top:10%;left:5%;position:relative">${bundle.getString('private_books')}</a>
	</div>
	
	<div  onclick="switch_book_orders()" style="text-align:center;cursor:pointer;height:30px;width:80px;position:relative;background-color:rgba(182, 255, 10, 0.5);top:90px;left:90%;border-radius:15px;">
	  <a style="font-family:Philosopher;top:10%;position:relative;">${bundle.getString('orders')}</a>
	</div>
	
	<a onclick="switchEditProfile()" id="red">${bundle.getString('edit')}</a>
	
	<div  id="books" style="display:none">
	<table id="table" border="1" width="100%" cellpadding="5">
   <tr>
    <td>${bundle.getString('book_id')}</td>
    <th>${bundle.getString('book_name')}</th>
	  <th>${bundle.getString('author_name')}</th>
    <th>${bundle.getString('refund_date')}</th>
   </tr>
   
   
    <c:forEach var="book" items="${books}" >
   <tr>
     <td>${book.getId()}</td>
    <td>${book.getName()}</td>
	  <th>${book.getAuthor()}</th>
    <td>${book.getExpireDate()}</td>
  </tr>
   </c:forEach>



 </table>
   </div>
   
   
   <div  id="bookorders" style="display:block">
	<table id="table" border="1" width="100%" cellpadding="5">
   <tr>
   <th>${bundle.getString('order_id')}</th>
    <th>${bundle.getString('book_name')}</th>
	 <th>${bundle.getString('author_name')}</th>
	<th>${bundle.getString('cancel_order')}</th>
   </tr>
   
    <c:forEach var="order" items="${orders}" >
   <tr>
   <th>${order.getId()}</th>
    <td>${order.getName()}</td>
	    <td>${order.getAuthor()}</td>
	<th onclick="cancel_order('${order.getId()}')" id="cancel">${bundle.getString('cancel_order')}</th>
  </tr>
  </c:forEach>
  </table>
  
</div>
</div>

	
	
	</div>
	 <div id="editarea" style="display:none">
	
		<a  style="cursor:pointer;position:relative;top:50px;left:35%;font-size:20pt;font-family:Philosopher;color:white;">${bundle.getString('edit_profile')}</a>
			
			<div id="box1" class="box" style="display:block" >
     
            <form enctype="multipart/form-data" action="LibraryServlet" method="POST">
                
                <div class="inputBox">
                    <input type="text" name="name" >
                    <label >${bundle.getString('user_name')}</label>
                </div>
                
                <div class="inputBox">
                    <input type="text" name="surname">
                    <label >${bundle.getString('user_surname')}</label>
                </div>
                
                  <div id="file">
                <input type="file" name="userfile" >
                <label style="color:white;top:50px;position:absolute;left:0px" >${bundle.getString('change_avatar')}</label>
                 <a onclick="switchProfile()" style="cursor:pointer;position:absolute;top:90px;right:0px;">${bundle.getString('back_to_profile')}</a>
                </div>
                
                <input   type="submit" name="" value="Submit">
              
                 <input  type="hidden" name="command" value="edit_profile">
            </form>
           
        </div>
        
        
        
        
        
        
        
        
        
        
		</div>
	</body>
	