package app.DAOs;

import app.ConnectionPool;
import app.entities.Subscription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {

    public  static Subscription getSubFromRS(ResultSet rs) throws SQLException {
        return new Subscription(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)
                ,rs.getInt(5),rs.getBoolean(6));
    }

    public static int getMaxID() {
        int max = 0;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT MAX(id) FROM subscription");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                max = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return max;
    }
    public static List<Subscription> getUserSubscriptions(int id){
       List<Subscription> subs = new ArrayList<>();
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM subscription where client_id = " + id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                subs.add(getSubFromRS(rs));
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return subs;
    }

    public static Subscription subscribe(int clientId, int periodicalId, int period) {
        Subscription sub = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("INSERT INTO subscription(id, client_id, periodical_id, start_date, period,status) VALUES( ?, ?, ?, ?, ?,true)");
            int max = getMaxID();
            LocalDate today = LocalDate.now();
            System.out.println(max + " " + clientId + " " + periodicalId + " " + Date.valueOf(today) + " " + period);
            st.setInt(1,max+1);
            st.setInt(2,clientId);
            st.setInt(3,periodicalId);
            st.setDate(4, Date.valueOf(today));
            st.setInt(5,period);
            Subscription temp = new Subscription(max+1,clientId,periodicalId,today.toString(),period,true);
            st.executeUpdate();
            sub = temp;
            con.commit();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return sub;
    }

    public static void deactivateSubscription(int subscribtionId){
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            Statement statement =  con.createStatement();
            String sql = "UPDATE subscription SET status = "
                    + false + " WHERE id = " + subscribtionId;
            statement.executeUpdate(sql);
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnectionPool.getInstance().releaseConnection(con);
    }
}
