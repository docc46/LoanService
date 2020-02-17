package com.kuros.LoanService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ProlongRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int loanId;
    private int prolongDays;

    public ProlongRequest(int prolongDays) {
        this.prolongDays = prolongDays;
    }

    public ProlongRequest() {
    }
}
