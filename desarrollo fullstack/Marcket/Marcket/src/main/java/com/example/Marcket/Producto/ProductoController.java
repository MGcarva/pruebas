package com.example.Marcket.Producto;

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
@RequestMapping("/producto")
@RequiredArgsConstructor
@Tag(name = "Producto", description = "Operaciones relacionadas con productos del sistema")
public class ProductoController {

    private final ProductoServicio productoServicio;

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en el sistema")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    public Producto createProducto(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo producto", required = true,
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class),
                examples = @ExampleObject(name = "EjemploProducto", value = "{\"id\": 1, \"nombre\": \"Notebook\", \"precio\": 499990}"))
        )
        @RequestBody Producto producto) {
        productoServicio.createProducto(producto);
        return producto;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtiene una lista de todos los productos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Producto.class))))
    })
    public List<Producto> getAllProductos() {
        return productoServicio.getAllProductos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public Producto getProductoById(
        @Parameter(description = "ID del producto", required = true, example = "1")
        @PathVariable Integer id) {
        return productoServicio.getProductoById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public Producto updateProducto(
        @Parameter(description = "ID del producto a actualizar", required = true, example = "1")
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del producto", required = true)
        @RequestBody Producto producto) {
        productoServicio.updateProducto(id, producto);
        return producto;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public void deleteProducto(
        @Parameter(description = "ID del producto a eliminar", required = true, example = "1")
        @PathVariable Integer id) {
        productoServicio.deleteProducto(id);
    }
}
