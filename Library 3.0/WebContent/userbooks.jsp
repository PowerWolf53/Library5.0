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
         <link rel="stylesheet" type="text/css" href="css/userbooks.css">
		<script src="js/userbooks.js" charset="utf-8"></script>
    </head>
    <body>


<div style="display:none">
<form name="Form" action="LibraryServlet" method="POST">
<input type="text" name="command" value="get_user_books">
</form>
</div>

<c:if test="${user_books==null}">
            <script>se()</script>
        </c:if>

<div class="d-none d-md-block container-fluid  row " id="block1" >

<div class="col-md-6" id="profilearea" style="background-color:rgba(250, 250, 250, 0.8);">
	
	
	<a id="label" style="position:absolute;top:50px;left:32%;font-size:18pt;">Список выданых книг</a>

	
	<div  id="books" style="display:block">
	<table id="table" border="1" width="100%" cellpadding="5">
   <tr>
    <td>Номер личной книги</td>
    <th>Имя</th>
    <th>Фамилия</th>
    <th>Название</th>
	  <th>Автор</th>
    <th>Дата сдачи</th>
   </tr>
   
   
 <c:forEach var="book" items="${user_books}" >
   <tr>
     <td>${book.getNumber()}</td>
    <td>${book.getUserName()}</td>
      <td>${book.getUserSurname()}</td>
	  <th>${book.getBookName()}</th>
	    <th>${book.getBookAuthor()}</th>
    <td>${book.getExpireDate()}</td>
  </tr>
  </c:forEach>



 </table>
 </div>
</div>
</div>
	
	