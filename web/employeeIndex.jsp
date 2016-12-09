<%-- 
    Document   : employeeIndex
    Created on : Dec 1, 2016, 4:57:23 PM
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
        
        <title>Vibe Employee Login</title>
        <style>
            body {
                margin-left: 10%;
                margin-right: 10%;
            }
            
        </style>
        
    </head>
    <body>
           
            <c:choose>
            <c:when test="${employee eq null}"> 
                    <%@include file="/pages/emp-anon-header.jsp"%>
            </c:when>
                <c:when test="${employee ne null}">
                    <%@include file="/pages/emp-header.jsp"%>
                    <div>
                        Welcome ${employee.firstName} ${employee.lastName}!
                        
                    </div>                 
                    <hr>                       
                </c:when>
            </c:choose>
                    
    </body>
</html>