package com.uralkeser.service;

import com.uralkeser.dao.PaymentDao;
import com.uralkeser.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentDao paymentDao;

    public Payment savePayment(Payment payment){ return paymentDao.save(payment);}

    public List<Payment> getAllPaymentsByClient(Long clientId){ return paymentDao.findAllByClientId(clientId);}

    public List<Payment> getAllPaymentsByTimePeriod(Date startDate, Date endDate){ return paymentDao.findAllPaymentsByTimePeriod(startDate, endDate);}

}
