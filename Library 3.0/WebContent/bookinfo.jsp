<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@include file="WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta http-equiv="Pragma" content="no-cache" />
    
    <link rel="stylesheet" type="text/css" href="css/bookinfo.css">
    <script src="js/bookinfo.js" charset="utf-8"></script>
	
</head>
<title>Insert title here</title>
</head>
<body>
 <div id="block1"> 
          
     
    <div id="bookimage">
	<img id="mainimage" src="bookimages/${book.getImage()}">
	</div>
	
	<div id="bookinfo">
	
	<div style="position:absolute;width:45%;height:100%;left:0%;">
		<a style="font-family: Philosopher;position:absolute;top:10%;font-size:20pt;left:20%;">Name:</a>
		<a style="font-family: Philosopher;position:absolute;top:20%;font-size:20pt;left:20%;">Author:</a>
		<a style="font-family: Philosopher;position:absolute;top:30%;font-size:20pt;left:20%;">Ammount:</a>
		<a style="font-family: Philosopher;position:absolute;top:40%;font-size:20pt;left:20%;">Description:</a>
	</div>	
		
	<div style="position:absolute;width:45%;height:100%;left:45%;">
		<a style=" font-family: Philosopher;position:absolute;top:10%;font-size:20pt;left:10%;">${book.getName()}</a>
		<a style=" font-family: Philosopher;position:absolute;top:20%;font-size:20pt;left:10%;">${book.getAuthor()}</a>
		<a style=" font-family: Philosopher;position:absolute;top:30%;font-size:20pt;left:10%;">${book.getAmmount()}</a>
		<p style=" font-family: Philosopher;position:absolute;top:40%;font-size:20pt;margin-right:5%;margin-left:10%;">${book.getDescription()} </p>
	</div>	
	
	
	
</div>
		 <c:if test="${book.getAmmount()!=0}">
		<div style="position:relative;
	height:50px;
	width:150px;
	top:-320px;
	left:270px;
	cursor:pointer;
    border-radius: 15px;">
		
		<div onclick="order(${id},${book.getId()})" id="order"  >
		<a style="position:relative;top:5%;left:30%;">Order</a>
		</div>
		</div>
		</c:if>
</div>
		



	
	
</body>
</html>