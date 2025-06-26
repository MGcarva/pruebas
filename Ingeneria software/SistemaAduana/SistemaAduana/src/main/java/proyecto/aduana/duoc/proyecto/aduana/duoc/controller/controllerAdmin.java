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
import proyecto.aduana.duoc.proyecto.aduana.duoc.repository.repositoryAdmin;

import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Admin;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Viajero;


@RestController
@RequestMapping("admin")
 @Repository

public class controllerAdmin {

     @Autowired
    private repositoryAdmin adminRepository; 
    @PersistenceContext
    private EntityManager entityManager;
  
    
    @PostMapping("/guardar")
    public String guardarAdmin(@ModelAttribute Admin admin) {
        adminRepository.insertarAdmin(admin.getNombre(), admin.getRut(),  admin.getPassword(), admin.getEmail());
        
        return "redirect:/home";
    }
    
     @GetMapping("/buscarPorRut/{rut}/{password}")
    public ResponseEntity<?> login( @PathVariable String rut,    @PathVariable String password) {
     

        try {
            Admin admin = (Admin) entityManager
                .createQuery("SELECT v FROM Admin v WHERE v.rut = :rut AND v.password = :password")
                .setParameter("rut", rut)
                .setParameter("password", password)
                .getSingleResult();

            return ResponseEntity.ok(admin);

        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}
