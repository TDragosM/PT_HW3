package model;

public class Order {
    private int id;
    private int quantity;
    private int clientId;
    private int productId;
    public Order(){

    }

    public Order(int clientId, int productId, int quantity) {
        this.clientId=clientId;
        this.productId=productId;
        this.quantity=quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return clientId;
    }

    public int getIdProduct() {
        return productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int idClient) {
        this.clientId = idClient;
    }

    public void setIdProduct(int idProduct) {
        this.productId = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
