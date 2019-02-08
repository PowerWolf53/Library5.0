<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.ResourceBundle" %>
    <%@include file="WEB-INF/header.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="js/main.js" charset="utf-8"></script>
   
<title>Insert title here</title>
</head>
<body>

      
      <div style="display:none">
<form name="Form" action="LibraryServlet" method="POST">
<input type="text" name="command" value="get_bestsellers">
</form>
</div>

 <c:if test="${books==null}">
            <script>se()</script>
        </c:if> 
     
     
   

<div  id="block1">
    
       <img src="images/label.png" style="width:150px;height:150px;top:70px;left:290px;position:relative;">
        
        <div onclick="subm('${books.get(0).getName()}')" style="height:180px;width:120px;position:relative;background-color:red;left:700px;top:0px;">
      		<img style="width:100%;height:100%;" src="bookimages/${books.get(0).getImage()}"/>
      		
      		<div style="display:none">
        	<form name="${books.get(0).getName()}" action="LibraryServlet" method="POST">
        		<input type="text" name="command" value="get_concrete_book">
        		<input type="text" name="id" value="${books.get(0).getId()}">
        	</form>
        </div>
        </div>
        
        <div onclick="subm('${books.get(1).getName()}')" style="height:180px;width:120px;position:relative;background-color:red;left:500px;top:-180px;">
      		<img style="width:100%;height:100%;" src="bookimages/${books.get(1).getImage()}"/>
      		<div style="display:none">
      		<form name="${books.get(1).getName()}" action="LibraryServlet" method="POST">
        		<input type="text" name="command" value="get_concrete_book">
        		<input type="text" name="id" value="${books.get(1).getId()}">
        	</form>
        </div>
        </div>
        
        <div onclick="subm('${books.get(2).getName()}')" style="height:180px;width:120px;position:relative;background-color:red;left:900px;top:-360px;">
      		<img style="width:100%;height:100%;" src="bookimages/${books.get(2).getImage()}"/>
      		<div style="display:none">
      		<form name="${books.get(2).getName()}" action="LibraryServlet" method="POST">
        		<input type="text" name="command" value="get_concrete_book">
        		<input type="text" name="id" value="${books.get(2).getId()}">
        	</form>
        </div>
          
          
     </div>
     </div>
     
     <div  id="block2">
     
       </div>
       
          <div  id="block3">
     
       </div>
       
       <div id="block4">
      		<img src="images/label2.png" style="position:relative;height:68px;left:43%;top:15px;"/> 
      		<img src="images/label5.jpg" style="position:relative;height:68px;left:0%;width:120px;top:15px"/> 
      		
      		<img src="images/zone.jpg" style="position:relative;height:68px;left:54.3%;width:230px;top:15px"/> 
       </div>

</body>
</html>