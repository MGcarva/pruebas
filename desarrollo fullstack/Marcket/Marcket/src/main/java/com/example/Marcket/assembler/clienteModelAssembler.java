package com.example.Marcket.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Marcket.Cliente.ClienteController_V2;
import com.example.Marcket.Cliente.Cliente;


@Component
public class clienteModelAssembler  implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
  
@SuppressWarnings("null")
@Override
public EntityModel<Cliente> toModel(Cliente cliente) {
return EntityModel.of(cliente,linkTo(methodOn(ClienteController_V2.class).getAllClientes()).withRel("carreras"));
    
}
}
