package proyecto.aduana.duoc.proyecto.aduana.duoc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import proyecto.aduana.duoc.proyecto.aduana.duoc.repository.repositoryViajero;

import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Viajero;


@RestController
@RequestMapping("viajero")
 @Repository


public class controllerViajero {
     @Autowired
    private repositoryViajero viajeroRepository; 
    @PersistenceContext
    private EntityManager entityManager;
  
    
    @PostMapping("/guardar")
    public String guardarViajero(@ModelAttribute Viajero viajero) {
        viajeroRepository.insertarViajero(viajero.getNombre(), viajero.getRut(),   viajero.getPassword(),
                                      viajero.getEmail(), viajero.getNacionalidad(), viajero.getFechanacimiento()  );
        
        return "redirect:/home";
    }
    // Método para buscar un viajero por su rut y contraseña
    
   
     @GetMapping("/buscarPorRut/{rut}/{password}")
    public ResponseEntity<?> login( @PathVariable String rut,    @PathVariable String password) {
     

        try {
            Viajero viajero = (Viajero) entityManager
                .createQuery("SELECT v FROM Viajero v WHERE v.rut = :rut AND v.password = :password")
                .setParameter("rut", rut)
                .setParameter("password", password)
                .getSingleResult();

            return ResponseEntity.ok(viajero);

        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}


    /**************************************************************************************** */
   // public String buscarViajero(@ModelAttribute modelViajero viajero) {

          
       
      //viajeroRepository.buscarViajero(viajero.getRut(), viajero.getPassword());
     
    //}



