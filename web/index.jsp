<%-- 
    Document   : index
    Created on : Nov 30, 2016, 8:10:24 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vibe</title>
    </head>
    <body>
        <h1>Vibe</h1>
        <hr>
        
        <form id = "login" method="post" action ="./login">
            <div>
                <label>Username:</label>
                <input name="user" id="user" size="36" type="text" value="" required="true">
            </div>
            <div>
                <label>Password:</label>
                <input name="password" id="password" size="36" type="text" value="" required="true">
            </div>
            <input id="login-submit" name="login-submit" value="Login" type="submit">
        </form>
    </body>
</html>
