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
   
<title>Insert title here</title>
</head>
<body>
<c:set var="page" scope="page" value="main.jsp" />
      
       	<%
       	if(request.getSession().getAttribute("bundle")==null)
       	{
       		ResourceBundle bundle=ResourceBundle.getBundle("locales/resources");
       		request.getSession().setAttribute("bundle", bundle);
       	}
       	%>
     
     
   

<div class="d-none d-md-block container-fluid  row " id="block1">
    
       
        
          
          
     </div>

</body>
</html>