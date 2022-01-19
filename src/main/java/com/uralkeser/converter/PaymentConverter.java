package com.uralkeser.converter;

import com.uralkeser.dto.DebtDto;
import com.uralkeser.dto.PaymentDto;
import com.uralkeser.entity.Debt;
import com.uralkeser.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentConverter {
    PaymentConverter INTANCE = Mappers.getMapper(PaymentConverter.class);

    @Mapping(target="debtId", source = "debt.id")
    List<PaymentDto> convertPaymentListToPaymentDtoList(List<Payment> debtList);

    @Mapping( target="debt.id", source="debtId")
    Payment convertPaymentDtoToPayment(PaymentDto paymentDto);
}
