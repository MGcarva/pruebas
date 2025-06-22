package Aduana.SistemaAduana.Admin;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Admin {

@Id
@GeneratedValue
private Integer id;

@Basic
private String  nombre;
private String  rut;
private String  email;
private String  contraseña;











}
