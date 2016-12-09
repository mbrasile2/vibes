<%-- 
    Document   : employeeSettings
    Created on : Dec 8, 2016, 5:04:11 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Settings</title>
    </head>
    <body>
        <h1>Employee Settings</h1>
        
        Add Employee
    
        <form id ='register' class="form-horizontal" method='post' action ='/vibe/employeeUpdate'>
                <fieldset>
                <!-- hidden parameter for action -->
                <input name ="action" id ="action" value ="add_employee" hidden>
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
    </body>
</html>
