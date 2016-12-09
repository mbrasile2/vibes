<%-- 
    Document   : sales
    Created on : Dec 9, 2016, 11:59:18 AM
    Author     : michael
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
                    <span style="height: 100%; width:100%;">
                        <div>
                            <h3>View Sales by Month</h3>
                            <form method="post" action="/vibe/sales" class="form-horizontal">
                                <select name="select_dates">
                                  <c:forEach items="${dates}" var="date">
                                      <option id="date" name="date" value="${date}">${date}</option>
                                  </c:forEach>
                                </select>
                                <input type="submit" value ="Search">
                            </form>
                        </div>
                        <hr>
                        <div>
                            <h3>View Transactions by Item Name</h3>
                            <form method="post" action="/vibe/sales" class="form-horizontal">
                                <select name="select_names">
                                  <c:forEach items="${item_names}" var="i">
                                      <option id="item_name" name="item_name" value="${i}">${i}</option>
                                  </c:forEach>
                                </select>
                                <input type="submit" value ="Search">
                            </form>
                        </div>
                        <hr>
                        <div>
                            <h3>View Transactions by User Name</h3>
                            <form method="post" action="/vibe/sales" class="form-horizontal">
                                <select name="select_users">
                                  <c:forEach items="${user_names}" var="u">
                                      <option id="user_name" name="user_name" value="${u}">${u}</option>
                                  </c:forEach>
                                </select>
                                <input type="submit" value ="Search">
                            </form>
                        </div>
                    </span>
                   <hr> 
                    <table style="width:100%;text-align:center">
                        <tr>
                            <th>ID</th>
                            <th>Date</th> 
                            <th>Advertisement ID</th>
                            <th>Units Sold</th>
                            <th>Customer ID</th>
                        </tr>
                        <c:if test="${sales ne null}"> 
                            <c:forEach items="${sales}" var = "t">
                                    <tr>
                                        <th>${t.transactionID}</th>
                                        <th>${t.date}</th> 
                                        <th>${t.advertisementID}</th>
                                        <th>${t.numUnits}</th>
                                        <th>${t.accountNum}</th>
                                    </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    
                    </c:when>
            </c:choose>
    </body>
</html>
