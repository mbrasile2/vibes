<%-- 
    Document   : emp-header
    Created on : Dec 8, 2016, 11:02:59 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vibe</title>
    </head>
    <h1>Vibe Employee Portal</h1>
    <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/vibe/employeeIndex.jsp">Home</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
         
                <li><a href="/vibe/employeeSettings.jsp">Employee Info</a></li>
                <li><a href="/vibe/sales.jsp">Sales</a></li>
                <li><a href="/vibe/customer">Customers</a></li>
                <li><a href="/vibe/items">Items</a></li>
       
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
          <li><a href="/vibe/logout">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>           
</html>
