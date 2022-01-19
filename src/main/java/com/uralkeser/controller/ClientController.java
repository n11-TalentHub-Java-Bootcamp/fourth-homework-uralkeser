package com.uralkeser.controller;


import com.uralkeser.converter.ClientConverter;
import com.uralkeser.dto.ClientDto;
import com.uralkeser.entity.Client;
import com.uralkeser.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping()
    public List<ClientDto> getAll(){
       List<Client> clientList;

       clientList = clientService.getAllClients();

       List<ClientDto> clientDtoList = ClientConverter.INSTANCE.convertClientListToClientDtoList(clientList);

       return clientDtoList;
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody ClientDto clientDto){

        Client client = ClientConverter.INSTANCE.convertClientDtoToClient(clientDto);

        client = clientService.saveClient(client);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(client.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        clientService.deleteClientById(id);
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody ClientDto clientDto){
        Client client = ClientConverter.INSTANCE.convertClientDtoToClient(clientDto);
        List<Client> clientList = clientService.getClientByUserName(client.getUserName());
        if(clientList.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else{
            client.setId(clientList.get(0).getId());
//            client = clientService.saveClient(client);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(client.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();
        }
    }


}
