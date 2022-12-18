package app.DAOs;

import app.ConnectionPool;
import app.entities.Periodical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalsDAO {

    public static Periodical getPeriodicalFromRS(ResultSet rs) throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setId(rs.getInt(1));
        periodical.setName(rs.getString(2));
        periodical.setPrice(rs.getDouble(3));
        return periodical;
    }

    public static Periodical getPeriodicalById(int id) {
        Periodical periodical = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM periodical WHERE id =" + id;
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                periodical = getPeriodicalFromRS(rs);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return periodical;
    }

    public static List<Periodical> getPeriodicalList()  {

        List<Periodical> periodicalList = new ArrayList<>();
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM periodical");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                periodicalList.add(getPeriodicalFromRS(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return periodicalList;
    }

    public static void deletePeriodical(int id)  {
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
           Statement statement =  con.createStatement();
           statement.executeUpdate("DELETE FROM Periodical WHERE id = " + id);
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

    public static void editPeriodical(Periodical updatedPeriodical) throws ClassNotFoundException {
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            Statement statement =  con.createStatement();
            String sql = "UPDATE Periodical SET" + " name = '"
                    + updatedPeriodical.getName() + "' , price = " + updatedPeriodical.getPrice() + " WHERE id = " + updatedPeriodical.getId();
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

    public static boolean addPeriodical(Periodical periodical) throws ClassNotFoundException {
        boolean success = false;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            Statement statement = con.createStatement();
            String query = "INSERT INTO periodical(id, name, price) VALUES( ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, periodical.getId());
            preparedStatement.setString(2, periodical.getName());
            preparedStatement.setDouble(3, periodical.getPrice());
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return success;
    }
}
