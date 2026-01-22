package com.credit_query.backend.producer;

import com.credit_query.backend.dto.AuditLogEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendLog(String op, String param, String status, long duration, String errorMsg) {

        AuditLogEventDTO event = new AuditLogEventDTO(
                op, param, status, duration, errorMsg, LocalDateTime.now()
        );

        kafkaTemplate.send("credit-query-audit", event).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Erro ao enviar mensagem de auditoria: {}", ex.getMessage());
            }
        });
    }
}