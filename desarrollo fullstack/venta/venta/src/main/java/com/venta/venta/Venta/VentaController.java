package com.venta.venta.Venta;

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
@RequestMapping("api/venta")
@RequiredArgsConstructor
@Tag(name = "Venta", description = "Operaciones relacionadas con las ventas realizadas")
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    @Operation(summary = "Crear una nueva venta", description = "Registra una nueva venta en el sistema")
    @ApiResponse(responseCode = "201", description = "Venta creada exitosamente")
    public Venta createVenta(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la nueva venta", required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Venta.class),
                examples = @ExampleObject(name = "EjemploVenta", value = "{\"id\": 1, \"producto\": \"Notebook\", \"cantidad\": 2, \"total\": 999980}")
            )
        )
        @RequestBody Venta venta) {
        ventaService.createVenta(venta);
        return venta;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las ventas", description = "Obtiene una lista de todas las ventas registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Venta.class))))
    })
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venta por ID", description = "Obtiene una venta específica según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public Venta getVentaById(
        @Parameter(description = "ID de la venta", required = true, example = "1")
        @PathVariable Integer id) {
        return ventaService.getVentaById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una venta", description = "Actualiza los datos de una venta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta actualizada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class))),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public Venta updateVenta(
        @Parameter(description = "ID de la venta a actualizar", required = true, example = "1")
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados de la venta", required = true)
        @RequestBody Venta venta) {
        ventaService.updateVenta(id, venta);
        return venta;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta", description = "Elimina una venta según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public void deleteVenta(
        @Parameter(description = "ID de la venta a eliminar", required = true, example = "1")
        @PathVariable Integer id) {
        ventaService.deleteVenta(id);
    }
}
