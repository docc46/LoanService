package com.kuros.LoanService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String clientName;
    private String clientSurname;
    private BigDecimal amount;
    private Period period;
    private LocalDate acceptanceDay = LocalDate.now();

    public Loan(Period period) {
        this.period = period;
    }

    public Loan() {
    }

}
