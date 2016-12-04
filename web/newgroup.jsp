<%-- 
    Document   : newgroup
    Created on : Dec 2, 2016, 3:56:28 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make a new Group</title>
    </head>
    <body>
        <h1>Vibe</h1>
        <hr>
        
        <div>
            <input type='button' value = 'Make a new Group' onclick="location.href='./newgroup.jsp'">
        </div>
        
        <form id ='makeGroup' method='post' action ='./update'>
                <!-- hidden parameter for action -->
                <input name ="action" id ="action" value ="newGroup" hidden>
                <input name ="owner" id ="owner" value ="${user.emailAddress}" hidden>
                
                <div>
                    <label>Group Name:</label>
                    <input name="group" id="group" size="36" type="text" value="" required="true">
                </div>
                <input id="group-submit" name="group-submit" value="Create Group!" type="submit">
        </form>
    </body>
</html>
