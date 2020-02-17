package com.kuros.LoanService;

import com.kuros.LoanService.model.Loan;
import com.kuros.LoanService.model.LoanRequest;
import com.kuros.LoanService.model.ProlongRequest;
import com.kuros.LoanService.repository.LoanRepository;
import com.kuros.LoanService.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Period;

@Component
public class LoanEvaluator {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    private final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(10000.00);

    public Boolean isRisky(LoanRequest request) {
        return (isMaxAmountAndAtNight(request) || isTooManyRequests(request));
    }

    public Boolean isMaxAmountAndAtNight(LoanRequest request) {
        return (request.getDateTime().getHour() < 6 && request.getAmount().compareTo(MAX_AMOUNT) == 0);
    }

    public Boolean isTooManyRequests(LoanRequest request) {
        return (loanRequestRepository.countByIpAddress(request.getIpAddress()) >= 3);
    }

    public Loan grantLoan(LoanRequest request) {
        Loan loan = new Loan();
        loan.setClientName(request.getClientName());
        loan.setClientSurname(request.getClientSurname());
        loan.setAmount(request.getAmount());
        loan.setPeriod(Period.ofMonths(request.getPeriodOfMonths()));
        return loan;
    }

    public Loan prolong(Loan loan, ProlongRequest request) {
        loan.setPeriod(loan.getPeriod().plusDays(request.getProlongDays()).normalized());
        return loan;
    }

    public void evaluate(LoanRequest request) {
        if (!isRisky(request)) loanRepository.save(grantLoan(request));
    }

}
