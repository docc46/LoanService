package com.kuros.LoanService;

import com.kuros.LoanService.model.Loan;
import com.kuros.LoanService.model.LoanRequest;
import com.kuros.LoanService.model.ProlongRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class LoanEvaluatorTest {

    LoanEvaluator loanEvaluator = new LoanEvaluator();

    @Test
    void shouldCheckIfMaxAmountAndAtNight() {

        assertFalse(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(10000.00), LocalDateTime.of(2020, 12, 31, 23, 59), "1.1.1.1")));
        assertFalse(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(200.00), LocalDateTime.of(2020, 12, 31, 23, 59), "1.1.1.1")));
        assertFalse(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(10000.00), LocalDateTime.of(2022, 1, 1, 7, 0), "1.1.1.1")));
        assertFalse(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(200.00), LocalDateTime.of(2022, 1, 1, 6, 0), "1.1.1.1")));
        assertFalse(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(9999.99), LocalDateTime.of(2022, 1, 1, 6, 0), "1.1.1.1")));
        assertFalse(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(9999.99), LocalDateTime.of(2022, 1, 1, 23, 59), "1.1.1.1")));

        assertTrue(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(10000.00), LocalDateTime.of(2020, 12, 31, 0, 0), "1.1.1.1")));
        assertTrue(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(10000.00), LocalDateTime.of(2020, 12, 31, 0, 0), "1.1.1.1")));
        assertTrue(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(10000.00), LocalDateTime.of(2022, 1, 1, 5, 59), "1.1.1.1")));
        assertTrue(loanEvaluator.isMaxAmountAndAtNight(new LoanRequest(BigDecimal.valueOf(10000.00), LocalDateTime.of(2022, 1, 1, 5, 59), "1.1.1.1")));
    }

    @Test
    void shouldGrantLoan() {

        LoanRequest request1 = new LoanRequest("John", "Snow", BigDecimal.valueOf(10000.00), 12);
        LoanRequest request2 = new LoanRequest("Cersei", "Lannister", BigDecimal.valueOf(9999.99), 1);
        LoanRequest request3 = new LoanRequest("Tywin", "Lannister", BigDecimal.valueOf(0.00), 24);

        Loan loan1 = loanEvaluator.grantLoan(request1);
        Loan loan2 = loanEvaluator.grantLoan(request2);
        Loan loan3 = loanEvaluator.grantLoan(request3);

        assertEquals(loan1.getClientName(), request1.getClientName());
        assertEquals(loan1.getClientSurname(), request1.getClientSurname());
        assertEquals(loan1.getAmount(), request1.getAmount());
        assertEquals(loan1.getPeriod().getMonths(), request1.getPeriodOfMonths());

        assertEquals(loan2.getClientName(), request2.getClientName());
        assertEquals(loan2.getClientSurname(), request2.getClientSurname());
        assertEquals(loan2.getAmount(), request2.getAmount());
        assertEquals(loan2.getPeriod().getMonths(), request2.getPeriodOfMonths());

        assertEquals(loan3.getClientName(), request3.getClientName());
        assertEquals(loan3.getClientSurname(), request3.getClientSurname());
        assertEquals(loan3.getAmount(), request3.getAmount());
        assertEquals(loan3.getPeriod().getMonths(), request3.getPeriodOfMonths());
    }

    @Test
    void shouldProlongLoanPeriod() {

        ProlongRequest request1 = new ProlongRequest(10);
        ProlongRequest request2 = new ProlongRequest(0);
        ProlongRequest request3 = new ProlongRequest(400);

        Loan loan1 = new Loan(Period.ofMonths(1));
        Loan loan2 = new Loan(Period.ofMonths(1));
        Loan loan3 = new Loan(Period.ofMonths(11));

        assertEquals(Period.ofMonths(1).plusDays(10), loanEvaluator.prolong(loan1, request1).getPeriod());
        assertEquals(Period.ofMonths(1).plusDays(0), loanEvaluator.prolong(loan2, request2).getPeriod());
        assertEquals(Period.ofMonths(11).plusDays(400), loanEvaluator.prolong(loan3, request3).getPeriod());
    }

}