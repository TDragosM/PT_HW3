package businessLayer;

import dataAccessLayer.ProductDAO;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(String name, int stock) throws SQLException {
        Product product = new Product(name, stock);
        productDAO.insert(product);
    }

    public void updateProduct(int id, String name, int stock) throws SQLException {
        Product product = new Product(id, name, stock);
        productDAO.update(product);
    }

    public void deleteProduct(int id) throws SQLException {
        productDAO.delete(id);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAll();
    }
}
