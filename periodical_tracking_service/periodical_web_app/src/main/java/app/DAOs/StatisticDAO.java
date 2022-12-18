package app.DAOs;
import app.entities.Periodical;
import app.entities.Statistic;

import java.sql.*;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticDAO {
    public static Statistic getSystemStat() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/periodicals", "postgres", "admin")) {
            int users = 0;
            int periodicals = 0;
            int subscriptions = 0;
            PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM Client");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                users = rs.getInt(1);
            }
            st= con.prepareStatement("SELECT COUNT(*) FROM Periodical");
            rs = st.executeQuery();
            while (rs.next()){
                periodicals = rs.getInt(1);
            }
            st= con.prepareStatement("SELECT COUNT (*) FROM Subscription WHERE status=true");
            rs = st.executeQuery();
            while (rs.next()){
                subscriptions = rs.getInt(1);
            }
            st= con.prepareStatement("SELECT periodical.*,count(*)\n" +
                    "FROM (periodical INNER JOIN subscription s on periodical.id = s.periodical_id)\n" +
                    "WHERE s.status = true\n" +
                    "GROUP BY periodical.id");
            rs = st.executeQuery();
            List<Map.Entry<Integer,Periodical>> periodicalsStat = new ArrayList<>();
            while (rs.next()){
                periodicalsStat.add(new AbstractMap.SimpleEntry<Integer,Periodical>(rs.getInt(4),new Periodical(rs.getInt(1),rs.getString(2),rs.getDouble(3))));
            }
            System.out.println(users+" " + periodicals);
            Statistic statistic = new Statistic(users,periodicals,subscriptions,periodicalsStat);
            return statistic;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
