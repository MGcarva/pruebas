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
import proyecto.aduana.duoc.proyecto.aduana.duoc.repository.repositoryFuncionario;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Admin;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Funcionario;
import proyecto.aduana.duoc.proyecto.aduana.duoc.Model.Viajero;

@RestController
@RequestMapping("funcionario")
 @Repository

public class controllerFuncionario {

      @Autowired
    private repositoryFuncionario repositoryFuncionario; 
    @PersistenceContext
    private EntityManager entityManager;

     @PostMapping("/guardar")
    public String guardarViajero(@ModelAttribute Funcionario funcionario) {
        repositoryFuncionario.insertarFuncionario(funcionario.getNombre(), funcionario.getRut(),   funcionario.getPassword(),
                                      funcionario.getEmail(), funcionario.getEspecialidad(), funcionario.getOrganismo()  );
        
        return "redirect:/home";
    
}

@GetMapping("/buscarPorRut/{rut}/{password}")
    public ResponseEntity<?> login( @PathVariable String rut,    @PathVariable String password) {
     

        try {
            Funcionario funcionario = (Funcionario) entityManager
                .createQuery("SELECT v FROM Funcionario v WHERE v.rut = :rut AND v.password = :password")
                .setParameter("rut", rut)
                .setParameter("password", password)
                .getSingleResult();

            return ResponseEntity.ok(funcionario);

        } catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Rut o Password incorrectas");
        }
    }
}