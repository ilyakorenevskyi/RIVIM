package app.entities;

import java.util.List;
import java.util.Map;

public class Statistic {
    private int users;
    private int periodicals;
    private int activeSubscription;
    private List<Map.Entry<Integer,Periodical>> periodicalBySubs;

    public Statistic() {
    }

    public Statistic(int users, int periodicals, int activeSubscription,  List<Map.Entry<Integer,Periodical>> periodicalBySubs) {
        this.users = users;
        this.periodicals = periodicals;
        this.activeSubscription = activeSubscription;
        this.periodicalBySubs = periodicalBySubs;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(int periodicals) {
        this.periodicals = periodicals;
    }

    public int getActiveSubscription() {
        return activeSubscription;
    }

    public void setActiveSubscription(int activeSubscription) {
        this.activeSubscription = activeSubscription;
    }

    public  List<Map.Entry<Integer,Periodical>> getPeriodicalBySubs() {
        return periodicalBySubs;
    }

    public void setPeriodicalBySubs( List<Map.Entry<Integer,Periodical>> periodicalBySubs) {
        this.periodicalBySubs = periodicalBySubs;
    }
}
