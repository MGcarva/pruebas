package proyecto.aduana.duoc.proyecto.aduana.duoc.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Viajero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class repositoryViajero {
    @PersistenceContext
    private EntityManager entityManager;
  

    @Transactional
     public void insertarViajero(String nombre, String rut, String dv, String password,
                                 String email, String nacionalidad, String fechanacimiento  ) {
        entityManager.createNativeQuery("CALL sp_inserta_viajero(?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, nombre)
                .setParameter(2, rut)
                .setParameter(3, dv)
                .setParameter(4, password)
                .setParameter(5, email)
                .setParameter(6, nacionalidad)
                .setParameter(7, fechanacimiento)
                .executeUpdate();
    }

    public interface ViajeroRepository extends JpaRepository<Viajero, Long> {
    Optional<Viajero> findByRutAndPassword(String rut, String password);
} 


     /************************************************************************************** */
        //public  void buscarViajero(String rut,String password) {
        
        //entityManager.createNativeQuery("CALL sp_busca_viajero(?,?)")
            //.setParameter(1, rut)
            //  .setParameter(2, password)
            //.getResultList();

   // }

    }


   


   

   
