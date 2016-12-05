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
        
        <div id ="new-grp-btn">
            <input type='button' value = 'Make a new Group' onclick="">
        </div>
        <div id ="new_group">
            <form method ="post" action ="./update">
                <input name ="action" id ="action" value ="new_group" hidden>
                <input name ="owner" id ="owner" value ="${user.accountNumber}" hidden>
                <div>
                    <label>Group Name:</label>
                    <input name = "groupname" id="groupname" type="text"> 
                </div> 
                
                <input id="new-group-submit" name="new-group-submit" value="Create Group" type="submit">
            </form>
        </div>
    </body>
</html>
