package com.example.Marcket.Admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Administrador", description = "Operaciones relacionadas con los administradores del sistema")
public class AdminController {

    private final AdminServicio adminServicio;

    @PostMapping
    @Operation(summary = "Crear un nuevo administrador", description = "Registra un nuevo administrador en el sistema")
    @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente")
    public Admin createAdmin(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo administrador", required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Admin.class),
                examples = @ExampleObject(name = "EjemploAdmin", value = "{\"id\": 1, \"nombre\": \"Juan\", \"correo\": \"juan@duoc.cl\"}"))
        )
        @RequestBody Admin admin) {
        adminServicio.createAdmin(admin);
        return admin;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los administradores", description = "Obtiene una lista de todos los administradores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Admin.class))))
    })
    public List<Admin> getAllAdmins() {
        return adminServicio.getAllAdmins();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener administrador por ID", description = "Obtiene un administrador específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public Admin getAdminById(
        @Parameter(description = "ID del administrador", required = true, example = "1")
        @PathVariable Integer id) {
        return adminServicio.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un administrador", description = "Actualiza los datos de un administrador existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador actualizado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Admin.class))),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public Admin updateAdmin(
        @Parameter(description = "ID del administrador a actualizar", required = true, example = "1")
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del administrador", required = true)
        @RequestBody Admin admin) {
        adminServicio.updateAdmin(id, admin);
        return admin;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un administrador", description = "Elimina un administrador según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public void deleteAdmin(
        @Parameter(description = "ID del administrador a eliminar", required = true, example = "1")
        @PathVariable Integer id) {
        adminServicio.deleteAdmin(id);
    }
}
