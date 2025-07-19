package model;

import java.sql.Date;

public class Request {
    private int id;
    private User user;
    private Date fromDate;
    private Date toDate;
    private String reason;
    private LeaveStatus status;

    public Request() {}

    // Constructor khi tạo mới đơn
    public Request(User user, Date fromDate, Date toDate, String reason) {
        this.user = user;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status = LeaveStatus.PENDING;
    }

    // Constructor đầy đủ
    public Request(int id, User user, Date fromDate, Date toDate, String reason, LeaveStatus status) {
        this.id = id;
        this.user = user;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status = status;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
}
