package ec.edu.ups.rest;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Cliente;
import ec.edu.ups.entidad.Producto;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cliente/")
public class ClienteRest {
    @EJB
    ClienteFacade ejbClienteFacade;


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response message() {
        List<Cliente> clientes = ejbClienteFacade.findAll();
        GenericEntity<List<Cliente>> myEntity = new GenericEntity<List<Cliente>>(clientes) {
        };
        return Response.status(200).entity(myEntity).build();
    }




}
