package ar.edu.dsi.gpstracker.repository;

import ar.edu.dsi.gpstracker.model.Bolsin;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBolsinRepository {

    private final List<Bolsin> bolsines = new ArrayList<>();

    @PostConstruct
    void init() {
        bolsines.add(new Bolsin(101, "CM-CBA-01", "CM-ROS-02", -31.4201, -64.1888, LocalDateTime.of(2026, 3, 7, 12, 30, 0)));
        bolsines.add(new Bolsin(102, "CM-ROS-02", "CM-MDZ-03", -32.9468, -60.6393, LocalDateTime.of(2026, 3, 7, 12, 32, 15)));
        bolsines.add(new Bolsin(103, "CM-MDZ-03", "CM-BA-01", -32.8895, -68.8458, LocalDateTime.of(2026, 3, 7, 12, 35, 4)));
        bolsines.add(new Bolsin(104, "CM-BA-01", "CM-CBA-01", -34.6037, -58.3816, LocalDateTime.of(2026, 3, 7, 12, 36, 20)));
        bolsines.add(new Bolsin(105, "CM-TUC-01", "CM-SLA-01", -26.8083, -65.2176, LocalDateTime.of(2026, 3, 7, 12, 40, 31)));
        bolsines.add(new Bolsin(106, "CM-SLA-01", "CM-JUJ-01", -24.7829, -65.4232, LocalDateTime.of(2026, 3, 7, 12, 43, 18)));
        bolsines.add(new Bolsin(107, "CM-NQN-01", "CM-MDQ-01", -38.9516, -68.0591, LocalDateTime.of(2026, 3, 7, 12, 45, 46)));
        bolsines.add(new Bolsin(108, "CM-MDQ-01", "CM-BA-01", -38.0055, -57.5426, LocalDateTime.of(2026, 3, 7, 12, 47, 2)));
        bolsines.add(new Bolsin(109, "CM-LPL-01", "CM-CBA-01", -34.9215, -57.9545, LocalDateTime.of(2026, 3, 7, 12, 49, 27)));
        bolsines.add(new Bolsin(110, "CM-CBA-02", "CM-ROS-02", -31.4167, -64.1833, LocalDateTime.of(2026, 3, 7, 12, 52, 9)));
    }

    public List<Bolsin> findAll() {
        return List.copyOf(bolsines);
    }

    public Optional<Bolsin> findByNumeroBolsin(Integer numeroBolsin) {
        return bolsines.stream()
                .filter(b -> b.getNumeroBolsin().equals(numeroBolsin))
                .findFirst();
    }
}
