<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <%@include file="WEB-INF/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="Iutf-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/orderpanel.css">
    <script src="js/orderpanel.js" charset="utf-8"></script>
</head>
<body>

<div style="display:none">
<form name="orders" action="LibraryServlet" method="POST">
	<input name="command" value="get_orders">
</form>
</div>

 <c:if test="${orders==null}">
            <script>send()</script>
        </c:if>
        

 <div class="d-none d-md-block container-fluid  row " id="block1">
<div class="container-fluid row" style="width:70%;height:auto;left:20%;position:relative;top:20px;min-height:900px;overflow:scroll;background-color:rgba(255, 255, 255, 0.8);border-radius:30px;">
		  
		  <a style="position:absolute;font-size:20pt;left:40%;top:50px">${bundle.getString('manage_orders')}</a>
		  
		  <table style="position:relative;width:90%;left:5%;top:150px;" id="table" border="1" width="100%" cellpadding="5">
   <tr>
    <th>${bundle.getString('order_id')} </th>
    <th>${bundle.getString('book_name')} </th>
	   <th>${bundle.getString('user_name')} </th>
	   <th>${bundle.getString('set_refund_date')} </th>
	<th>${bundle.getString('cancel_order')} </th>
	<th>${bundle.getString('submit_order')} </th>
   </tr>
   
   <c:forEach var="order" items="${orders}" >
   <tr>
  <td>${order.getId()}</td>
    <td>${order.getBookName()}</td>
	<th>${order.getUserName()}</th>
	
	<td>
		<form name="${order.getId()}">
			<input id="year${order.getId()}"  style="margin-top:10px;margin-bottom:0px;margin-left:10px;height:30px;width:40px;text-align:center"  type="text" placeholder="year" >
			<input id="month${order.getId()}"  style="margin-left:10px;height:30px;width:40px;text-align:center"  type="text" placeholder="mon"  >
			<input id="day${order.getId()}" style="margin-left:10px;height:30px;width:40px;text-align:center"  type="text" placeholder="day"  >
		</form>
	</td>
	
	  <td onclick="cancel('${order.getId()}')" id="cancel">${bundle.getString('cancel_order')}</td>
	  <td onclick ="submit('${order.getId()}',${order.getBookId()},'year${order.getId()}','month${order.getId()}','day${order.getId()}',${order.getUserId()})" id="subm">${bundle.getString('submit_order')}</td>
  </tr>

   </c:forEach>
  
   </table>
  
  
  

 
		  
		  </div>
		    </div>
       
</body>
</html>