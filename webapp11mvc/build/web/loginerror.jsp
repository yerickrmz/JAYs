<%-- 
    Document   : loginerror
    Created on : Jun 19, 2017, 12:23:46 PM
    Author     : wendy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Login</title>
    </head>
    <body>
        <p>Sorry! username or password error</p>
        <jsp:useBean id="error" scope="request" type="java.lang.String" />
        <p>
            Error: ${error}
        </p>
    </body>
</html>
