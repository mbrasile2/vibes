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
        <h1>Vibes</h1>
        
            <hr>
             <c:choose>
            <c:when test="${employee != null}">
                
            <input type='button' value = 'Logout' onclick="location.href='/vibes/logout'">
            <hr>
            Welcome ${employee.firstName} ${employee.lastName}!
            <div>
                <input type='button' value = 'Manage Advertisments' onclick="location.href='/vibe/advertisements.jsp'">
            </div>
            <div>
                <input type='button' value = 'Record Transaction' onclick="location.href='./vibe/wall.jsp'">
            </div>
            <div>
                <input type='button' value = 'Customer Information' onclick="location.href='./customerInfo.jsp'">
            </div>
            <div>
                <input type='button' value = 'Best Seller List' onclick="location.href='./vibe/groups.jsp'">
            </div>
            <div>
                <input type='button' value = 'Item Suggestion List' onclick="location.href='./vibe/groups.jsp'">
            </div>
            </c:when>
            <c:otherwise>
        <div id ='login-area'>
            <form id = "login" method="post" action ="./employeeLogin">
                <div>
                    <label>SSN:</label>
                    <input name="ssn" id="ssn" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Password:</label>
                    <input name="password" id="password" size="36" type="text" value="" required="true">
                </div>
                <input id="login-submit" name="login-submit" value="Login" type="submit">
            </form>
        </div>
        </c:otherwise>
        </c:choose>
    </body>
</html>