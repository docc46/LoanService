package com.kuros.LoanService.repository;

import com.kuros.LoanService.model.ProlongRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongRequestRepository extends JpaRepository<ProlongRequest, Integer> {
}
