<%-- 
    Document   : createRequest
    Created on : Jul 12, 2025, 11:32:48 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leave of absence</title>
    </head>
    <body>
        <h1>Create leave application</h1>
        <<form action="createRequest" method="post">
            From: <input type="date" name="fromDate" required /><br/><!-- comment -->
            To: <input type="date" name="toDate" required /><br/><!-- comment -->
            Reason: <input type="text" name="reason"/> <br/>
            <input type="submit" value="Send" />
        </form>
    </body>
</html>
