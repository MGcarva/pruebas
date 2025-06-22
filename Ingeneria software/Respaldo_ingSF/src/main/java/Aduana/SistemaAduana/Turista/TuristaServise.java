package Aduana.SistemaAduana.Turista;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TuristaServise {

    private final TuristaRepository turistaRepo;

    // Crear nuevo turista
    public void createTurista(Turista turista) {
        turistaRepo.save(turista);
    }

    // Obtener todos los turistas
    public List<Turista> getAllTuristas() {
        return turistaRepo.findAll();
    }

    // Buscar turista por ID
    public Optional<Turista> getTuristaById(Integer id) {
        return turistaRepo.findById(id);
    }

    // Buscar turista por RUT
    public Optional<Turista> getTuristaByRut(String rut) {

        return turistaRepo.findByRut(rut);
    }

    // Actualizar turista
    public void updateTurista(Integer id, Turista turista) {
        if (turistaRepo.existsById(id)) {
            turista.setId(id); 
            turistaRepo.save(turista);
        } else {
            throw new RuntimeException("Turista no encontrado para actualizar");
        }
    }

    // Eliminar turista
    public void deleteTurista(Integer id) {
        if (turistaRepo.existsById(id)) {
            turistaRepo.deleteById(id);
        } else {
            throw new RuntimeException("Turista no encontrado para eliminar");
        }
    }
}
