package com.kuros.LoanService;

import com.kuros.LoanService.exceptions.LoanNotFoundException;
import com.kuros.LoanService.model.Loan;
import com.kuros.LoanService.model.LoanRequest;
import com.kuros.LoanService.model.ProlongRequest;
import com.kuros.LoanService.repository.LoanRepository;
import com.kuros.LoanService.repository.LoanRequestRepository;
import com.kuros.LoanService.repository.ProlongRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private ProlongRequestRepository prolongRequestRepository;

    @Autowired
    private LoanEvaluator loanEvaluator;

    @PostMapping("/askforloan")
    LoanRequest newLoanRequest(@RequestBody LoanRequest loanRequest, HttpServletRequest httpRequest) {
        loanRequest.setIpAddress(httpRequest.getRemoteAddr());
        loanEvaluator.evaluate(loanRequest);
        return loanRequestRepository.save(loanRequest);
    }

    @PostMapping("/prolong")
    ProlongRequest newProlongRequest(@RequestBody ProlongRequest request) {
        Loan loan = loanRepository.findById(request.getLoanId()).orElseThrow(() -> new LoanNotFoundException(request.getLoanId()));
        loanEvaluator.prolong(loan, request);
        return prolongRequestRepository.save(request);
    }

    @GetMapping("/loanrequests")
    List<LoanRequest> allRequests() {
        return loanRequestRepository.findAll();
    }

    @GetMapping("/loans")
    List<Loan> allLoans() {
        return loanRepository.findAll();
    }

}
