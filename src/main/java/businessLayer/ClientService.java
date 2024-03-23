package businessLayer;

import dataAccessLayer.ClientDAO;
import model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private static ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void addClient(String name, String email, String address) throws SQLException {
        Client client = new Client(name, email, address);
        clientDAO.create(client);
    }

    public void updateClient(int id, String name, String email, String address) throws SQLException {
        Client client = new Client(id, name, email, address);
        clientDAO.update(client);
    }

    public void deleteClient(int id) throws SQLException {
        clientDAO.delete(id);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientDAO.findAll();
    }
}

