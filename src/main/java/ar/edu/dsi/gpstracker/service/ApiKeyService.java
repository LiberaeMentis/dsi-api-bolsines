package ar.edu.dsi.gpstracker.service;

import ar.edu.dsi.gpstracker.config.GpsApiProperties;
import ar.edu.dsi.gpstracker.exception.ApiKeyInvalidException;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    private final GpsApiProperties gpsApiProperties;

    public ApiKeyService(GpsApiProperties gpsApiProperties) {
        this.gpsApiProperties = gpsApiProperties;
    }

    public void validateForXtr4500l(String apiKey) {
        validate(apiKey, gpsApiProperties.getKeys().getXtr4500l(), "XTR-4500L");
    }

    public void validateForNavTrackQx7a(String apiKey) {
        validate(apiKey, gpsApiProperties.getKeys().getNavtrackQx7a(), "NavTrack QX-7A");
    }

    public void validateForGeoPulseMtr900(String apiKey) {
        validate(apiKey, gpsApiProperties.getKeys().getGeopulseMtr900(), "GeoPulse MTR-900");
    }

    private void validate(String apiKey, String expectedApiKey, String model) {
        if (apiKey == null || apiKey.isBlank() || expectedApiKey == null || expectedApiKey.isBlank() || !expectedApiKey.equals(apiKey)) {
            throw new ApiKeyInvalidException("La API Key provista no es valida para el modelo " + model + ".");
        }
    }
}
