package com.kuros.LoanService.repository;

import com.kuros.LoanService.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer> {
    int countByIpAddress(String ipAddress);
}
