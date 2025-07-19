<%-- 
    Document   : error
    Created on : Jul 17, 2025, 11:47:59 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lỗi hệ thống</title>
</head>
<body>
    <h2 style="color:red;">Có lỗi xảy ra!</h2>
    <p><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "Không rõ nguyên nhân." %></p>
    <a href="login.jsp">Quay lại trang đăng nhập</a>
</body>
</html>
