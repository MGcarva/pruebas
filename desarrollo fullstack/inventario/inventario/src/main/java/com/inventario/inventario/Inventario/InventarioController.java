package com.inventario.inventario.Inventario;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
@Tag(name = "Inventario", description = "Operaciones relacionadas con el inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    // CREATE
    @PostMapping
    @Operation(summary = "Crear un nuevo inventario", description = "Registra un nuevo ítem en el inventario")
    @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente")
    public Inventario createInventario(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo inventario", required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Inventario.class),
                examples = @ExampleObject(name = "EjemploInventario", 
                    value = "{\"id\": 1, \"nombre\": \"Mouse\", \"cantidad\": 20, \"precio\": 15000}"))
        )
        @RequestBody Inventario inventario) {
        inventarioService.createInventario(inventario);
        return inventario;
    }

    // READ: obtener todos
    @GetMapping
    @Operation(summary = "Obtener todos los inventarios", description = "Obtiene una lista de todos los productos en inventario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Inventario.class))))
    })
    public List<Inventario> getAllInventarios() {
        return inventarioService.getAllInventarios();
    }

    // READ: obtener por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener inventario por ID", description = "Obtiene un ítem del inventario según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public Inventario getInventarioById(
        @Parameter(description = "ID del inventario", required = true, example = "1")
        @PathVariable Integer id) {
        return inventarioService.getInventarioById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    // UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un inventario", description = "Actualiza los datos de un ítem en el inventario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario actualizado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public Inventario updateInventario(
        @Parameter(description = "ID del inventario a actualizar", required = true, example = "1")
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del inventario", required = true)
        @RequestBody Inventario inventario) {
        inventarioService.updateInventario(id, inventario);
        return inventario;
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un inventario", description = "Elimina un ítem del inventario según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public void deleteInventario(
        @Parameter(description = "ID del inventario a eliminar", required = true, example = "1")
        @PathVariable Integer id) {
        inventarioService.deleteInventario(id);
    }
}