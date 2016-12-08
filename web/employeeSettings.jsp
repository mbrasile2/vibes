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
        <form method="post" action="/vibe/employeeUpdate">
            <input type="text" name = "action" value="add_employee" hidden> 
            <input type="submit" value="Add New Employee">
        </form>
    </body>
</html>
