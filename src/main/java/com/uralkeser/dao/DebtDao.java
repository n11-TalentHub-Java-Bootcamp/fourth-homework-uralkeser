package com.uralkeser.dao;

import com.uralkeser.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtDao extends JpaRepository<Debt,Long> {

    List<Debt> findAll();

    @Query("select debt from Debt debt where debt.client.id = :clientId and debt.remaining > 0")
    List<Debt> findAllByClientId(Long clientId); //E




}
