<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/testajax.js" charset="utf-8"></script>

</head>
<body>



<div style="display:none">
<form name="Form" action="TestServlet" method="GET">
<input type="text" name="name" value="Nastya">
</form>
</div>

 <div onclick="reload()" style="width:200px;height:100px;background-color:red;"><a>Send</a></div>

</body>
</html>