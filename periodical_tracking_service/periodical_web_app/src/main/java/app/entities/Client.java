package app.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Client {
    public int id;
    public String name;
    public String city;
    public String adress;
    public String email;

    public Client() {
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Client(int id, String name,String city, String adress,String email) {
        this.id = id;
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Client getClientFromRS(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(Integer.parseInt(rs.getString(1)));
        client.setName(rs.getString(2));
        return client;
    }
}
