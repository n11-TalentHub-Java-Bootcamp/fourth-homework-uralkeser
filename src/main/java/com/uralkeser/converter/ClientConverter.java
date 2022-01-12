package com.uralkeser.converter;


import com.uralkeser.dto.ClientDto;
import com.uralkeser.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientConverter {

    ClientConverter INSTANCE = Mappers.getMapper(ClientConverter.class);

    List<ClientDto> convertAllClientListToClientDtoList (List<Client> clientList);

    Client convertAllClientDtoListToClientList(ClientDto clientDto);

}
