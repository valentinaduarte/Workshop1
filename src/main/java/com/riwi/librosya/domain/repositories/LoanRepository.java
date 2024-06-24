package com.riwi.librosya.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.librosya.domain.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
}

