<%-- 
    Document   : employeeIndex
    Created on : Dec 1, 2016, 4:57:23 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        
        <title>Vibe Employee Login</title>
        
        <script>
            function populateForm() {
                $('#login-area').hide();
                $('#new-user').show();
            }
        </script>
    </head>
    <body>
        <h1>Vibe</h1>
        
            <hr>
            <c:choose>
            <c:when test="${employee eq null}"> 
        <div id ='login-area'>
            <form id = "login" method="post" action ="/vibe/employee-login">
                <div>
                    <label>SSN:</label>
                    <input name="user" id="user" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Password:</label>
                    <input name="password" id="password" size="36" type="text" value="" required="true">
                </div>
                <input id="login-submit" name="login-submit" value="Login" type="submit">
            </form>
        </div>
            </c:when>
                <c:when test="${employee ne null}">
                    Welcome ${employee.firstName} ${employee.lastName}!
                    
                    <c:if test="${employee.manager}">
                        <input type="button" value="Edit employee information" onclick="location.href='/vibe/employeeSettings.jsp'">
                    </c:if>
                        
                        <a href="/vibe/logout">Logout Here</a>
                </c:when>
            </c:choose>
                    
    </body>
</html>