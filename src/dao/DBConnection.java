package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.IModel;


public abstract class DBConnection<T extends IModel> {

    private static final String DB_URL =
        "jdbc:sqlserver://localhost:1433;databaseName=leavemanagement;encrypt=true;trustServerCertificate=true;";
    private static final String DB_USER = "hienlthe186881";
    private static final String DB_PASS = "23072004";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    protected Connection connection;

    public DBConnection() {
        this.connection = openNewConnection();  // cố mở ngay
    }

    private Connection openNewConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Driver not found", e);
            throw new RuntimeException("Không tìm thấy driver JDBC SQL Server.", e);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "Cannot connect DB", e);
            throw new RuntimeException("Không kết nối được tới database leavemanagement.", e);
        }
    }

    
    protected Connection ensureConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = openNewConnection();
        }
        return connection;
    }

    /**
     * Đóng connection (nếu bạn muốn chủ động đóng; phần lớn DAO không cần gọi).
     */
    protected void closeQuietly() {
        if (connection != null) {
            try { connection.close(); } catch (SQLException e) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.WARNING, "Error closing connection", e);
            }
        }
    }

    // abstract CRUD
    public abstract ArrayList<T> list();
    public abstract T get(int id);
    public abstract void insert(T model);
    public abstract void update(T model);
    public abstract void delete(T model);
}
