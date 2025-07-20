package controller;

import dao.RequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import model.Request;
import model.User;

@WebServlet(name = "CreateRequestServlet", urlPatterns = {"/createRequest"})
public class CreateRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            // Lấy user từ session
            User currentUser = (User) req.getSession().getAttribute("user");
            if (currentUser == null) {
                out.println("Bạn cần đăng nhập trước!");
                return;
            }

            // Lấy dữ liệu từ form
            String fromDateStr = req.getParameter("fromDate");
            String toDateStr = req.getParameter("toDate");
            String reason = req.getParameter("reason");

            Date fromDate = Date.valueOf(fromDateStr);
            Date toDate = Date.valueOf(toDateStr);

            // Tạo object Request
            Request r = new Request();
            r.setUserId(currentUser.getId());
            r.setFrom(fromDate);
            r.setTo(toDate);
            r.setReason(reason);
            r.setStatus("PENDING");

            // Lưu vào DB
            RequestDAO dao = new RequestDAO();
            dao.insert(r);

            out.println("Request created successfully!");
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("Please submit the form to create a request!");
    }
}
