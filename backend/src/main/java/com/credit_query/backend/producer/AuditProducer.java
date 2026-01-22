package com.credit_query.backend.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendLog(String message) {
        kafkaTemplate.send("credit-query-audit", message).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Erro ao enviar mensagem de auditoria: {}", ex.getMessage());
            } else {
                log.info("Mensagem de auditoria enviada com sucesso: {}", message);
            }
        });
    }
}