package dataAccessLayer;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Client client) throws SQLException {
        String query = "INSERT INTO clients(name, email, address) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getAddress());
            statement.executeUpdate();
        }
    }

    public void update(Client client) throws SQLException {
        String query = "UPDATE clients SET name=?, email=?, address=? WHERE idClient=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getAddress());
            statement.setInt(4, client.getIdClient());
            statement.executeUpdate();
        }
    }

    public void delete(int idClient) throws SQLException {
        String query = "DELETE FROM clients WHERE idClient=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idClient);
            statement.executeUpdate();
        }
    }

    public List<Client> findAll() throws SQLException {
        String query = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("idClient"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setAddress(resultSet.getString("address"));
                clients.add(client);
            }
        }

        return clients;
    }

    public Client findById(int idClient) throws SQLException {
        String query = "SELECT * FROM clients WHERE idClient=?";
        Client client = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idClient);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    client = new Client();
                    client.setIdClient(resultSet.getInt("idClient"));
                    client.setName(resultSet.getString("name"));
                    client.setEmail(resultSet.getString("email"));
                    client.setAddress(resultSet.getString("address"));
                }
            }
        }

        return client;
    }
}

