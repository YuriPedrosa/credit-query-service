package com.credit_query.backend.controller;

import com.credit_query.backend.IntegrationTestBase;
import com.credit_query.backend.dto.ApiErrorDTO;
import com.credit_query.backend.dto.CreditoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
class CreditoControllerIT extends IntegrationTestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve retornar 200 OK ao buscar crédito existente no banco populado pelo Flyway")
    void getByCredito_ReturnsOk() {
        ResponseEntity<CreditoDTO> response = restTemplate.getForEntity("/api/creditos/credito/123456", CreditoDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("123456", response.getBody().numeroCredito());
    }

    @Test
    @DisplayName("Deve retornar 404 ao buscar crédito inexistente")
    void getByCredito_ReturnsNotFound() {
        ResponseEntity<ApiErrorDTO> response = restTemplate.getForEntity("/api/creditos/credito/1111", ApiErrorDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Recurso não encontrado", response.getBody().title());
    }
}