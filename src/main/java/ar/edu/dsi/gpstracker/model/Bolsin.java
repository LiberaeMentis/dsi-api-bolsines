package ar.edu.dsi.gpstracker.model;

import java.time.LocalDateTime;

public class Bolsin {
    private Integer numeroBolsin;
    private String codigoComisionMedicaOrigen;
    private String codigoComisionMedicaDestino;
    private Double latitud;
    private Double longitud;
    private LocalDateTime fechaHoraUltimaActualizacion;

    public Bolsin(Integer numeroBolsin,
                  String codigoComisionMedicaOrigen,
                  String codigoComisionMedicaDestino,
                  Double latitud,
                  Double longitud,
                  LocalDateTime fechaHoraUltimaActualizacion) {
        this.numeroBolsin = numeroBolsin;
        this.codigoComisionMedicaOrigen = codigoComisionMedicaOrigen;
        this.codigoComisionMedicaDestino = codigoComisionMedicaDestino;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaHoraUltimaActualizacion = fechaHoraUltimaActualizacion;
    }

    public Integer getNumeroBolsin() {
        return numeroBolsin;
    }

    public String getCodigoComisionMedicaOrigen() {
        return codigoComisionMedicaOrigen;
    }

    public String getCodigoComisionMedicaDestino() {
        return codigoComisionMedicaDestino;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public LocalDateTime getFechaHoraUltimaActualizacion() {
        return fechaHoraUltimaActualizacion;
    }
}
