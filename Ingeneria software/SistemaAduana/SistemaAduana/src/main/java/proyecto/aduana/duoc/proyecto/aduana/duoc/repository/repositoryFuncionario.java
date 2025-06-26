package proyecto.aduana.duoc.proyecto.aduana.duoc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Funcionario;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Viajero;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public class repositoryFuncionario {

  @PersistenceContext
    private EntityManager entityManager;

    @Transactional
     public void insertarFuncionario(String nombre, String rut,  String password,
                                 String email, String especialidad, String organismo  ) {
        entityManager.createNativeQuery("CALL sp_inserta_funcionario( ?, ?, ?, ?, ?, ?)")
                .setParameter(1, nombre)
                .setParameter(2, rut)
                 .setParameter(3, password)
                .setParameter(4, email)
                .setParameter(5, especialidad)
                .setParameter(6, organismo)
                .executeUpdate();
    }

    public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Viajero> findByRutAndPassword(String rut, String password);
    
}
}