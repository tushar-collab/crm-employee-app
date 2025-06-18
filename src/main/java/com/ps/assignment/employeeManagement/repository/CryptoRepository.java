package com.ps.assignment.employeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ps.assignment.employeeManagement.model.Crypto;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    @Query("SELECT c FROM Crypto c WHERE c.coin = ?1 AND c.wallet = ?2 AND c.network = ?3")
    public Optional<Crypto> findByCoinWalletNetwork(String coin, String wallet, String network);
    
}
