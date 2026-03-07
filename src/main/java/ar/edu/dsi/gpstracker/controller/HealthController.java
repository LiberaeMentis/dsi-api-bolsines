package ar.edu.dsi.gpstracker.controller;

import ar.edu.dsi.gpstracker.dto.HealthResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public HealthResponseDto health() {
        return new HealthResponseDto("ok");
    }
}
