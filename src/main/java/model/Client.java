package model;

public class Client {
    private int idClient;
    private String name;
    private String email;
    private String address;

    public Client(int id, String name, String email, String address) {
        this.idClient=id;
        this.name=name;
        this.email=email;
        this.address=address;
    }

    public Client() {

    }

    public Client(String name, String email, String address) {
        this.name=name;
        this.email=email;
        this.address=address;
    }

    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }

    public int getIdClient(){
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient=idClient;
    }
    public void setName(String n){
        name=n;
    }
    public void setEmail(String e){
        email=e;
    }
    public void setAddress(String a){
        address=a;
    }
}
