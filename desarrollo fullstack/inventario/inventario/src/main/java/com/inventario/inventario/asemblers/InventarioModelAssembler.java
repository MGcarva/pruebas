package com.inventario.inventario.asemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import com.inventario.inventario.Inventario.InventarioController_V2;
import com.inventario.inventario.Inventario.Inventario;


@Component
public class InventarioModelAssembler  implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {
  
@SuppressWarnings("null")
@Override
public EntityModel<Inventario> toModel(Inventario inventario) {
return EntityModel.of(inventario,linkTo(methodOn(InventarioController_V2.class).getAllInventarios()).withRel("carreras"));
    
}
}
