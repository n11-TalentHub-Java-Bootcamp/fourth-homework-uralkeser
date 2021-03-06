package com.uralkeser.service;

import com.uralkeser.dao.ClientDao;
import com.uralkeser.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientDao clientDao;

    @Transactional
    public List<Client> getAllClients(){
        return clientDao.findAll();
    }

    //can be used for update and insert operations both
    @Transactional
    public Client saveClient(Client client){
        return clientDao.save(client);
    }

    @Transactional
    public void deleteClientById(Long clientId){
        clientDao.deleteById(clientId);
    }

    @Transactional
    public List<Client> getClientByUserName(String userName){ return clientDao.findClientByUserName(userName);}

}
