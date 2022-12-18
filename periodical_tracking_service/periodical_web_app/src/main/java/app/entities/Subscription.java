package app.entities;

import app.DAOs.PeriodicalsDAO;

import java.sql.SQLException;

public class Subscription {
    private final int id;
    private int clientId;
    private int periodicalId;
    private String date;
    private int period;
    private boolean active;

    public int getId() {
        return id;
    }

    public Subscription(int id, int clientId, int periodicalId, String date, int period, boolean active) {
        this.id = id;
        this.clientId = clientId;
        this.periodicalId = periodicalId;
        this.date = date;
        this.period = period;
        this.active = active;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public Periodical getPeriodical() throws SQLException, ClassNotFoundException {
        return PeriodicalsDAO.getPeriodicalById(this.periodicalId);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }



}
