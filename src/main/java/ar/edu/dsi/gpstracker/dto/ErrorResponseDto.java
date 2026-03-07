package ar.edu.dsi.gpstracker.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String error,
        String mensaje,
        int status,
        LocalDateTime timestamp
) {
}
