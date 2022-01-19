package com.uralkeser.dao;

import com.uralkeser.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {

    @Query("SELECT payment FROM Payment payment WHERE payment.date BETWEEN :startTime AND :endTime ")
    List<Payment> findAllPaymentsByTimePeriod(Date startTime, Date endTime);

    @Query("SELECT payment FROM Payment payment INNER JOIN Debt debt ON debt.id = payment.debt.id  WHERE debt.client.id = :clientId ")
    List<Payment> findAllByClientId(Long clientId);
}
