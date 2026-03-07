package ar.edu.dsi.gpstracker.controller;

import ar.edu.dsi.gpstracker.model.Bolsin;
import ar.edu.dsi.gpstracker.service.BolsinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mock/bolsines")
public class MockDataController {

    private final BolsinService bolsinService;

    public MockDataController(BolsinService bolsinService) {
        this.bolsinService = bolsinService;
    }

    @GetMapping
    public List<Bolsin> getAll() {
        return bolsinService.getAll();
    }

    @GetMapping("/{numeroBolsin}")
    public Bolsin getByNumero(@PathVariable Integer numeroBolsin) {
        return bolsinService.getByNumeroBolsin(numeroBolsin);
    }
}
