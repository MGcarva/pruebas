package com.example.Marcket.Cliente;

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
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@Tag(name = "Cliente", description = "Operaciones relacionadas con los clientes del sistema")
public class ClienteController {

    private final ClienteServicio clienteServicio;

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en el sistema")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    public Cliente createCliente(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo cliente", required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cliente.class),
                examples = @ExampleObject(name = "EjemploCliente", value = "{\"id\": 1, \"nombre\": \"Pedro\", \"correo\": \"pedro@correo.cl\"}"))
        )
        @RequestBody Cliente cliente) {
        clienteServicio.createCliente(cliente);
        return cliente;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Cliente.class))))
    })
    public List<Cliente> getAllClientes() {
        return clienteServicio.getAllClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Obtiene un cliente específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public Cliente getClienteById(
        @Parameter(description = "ID del cliente", required = true, example = "1")
        @PathVariable Integer id) {
        return clienteServicio.getClienteById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public Cliente updateCliente(
        @Parameter(description = "ID del cliente a actualizar", required = true, example = "1")
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del cliente", required = true)
        @RequestBody Cliente cliente) {
        clienteServicio.updateCliente(id, cliente);
        return cliente;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public void deleteCliente(
        @Parameter(description = "ID del cliente a eliminar", required = true, example = "1")
        @PathVariable Integer id) {
        clienteServicio.deleteCliente(id);
    }
}
