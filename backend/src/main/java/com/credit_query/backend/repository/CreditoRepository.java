package com.credit_query.backend.repository;

import com.credit_query.backend.entity.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByNumeroNfse(String nfse);
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}
