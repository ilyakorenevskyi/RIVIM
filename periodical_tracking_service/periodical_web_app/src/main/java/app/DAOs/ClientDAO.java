package app.DAOs;

import app.ConnectionPool;
import app.entities.Client;

import java.sql.*;
import java.util.*;

public class ClientDAO {

    public static int getMaxID() {
        int max = 0;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT MAX(id) FROM client");
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

    public static Client getUserInfo(String login) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM client where login=?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return Client.getClientFromRS(rs);
            }
            else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return null;
    }

    public static boolean validate(String login, String password){
        boolean status = false;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM client where login=? and password=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            status = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return status;
    }

    public static void deleteClient(int id)  {
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            Statement statement =  con.createStatement();
            statement.executeUpdate("DELETE FROM client WHERE id = " + id);
            con.commit();
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
    }

    public static boolean addClient(Client client, String login, String password) {
        boolean success = false;
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("INSERT INTO client(id,login,password,name) VALUES( ?, ?, ?, ?)");
            st.setInt(1,client.getId());
            st.setString(2,login);
            st.setString(3,password);
            st.setString(4,client.getName());
            st.executeUpdate();
            success = true;
            con.commit();
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return success;
    }

    public static List<Client> getUserList() {
        List<Client> userList = new ArrayList<Client>();
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM client");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                userList.add(Client.getClientFromRS(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return userList;
    }

    public static String getUserRole(String login) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement st = con.prepareStatement("SELECT role.name FROM client INNER JOIN role on client.role = role.id where login=?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }
            else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(con);
        return null;
    }
}
