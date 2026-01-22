package com.credit_query.backend.service;

import com.credit_query.backend.dto.CreditoDTO;
import com.credit_query.backend.entity.Credito;
import com.credit_query.backend.exception.ResourceNotFoundException;
import com.credit_query.backend.producer.AuditProducer;
import com.credit_query.backend.repository.CreditoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository repository;

    @Mock
    private AuditProducer auditProducer;

    @InjectMocks
    private CreditoService service;

    @Test
    @DisplayName("Deve retornar crédito com sucesso e disparar log")
    void buscarPorNumeroCredito_Sucesso() {
        String numero = "123456";
        Credito credito = new Credito();
        credito.setNumeroCredito(numero);
        when(repository.findByNumeroCredito(numero)).thenReturn(Optional.of(credito));

        CreditoDTO resultado = service.buscarPorNumeroCredito(numero);

        assertNotNull(resultado);
        verify(auditProducer, times(1)).sendLog(eq("BUSCA_POR_NUMERO"), eq(numero), eq("SUCESSO"), anyLong(), any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando crédito não existe")
    void buscarPorNumeroCredito_NotFound() {
        String numero = "1111";
        when(repository.findByNumeroCredito(numero)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorNumeroCredito(numero));

        verify(auditProducer).sendLog(any(), any(), eq("NAO_ENCONTRADO"), anyLong(), any());
    }
}