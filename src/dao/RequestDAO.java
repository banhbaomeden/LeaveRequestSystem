package dao;

import model.Request;
import model.User;
import model.LeaveStatus;

import java.sql.*;
import java.util.*;

public class RequestDAO {
    public void save(Request r) {
        String sql = "INSERT INTO requests (user_id, from_date, to_date, reason, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getUser().getId());
            ps.setDate(2, r.getFromDate());
            ps.setDate(3, r.getToDate());
            ps.setString(4, r.getReason());
            ps.setString(5, r.getStatus().name());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Request> getByUser(int userId) {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request r = new Request(
                    rs.getInt("id"),
                    new User(userId), // chỉ có id, chưa truy vấn đủ info
                    rs.getDate("from_date"),
                    rs.getDate("to_date"),
                    rs.getString("reason"),
                    LeaveStatus.valueOf(rs.getString("status"))
                );
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Request> getPending() {
        return getByStatus("PENDING");
    }

    public List<Request> getApprovedRequests() {
        return getByStatus("APPROVED");
    }

    private List<Request> getByStatus(String status) {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT r.*, u.username FROM requests r JOIN users u ON r.user_id = u.id WHERE r.status = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User(rs.getInt("user_id"), rs.getString("username"), null, null, null);
                Request r = new Request(
                    rs.getInt("id"),
                    u,
                    rs.getDate("from_date"),
                    rs.getDate("to_date"),
                    rs.getString("reason"),
                    LeaveStatus.valueOf(rs.getString("status"))
                );
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateStatus(int id, String newStatus) {
        String sql = "UPDATE requests SET status = ? WHERE id = ?";
        try (Connection conn = (Connection) DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
