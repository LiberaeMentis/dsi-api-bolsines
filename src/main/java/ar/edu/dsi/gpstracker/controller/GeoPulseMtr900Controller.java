package ar.edu.dsi.gpstracker.controller;

import ar.edu.dsi.gpstracker.service.ApiKeyService;
import ar.edu.dsi.gpstracker.service.BolsinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geopulse-mtr-900")
public class GeoPulseMtr900Controller {

    private final ApiKeyService apiKeyService;
    private final BolsinService bolsinService;

    public GeoPulseMtr900Controller(ApiKeyService apiKeyService, BolsinService bolsinService) {
        this.apiKeyService = apiKeyService;
        this.bolsinService = bolsinService;
    }

    @GetMapping("/fetchCargoPositions")
    public List<List<Object>> fetchCargoPositions(
            @RequestHeader("X-API-Key") String apiKey,
            @RequestParam Integer numeroBolsin
    ) {
        apiKeyService.validateForGeoPulseMtr900(apiKey);
        return bolsinService.getGeoPulsePositions(numeroBolsin);
    }
}
