package businessLayer;

import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;
import model.Order;
import model.Product;

import java.sql.SQLException;

public class OrderService {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderService(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    public void createOrder(int clientId, int productId, int quantity) throws InsufficientStockException, SQLException {
        Product product = productDAO.getById(productId);
        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Not enough stock to fulfill order.");
        }
        productDAO.updateProductStock(productId,  quantity);

        Order order = new Order(clientId, productId, quantity);
        orderDAO.create(order);
    }
}
