package com.kuros.LoanService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String clientName;
    private String clientSurname;
    private BigDecimal amount;
    private int periodOfMonths;
    private LocalDateTime dateTime = LocalDateTime.now();
    private String ipAddress;

    public LoanRequest(String clientName, String clientSurname, BigDecimal amount, int periodOfMonths) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.amount = amount;
        this.periodOfMonths = periodOfMonths;
    }

    public LoanRequest(BigDecimal amount, LocalDateTime dateTime, String ipAddress) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.ipAddress = ipAddress;
    }

    public LoanRequest() {
    }
}
