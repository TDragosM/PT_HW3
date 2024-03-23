package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import businessLayer.*;
import dataAccessLayer.*;
import model.Client;
import model.Product;

public class MainGUI {
    private JFrame mainFrame;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JFrame clientFrame;
    private JButton addClient;
    private JButton updateClient;
    private JButton deleteClient;
    private JButton allClients;

    private JFrame prductFrame;

    private DatabaseConnection dbConnection=new DatabaseConnection();

    public MainGUI() {
        mainFrame = new JFrame("Orders Management");
        clientButton = new JButton("Client Operations");
        productButton = new JButton("Product Operations");
        orderButton = new JButton("Create Product Order");

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClientWindow();
            }
        });

        productButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProductWindow();
            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOrderWindow();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.add(clientButton);
        mainPanel.add(productButton);
        mainPanel.add(orderButton);

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void showClientWindow() {
        // create and show the client window
        mainFrame.setVisible(false);
        clientFrame=new JFrame("ClientWindow");
        addClient=new JButton("Add Client");
        updateClient=new JButton("Update Client");
        deleteClient=new JButton("Delete Client");
        allClients=new JButton("All Clients");

        addClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddClient();
            }
        });
        updateClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateClient();
            }
        });
        deleteClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteClient();
            }
        });
        allClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showAllClients();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JPanel clientPanel=new JPanel();
        clientPanel.add(addClient);
        clientPanel.add(updateClient);
        clientPanel.add(deleteClient);
        clientPanel.add(allClients);

        clientFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Set the mainFrame to visible when the clientFrame is closed
                mainFrame.setVisible(true);
            }
        });

        clientFrame.add(clientPanel);
        clientFrame.pack();
        clientFrame.setVisible(true);

    }

    private void showAddClient(){
        clientFrame.setVisible(false);
        JFrame addClientFrame = new JFrame("Add Client");
        addClientFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientFrame.setVisible(true);
            }
        });
        JTextField clientName=new JTextField("Name");
        JTextField clientEmail=new JTextField("Email");
        JTextField clientAddress=new JTextField("Address");
        JButton adauga=new JButton("Adauga");
        JPanel addClientPanel=new JPanel();
        addClientPanel.add(clientName);
        addClientPanel.add(clientEmail);
        addClientPanel.add(clientAddress);
        addClientPanel.add(adauga);
        adauga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=clientName.getText();
                String email=clientEmail.getText();
                String address=clientAddress.getText();

                Connection connection=dbConnection.getConnection();
                ClientService clientService=new ClientService(new ClientDAO(connection));
                try {
                    clientService.addClient(name,email,address);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        addClientFrame.add(addClientPanel);
        addClientFrame.pack();
        addClientFrame.setVisible(true);
    }
    private void showUpdateClient(){
        clientFrame.setVisible(false);
        JFrame updateClientFrame = new JFrame("Update Client");
        updateClientFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientFrame.setVisible(true);
            }
        });
        JTextField clientId=new JTextField("1");
        JTextField clientName=new JTextField("Name");
        JTextField clientEmail=new JTextField("Email");
        JTextField clientAddress=new JTextField("Address");
        JButton updateaza=new JButton("Update");
        JPanel updateClientPanel=new JPanel();
        updateClientPanel.add(clientId);
        updateClientPanel.add(clientName);
        updateClientPanel.add(clientEmail);
        updateClientPanel.add(clientAddress);
        updateClientPanel.add(updateaza);
        updateaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(clientId.getText());
                String name=clientName.getText();
                String email=clientEmail.getText();
                String address=clientAddress.getText();

                Connection connection=dbConnection.getConnection();
                ClientService clientService=new ClientService(new ClientDAO(connection));
                try {
                    clientService.updateClient(id,name,email,address);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateClientFrame.add(updateClientPanel);
        updateClientFrame.pack();
        updateClientFrame.setVisible(true);
    }
    private void showDeleteClient(){
        clientFrame.setVisible(false);
        JFrame deleteClientFrame = new JFrame("Delete Client");
        deleteClientFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientFrame.setVisible(true);
            }
        });
        JTextField clientId=new JTextField("1");
        JTextField clientName=new JTextField("Name");
        JTextField clientEmail=new JTextField("Email");
        JTextField clientAddress=new JTextField("Address");
        JButton sterge=new JButton("Delete");
        JPanel deleteClientPanel=new JPanel();
        deleteClientPanel.add(clientId);

        deleteClientPanel.add(sterge);
        sterge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(clientId.getText());


                Connection connection=dbConnection.getConnection();
                ClientService clientService=new ClientService(new ClientDAO(connection));
                try {
                    clientService.deleteClient(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        deleteClientFrame.add(deleteClientPanel);
        deleteClientFrame.pack();
        deleteClientFrame.setVisible(true);
    }
    private void showAllClients() throws SQLException {
        Connection connection=dbConnection.getConnection();
        ClientService clientService=new ClientService(new ClientDAO(connection));
        java.util.List<Client> clientList =  clientService.getAllClients();
        clientFrame.setVisible(false);

        // Create a new JFrame to display the JTable
        JFrame frame = new JFrame("All Clients");
        JPanel panel = new JPanel(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clientFrame.setVisible(true);
            }
        });
        // Create a JTable with the client data
        /*String[] columnNames = {"ID", "Name", "Address", "Email"};
        Object[][] rowData = new Object[clientList.size()][4];
        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            rowData[i][0] = client.getIdClient();
            rowData[i][1] = client.getName();
            rowData[i][2] = client.getAddress();
            rowData[i][3] = client.getEmail();
        }*/
        JTable table = retrieveProperties(clientList);

        // Add the JTable to the panel
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Display the JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private void showProductWindow() {
        mainFrame.setVisible(false);
        prductFrame=new JFrame("Product Window");
        JButton addProduct=new JButton("Add Product");
        JButton updateProduct=new JButton("Update Product");
        JButton deleteProduct=new JButton("Delete Product");
        JButton allProducts=new JButton("All Products");

        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddProduct();
            }
        });
        updateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateProduct();
            }
        });
        deleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeleteProduct();
            }
        });
        allProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showAllProducts();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JPanel productPanel=new JPanel();
        productPanel.add(addProduct);
        productPanel.add(updateProduct);
        productPanel.add(deleteProduct);
        productPanel.add(allProducts);

        prductFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Set the mainFrame to visible when the clientFrame is closed
                mainFrame.setVisible(true);
            }
        });

        prductFrame.add(productPanel);
        prductFrame.pack();
        prductFrame.setVisible(true);
    }

    private void showAddProduct(){
        prductFrame.setVisible(false);
        JFrame addProductFrame = new JFrame("Add Product");
        addProductFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                prductFrame.setVisible(true);
            }
        });
        JTextField productName=new JTextField("Name");

        JTextField productStock=new JTextField("Stock");
        JButton adauga=new JButton("Adauga");
        JPanel addProductPanel=new JPanel();
        addProductPanel.add(productName);

        addProductPanel.add(productStock);
        addProductPanel.add(adauga);
        adauga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=productName.getText();
                int stock= Integer.parseInt(productStock.getText());

                Connection connection=dbConnection.getConnection();
                ProductService productService=new ProductService(new ProductDAO(connection));
                try {
                    productService.addProduct(name,stock);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        addProductFrame.add(addProductPanel);
        addProductFrame.pack();
        addProductFrame.setVisible(true);
    }
    private void showUpdateProduct(){
        prductFrame.setVisible(false);
        JFrame updateProductFrame = new JFrame("Update Product");
        updateProductFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                prductFrame.setVisible(true);
            }
        });
        JTextField productId=new JTextField("ID");
        JTextField productName=new JTextField("Name");
        JTextField productStock=new JTextField("Stock");
        JButton updateaza=new JButton("Update");
        JPanel updateProductPanel=new JPanel();
        updateProductPanel.add(productId);
        updateProductPanel.add(productName);

        updateProductPanel.add(productStock);
        updateProductPanel.add(updateaza);
        updateaza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(productId.getText());
                String name=productName.getText();
                int stock= Integer.parseInt(productStock.getText());

                Connection connection=dbConnection.getConnection();
                ProductService productService=new ProductService(new ProductDAO(connection));
                try {
                    productService.updateProduct(id,name,stock);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateProductFrame.add(updateProductPanel);
        updateProductFrame.pack();
        updateProductFrame.setVisible(true);
    }
    private void  showDeleteProduct(){
        prductFrame.setVisible(false);
        JFrame deleteProductFrame = new JFrame("Delete Product");
        deleteProductFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                prductFrame.setVisible(true);
            }
        });
        JTextField productId=new JTextField("ID");

        JButton sterge=new JButton("Delete");
        JPanel deleteProductPanel=new JPanel();
        deleteProductPanel.add(productId);

        deleteProductPanel.add(sterge);
        sterge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(productId.getText());

                Connection connection=dbConnection.getConnection();
                ProductService productService=new ProductService(new ProductDAO(connection));
                try {
                    productService.deleteProduct(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        deleteProductFrame.add(deleteProductPanel);
        deleteProductFrame.pack();
        deleteProductFrame.setVisible(true);
    }
    private void showAllProducts() throws SQLException {
        Connection connection=dbConnection.getConnection();
        ProductService productService=new ProductService(new ProductDAO(connection));
        List<Product> productList =  productService.getAllProducts();
        prductFrame.setVisible(false);

        // Create a new JFrame to display the JTable
        JFrame frame = new JFrame("All Products");
        JPanel panel = new JPanel(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                prductFrame.setVisible(true);
            }
        });
        /*
        // Create a JTable with the client data
        String[] columnNames = {"ID", "Name", "Quantity"};
        Object[][] rowData = new Object[productList.size()][3];
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            rowData[i][0] = product.getIdProduct();
            rowData[i][1] = product.getName();
            rowData[i][2] = product.getStock();

        }
        JTable table = new JTable(rowData, columnNames);
*/
        JTable table = retrieveProperties(productList);
        // Add the JTable to the panel
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Display the JFrame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void showOrderWindow() {
        mainFrame.setVisible(false);
        JFrame orderFrame=new JFrame("Order Window");
        orderFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Set the mainFrame to visible when the clientFrame is closed
                mainFrame.setVisible(true);
            }
        });
        JTextField clientID=new JTextField("Client ID");
        JTextField productId=new JTextField("Product ID");
        JTextField cantitate=new JTextField("Quantity");
        JButton order=new JButton("Confirm Order");
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idClient= Integer.parseInt(clientID.getText());
                int idProduct=Integer.parseInt(productId.getText());
                int quantity=Integer.parseInt(cantitate.getText());
                Connection connection=dbConnection.getConnection();
                OrderService orderService=new OrderService(new OrderDAO(connection),new ProductDAO(connection));
                try {
                    orderService.createOrder(idClient,idProduct,quantity);
                } catch (InsufficientStockException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JPanel orderPanel=new JPanel();
        orderPanel.add(clientID);

        orderPanel.add(productId);
        orderPanel.add(cantitate);
        orderPanel.add(order);
        orderFrame.add(orderPanel);
        orderFrame.pack();
        orderFrame.setVisible(true);
    }
    public JTable retrieveProperties(List<?> list) {
        // Obțineți numărul de coloane pentru tabel din clasa primului obiect din listă
        int numColumns = list.get(0).getClass().getDeclaredFields().length;
        // Crearea matricei de date pentru JTable
        String[][] data = new String[list.size()][numColumns];

        // Obțineți header-ul pentru tabel
        List<String> header = new ArrayList<>();
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        for (Field field : fields) {
            header.add(field.getName());
        }

        // Iterați prin lista și obțineți valorile câmpurilor pentru fiecare obiect
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            int j = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(obj);
                    // Setarea valorii în matricea de date
                    data[i][j] = String.valueOf(value);
                    j++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Crearea JTable cu matricea de date și header-ul
        JTable jTable = new JTable(data, header.toArray());

        return jTable;
    }



    public static void main(String[] args) {
        /*Client c = new Client();
        List<Client> LC = new ArrayList<>();
        LC.add(c);
        retrieveProperties(LC);*/
        new MainGUI();
    }
}
