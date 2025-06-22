<%-- 
    Document   : login
    Created on : Jun 22, 2025, 11:17:58 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form method="post" action="login">
            Username: <input name="username" /><br/><!-- comment -->
            Password: <input type="password" name="password" /><br/><!-- comment -->
            <input type="submit" value="Login"/>
        </form>
        <%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %>
    </body>
</html>
