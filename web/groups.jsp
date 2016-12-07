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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/united/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Vibe Groups</title>
        
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
                <h2> Groups I own: </h2>
                    <c:forEach items="${groups}" var = "group">
                        <hr>
                        ${group.groupName} 
                        <br>
                        ${group.pageID}
                    </c:forEach>
                        <hr>
                <h2> Groups I'm a member of: </h2>
                    <c:forEach items="${groupMembership}" var = "member">
                        <hr>
                        ${member.groupName} 
                        <br>
                        ${member.pageID}
                        <form method="post" action ="/vibe/update">
                            <input name ="action" id ="action" value ="leave_group" hidden>
                            <input name ="groupLeaving" id ="groupLeaving" value ="${member.groupID}" hidden>
                            <input value="Leave Group" type="submit">
                        </form>
                    </c:forEach>
    </body>
    </body>
</html>
 