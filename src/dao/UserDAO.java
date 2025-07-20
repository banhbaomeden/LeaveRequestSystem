package dao;

import java.util.ArrayList;
import model.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p14s
 */
public class UserDAO extends DBConnection<User> {

    public User get(String username,String password) throws SQLException {
   
        User user = null;
        String sql = "SELECT u.id, u.username, u.password, u.role_id, u.department_id "
                   + "FROM users u WHERE u.username = ? AND u.password = ?";
        try (Connection con = ensureConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, username);
            stm.setString(2, password);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setDepartmentId(rs.getInt("department_id"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT id, username, password, role_id, department_id FROM users";
        try (Connection con = ensureConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("role_id"));
                user.setDepartmentId(rs.getInt("department_id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public User get(int id) {
        User user = null;
        String sql = "SELECT id, username, password, role_id, department_id FROM users WHERE id = ?";
        try (Connection con = ensureConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setDepartmentId(rs.getInt("department_id"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users (username, password, role_id, department_id) VALUES (?,?,?,?)";
        try (Connection con = ensureConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getRoleId());
            stm.setInt(4, user.getDepartmentId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username=?, password=?, role_id=?, department_id=? WHERE id=?";
        try (Connection con = ensureConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getRoleId());
            stm.setInt(4, user.getDepartmentId());
            stm.setInt(5, user.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection con = ensureConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, user.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
