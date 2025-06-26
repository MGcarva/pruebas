package proyecto.aduana.duoc.proyecto.aduana.duoc.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity

public class Viajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String nombre;
    
    private String rut;
    private String dv;
    private String password;
    private String email;
    private String nacionalidad;
    private String fec_nac;
 public Viajero() {}

    public Viajero(String nombre, String apellido, String rut, String dv, String password , String email, String nacionalidad, String fechanacimiento) {
        this.nombre = nombre;
      
        this.rut = rut;
        this.dv = dv;
        this.password = password;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.fec_nac = fechanacimiento;
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
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getFechanacimiento() {
        return fec_nac;
    }
    public void setFechanacimiento(String fechanacimiento) {
        this.fec_nac = fechanacimiento;
}



    
}