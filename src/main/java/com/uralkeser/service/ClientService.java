package com.uralkeser.service;

import com.uralkeser.dao.ClientDao;
import com.uralkeser.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientDao clientDao;

    public List<Client> getAllClients(){
        return clientDao.findAll();
    }

    //can be used for both update and insert operations
    public Client saveClient(Client client){
        return clientDao.save(client);
    }

    public void deleteClientById(Long clientId){
        clientDao.deleteById(clientId);
    }

    public List<Client> getClientByUserName(String userName){ return clientDao.findClientByUserName(userName);}

}
