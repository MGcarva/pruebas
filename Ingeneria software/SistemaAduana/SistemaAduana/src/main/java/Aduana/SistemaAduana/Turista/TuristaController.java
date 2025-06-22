package Aduana.SistemaAduana.Turista;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/viajero")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080") // Permite llamadas desde HTML local
public class TuristaController {

    private final TuristaServise turistaServise;

    // Crear un nuevo turista
    @PostMapping
    public void createTurista(@RequestBody Turista turista) {
        turistaServise.createTurista(turista);
    }

    // Obtener todos los turistas
    @GetMapping
    public List<Turista> getAllTuristas() {
        return turistaServise.getAllTuristas();
    }

    // Obtener un turista por ID
    @GetMapping("/{id}")
    public Turista getTuristaById(@PathVariable Integer id) {
        return turistaServise.getTuristaById(id)
                .orElseThrow(() -> new RuntimeException("Turista no encontrado"));
    }

    // Obtener un turista por RUT
    @GetMapping("/rut/{rut}")
    public Turista getTuristaByRut(@PathVariable String rut) {
        
        return turistaServise.getTuristaByRut(rut)
                .orElseThrow(() -> new RuntimeException("Turista no encontrado con RUT: " + rut));
    }

    // Actualizar un turista existente
    @PutMapping("/{id}")
    public void updateTurista(@PathVariable Integer id, @RequestBody Turista turista) {
        turistaServise.updateTurista(id, turista);
    }

    // Eliminar un turista por ID
    @DeleteMapping("/{id}")
    public void deleteTurista(@PathVariable Integer id) {
        turistaServise.deleteTurista(id);
    }
}
