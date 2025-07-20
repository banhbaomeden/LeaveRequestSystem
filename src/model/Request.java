package model;

import java.sql.Date;

public class Request implements IModel {
    private int id;
    private int userId;
    private Date from;
    private Date to;
    private String reason;
    private String status;

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getFrom() {
        return from;
    }
    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }
    public void setTo(Date to) {
        this.to = to;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
