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
        <h2> Your Messages: </h2>
        <c:forEach items="${messages}" var = "m">
            <hr>
            ${m.sender}  wrote on ${m.date} :
            <br>
            ${m.content}
        </c:forEach>
    </body>
</html>
