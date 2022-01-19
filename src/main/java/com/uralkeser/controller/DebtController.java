package com.uralkeser.controller;

import com.uralkeser.converter.DebtConverter;
import com.uralkeser.dto.DebtDto;
import com.uralkeser.entity.Debt;
import com.uralkeser.service.DebtService;
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
@RequestMapping("api/debts")
public class DebtController {

    @Autowired
    DebtService debtService;

    @PostMapping()//3.a
    @Transactional
    public ResponseEntity<Object> save(@RequestBody DebtDto debtDto){

        Debt debt = DebtConverter.INTANCE.convertDebtDtoToDebt(debtDto);

        debt.setRemaining(debt.getAmount());
        debt.setType("NORMAL");
        debt.setPrincipalDebt(null);


        debt = debtService.saveDebt(debt);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(debt.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @GetMapping({"/between/{startDate}/and/{endDate}"})//3.d
    @Transactional
    public List<DebtDto> getAllDebtsByTimePeriod(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){

        List<Debt> debtList;

        debtList = debtService.getAllDebtsByTimePeriod(startDate, endDate);

        List<DebtDto> debtDtoList = DebtConverter.INTANCE.convertDebtListToDebtDtoList(debtList);

        return debtDtoList;
    }

    @GetMapping("{clientId}")//3.e
    @Transactional
    public List<DebtDto> getAlDebtsOfAClient(@PathVariable Long clientId){
        List<Debt> debtList;

        debtList = debtService.getAllDebtsByClient(clientId);

        List<DebtDto> debtDtoList = DebtConverter.INTANCE.convertDebtListToDebtDtoList(debtList);

        return debtDtoList;

    }

    @GetMapping("/overdue/{clientId}")//3.f
    @Transactional
    public List<DebtDto> getOverdueDebtsOfAClient(@PathVariable Long clientId) {
        List<Debt> debtList;
        Date today = new Date();

        debtList = debtService.getOverdueDebtsOfAClient(clientId,today);

        List<DebtDto> debtDtoList = DebtConverter.INTANCE.convertDebtListToDebtDtoList(debtList);

        return debtDtoList;
    }

    @GetMapping("/totalDebtAmount/{clientId}")//3.g
    @Transactional
    public BigDecimal getTotalDebtAmountOfAClient(@PathVariable Long clientId){
        List<Debt> debtList;
        BigDecimal total = new BigDecimal(0);
        Date today = new Date();
        Date expiryDate = new Date("2018/01/01");


        debtList = debtService.getAllDebtsByClient(clientId);

        for (Debt debt : debtList) {

            //check maturity date if it is overdue then calculate expiry debt
            if(debt.getMaturityDate().before(expiryDate)){
                BigDecimal lateDebtPercent = new BigDecimal(0.015);
                BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
                BigDecimal lateDebtAmount;
                lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
                total = total.add(lateDebtAmount);

            } else if(debt.getMaturityDate().before(today)){
                BigDecimal lateDebtPercent = new BigDecimal(0.02);
                BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
                BigDecimal lateDebtAmount;
                lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
                total = total.add(lateDebtAmount);
            }

            total = total.add(debt.getRemaining());

        }

        return total;
    }

    @GetMapping("/totalOverdueDebtAmount/{clientId}")//3.h
    @Transactional
    public BigDecimal getTotalOverdueDebtAmountOfAClient(@PathVariable Long clientId){
        List<Debt> debtList;
        BigDecimal total = new BigDecimal(0);
        Date today = new Date();
        Date expiryDate = new Date("2018/01/01");


        debtList = debtService.getOverdueDebtsOfAClient(clientId,today);

        for (Debt debt : debtList) {

            //check maturity date if it is overdue then calculate expiry debt
            if(debt.getMaturityDate().before(expiryDate)){
                BigDecimal lateDebtPercent = new BigDecimal(0.015);
                BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
                BigDecimal lateDebtAmount;
                lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
                total = total.add(lateDebtAmount);

            } else if(debt.getMaturityDate().before(today)){
                BigDecimal lateDebtPercent = new BigDecimal(0.02);
                BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
                BigDecimal lateDebtAmount;
                lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
                total = total.add(lateDebtAmount);
            }
            total = total.add(debt.getRemaining());

        }
        return total;
    }

    @GetMapping("/onlyOverdueDebtAmount/{clientId}")//3.i
    @Transactional
    public BigDecimal getOnlyOverdueDebtAmountOfAClient(@PathVariable Long clientId){
        List<Debt> debtList;
        BigDecimal total = new BigDecimal(0);
        Date today = new Date();
        Date expiryDate = new Date("2018/01/01");


        debtList = debtService.getOverdueDebtsOfAClient(clientId,today);

        for (Debt debt : debtList) {

            //check maturity date if it is overdue then calculate expiry debt
            if(debt.getMaturityDate().before(expiryDate)){
                BigDecimal lateDebtPercent = new BigDecimal(0.015);
                BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
                BigDecimal lateDebtAmount;
                lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
                total = total.add(lateDebtAmount);

            } else if(debt.getMaturityDate().before(today)){
                BigDecimal lateDebtPercent = new BigDecimal(0.02);
                BigDecimal spentTime = new BigDecimal(TimeUnit.DAYS.convert(Math.abs(today.getTime() - debt.getMaturityDate().getTime()), TimeUnit.MILLISECONDS));
                BigDecimal lateDebtAmount;
                lateDebtAmount = debt.getRemaining().multiply(lateDebtPercent.multiply(spentTime));
                total = total.add(lateDebtAmount);
            }

        }
        return total;
    }

}
