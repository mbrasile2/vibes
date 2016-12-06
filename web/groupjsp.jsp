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
        
        <script>
            function populateEdit(postID) {
                var cont = "#content_" + postID;
                var edit = "#edit_btn_" + postID;
                var area = "#edit_area_" + postID;
                $(cont).hide();
                $(edit).hide();
                $(area).show();
            }
        </script>
        
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
                </div>
                <input id="add-submit" name="add-submit" value="Add User" type="submit">
                <br>
                <input type ="button" value="Edit Group Settings" onclick="location.href='/vibe/groupSettings.jsp'">
            </form>
                <hr>
                <form method ="post" action ="/vibe/update" id ="page_post">
                    <input name ="action" id ="action" value ="post" hidden>
                    <textarea name="post_data" id="post_data" cols="40" rows="5" placeholder="What's on your mind?"></textarea>
                    <input id="post-submit" name="post-submit" value="Post!" type="submit">
                </form>
            </c:if>
            <hr>
            <c:forEach var="g" items = "${groupMembership}">
                <c:if test="${g.groupID == currentGroup.groupID}">
                    <form method ="post" action ="/vibe/update" id ="page_post">
                        <input name ="action" id ="action" value ="post" hidden>
                        <textarea name="post_data" id="post_data" cols="40" rows="5" placeholder="What's on your mind?"></textarea>
                        <input id="post-submit" name="post-submit" value="Post!" type="submit">
                    </form>
                </c:if>
            </c:forEach>
           
            <c:forEach items = "${currentPosts}" var = "post">
                <div id = "content_${post.postID}">
                    <div>
                        ${post.author} wrote on ${post.date}:
                    </div>
                    <div>
                        ${post.content}
                    </div>
                    <c:if test="${currentGroup.groupOwner == user.accountNumber || post.authorID == user.accountNumber}">
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="deletePost" hidden>
                            <input name ="postInfo" id ="postInfo" value ="${post.postID}" hidden>
                            <input id="deletePost" name="deletePost" value="Remove Post" type="submit">
                        </form>
                    </c:if>
                </div>
                <c:if test="${post.authorID == user.accountNumber}">
                    <div id="edit_btn_${post.postID}">
                        <input type="button" value="Edit Post" onclick="populateEdit(${post.postID})">
                    </div>
                    <div id="edit_area_${post.postID}" hidden>
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="editPost" hidden>
                            <textarea name="edit_data" cols="40" rows="5">${post.content}</textarea>
                            <input name ="postInfo" id ="postInfo" value ="${post.postID}" hidden>
                            <input id="editPost" name="editPost" value="Edit Post" type="submit">
                        </form>
                    </div>
                </c:if>
            </c:forEach>
    </body>
</html>
