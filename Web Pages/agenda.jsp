<%-- 
    Document   : agenda
    Created on : Jul 17, 2025, 11:47:36 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Request" %>
<%
    List<Request> agenda = (List<Request>) request.getAttribute("agenda");
%>
<html>
<head>
    <title>Lịch nghỉ làm</title>
</head>
<body>
    <h2>Lịch nghỉ làm</h2>
    <table border="1">
        <tr>
            <th>Nhân viên</th><th>Từ ngày</th><th>Đến ngày</th><th>Lý do</th>
        </tr>
        <%
            for (Request r : agenda) {
        %>
        <tr>
            <td><%= r.getUser().getUsername() %></td>
            <td><%= r.getFromDate() %></td>
            <td><%= r.getToDate() %></td>
            <td><%= r.getReason() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

