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
        <title>Manage Group Settings</title>
    </head>
    <body>
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
