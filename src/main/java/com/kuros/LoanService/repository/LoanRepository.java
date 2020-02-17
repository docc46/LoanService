package com.kuros.LoanService.repository;

import com.kuros.LoanService.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
}
