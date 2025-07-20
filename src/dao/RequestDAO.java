package dao;

import model.Request;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO thao tác bảng requests.
 */
public class RequestDAO extends DBConnection<Request> {

    private static final Logger LOGGER = Logger.getLogger(RequestDAO.class.getName());

    /* ===== Helper map row ===== */
    private Request mapRow(ResultSet rs) throws SQLException {
        Request r = new Request();
        r.setId(rs.getInt("id"));
        r.setUserId(rs.getInt("user_id"));
        r.setFrom(rs.getDate("from_date"));
        r.setTo(rs.getDate("to_date"));
        r.setReason(rs.getString("reason"));
        r.setStatus(rs.getString("status"));
        return r;
    }

    /* ===== Lấy các request của 1 user ===== */
    public ArrayList<Request> getByUserId(int userId) {
        ArrayList<Request> list = new ArrayList<>();
        String sql = "SELECT id,user_id,from_date,to_date,reason,status FROM requests WHERE user_id=?";
        try (Connection con = ensureConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "getByUserId error", e);
        }
        return list;
    }

    /* ===== IMPLEMENT abstract methods từ DBConnection ===== */

    @Override
    public ArrayList<Request> list() {
        ArrayList<Request> list = new ArrayList<>();
        String sql = "SELECT id,user_id,from_date,to_date,reason,status FROM requests";
        try (Connection con = ensureConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "list error", e);
        }
        return list;
    }

    @Override
    public Request get(int id) {
        Request r = null;
        String sql = "SELECT id,user_id,from_date,to_date,reason,status FROM requests WHERE id=?";
        try (Connection con = ensureConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) r = mapRow(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "get error", e);
        }
        return r;
    }

    @Override
    public void insert(Request r) {
        String sql = "INSERT INTO requests(user_id,from_date,to_date,reason,status) VALUES(?,?,?,?,?)";
        try (Connection con = ensureConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getUserId());
            ps.setDate(2, r.getFrom());
            ps.setDate(3, r.getTo());
            ps.setString(4, r.getReason());
            ps.setString(5, r.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "insert error", e);
        }
    }

    @Override
    public void update(Request r) {
        String sql = "UPDATE requests SET from_date=?,to_date=?,reason=?,status=? WHERE id=?";
        try (Connection con = ensureConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, r.getFrom());
            ps.setDate(2, r.getTo());
            ps.setString(3, r.getReason());
            ps.setString(4, r.getStatus());
            ps.setInt(5, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "update error", e);
        }
    }

    @Override
    public void delete(Request r) {
        String sql = "DELETE FROM requests WHERE id=?";
        try (Connection con = ensureConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "delete error", e);
        }
    }
}
