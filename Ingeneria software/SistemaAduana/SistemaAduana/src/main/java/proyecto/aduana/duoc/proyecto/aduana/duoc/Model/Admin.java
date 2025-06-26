package proyecto.aduana.duoc.proyecto.aduana.duoc.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity

public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String nombre;
    
    private String rut;
    private String dv;
    private String password;
    private String email;
    
    public Admin() {}

    public Admin(String nombre, String rut, String dv, String password, String email) {
        this.nombre = nombre;
        this.rut = rut;
        this.dv = dv;
        this.password = password;
        this.email = email;
    }
    // Getters y Setters    
    public String getNombre() {
        return nombre;
    }   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
        }
    public String getDv() {   
      return dv;
    }
    public void setDv(String dv) {
        this.dv = dv;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
