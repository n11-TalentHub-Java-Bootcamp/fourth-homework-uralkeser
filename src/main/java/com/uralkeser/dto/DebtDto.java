package com.uralkeser.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DebtDto {
    private BigDecimal amount;
    private BigDecimal remaining;
    private Date maturityDate;
    private String type;
    private Long principalDebtId;
    private Long clientId;

    public DebtDto(BigDecimal amount, BigDecimal remaining, Date maturityDate, String type, Long principalDebtId, Long clientId) {
        this.amount = amount;
        this.remaining = remaining;
        this.maturityDate = maturityDate;
        this.type = type;
        this.principalDebtId = principalDebtId;
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrincipalDebtId() {
        return principalDebtId;
    }

    public void setPrincipalDebtId(Long principalDebtId) {
        this.principalDebtId = principalDebtId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
