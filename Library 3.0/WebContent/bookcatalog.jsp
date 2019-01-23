<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@include file="WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/catalog.css">
    <script src="js/bookcatalog.js" charset="utf-8"></script>
</head>

<body>


<div style="display:none">
<form name="Form" action="LibraryServlet" method="POST">
<input type="text" name="command" value="get_all_books">
</form>
</div>

 <c:if test="${books==null}">
            <script>se()</script>
        </c:if>




<div class="d-none d-md-block container-fluid  row " id="block2">
<div class="container-fluid row" style="width:70%;height:auto;left:20%;position:relative;top:20px;margin-bottom:200px;">
		  
		  
		   
		   <c:forEach var="book" items="${books}" >
        <div class="col-md-3" id="book">
        <a onclick="subm('${book.getName()}')"><img src="bookimages/${book.getImage()}" style="position:relative;width:80%;height:100%;left:10%;"></a>
        <div style="display:none">
        	<form name="${book.getName()}" action="LibraryServlet" method="POST">
        		<input type="text" name="command" value="get_concrete_book">
        		<input type="text" name="id" value="${book.getId()}">
        	</form>
        </div>
        </div>
        
    </c:forEach>
		   
		  </div>
		  
		  <div style="width:100%;height:50px">
		  
		  </div>
</div>
</body>
</html>