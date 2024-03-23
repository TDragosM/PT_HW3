package dataAccessLayer;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Order order) throws SQLException {
        String sql = "INSERT INTO orders (quantity, clientId, productId) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getQuantity());
            statement.setInt(2, order.getIdClient());
            statement.setInt(3, order.getIdProduct());
            statement.executeUpdate();
        }
    }

    public void update(Order order) throws SQLException {
        String sql = "UPDATE orders SET quantity=?, clientId=?, productId=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getQuantity());
            statement.setInt(2, order.getIdClient());
            statement.setInt(3, order.getIdProduct());
            statement.setInt(4, order.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Order order) throws SQLException {
        String sql = "DELETE FROM orders WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getId());
            statement.executeUpdate();
        }
    }

    public List<Order> getAll() throws SQLException {
        String sql = "SELECT * FROM orders";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setIdClient(resultSet.getInt("id_client"));
                order.setIdProduct(resultSet.getInt("id_product"));
                orders.add(order);
            }
            return orders;
        }
    }

    public Order getById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setIdClient(resultSet.getInt("id_client"));
                    order.setIdProduct(resultSet.getInt("id_product"));
                    return order;
                } else {
                    return null;
                }
            }
        }
    }
}
