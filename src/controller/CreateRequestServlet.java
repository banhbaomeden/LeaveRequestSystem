package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

import dao.RequestDAO;
import model.Request;
import model.User;

public class CreateRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Date fromDate = Date.valueOf(req.getParameter("fromDate"));
        Date toDate = Date.valueOf(req.getParameter("toDate"));
        String reason = req.getParameter("reason");

        Request leaveRequest = new Request(user, fromDate, toDate, reason);
        new RequestDAO().save(leaveRequest);

        req.setAttribute("successMessage", "Đơn xin nghỉ đã được gửi.");
        req.getRequestDispatcher("createRequest.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Gọi lại doPost nếu cần
        doPost(req, resp);
    }
}
