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
        
        <script>
        function populateEdit(ID) {
                var def = "#def_" + ID;
     
                var area = "#edit_" + ID;
             
                $(def).hide();
        
                $(area).show();
            }
            </script>
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
     <c:if test="${not employee.manager}">
         <form class="form-horizontal" method='post' action ='/vibe/employeeUpdate'>
                <fieldset>
                <!-- hidden parameter for action -->
                <input name ="action" value ="add_cust" hidden>
                <legend>New Customer</legend>
                <div class="form-group">
                    <label class="col-lg-2 control-label">First Name:</label>
                    <div class="col-lg-3">
                    <input name="fname" id="fname" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Last Name:</label>
                    <div class="col-lg-3">
                    <input name="lname" id="lname" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Address:</label>
                    <div class="col-lg-3">
                    <input name="address" id="address" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">City:</label>
                    <div class="col-lg-3">
                    <input name="city" id="city" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">State:</label>
                    <div class="col-lg-3">
                    <input name="state" id="state" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Zip Code:</label>
                    <div class="col-lg-3">
                    <input name="zip" id="zip" class="form-control" type="text" value="" required="true">
                    </div> 
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Telephone Number:</label>
                    <div class="col-lg-3">
                    <input name="phone" id="phone" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Date of Birth: </label>
                    <div class="col-lg-3">
                    <input name="dob" class="form-control" type="text" value="" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Sex: </label>
                    <div class="col-lg-3">
                    <input name="sex" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Email Address: </label>
                    <div class="col-lg-3">
                    <input name="email" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Preferences: </label>
                    <div class="col-lg-3">
                    <input name="prefs" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-3 col-lg-offset-2">
            
                    <button type="submit" class="btn btn-primary">Add New Customer</button>
                </div>
                    
                </fieldset>
            </form>
         
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Sex</th>
                    <th>Email</th>
                    <th>DOB</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip code</th>
                    <th>Telephone</th>
                    <th>Preferences</th>
                    <th> </th>
                    <th> </th>
            </tr> 
        </thead>
    <tbody>
    <c:forEach items="${customerList}" var="c">
        <tr id = "def_${c.customerID}">
            <td>${c.firstName}</td>
            <td>${c.lastName}</td>
            <td>${c.sex}</td>
            <td>${c.email}}</td>
            <td>${c.dob}</td>
            <td>${c.address}</td>
            <td>${c.city}</td>
            <td>${c.state}</td>
            <td>${c.zipcode}</td>
            <td>${c.telephone}</td>
            <td>${c.preferences}</td>
            <td><input type="button" value="Edit Customer" onclick="populateEdit(${c.customerID})"></td>
            <td>
                <form method="post" action="/vibe/employeeUpdate">
                <input type="text" name= "action" value="delete_cust" hidden>
                <input type="text" name= "id" value="${c.customerID}" hidden>
                <input type="submit" value="Delete Customer">
                </form>
            </td>
        </tr>
        <form method="post" action="/vibe/employeeUpdate">
            <tr id = "edit_${c.customerID}" hidden>
                <td><input type="text" value="${c.firstName}" name="fname"></td>
                <td><input type="text" value="${c.lastName}" name="lname"></td>
                <td><input type="text" value="${c.sex}" name="sex"></td>
                <td><input type="text" value="${c.email}" name="email"></td>
                <td><input type="text" value="${c.dob}" name="dob"></td>
                <td><input type="text" value="${c.address}" name="address"></td>
                <td><input type="text" value="${c.city}" name="city"></td>
                <td><input type="text" value="${c.state}" name="state"></td>
                <td><input type="text" value="${c.zipcode}" name="zip"></td>
                <td><input type="text" value="${c.telephone}" name="phone"></td>
                <td><input type="text" value="${c.preferences}" name="preferences"></td>
                <td><input type="submit" id = "edit_form_${c.customerID}" value="Edit Info"></td>
            </tr>
            <input type="text" name="action" value="edit_customer" hidden>
            <input type="text" name="id" value="${c.customerID}" hidden>   
        </form>
        </c:forEach>
    </tbody>
    </table> 
    
                        </c:if>
                    </div>                                   
                </c:when>
            </c:choose>
                    
    </body>
</html>