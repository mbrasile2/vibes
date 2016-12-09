<%-- 
    Document   : emp-anon-header
    Created on : Dec 8, 2016, 11:11:36 PM
    Author     : kATHRYN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>       
        <style>
            body {
                margin-left: 10%;
                margin-right: 10%;
            }
            
        </style>
        <title>Vibe Employee Portal</title>
    </head>
    <body>
        <h1>Vibe Employee Portal</h1>
        <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/vibe/">Home</a>
    </div>
  </div>
</nav>
      <div id ='login-area'>
        <form class="form-horizontal" id = "login" method="post" action ="/vibe/employee-login">
            <fieldset>
                <legend>Employee Log In</legend>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">SSN</label>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" id="inputEmail" name="user" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword"  class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-5">
                        <input type="password" class="form-control" name="password" required="true">
                    </div>    
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                    <input type="submit" class="btn btn-primary" value="Login">
                </div>
            </div>
        </fieldset>
        </form>
                
        </div>
    </body>
</html>