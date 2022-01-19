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

    @Query("SELECT debt FROM Debt debt WHERE debt.client.id = :clientId AND debt.remaining > 0")
    List<Debt> findAllByClientId(Long clientId);

    @Query("SELECT debt FROM Debt debt WHERE debt.startDate BETWEEN :startTime AND :endTime AND debt.remaining > 0")
    List<Debt> findAllDebtsByTimePeriod(Date startTime, Date endTime);

    @Query("SELECT debt FROM Debt debt WHERE debt.client.id = :clientId AND debt.maturityDate < :today AND debt.remaining > 0")
    List<Debt> findOverdueDebtsOfAClient(Long clientId, Date today);




}
