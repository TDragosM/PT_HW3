package dataAccessLayer;


import java.sql.*;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schoolddb";
    private static final String USER="root";
    private static final String PASS="Parola";
    private static DatabaseConnection singleInstance = new DatabaseConnection();

    public DatabaseConnection(){
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // method to create a new connection to the database
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.severe("Error creating database connection: " + e.getMessage());
        }
        return connection;
    }

    // method to get a single instance of the database connection
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    // method to close the database connection
    public static void close(Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.severe("Error closing database connection: " + e.getMessage());
        }
    }

    // method to close a statement
    public static void close(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.severe("Error closing database statement: " + e.getMessage());
        }
    }

    // method to close a result set
    public static void close(ResultSet resultSet){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            LOGGER.severe("Error closing database result set: " + e.getMessage());
        }
    }

}

