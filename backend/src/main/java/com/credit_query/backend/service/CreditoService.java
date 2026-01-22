package com.credit_query.backend.service;

import com.credit_query.backend.dto.CreditoDTO;
import com.credit_query.backend.entity.Credito;
import com.credit_query.backend.producer.AuditProducer;
import com.credit_query.backend.repository.CreditoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository repository;
    private final AuditProducer auditProducer;

    public List<CreditoDTO> buscarPorNfse(String nfse) {
        long startTime = System.currentTimeMillis();
        AtomicReference<String> status = new AtomicReference<>("SUCESSO");
        String erro = null;

        try {
            return repository.findByNumeroNfse(nfse).stream().map(this::mapToDTO).toList();
        } catch (Exception e) {
            status.set("ERRO");
            erro = e.getMessage();
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            auditProducer.sendLog("BUSCA_POR_NFSE", nfse, status.get(), duration, erro);
        }
    }

    public CreditoDTO buscarPorNumeroCredito(String numeroCredito) {
        long startTime = System.currentTimeMillis();
        AtomicReference<String> status = new AtomicReference<>("SUCESSO");
        String erro = null;

        try {
            return repository.findByNumeroCredito(numeroCredito)
                    .map(this::mapToDTO).
                    orElseThrow(() -> {
                        status.set("NAO_ENCONTRADO");
                        return new ResourceNotFoundException("Crédito " + numeroCredito + " não encontrado.");
                    });
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            status.set("ERRO");
            erro = e.getMessage();
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            auditProducer.sendLog("BUSCA_POR_NUMERO", numeroCredito, status.get(), duration, erro);
        }
    }

    private CreditoDTO mapToDTO(Credito c) {
        return new CreditoDTO(c.getNumeroCredito(), c.getNumeroNfse(), c.getDataConstituicao(), c.getValorIssqn(), c.getTipoCredito(), c.isSimplesNacional() ? "Sim" : "Não", c.getAliquota(), c.getValorFaturado(), c.getValorDeducao(), c.getBaseCalculo());
    }
}