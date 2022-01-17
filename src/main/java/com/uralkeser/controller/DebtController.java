package com.uralkeser.controller;

import com.uralkeser.converter.DebtConverter;
import com.uralkeser.dto.DebtDto;
import com.uralkeser.entity.Debt;
import com.uralkeser.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/debts")
public class DebtController {

    @Autowired
    DebtService debtService;

    @GetMapping("{id}")
    public List<DebtDto> findAlDebtsByClient(@PathVariable Long clientId){
        List<Debt> debtList;

        debtList = debtService.getAllDebtsByClient(clientId);

        List<DebtDto> debtDtoList = DebtConverter.INTANCE.convertDebtListToDebtDtoList(debtList);

        return debtDtoList;
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody DebtDto debtDto){

        Debt debt = DebtConverter.INTANCE.convertDebtDtoToDebt(debtDto);

        if(!debt.getType().equals("NORMAL"))
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        else{
            debt = debtService.saveDebt(debt);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(debt.getId())
                    .toUri();

            return ResponseEntity.created(uri).build();
        }
    }
}
