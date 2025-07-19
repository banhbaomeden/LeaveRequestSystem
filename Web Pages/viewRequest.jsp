<%-- 
    Document   : viewRequest
    Created on : Jul 17, 2025, 11:46:29 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Request" %>
<%
    List<Request> requestList = (List<Request>) request.getAttribute("requests");
%>
<html>
<head>
    <title>Danh sách đơn nghỉ</title>
</head>
<body>
    <h2>Danh sách đơn nghỉ</h2>
    <table border="1">
        <tr>
            <th>Từ ngày</th><th>Đến ngày</th><th>Lý do</th><th>Trạng thái</th>
        </tr>
        <%
            for (Request r : requestList) {
        %>
            <tr>
                <td><%= r.getFromDate() %></td>
                <td><%= r.getToDate() %></td>
                <td><%= r.getReason() %></td>
                <td><%= r.getStatus() %></td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>

