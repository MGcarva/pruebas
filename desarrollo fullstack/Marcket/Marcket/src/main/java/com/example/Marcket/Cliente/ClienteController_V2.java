package com.example.Marcket.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Marcket.assembler.clienteModelAssembler;
import com.example.Marcket.Cliente.ClienteController_V2;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController_V2 {
  
     @Autowired
    private ClienteServicio ventaService;
    @Autowired
    private clienteModelAssembler assembler;
    //Busca todos los productos
     @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>> getAllClientes()
     {
           List<EntityModel<Cliente>> cliente = ventaService.getAllClientes().stream().map(assembler::toModel).collect(Collectors.toList());
            return CollectionModel.of(cliente,linkTo(methodOn(ClienteController_V2.class).getAllClientes()).withSelfRel());
     }
     //Busca un producto por id 

        @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> getClienteById(@PathVariable int id) {
        Cliente cliente = ventaService.getClienteById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));    
                return ResponseEntity.ok(assembler.toModel(cliente));
                }
    //Crea un nuevo producto
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> createCliente(@RequestBody Cliente cliente) {
        ventaService.createCliente(cliente);
        EntityModel<Cliente> entityModel = assembler.toModel(cliente);
        return ResponseEntity.created(linkTo(methodOn(ClienteController_V2.class).getClienteById(cliente.getId())).toUri()).body(entityModel);
        }
    //Actualiza un producto existente
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        ventaService.updateCliente(id, cliente);
        EntityModel<Cliente> entityModel = assembler.toModel(cliente);
        return ResponseEntity.ok(entityModel);
    }
    // Elimina un producto por id
    @DeleteMapping(value = "{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        ventaService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
