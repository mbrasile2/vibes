<%-- 
    Document   : buyItem
    Created on : Dec 9, 2016, 8:48:36 AM
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
        
        <title>JSP Page</title>
    </head>
    <body>
        
        <table class="table table-striped table-hover">
        <thead>
            <tr>
                    <th>Item Name</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Company</th>
                    <th> </th>
                    <th> </th>
            </tr> 
        </thead>
    <tbody>
    <c:forEach items="${adverts}" var="a">
        <tr>
            <td>${a.name}</td>
            <td>${a.content}</td>
            <td>${a.type}</td>
            <td>${a.price}</td>
            <td>${a.company}</td>
            <form method="post" action="/vibe/update">
                <td>
                    <input type="text" name= "num" value="1">
                </td>
            <td>
                <input type="text" name= "action" value="buy_item" hidden>
                <input type="text" name= "id" value="${a.adID}" hidden>
                <input type="submit" value="Buy Item">
            </td>
            </form>
        </tr>
        </c:forEach>
    </tbody>
    </table> 
    </body>
</html>
