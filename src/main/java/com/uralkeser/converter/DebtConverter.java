package com.uralkeser.converter;

import com.uralkeser.dto.DebtDto;
import com.uralkeser.entity.Debt;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtConverter {

    DebtConverter INTANCE = Mappers.getMapper(DebtConverter.class);

    @Mapping(target="clientId", source = "client.id")
    List<DebtDto> convertDebtListToDebtDtoList(List<Debt> debtList);

    @Mapping(source="clientId", target = "client.id")
    Debt convertDebtDtoToDebt(DebtDto debtDto);


}
