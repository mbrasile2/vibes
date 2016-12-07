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
            <input type='button' value = 'Make a new Group' data-toggle="modal" data-target="#newGroupModal" class="btn btn-primary">
        </div>
                <h2> Groups I own: </h2>
                    <c:forEach items="${groups}" var = "group">
                        <hr>
                        <a href = "/vibe/page/${group.pageID}"> ${group.groupName} </a>
                        <br>
                        ${group.pageID}
                    </c:forEach>
                        <hr>
                <h2> Groups I'm a member of: </h2>
                    <c:forEach items="${groupMembership}" var = "member">
                        <hr>
                        <a href = "/vibe/page/${member.pageID}">${member.groupName} </a>
                        <br>
                        ${member.pageID}
                        <form method="post" action ="/vibe/update">
                            <input name ="action" id ="action" value ="leave_group" hidden>
                            <input name ="groupLeaving" id ="groupLeaving" value ="${member.groupID}" hidden>
                            <input value="Leave Group" type="submit">
                        </form>
                    </c:forEach>
                        
                        
    <div class="modal" id ="newGroupModal" style="display:none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id ='group-area'>
                            <form method ="post" action ="/vibe/update" class="form-horizontal">
                                <input name ="action" id ="action" value ="new_group" hidden>
                                <input name ="owner" id ="owner" value ="${user.accountNumber}" hidden>
                            <fieldset>
                            <legend>New Group Creation</legend>
                            <div class="form-group">
                                <label for="inputName" class="col-lg-2 control-label">Group Name: </label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name = "groupname">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <input type="submit" class="btn btn-primary" value ="Create Group">
                                </div>
                            </div>
                            </fieldset>
                        </form>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
 