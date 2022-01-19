package com.uralkeser.controller;


import com.uralkeser.converter.DebtConverter;
import com.uralkeser.converter.PaymentConverter;
import com.uralkeser.dto.DebtDto;
import com.uralkeser.dto.PaymentDto;
import com.uralkeser.entity.Debt;
import com.uralkeser.entity.Payment;
import com.uralkeser.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> save(@RequestBody PaymentDto paymentDto){
        Payment payment = PaymentConverter.INTANCE.convertPaymentDtoToPayment(paymentDto);

        payment = paymentService.savePayment(payment);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(payment.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping({"/between/{startDate}/and/{endDate}"})
    @Transactional
    public List<PaymentDto> getAllPaymentsByTimePeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Payment> paymentList;

        paymentList = paymentService.getAllPaymentsByTimePeriod(startDate, endDate);

        List<PaymentDto> paymentDtoList = PaymentConverter.INTANCE.convertPaymentListToPaymentDtoList(paymentList);

        return paymentDtoList;
    }

    @GetMapping("{clientId}")
    @Transactional
    public List<PaymentDto> getAlDebtsOfAClient(@PathVariable Long clientId){
        List<Payment> paymentList;

        paymentList = paymentService.getAllPaymentsByClient(clientId);

        List<PaymentDto> paymentDtoList = PaymentConverter.INTANCE.convertPaymentListToPaymentDtoList(paymentList);

        return paymentDtoList;

    }
}
