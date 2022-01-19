package com.uralkeser.controller;


import com.uralkeser.converter.DebtConverter;
import com.uralkeser.converter.PaymentConverter;
import com.uralkeser.dto.DebtDto;
import com.uralkeser.dto.PaymentDto;
import com.uralkeser.entity.Debt;
import com.uralkeser.entity.Payment;
import com.uralkeser.service.DebtService;
import com.uralkeser.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    DebtService debtService;

    @PostMapping()//4.a
    public ResponseEntity<Object> save(@RequestBody PaymentDto paymentDto){
        Payment payment = PaymentConverter.INTANCE.convertPaymentDtoToPayment(paymentDto);
        List<Debt> debtList;
        debtList = debtService.getAllById(paymentDto.getDebtId());

        Debt debt = debtList.get(0);

        Date today = new Date();
        Date expiryDate = new Date("2018/01/01");

         if(debt.getMaturityDate().before(today)){
             BigDecimal lateDebtPercent = new BigDecimal(0.02);
             if(debt.getMaturityDate().before(expiryDate)) {
                 lateDebtPercent = new BigDecimal(0.015);
             }
            Debt lateDebt = new Debt();
            BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
            BigDecimal lateDebtAmount;
            lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
            lateDebt.setAmount(lateDebtAmount);
            lateDebt.setRemaining(new BigDecimal(0));
            lateDebt.setStartDate(paymentDto.getDate());
            lateDebt.setMaturityDate(null);
            lateDebt.setType("GECIKME_ZAMMI");
            lateDebt.setPrincipalDebt(debt);
            lateDebt.setClient(debt.getClient());
            lateDebt = debtService.saveDebt(lateDebt);
        }

        debt.setRemaining(new BigDecimal(0));
        debt = debtService.saveDebt(debt);

        payment = paymentService.savePayment(payment);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(payment.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping({"/between/{startDate}/and/{endDate}"})//4.b
    public List<PaymentDto> getAllPaymentsByTimePeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Payment> paymentList;

        paymentList = paymentService.getAllPaymentsByTimePeriod(startDate, endDate);

        List<PaymentDto> paymentDtoList = PaymentConverter.INTANCE.convertPaymentListToPaymentDtoList(paymentList);

        return paymentDtoList;
    }

    @GetMapping("{clientId}")//4.c
    public List<PaymentDto> getAllPaymentsOfAClient(@PathVariable Long clientId){
        List<Payment> paymentList;

        paymentList = paymentService.getAllPaymentsByClient(clientId);

        List<PaymentDto> paymentDtoList = PaymentConverter.INTANCE.convertPaymentListToPaymentDtoList(paymentList);

        return paymentDtoList;

    }
}
