package com.uralkeser.controller;


import com.uralkeser.converter.ClientConverter;
import com.uralkeser.dto.ClientDto;
import com.uralkeser.entity.Client;
import com.uralkeser.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping()
    public List<ClientDto> findAll(){
       List<Client> clientList;

       clientList = clientService.getAllClients();

       List<ClientDto> clientDtoList = ClientConverter.INSTANCE.convertAllClientListToClientDtoList(clientList);

       return clientDtoList;
    }


}
