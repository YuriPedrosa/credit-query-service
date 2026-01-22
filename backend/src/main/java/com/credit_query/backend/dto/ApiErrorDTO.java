package com.credit_query.backend.dto;

import java.time.LocalDateTime;

public record ApiErrorDTO(
        String title,
        int status,
        LocalDateTime timestamp,
        String detail
) {
}