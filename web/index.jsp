<%-- 
    Document   : index
    Created on : Nov 30, 2016, 8:10:24 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        
        <title>Vibe</title>
        
        <script>
            function populateForm() {
                $('#login-area').hide();
                $('#new-user').show();
            }
        </script>
    </head>
    <body>
        <h1>Vibe</h1>
        <c:choose>
            <c:when test="${user != null}">
                
            <input type='button' value = 'Logout' onclick="location.href='./logout'">
            <hr>
            Welcome ${user.firstName} ${user.lastName}!
            <div>
                <input type='button' value = 'Messages' onclick="location.href='./messages.jsp'">
            </div>
            <div>
                <input type='button' value = 'My Wall' onclick="location.href='./page/${pageID}'">
            </div>
            <div>
                <input type='button' value = 'Check my Groups' onclick="location.href='./groups.jsp'">
            </div>
            </c:when>
        <c:otherwise>
            <hr>
        <div id ='login-area'>
            <form id = "login" method="post" onclick ="./login">
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
            <p>
                Employees, log in <a href = "./employeeIndex.jsp">here</a>.
            <p>
                New member? Sign up for a free account!
                <input type='button' value = 'Register' onclick='populateForm()'>
            </p>
        </div>
        <div id='new-user' hidden>
            <form id ='register' method='post' action ='./update'>
                <!-- hidden parameter for action -->
                <input name ="action" id ="action" value ="register" hidden>
                <div>
                    <label>First Name:</label>
                    <input name="fname" id="fname" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Last Name:</label>
                    <input name="lname" id="lname" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Address:</label>
                    <input name="address" id="address" size="36" type="text" value="">
                </div>
                <div>
                    <label>City:</label>
                    <input name="city" id="city" size="36" type="text" value="">
                </div>
                <div>
                    <label>State:</label>
                    <input name="state" id="state" size="15" type="text" value="">
                </div>
                <div>
                    <label>Zip Code:</label>
                    <input name="zip" id="zip" size="25" type="text" value="">
                </div>
                <div>
                    <label>Telephone Number:</label>
                    <input name="phone" id="phone" size="25" type="text" value="">
                </div>
                <div>
                    <label>Email Address:</label>
                    <input name="email" id="email" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Password:</label>
                    <input name="password" id="password" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Preferences:</label>
                    <input name="prefs" id="prefs" size="36" type="text" value="">
                </div>
                <input id="register-submit" name="register-submit" value="Register!" type="submit">
            </form>
        </div>
        </c:otherwise>
        </c:choose>
    </body>
</html>
