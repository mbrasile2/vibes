<%-- 
    Document   : wall
    Created on : Dec 2, 2016, 3:40:55 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wall</title>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
       
    </head>
    <body>
        <h1>Vibe</h1>
        <hr>
        
        <form method ="post" action ="./update" id ="page_post">
            <input name ="action" id ="action" value ="post" hidden>
            <textarea name="post_data" id="post_data" cols="40" rows="5" placeholder="What's on your mind?"></textarea>
            <input id="post-submit" name="post-submit" value="Post!" type="submit">
        </form>
        
        <c:forEach items="${sessionScope.postlist}" var = "post">
            <hr>
            <c:out value="${post.postDate}" />
            <br>
            <c:out value="${post.content}}" />
        </c:foreach>
            
        
    </body>
</html>
