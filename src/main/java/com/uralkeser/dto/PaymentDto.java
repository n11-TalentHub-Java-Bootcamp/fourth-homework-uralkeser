package com.uralkeser.dto;

import java.util.Date;

public class PaymentDto {

    private Date date;
    private Long debtId;

    public PaymentDto(Date date, Long debtId) {
        this.date = date;
        this.debtId = debtId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }
}
