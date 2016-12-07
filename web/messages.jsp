<%-- 
    Document   : messages
    Created on : Dec 2, 2016, 3:39:23 PM
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
        
        <title>Messages</title>
        
        <style>
            body {
                margin-left: 10%;
                margin-right: 10%;
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${user != null}">   
            <%@include file="/pages/loggedin-header.jsp"%>
            </c:when>
        <c:otherwise>
            <jsp:forward page="/index.jsp" />
        </c:otherwise>
        </c:choose>
        
        <form class="form-horizontal" method ="post" action ="./update">
            <fieldset>
                <legend>Send a message</legend>
                <input name ="action" id ="action" value ="send_msg" hidden>
                <input name ="sender" id ="sender" value ="${user.accountNumber}" hidden>
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-1 control-label">Receiver:</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="receiver" size = "35" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                        <label for="input" class="col-sm-1 control-label">Message:</label>
                        <div class="col-sm-3">
                        <textarea name="content" cols="40" rows="5" placeholder="Type your message here."></textarea>
                    </div>
                </div>
                <label class="col-sm-1 control-label"></label>
                <input id="msg-submit" name="msg-submit" class="btn btn-primary" value="Send Message" type="submit">
            </fieldset>
        </form>
            
        </div>
        <h2> Your Messages: </h2>
        <c:forEach items="${messages}" var = "m">
            
            <div class="panel panel-info" style="width:550px;">
                <div class="panel-heading">
                    <h3 class="panel-title">${m.sender}  wrote on ${m.date} :</h3>
                </div>
            <div class="panel-body">
                ${m.content}
                <br>
                <div style = "margin-left: 50%">
                    <form method='post' action ='./update'>
                        <!-- hidden parameter for action -->
                        <input name ="action" id ="action" value ="delete_msg" hidden>
                        <input name ="mid" id ="action" value ="${m.mid}" hidden>
                
                        <input value="Delete message" type="submit" class="btn btn-default">
                    </form>
                </div>
            </div>
        </div>
 
            
        </c:forEach>
    </body>
</html>
