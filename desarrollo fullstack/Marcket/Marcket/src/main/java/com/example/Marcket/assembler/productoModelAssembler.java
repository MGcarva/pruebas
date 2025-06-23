package com.example.Marcket.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Marcket.Producto.ProductoController_V2;
import com.example.Marcket.Producto.Producto;


@Component
public class productoModelAssembler  implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
@SuppressWarnings("null")
@Override
public EntityModel<Producto> toModel(Producto producto) {
return EntityModel.of(producto,linkTo(methodOn(ProductoController_V2.class).getAllProductos()).withRel("carreras"));
}
}