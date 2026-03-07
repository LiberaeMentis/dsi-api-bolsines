package ar.edu.dsi.gpstracker.dto;

import java.time.LocalDateTime;

public record XtrBolsinLocationItemDto(
        Integer numeroBolsin,
        Double latitud,
        Double longitud,
        LocalDateTime fechaHoraUltimaActualizacion
) {
}
