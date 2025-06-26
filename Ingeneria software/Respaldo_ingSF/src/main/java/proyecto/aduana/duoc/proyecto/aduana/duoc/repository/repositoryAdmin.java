package proyecto.aduana.duoc.proyecto.aduana.duoc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class repositoryAdmin {
 @PersistenceContext
    private EntityManager entityManager;

    @Transactional
     public void insertarViajero(String nombre, String rut, String dv, String password,
                                 String email, String nacionalidad, String fechanacimiento  ) {
        entityManager.createNativeQuery("CALL sp_inserta_admin(?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, nombre)
                .setParameter(2, rut)
                .setParameter(3, dv)
                .setParameter(4, password)
                .setParameter(5, email)
                .setParameter(6, nacionalidad)
                .setParameter(7, fechanacimiento)
                .executeUpdate();
    }
    
}
