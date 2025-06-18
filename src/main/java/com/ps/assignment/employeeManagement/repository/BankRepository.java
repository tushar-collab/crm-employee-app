package com.ps.assignment.employeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ps.assignment.employeeManagement.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    
    @Query("SELECT b FROM Bank b WHERE b.cardExpire = ?1 AND b.cardNumber = ?2 AND b.cardType = ?3 AND b.currency = ?4 AND b.iban = ?5")
    public Optional<Bank> findByObjectParams(String cardExpire, String cardNumber, String cardType, String currency, String iban);

}
