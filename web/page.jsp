<%-- 
    Document   : groupjsp
    Created on : Dec 4, 2016, 10:51:55 PM
    Author     : kATHRYN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/vibe_library.tld" prefix = "fn" %>
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
            
            function populateEditComm(commentID) {
                var cont = "#comm_content_" + commentID;
                var edit = "#comm_edit_btn_" + commentID;
                var area = "#comm_edit_area_" + commentID;
                $(cont).hide();
                $(edit).hide();
                $(area).show();
            }
        </script>
        
    </head>
    <body>
        <c:if test="${isGroup || ((not isGroup) && (currentGroup.groupOwner ne user.accountNumber))}">
            <h1>${currentGroup.groupName}</h1>
        </c:if>
        <c:if test="${not isGroup && currentGroup.groupOwner eq user.accountNumber}">
            <h1>My Wall</h1>
        </c:if>
        <c:choose>
            <c:when test="${user != null}">   
            <input type='button' value = 'Logout' onclick="location.href='/vibe/logout'">
            <hr>
            </c:when>
        <c:otherwise>
        </c:otherwise>
        </c:choose>
            <hr>
            <c:if test="${currentGroup.groupOwner == user.accountNumber && isGroup}">
                <input type ="button" value="Edit Group Settings" onclick="location.href='/vibe/groupSettings.jsp'">
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
                    
                    <form method ="post" action ="/vibe/update">
                        <input name ="action" value ="comment" hidden>
                        <input name ="postID" value ="${post.postID}" hidden>
                        <textarea name="comment_data" id="comment_data" cols="40" rows="5" placeholder="Reply here"></textarea>
                        <input id="comment-submit" name="comment-submit" value="Comment" type="submit">
                    </form>
                    
                    <jsp:useBean id= "tempBean" scope= "page" class= "beans.postLikeBean"> </jsp:useBean>
                    <jsp:setProperty name="tempBean" property="userID" value = "${user.accountNumber}"/>  
                    <jsp:setProperty name="tempBean" property="postID" value = "${post.postID}"/>  
                    
                    <c:if test="${not fn:contains(postLikes, tempBean)}">
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="likePost" hidden>
                            <input name ="postInfo" id ="postInfo" value ="${post.postID}" hidden>
                            <input id="likePost" name="likePost" value="Like Post" type="submit">
                        </form>
                    </c:if>                    
                    <c:if test="${fn:contains(postLikes, tempBean)}">
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="unlikePost" hidden>
                            <input name ="postInfo" id ="postInfo" value ="${post.postID}" hidden>
                            <input id="unlikePost" name="unlikePost" value="Unlike Post" type="submit">
                        </form>
                    </c:if>
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
                <c:if test="${not empty commentList}">
                    <c:forEach items="${commentList}" var ="cxv">
                        <div id ="comm_content_${cxv.commentID}">
                        <c:if test="${cxv.postID == post.postID}">
                            <div>
                                ${cxv.author} wrote on ${cxv.date}: 
                                
                            </div>
                                <div>
                                    ${cxv.content}
                                </div>
                        </div>
                        <jsp:useBean id= "tempBean2" scope= "page" class= "beans.commentLikeBean"> </jsp:useBean>
                        <jsp:setProperty name="tempBean2" property="userID" value = "${user.accountNumber}"/>  
                        <jsp:setProperty name="tempBean2" property="commentID" value = "${cxv.commentID}"/>  
                    
                    <c:if test="${not fn:contains(commentLikes, tempBean2)}">
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="likeComment" hidden>
                            <input name ="commentInfo" id ="commentInfo" value ="${cxv.commentID}" hidden>
                            <input id="likeComment" name="likeComment" value="Like Comment" type="submit">
                        </form>
                    </c:if>                    
                        <c:if test="${fn:contains(commentLikes, tempBean2)}">
                            <form method ="post" action ="/vibe/update">
                               <input name ="action" value ="unlikeComment" hidden>
                               <input name ="commentInfo" id ="commentInfo" value ="${cxv.commentID}" hidden>
                               <input id="unlikeComment" name="unlikeComment" value="Unlike Comment" type="submit">
                            </form>
                        </c:if>
                    <c:if test="${cxv.authorID == user.accountNumber}">
                    <div id="comm_edit_btn_${cxv.commentID}">
                        <input type="button" value="Edit Comment" onclick="populateEditComm(${cxv.commentID})">
                    </div>
                    <div id="comm_edit_area_${cxv.commentID}" hidden>
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="editComment" hidden>
                            <textarea name="edit_data" cols="40" rows="5">${cxv.content}</textarea>
                            <input name ="commentInfo" id ="commentInfo" value ="${cxv.commentID}" hidden>
                            <input id="editComment" name="editComment" value="Edit Comment" type="submit">
                        </form>
                    </div>
                    </c:if>
                    <c:if test="${currentGroup.groupOwner == user.accountNumber || comment.authorID == user.accountNumber}">
                        <form method ="post" action ="/vibe/update">
                            <input name ="action" value ="deleteComment" hidden>
                            <input name ="commentInfo" value ="${cxv.commentID}" hidden>
                            <input name="deleteComment" value="Remove Comment" type="submit">
                        </form>
                    </c:if>  
                        </c:if>
                </c:forEach>
             </c:if>
        </c:forEach>
    </body>
</html>
