<%-- 
    Document   : anon-header
    Created on : Dec 7, 2016, 11:26:27 AM
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
            .jumbotron > * {
                margin-left: 10%;
                margin-right: 10%;
            }
        </style>
        <title>Vibe</title>
    </head>
    <body>
        <h1>Vibe</h1>
        <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/vibe/">Home</a>
    </div>
  </div>
</nav>
        <div class="modal" id ="loginModal" style="display:none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id ='login-area'>
                            <form class="form-horizontal" id = "login" method="post" onclick ="./login">
            <fieldset>
                <legend>User Log In</legend>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="inputEmail" name="user" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword"  class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
                    </div>    
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </div>
        </fieldset>
        </form>
                
        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="jumbotron">
            <h1>Welcome!</h1>
            <p>Vibe is a new way to keep in touch with your friends. Make groups, send messages, post on your personal wall!</p>
            <p><a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#loginModal">Login</a> 
                <a class="btn btn-primary btn-lg" id="open-register" data-toggle="modal" data-target="#registerModal">Register</a></p>
        </div>
        
            <hr>
        <div id ='login-area'>
            <p>
                Employees, log in <a href = "./employeeIndex.jsp">here</a>.
        </div>
            
            <div class="modal" id ="registerModal" style="display:none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div id='new-user'>
            <form id ='register' class="form-horizontal" method='post' action ='./update'>
                <fieldset>
                <!-- hidden parameter for action -->
                <input name ="action" id ="action" value ="register" hidden>
                <legend>Register</legend>
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
                    <input name="address" id="address" class="form-control" type="text" value="">
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
                    <input name="zip" id="zip" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Telephone Number:</label>
                    <div class="col-lg-10">
                    <input name="phone" id="phone" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Email Address:</label>
                    <div class="col-lg-10">
                    <input name="email" id="email" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Password:</label>
                    <div class="col-lg-10">
                    <input name="password" id="password" class="form-control" type="text" value="" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label">Preferences:</label>
                    <div class="col-lg-10">
                    <input name="prefs" id="prefs" class="form-control" type="text" value="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                    <button type="reset" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
                </fieldset>
            </form>
        </div>
       
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
