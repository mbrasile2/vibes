<%-- 
    Document   : index
    Created on : Nov 30, 2016, 8:10:24 PM
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
         
        <style>
            body {
                margin-left: 10%;
                margin-right: 10%;
            }
            .jumbotron > * {
                margin-left: 10%;
                margin-right: 10%;
            }
        </style>
        <title>Vibe</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${user != null}"> 
                <%@include file="/pages/loggedin-header.jsp"%>
                <div>
                    Welcome ${user.firstName} ${user.lastName}! 
                </div>
                    
            <hr>
            
            </c:when>
        <c:otherwise>
           <%@include file="/pages/anon-header.jsp"%>
        </c:otherwise>
        </c:choose>
    </body>
</html>
