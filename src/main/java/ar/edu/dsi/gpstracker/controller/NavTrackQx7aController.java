package ar.edu.dsi.gpstracker.controller;

import ar.edu.dsi.gpstracker.service.ApiKeyService;
import ar.edu.dsi.gpstracker.service.BolsinService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/navtrack-qx-7a")
public class NavTrackQx7aController {

    private final ApiKeyService apiKeyService;
    private final BolsinService bolsinService;

    public NavTrackQx7aController(ApiKeyService apiKeyService, BolsinService bolsinService) {
        this.apiKeyService = apiKeyService;
        this.bolsinService = bolsinService;
    }

    @GetMapping(value = "/retrieveTrackingData", produces = MediaType.TEXT_PLAIN_VALUE)
    public String retrieveTrackingData(
            @RequestHeader("X-API-Key") String apiKey,
            @RequestParam Integer numeroBolsin,
            @RequestParam String codigoComisionMedicaDestino
    ) {
        apiKeyService.validateForNavTrackQx7a(apiKey);
        return bolsinService.getNavTrackData(numeroBolsin, codigoComisionMedicaDestino);
    }
}
