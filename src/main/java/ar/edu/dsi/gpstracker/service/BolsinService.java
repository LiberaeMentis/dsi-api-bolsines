package ar.edu.dsi.gpstracker.service;

import ar.edu.dsi.gpstracker.dto.XtrBolsinLocationItemDto;
import ar.edu.dsi.gpstracker.dto.XtrBolsinLocationResponseDto;
import ar.edu.dsi.gpstracker.exception.BolsinNotFoundException;
import ar.edu.dsi.gpstracker.exception.CommissionCodeMismatchException;
import ar.edu.dsi.gpstracker.model.Bolsin;
import ar.edu.dsi.gpstracker.repository.InMemoryBolsinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolsinService {

    private final InMemoryBolsinRepository bolsinRepository;

    public BolsinService(InMemoryBolsinRepository bolsinRepository) {
        this.bolsinRepository = bolsinRepository;
    }

    public List<Bolsin> getAll() {
        return bolsinRepository.findAll();
    }

    public Bolsin getByNumeroBolsin(Integer numeroBolsin) {
        return bolsinRepository.findByNumeroBolsin(numeroBolsin)
                .orElseThrow(() -> new BolsinNotFoundException(numeroBolsin));
    }

    public XtrBolsinLocationResponseDto getXtrBolsinLocation(Integer numeroBolsin, String codigoComisionMedicaOrigen) {
        Bolsin bolsin = getByNumeroBolsin(numeroBolsin);
        if (!bolsin.getCodigoComisionMedicaOrigen().equalsIgnoreCase(codigoComisionMedicaOrigen)) {
            throw new CommissionCodeMismatchException("codigoComisionMedicaOrigen no coincide con el bolsin solicitado.");
        }

        XtrBolsinLocationItemDto item = new XtrBolsinLocationItemDto(
                bolsin.getNumeroBolsin(),
                bolsin.getLatitud(),
                bolsin.getLongitud(),
                bolsin.getFechaHoraUltimaActualizacion()
        );
        return new XtrBolsinLocationResponseDto(List.of(item));
    }

    public String getNavTrackData(Integer numeroBolsin, String codigoComisionMedicaDestino) {
        Bolsin bolsin = getByNumeroBolsin(numeroBolsin);
        if (!bolsin.getCodigoComisionMedicaDestino().equalsIgnoreCase(codigoComisionMedicaDestino)) {
            throw new CommissionCodeMismatchException("codigoComisionMedicaDestino no coincide con el bolsin solicitado.");
        }

        return bolsin.getNumeroBolsin() + "," +
                bolsin.getLatitud() + "," +
                bolsin.getLongitud() + "," +
                bolsin.getFechaHoraUltimaActualizacion();
    }

    public List<List<Object>> getGeoPulsePositions(Integer numeroBolsin) {
        Bolsin bolsin = getByNumeroBolsin(numeroBolsin);
        List<Object> row = List.of(
                bolsin.getNumeroBolsin(),
                bolsin.getLatitud(),
                bolsin.getLongitud(),
                bolsin.getFechaHoraUltimaActualizacion().toString()
        );
        return List.of(row);
    }
}
