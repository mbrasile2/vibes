<%-- 
    Document   : index
    Created on : Nov 30, 2016, 8:10:24 PM
    Author     : kATHRYN
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
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/vibe/">Home</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/vibe/">Home<span class="sr-only">(current)</span></a></li>
        <li><a href="">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
      </ul>
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
        <c:choose>
            <c:when test="${user != null}">
                
            <input type='button' value = 'Logout' onclick="location.href='./logout'">
            <hr>
            Welcome ${user.firstName} ${user.lastName}!
            <div>
                <input type='button' value = 'Messages' onclick="location.href='./messages.jsp'">
            </div>
            <div>
                <input type='button' value = 'My Wall' onclick="location.href='./page/${pageID}'">
            </div>
            <div>
                <input type='button' value = 'Check my Groups' onclick="location.href='./groups.jsp'">
            </div>
            </c:when>
        <c:otherwise>
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
            
        
        </c:otherwise>
        </c:choose>
    </body>
</html>
