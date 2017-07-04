<%-- 
    Document   : loginsuccess
    Created on : Jun 19, 2017, 12:23:15 PM
    Author     : wendy
--%>

<%@page import="beans.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success Login</title>
    </head>
    <body>
        <p>You are successfully logged in!</p>
        <jsp:useBean id="user" scope="request" type="beans.User" />
        <p>
            Email: ${user.email}
            <br>
            Password: ${user.password}
        </p>
    </body>
</html>
