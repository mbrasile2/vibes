<%-- 
    Document   : groupjsp
    Created on : Dec 4, 2016, 10:51:55 PM
    Author     : kATHRYN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        
        <title>${currentGroup.groupName}</title>
    </head>
    <body>
        <h1>${currentGroup.groupName}</h1>
        <c:choose>
            <c:when test="${user != null}">
                
            <input type='button' value = 'Logout' onclick="location.href='/vibe/logout'">
            <hr>
            
        </c:when>
        <c:otherwise>
        </c:otherwise>
        </c:choose>
            <hr>
            <c:if test="${currentGroup.groupOwner == user.accountNumber}">
            <form method ="post" action= "/vibe/update">  
                <input name ="action" id ="action" value ="add_user" hidden>
                <input name ="group" id ="action" value ="${currentGroup.groupID}" hidden>
                <div>
                    <label>Add user (enter Email):</label>
                    <input name="email" id="email" size="36" type="text" value="" required="true">
                    <input id="add-submit" name="add-submit" value="Add User" type="submit">
                </div>
            </form>
            </c:if>
            <c:forEach items = "${currentPosts}" var = "post">
                <div>
                    ${post.author} wrote on ${post.date}:
                </div>
                <div>
                    ${post.content}
                </div>
            </c:forEach>
    </body>
</html>
