package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    public static int INITIAL_POOL_SIZE  = 5;
    private final List<Connection> connections;
    private final List<Connection> usedConnections = new ArrayList<>();
    private final static ConnectionPool instance  = new ConnectionPool("jdbc:postgresql://localhost:5432/periodicals", "postgres", "admin");

    public ConnectionPool(String url,String user,String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connections = new ArrayList<>(INITIAL_POOL_SIZE);
        for(int i = 0;i<INITIAL_POOL_SIZE;i++){
            try {
                connections.add(DriverManager.getConnection(url,user,password));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        synchronized (this) {
            if (connections.isEmpty()) {
                return conn;
            }
            conn = connections.remove(connections.size() - 1);
            usedConnections.add(conn);
        }
        return conn;
    }

    public boolean releaseConnection(Connection conn) {
        synchronized (this) {
            if(!usedConnections.contains(conn)) {
                return false;
            }
            else{
                usedConnections.remove(conn);
                connections.add(conn);
                return true;
            }
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

}
