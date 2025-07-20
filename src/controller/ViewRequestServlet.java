package controller;

import dao.RequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Request;
import model.User;

@WebServlet(name = "ViewRequestServlet", urlPatterns = {"/viewRequest"})
public class ViewRequestServlet extends HttpServlet {

    // Điều chỉnh ID role theo dữ liệu bảng roles của bạn
    private static final int ROLE_ADMIN   = 1;
    private static final int ROLE_MANAGER = 2;
    // (Ví dụ: EMPLOYEE = 3)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Lấy user từ session
        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 2. Quyết định phạm vi dữ liệu
        boolean showAll = false;

        // Nếu user có role admin / manager → được xem tất cả
        int roleId = currentUser.getRoleId();  // đảm bảo User có field roleId
        if (roleId == ROLE_ADMIN || roleId == ROLE_MANAGER) {
            showAll = true;
        }

        // Hoặc thêm ?all=1 trên URL để debug (mọi user)
        String allParam = req.getParameter("all");
        if ("1".equals(allParam) || "true".equalsIgnoreCase(allParam)) {
            showAll = true;
        }

        // 3. Lấy dữ liệu từ DAO
        RequestDAO requestDAO = new RequestDAO();
        List<Request> requestList;
        if (showAll) {
            requestList = requestDAO.list();          // tất cả
        } else {
            requestList = requestDAO.getByUserId(currentUser.getId()); // của user
        }

        // 4. Log debug ra console
        System.out.println("=== ViewRequestServlet ===");
        System.out.println("currentUser.id      = " + currentUser.getId());
        System.out.println("currentUser.roleId  = " + roleId);
        System.out.println("showAll             = " + showAll);
        System.out.println("requests returned   = " + (requestList == null ? 0 : requestList.size()));

        // 5. Đưa sang JSP
        req.setAttribute("requestList", requestList == null ? new ArrayList<>() : requestList);
        req.getRequestDispatcher("viewRequest.jsp").forward(req, resp);
    }
}
