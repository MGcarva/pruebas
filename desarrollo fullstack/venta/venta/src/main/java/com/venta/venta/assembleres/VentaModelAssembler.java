package com.venta.venta.assembleres;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.venta.venta.Venta.VentaController_V2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.venta.venta.Venta.Venta;


@Component

public class VentaModelAssembler  implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {
@Override
public EntityModel<Venta> toModel(Venta venta) {
return EntityModel.of(venta,linkTo(methodOn(VentaController_V2.class).getAllVentas()).withRel("carreras"));
}
}