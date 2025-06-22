package Aduana.SistemaAduana.Turista;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuristaRepository extends JpaRepository<Turista, Integer> {

    // Buscar un turista por su RUT
    Optional<Turista> findByRut(String rut);
}

