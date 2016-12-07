<%-- 
    Document   : groupSettings
    Created on : Dec 4, 2016, 11:15:42 PM
    Author     : kATHRYN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/united/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <title>Manage Group Settings</title>
        
        <style>
            body {
                margin-left: 10%;
                margin-right: 10%;
            }
        </style>
        
    </head>
    <body>
        <c:choose>
            <c:when test="${user != null}">   
            <%@include file="/pages/loggedin-header.jsp"%>
            </c:when>
        <c:otherwise>
            <jsp:forward page="/index.jsp" />
        </c:otherwise>
            </c:choose>
        
        <h1>Manage ${currentGroup.groupName} Settings</h1>
        <c:choose>
            <c:when test="${user != null}">
            <input type='button' value = 'Logout' onclick="location.href='/vibe/logout'">
            <hr>
            
             <div id ='manage-group'>
            <form method="post" action ="/vibe/update">
                <input name ="action" id ="action" value ="edit_group" hidden>
                <input name ="gid" id ="gid" value ="${currentGroup.groupID}" hidden>
                <div>
                    <label>Edit Group Name:</label>
                    <input name="gname" id="gname" size="36" type="text" placeholder="${currentGroup.groupName}" required="true">
                </div>
                <input value="Update Group" type="submit">
            </form>
                
                <form method ="post" action= "/vibe/update">  
                <input name ="action" id ="action" value ="add_user" hidden>
                <input name ="group" id ="action" value ="${currentGroup.groupID}" hidden>
                <div>
                    <label>Add user (enter Email):</label>
                    <input name="email" id="email" size="36" type="text" value="" required="true">
                </div>
                <input id="add-submit" name="add-submit" value="Add User" type="submit">
                <br>
               </form>
                
                
            <c:forEach items = "${groupUsers}" var = "u">
                <div>
                    <form method="post" action="/vibe/update">
                        <input name ="action" value ="remove_user" hidden>
                        <input name ="userID" value ="${u.accountNumber}" hidden>
                        ${u.firstName} ${u.lastName} 
                        <input type ="submit" value="Remove User"> 
                    </form>
                </div>
            </c:forEach>
        </div>
            <div>
                <form method="post" action ="/vibe/update">
                    <input name ="action" id ="action" value ="delete_group" hidden>
                    <input name ="gid" id ="gid" value ="${currentGroup.groupID}" hidden>
                    <input value="DELETE GROUP" type="submit">
                </form>
                </div>
        </c:when>
        <c:otherwise>
            <hr>
        </c:otherwise>
        </c:choose>
    </body>
</html>
