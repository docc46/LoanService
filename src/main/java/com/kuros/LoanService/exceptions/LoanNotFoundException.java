package com.kuros.LoanService.exceptions;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(int loanId) {
        super("Could not find loan " + loanId);
    }
}
