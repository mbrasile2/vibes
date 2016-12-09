<%-- 
    Document   : customers
    Created on : Dec 9, 2016, 1:30:18 AM
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
        
        <title>Customer Information</title>
        
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
                        <c:if test="${employee.manager}">
                        <h3>Customers Generating Most Revenue</h3>
                        <hr>
                        <c:forEach items="${custRevenue}" var="customer">
                            ${customer.firstName} ${customer.lastName}  ${customer.revenue}
                            <hr>
                        </c:forEach>
                        <h3>Search Customers by Item Purchased</h3>
                        <hr>
                        <form method="post" class="form-horizontal" action="/vibe/customer">
                            <fieldset>
                                <div class="form-group">
                                    <label for="inputItem" class="col-lg-2 control-label">Item</label>
                                        <div class="col-lg-5">
                                            <input type="text" class="form-control" name="item" required="true">
                                        </div>
                                
                                <input type="submit" class="btn btn-primary" value ="Search">
                                </div
                                <c:if test="${itemWanted ne null}">
                                    <div>
                                        Search results for ${itemWanted}:
                                    </div>
                                </c:if>
                                <c:forEach items="${customersWanted}" var="cust">
                                    ${cust.firstName}  ${cust.lastName} 
                                    <hr>
                                </c:forEach>
                            </fieldset>
                        </form>  
                        </c:if> 
                        <c:if test="employee.manager ne true">
                            
                        </c:if>
                    </div>                                   
                </c:when>
            </c:choose>
                    
    </body>
</html>