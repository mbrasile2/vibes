<%-- 
    Document   : items
    Created on : Dec 8, 2016, 11:28:54 PM
    Author     : kATHRYN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/united/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <title>Item Information</title>
        
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
                        <h3>Item List</h3>
                        <hr>
                        <h3>Most Active Items</h3>
                        <hr>
                        <c:forEach items="${hottestItems}" var="item">
                            ${item.numSold} ${item.name}  ${item.content}  ${item.price}
                            <hr>
                        </c:forEach>
                        <h3>Search Items By Company</h3>
                        <hr>
                        <form method="post" class="form-horizontal" action="/vibe/items">
                            <fieldset>
                                <div class="form-group">
                                    <label for="inputComp" class="col-lg-2 control-label">Company</label>
                                        <div class="col-lg-5">
                                            <input type="text" class="form-control" name="company" required="true">
                                        </div>
                                
                                <input type="submit" class="btn btn-primary" value ="Search">
                                </div>
                                <c:forEach items="${itemsWanted}" var="item">
                                    ${item.name}  ${item.content}  ${item.price}
                                    <hr>
                                </c:forEach>
                            </fieldset>
                        </form>
                        
                        <h3>Employees Generating Most Revenue</h3>
                        <hr>
                        <c:forEach items="${mostRevenue}" var="emp">
                            ${emp.firstName} ${emp.lastName}  ${emp.revenue}
                            <hr>
                        </c:forEach>
                    </div>                 
                    <hr>                       
                </c:when>
            </c:choose>
                    
    </body>
</html>