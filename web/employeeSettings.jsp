<%-- 
    Document   : employeeSettings
    Created on : Dec 8, 2016, 5:04:11 PM
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
        
        <title>Employee Settings</title>
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
        <h1>Employee Settings</h1>
        
        Add Employee
    
        <form id ='register' class="form-horizontal" method='post' action ='/vibe/employeeUpdate'>
                <fieldset>
                <!-- hidden parameter for action -->
                <input name ="action" value ="add_employee" hidden>
                <legend>New Employee</legend>
                <div class="form-group">
                    <label class="col-lg-2 control-label">First Name:</label>
                    <div class="col-lg-10">
                    <input name="fname" id="fname" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Last Name:</label>
                    <div class="col-lg-10">
                    <input name="lname" id="lname" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Address:</label>
                    <div class="col-lg-10">
                    <input name="address" id="address" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">City:</label>
                    <div class="col-lg-10">
                    <input name="city" id="city" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">State:</label>
                    <div class="col-lg-10">
                    <input name="state" id="state" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Zip Code:</label>
                    <div class="col-lg-10">
                    <input name="zip" id="zip" class="form-control" type="text" value="" required="true">
                    </div> 
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Telephone Number:</label>
                    <div class="col-lg-10">
                    <input name="phone" id="phone" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Hourly Rate</label>
                    <div class="col-lg-10">
                    <input name="rate" id="rate" class="form-control" type="text" value="" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Start Date: </label>
                    <div class="col-lg-10">
                    <input name="startDate" id="startDate" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">SSN: </label>
                    <div class="col-lg-10">
                    <input name="SSN" id="SSN" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">    
                    <label>
                        <input type="checkbox" name="manager"> Is a Manager?
                    </label>
                </div>
                </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Add New Employee</button>
                </div>
                    
                </fieldset>
            </form>
        
    <table class="table table-striped table-hover" style="width:2000px">
        <thead>
            <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>Phone Number</th>
                    <th>Hourly Pay</th>
                    <th>Start Date</th>
                    <th>Manager</th>
                    <th> </th>
                    <th> </th>
            </tr> 
        </thead>
    <tbody>
    <c:forEach items="${employeeList}" var="e">
        <tr id = "def_${e.empID}">
            <td>${e.firstName}</td>
            <td>${e.lastName}</td>
            <td>${e.address}</td>
            <td>${e.city}</td>
            <td>${e.state}</td>
            <td>${e.zipcode}</td>
            <td>${e.phoneNum}</td>
            <td>${e.pay}</td>
            <td>${e.startDate}</td>
            <td>${e.manager}</td>
            <td><input type="button" value="Edit Employee" onclick="populateEdit(${e.empID})"></td>
            <td>
                <form method="post" action="/vibe/employeeUpdate">
                <input type="text" name= "action" value="delete_emp" hidden>
                <input type="text" name= "id" value="${e.empID}" hidden>
                <input type="submit" value="Delete Employee">
                </form>
            </td>
        </tr>
        <form method="post" action="/vibe/employeeUpdate">
            <tr id = "edit_${e.empID}" hidden>
                <td><input type="text" value="${e.firstName}" name="fname"></td>
                <td><input type="text" value="${e.lastName}" name="lname"></td>
                <td><input type="text" value="${e.address}" name="address"></td>
                <td><input type="text" value="${e.city}" name="city"></td>
                <td><input type="text" value="${e.state}" name="state"></td>
                <td><input type="text" value="${e.zipcode}" name="zip"></td>
                <td><input type="text" value="${e.phoneNum}" name="phone"></td>
                <td><input type="text" value="${e.pay}" name="rate"></td>
                <td><input type="text" value="${e.startDate}" name="startDate"></td>
                <td><input type="text" value="${e.manager}" name="isManager"></td>
                <td><input type="submit" id = "edit_form_${e.empID}" value="Edit Info"></td>
            </tr>
            <input type="text" name="action" value="edit_emp" hidden>
            <input type="text" name="id" value="${e.empID}" hidden>   
        </form>
        </c:forEach>
    </tbody>
    </table> 
    </body>
</html>
