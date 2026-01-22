package com.credit_query.backend.dto;

import java.time.LocalDateTime;

public record AuditLogEventDTO(
        String operacao,
        String parametro,
        String status,
        Long duracaoMs,
        String mensagemErro,
        LocalDateTime timestamp
) {
}