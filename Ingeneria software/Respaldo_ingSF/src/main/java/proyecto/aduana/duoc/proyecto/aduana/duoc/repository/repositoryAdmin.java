package proyecto.aduana.duoc.proyecto.aduana.duoc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class repositoryAdmin {
 @PersistenceContext
    private EntityManager entityManager;

    @Transactional
     public void insertarAdmin(String nombre, String rut,  String password, String email ) {
        entityManager.createNativeQuery("CALL sp_inserta_admin(?, ?, ?, ?)")
                .setParameter(1, nombre)
                .setParameter(2, rut)
                .setParameter(3, password)
                .setParameter(4, email)
                .executeUpdate();
    }
   public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByRutAndPassword(String rut, String password);
} 

}
