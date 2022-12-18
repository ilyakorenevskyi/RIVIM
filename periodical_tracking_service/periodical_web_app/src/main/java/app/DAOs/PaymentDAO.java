package app.DAOs;

import app.ConnectionPool;
import app.entities.Payment;
import app.entities.Subscription;

import java.sql.*;
import java.time.LocalDate;

public class PaymentDAO {

    public static int getMaxID() throws ClassNotFoundException {
        int max = 0;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT MAX(id) FROM payment");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                max = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return max;
    }

    public static Payment createPayment(Subscription subscription) throws ClassNotFoundException {
        Payment payment = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO payment(id, subscription_id, date, amount) VALUES( ?, ?, ?, ?)");
            Payment temp = new Payment();
            int new_id = getMaxID()+1;
            ps.setInt(1,new_id);
            temp.setId(new_id);
            ps.setInt(2,subscription.getId());
            temp.setSubscriptionId(subscription.getId());
            LocalDate today = LocalDate.now();
            temp.setDate(today.toString());
            ps.setDate(3,Date.valueOf(today));
            double total = subscription.getPeriod() * subscription.getPeriodical().getPrice();
            temp.setTotalAmount(total);
            ps.setDouble(4, total);
            ps.executeUpdate();
            payment = temp;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return payment;
    }
}
