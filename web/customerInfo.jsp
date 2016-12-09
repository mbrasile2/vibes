<%-- 
    Document   : customerInfo
    Created on : Dec 9, 2016, 8:38:08 AM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <title>JSP Page</title>
         <script>
            function showMail() {
                
                $('#mailing-list').show();
            }
            
        </script>
    </head>
    <body>
        <h1>Customer Information</h1>
        <input type='button' value = 'View Mailing List'id="View Mailing List" onclick = 'showMail()'>
        <div id ='mailing-list' hidden>
            <h2>Mailing List</h2>
            <c:forEach items="${mailingList}" var = "m">
                <hr>
                <br>
                <br> User: ${m.firstName} ${m.lastName} email: ${m.email}<br>
                <br>
                </c:forEach>
        </div>
        <h2>Customer's Groups</h2>
        <c:choose>
        <c:when test="${foundGroups == null}">
         <div id ='group-finder'>
             <form id = 'groupFinder' method='post' action ='./suggestionServlet'>
                 <input name ="action" id ="action" value ="groupFind" hidden>
                 <div>
                    <label>Customer Email:</label>
                    <input name="customerE" id="customerE" size="36" type="text" value="" required="true">
                </div>
                 <input id="finder-submit" name="finder-submit" value="Find" type="submit">
                 </form>
             <div/>
        </c:when>
        <c:otherwise>
            <c:forEach items="${foundGroups}" var = "f">
                <hr>
                <br>
                <br> Groups: ${f} <br>
                <br>
            </c:forEach>
                <div id ='group-finder2'>
             <form id = 'groupFinder2' method='post' action ='./suggestionServlet'>
                 <input name ="action" id ="action" value ="groupFind" hidden>
                 <div>
                    <label>Another Customer's Email:</label>
                    <input name="customerE" id="customerE" size="36" type="text" value="" required="true">
                </div>
                 <input id="finder-submit" name="finder-submit" value="Find" type="submit">
                 </form>
             <div/>
        </c:otherwise>
                </c:choose>
        <h2>Customer Item Suggestion List<h2/>
            
        <c:choose>
        <c:when test="${suggestions == null}">
         <div id ='suggestion-area'>
             <form id = 'suggestion' method='post' action ='./suggestionServlet'>
                 <div>
                    <label>Customer Email:</label>
                    <input name="customer" id="customer" size="36" type="text" value="" required="true">
                </div>
                 <input id="suggestion-submit" name="suggestion-submit" value="Find" type="submit">
                 </form>
             <div/>
        </c:when>
        <c:otherwise>
            <c:forEach items="${suggestions}" var = "s">
                <hr>
                <br>
                <br> Item: ${s} <br>
                <br>
            </c:forEach>
                <div id ='suggestion-area2'>
             <form id = 'suggestion2' method='post' action ='./suggestionServlet'>
                 <div>
                    <label>Another Customer's Email:</label>
                    <input name="customer" id="customer" size="36" type="text" value="" required="true">
                </div>
                 <input id="suggestion-submit" name="suggestion-submit" value="Find" type="submit">
                 </form>
             <div/>
        </c:otherwise>
                </c:choose>
    </body>
</html>
