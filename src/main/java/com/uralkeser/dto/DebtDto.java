package com.uralkeser.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DebtDto {
    private BigDecimal amount;
    private Date startDate;
    private Date maturityDate;
    private Long clientId;

    public DebtDto(BigDecimal amount, Date startDate, Date maturityDate, Long clientId) {
        this.amount = amount;
        this.startDate = startDate;
        this.maturityDate = maturityDate;
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
