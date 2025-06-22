/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import org.apache.catalina.User;

/**
 *
 * @author admin
 */
public class UserDAO {
    public static User authenticate(String username, String password) {
        try (Connection conn = DBContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("full_name")) {};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
