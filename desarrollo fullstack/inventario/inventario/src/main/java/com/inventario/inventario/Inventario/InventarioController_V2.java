package com.inventario.inventario.Inventario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inventario.inventario.asemblers.InventarioModelAssembler;



import java.util.List;

import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/inventario")

public class InventarioController_V2 {
@Autowired
private InventarioService service;
@Autowired
    private InventarioModelAssembler assembler;
   //Buscar todos los inventarios
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAllInventarios()
     {
           List<EntityModel<Inventario>> inventario = service.getAllInventarios().stream().map(assembler::toModel).collect(Collectors.toList());
            return CollectionModel.of(inventario,linkTo(methodOn(InventarioController_V2.class).getAllInventarios()).withSelfRel());
     }
      //Busca un venta por id
     @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inventario> getInventarioById(@PathVariable int id) 
        {
           Inventario inventario = service.getInventarioById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
            return assembler.toModel(inventario);
        }

         
        //Crea un nuevo producto
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> createIventario(@RequestBody Inventario inventario) {
        service.createInventario(inventario);
        EntityModel<Inventario> entityModel = assembler.toModel(inventario);
        return ResponseEntity.created(linkTo(methodOn(InventarioController_V2.class).getInventarioById(inventario.getId())).toUri()).body(entityModel);
        }

         @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
            public ResponseEntity<EntityModel<Inventario>> updateProducto(@PathVariable int id, @RequestBody Inventario inventario) {
            service.updateInventario(id, inventario);
        EntityModel<Inventario> entityModel = assembler.toModel(inventario);
        return ResponseEntity.ok(entityModel);
    }

        // Elimina un Inventario por id
    @DeleteMapping(value = "{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteInventario(@PathVariable int id) {
        service.deleteInventario(id);
        return ResponseEntity.noContent().build();
    }
   
     



}
