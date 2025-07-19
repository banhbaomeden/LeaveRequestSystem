<%-- 
    Document   : approveRequest
    Created on : Jul 17, 2025, 11:47:04 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Request" %>
<%
    List<Request> pendingRequests = (List<Request>) request.getAttribute("pendingRequests");
%>
<html>
<head>
    <title>Duyệt đơn nghỉ</title>
</head>
<body>
    <h2>Duyệt đơn nghỉ</h2>
    <form action="approveRequest" method="post">
        <table border="1">
            <tr>
                <th>Chọn</th><th>Nhân viên</th><th>Từ ngày</th><th>Đến ngày</th><th>Lý do</th><th>Trạng thái</th>
            </tr>
            <%
                for (Request r : pendingRequests) {
            %>
            <tr>
                <td><input type="checkbox" name="requestIds" value="<%= r.getId() %>" /></td>
                <td><%= r.getUser().getUsername() %></td>
                <td><%= r.getFromDate() %></td>
                <td><%= r.getToDate() %></td>
                <td><%= r.getReason() %></td>
                <td><%= r.getStatus() %></td>
            </tr>
            <%
                }
            %>
        </table>
        <input type="submit" name="action" value="Phê duyệt" />
        <input type="submit" name="action" value="Từ chối" />
    </form>
</body>
</html>

