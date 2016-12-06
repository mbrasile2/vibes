<%-- 
    Document   : pagejsp
    Created on : Dec 5, 2016, 7:06:27 PM
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
        <c:if test="${currentGroup.pageID == pageID}">
            <h1>My Wall!</h1>
            </c:if>
            <c:if test="${currentGroup.pageID != pageID}">
                <h1>${currentGroup.groupName}</h1>
            </c:if>
            <c:if test="${user != null}">
                
            <input type='button' value = 'Logout' onclick="location.href='/vibe/logout'">
            <hr>
            
            </c:if>
            <hr>
            <form method ="post" action ="/vibe/update" id ="page_post">
                <input name ="action" id ="action" value ="post" hidden>
                <textarea name="post_data" id="post_data" cols="40" rows="5" placeholder="What's on your mind?"></textarea>
                <input id="post-submit" name="post-submit" value="Post!" type="submit">
            </form>
            <hr>
            <c:forEach items = "${currentPosts}" var = "post">
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
            </c:forEach>
    </body>
</html>