package com.uralkeser.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table
public class Debt {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "remaning", precision = 19, scale = 2)
    private BigDecimal remaining;

    @Column(name = "maturity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date maturityDate;

    @Column(name = "type")
    private String type;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "principal_debt_id")
    private Debt principalDebt; //if the type of debt is "GECIKME_ZAMMI" keep the id of principal debt

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "client_id",foreignKey = @ForeignKey(name = "fk_client_debt_id") )
    private Client client;

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", amount=" + amount +
                ", remaning=" + remaining +
                ", maturityDate=" + maturityDate +
                ", type='" + type + '\'' +
                ", principalDebtId=" + principalDebt +
                ", client_id=" + client +
                '}';
    }
}
