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
        <div id ='login-area'>
            <form id = "login" method="post" onclick ="./employee-login">
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
    </body>
</html>