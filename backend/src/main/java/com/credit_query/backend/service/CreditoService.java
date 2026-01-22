package com.credit_query.backend.service;

import com.credit_query.backend.dto.CreditoDTO;
import com.credit_query.backend.entity.Credito;
import com.credit_query.backend.repository.CreditoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository repository;

    public List<CreditoDTO> buscarPorNfse(String nfse) {
        return repository.findByNumeroNfse(nfse).stream()
                .map(this::mapToDTO).toList();
    }

    public CreditoDTO buscarPorNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
    }

    private CreditoDTO mapToDTO(Credito c) {
        return new CreditoDTO(
                c.getNumeroCredito(), c.getNumeroNfse(), c.getDataConstituicao(),
                c.getValorIssqn(), c.getTipoCredito(), c.isSimplesNacional() ? "Sim" : "Não",
                c.getAliquota(), c.getValorFaturado(), c.getValorDeducao(), c.getBaseCalculo()
        );
    }
}