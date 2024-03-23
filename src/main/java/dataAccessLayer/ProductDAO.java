package dataAccessLayer;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, stock) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.executeUpdate();
        }
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE products SET name=?, stock=? WHERE idProduct=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.setInt(3, product.getIdProduct());
            statement.executeUpdate();
        }
    }

    public void delete(int idProduct) throws SQLException {
        String sql = "DELETE FROM products WHERE idProduct=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProduct);
            statement.executeUpdate();
        }
    }

    public List<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM products";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setName(resultSet.getString("name"));
                product.setStock(resultSet.getInt("stock"));
                products.add(product);
            }
            return products;
        }
    }

    public Product getById(int idProduct) throws SQLException {
        String sql = "SELECT * FROM products WHERE idProduct=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProduct);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setIdProduct(resultSet.getInt("idProduct"));
                    product.setName(resultSet.getString("name"));
                    product.setStock(resultSet.getInt("stock"));
                    return product;
                } else {
                    return null;
                }
            }
        }
    }

    public void updateProductStock(int productId, int i) throws SQLException {
        Product product = getById(productId);
        String sql = "UPDATE products SET stock=? WHERE idProduct=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getStock() - i);
            statement.setInt(2, product.getIdProduct());
            statement.executeUpdate();
        }
    }

}
