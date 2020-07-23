package ec.edu.ups.rest;

import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Producto;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/productos/")
public class ProductoRest {
    @EJB
    ProductoFacade ejbproductoFacade;


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPersons() {
        Jsonb jsonb = JsonbBuilder.create();
        List<Producto> productos = new ArrayList<Producto>();
        productos = ejbproductoFacade.findAll();
        System.out.println(productos);

        return Response.ok(jsonb.toJson(productos))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }

}