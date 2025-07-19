/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RequestDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class ApproveRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("requestIds");
        String action = req.getParameter("action");

        if (ids != null && action != null) {
            for (String idStr : ids) {
                int id = Integer.parseInt(idStr);
                if (action.equals("Phê duyệt")) {
                    new RequestDAO().updateStatus(id, "APPROVED");
                } else if (action.equals("Từ chối")) {
                    new RequestDAO().updateStatus(id, "REJECTED");
                }
            }
        }

        resp.sendRedirect("approveRequest.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pendingRequests", new RequestDAO().getPending());
        req.getRequestDispatcher("approveRequest.jsp").forward(req, resp);
    }
    
}
