<%-- 
    Document   : groups
    Created on : Dec 2, 2016, 3:37:15 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vibe Groups</title>
    </head>
    <body>
        <h1>Vibe</h1>
        <hr>
        
        <div>
            <input type='button' value = 'Make a new Group' onclick="location.href='./newgroup.jsp'">
        </div>
        <div id ="new_group">
            <form method ="post" action ="./update">
                <input name ="action" id ="action" value ="new_grooup" hidden>
                <input name ="sender" id ="sender" value ="${user.accountNumber}" hidden>
                <label>Receiver (email address):</label>
                <input name = "receiver" id="receiver" type="text"> 
                <textarea name="content" id="content" cols="40" rows="5" placeholder="Type your message here."></textarea>
                <input id="msg-submit" name="msg-submit" value="Send Message" type="submit">
            </form>
        </div>
    </body>
</html>
