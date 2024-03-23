package model;

import java.util.Formatter;

public class Product {
    private int idProduct;
    private int stock;
    private String name;

    public Product(){

    }
    public Product(String name, int stock) {
        this.name=name;
        this.stock=stock;
    }

    public Product(int id, String name, int stock) {
        this.idProduct= id;
        this.stock=stock;
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public int getIdProduct(){
        return idProduct;
    }
    public int getStock(){
        return stock;
    }
    public void setIdProduct(int idProduct1){
        idProduct=idProduct1;
    }
    public void setName(String name1){
        name=name1;
    }
    public void setStock(int stock1){
        stock=stock1;
    }
}
