package com.example.Marcket.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Marcket.assembler.productoModelAssembler;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/producto")
public class ProductoController_V2 {
   @Autowired
    private ProductoServicio ventaService;
    @Autowired
    private productoModelAssembler assembler;
    //Busca todos los productos
     @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAllProductos()
     {
           List<EntityModel<Producto>> productos = ventaService.getAllProductos().stream().map(assembler::toModel).collect(Collectors.toList());
            return CollectionModel.of(productos,linkTo(methodOn(ProductoController_V2.class).getAllProductos()).withSelfRel());
     }

    //Busca un producto por id
    
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE  
    )
    public ResponseEntity<EntityModel<Producto>> getProductoById(@PathVariable int id) {
        Producto producto = ventaService.getProductoById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return ResponseEntity.ok(assembler.toModel(producto));
    }       
    //Crea un nuevo producto
   
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)     
    public ResponseEntity<EntityModel<Producto>> createProducto(@RequestBody Producto producto) {
        ventaService.createProducto(producto);
        EntityModel<Producto> entityModel = assembler.toModel(producto);
        return ResponseEntity.created(linkTo(methodOn(ProductoController_V2.class).getProductoById(producto.getId())).toUri()).body(entityModel);
    }
    //Actualiza un producto existente
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        ventaService.updateProducto(id, producto);
        EntityModel<Producto> entityModel = assembler.toModel(producto);
        return ResponseEntity.ok(entityModel);
    }

    // Elimina un producto por id
    @DeleteMapping(value = "{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        ventaService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    

}
