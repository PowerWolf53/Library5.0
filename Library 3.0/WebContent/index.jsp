<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Login</title>
        <link rel="stylesheet" href="css/authorisation.css">
        <script src="js/reg.js" charset="utf-8"></script>
    </head>
    <body>



 <c:if test="${error!=null}">
            <script>alert('${error}')</script>
        </c:if>

        <div id="box1" class="box" style="display: none">
            <h2>Registartion</h2>
            <form   action="MyServlet" method="POST" accept-charset="utf-8">
                
                <div class="inputBox">
                    <input type="text" name="name" required="" >
                    <label >Your name</label>
                </div>
                
                 <div class="inputBox">
                    <input type="text" name="surname" required="" >
                    <label >Your surname</label>
                </div>
                
                <div class="inputBox">
                    <input type="text" name="login" required="" >
                    <label >Login</label>
                </div>
                
                <div class="inputBox">
                    <input id="password" type="password" name="password" required="" onchange="verification()">
                    <label >Password</label>
                    
                </div>
                
                <div class="inputBox">
                    <input id="confirmpassword" type="password" name="confirmpassword" required="" onchange="verification()" >
                    <label >Confirm Password</label>
                </div>
                
                <input id="subm"  type="submit" name="" value="Submit" disabled>
                 <a id="switcher" onclick="switcher()" >To authorisation </a>
                 <input  type="hidden" name="command" value="registration">
            </form>
           
        </div>
        
        <div id="box2" class="box" style="display:block ">
            <h2>Login</h2>
            <form action="MyServlet" method="POST">
                
                <div class="inputBox">
                    <input type="text" name="login" required="" >
                    <label >Username</label>
                </div>
                
                <div class="inputBox">
                    <input type="password" name="password" required="">
                    <label >Password</label>
                </div>
                
                <input  type="submit" name="" value="Submit">
                 <a id="switcher" onclick="switcher()">To registration </a>
                 <input  type="hidden" name="command" value="authorisation">
            </form>
           
        </div>
    </body>
</html>