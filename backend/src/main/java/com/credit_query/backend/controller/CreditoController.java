package com.credit_query.backend.controller;

import com.credit_query.backend.dto.CreditoDTO;
import com.credit_query.backend.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CreditoController {

    private final CreditoService service;

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDTO>> getByNfse(@PathVariable String numeroNfse) {
        return ResponseEntity.ok(service.buscarPorNfse(numeroNfse));
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> getByCredito(@PathVariable String numeroCredito) {
        return ResponseEntity.ok(service.buscarPorNumeroCredito(numeroCredito));
    }
}