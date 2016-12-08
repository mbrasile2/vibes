<%-- 
    Document   : advertisments
    Created on : Dec 6, 2016, 10:23:00 PM
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <title>Advertisements</title>
        <script>
            function populateForm() {
                
                $('#create-ad').show();
            }
            function hideForm() {
                
                $('#create-ad').hide();
            }
            function showAds(){
                $('#view-ads').show();
            }
            function hideAds() {
                
                $('#view-ads').hide();
            }
        </script>
    </head>
    <body>
        <h1>Vibes</h1>
        <div>
            <input type='button' value = 'View Advertisements'id="View Advertisements" onclick = 'showAds()'>
        </div>
        <div id='view-ads' hidden>
            <h2> Advertisements </h2>
            <c:forEach items="${messages}" var = "m">
                
            </c:forEach>
        </div>
        <div>
                    <input type='button' value = 'Create Advertisement'id="Create-Advertisement" onclick = 'populateForm()'>
                </div>
        <div id='create-ad' hidden>
            <form id ='ads' method='post' action ='./advertisementServlet'>
                <!-- hidden parameter for action -->
                <input name ="action" id ="action" value ="create" hidden>
                <div>
                    <label>Type:</label>
                    <input name="type" id="type" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Item Name:</label>
                    <input name="iname" id="iname" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Company:</label>
                    <input name="company" id="company" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Content:</label>
                    <input name="content" id="content" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Price:</label>
                    <input name="price" id="price" size="36" type="text" value="" required="true">
                </div>
                <div>
                    <label>Units Available:</label>
                    <input name="uavailable" id="uavailable" size="36" type="text" value="" required="true">
                </div>
                <input id="create-submit" name="create-submit" value="Create" type="submit">
            </form>
        </div>
        
    </body>
</html>
