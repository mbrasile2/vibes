<%-- 
    Document   : messages
    Created on : Dec 2, 2016, 3:39:23 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messages</title>
    </head>
    <body>
        <h1>Vibe</h1>
        <hr>
        
        <div>
                <input type='button' value = 'Send a message' onclick=''>
        </div>
        <div id ="message_area">
            <form method ="post" action ="./update">
                <input name ="action" id ="action" value ="send_msg" hidden>
                <input name ="sender" id ="sender" value ="${user.accountNumber}" hidden>
                <label>Receiver (email address):</label>
                <input name = "receiver" id="receiver" type="text"> 
                <textarea name="content" id="content" cols="40" rows="5" placeholder="Type your message here."></textarea>
                <input id="msg-submit" name="msg-submit" value="Send Message" type="submit">
            </form>
        </div>
        <h2> Your Messages: </h2>
        <c:forEach items="${messages}" var = "m">
            <hr>
            ${m.sender}  wrote on ${m.date} :
            <br>
            ${m.content}
            <br>
            <form method='post' action ='./update'>
                <!-- hidden parameter for action -->
                <input name ="action" id ="action" value ="delete_msg" hidden>
                <input name ="mid" id ="action" value ="${m.mid}" hidden>
                
                <input value="Delete message" type="submit">
            </form>
        </c:forEach>
    </body>
</html>
