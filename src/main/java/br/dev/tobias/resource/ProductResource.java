package br.dev.tobias.resource;

import br.dev.tobias.entity.Product;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/{tenant}/api/v1/products")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ProductResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

    @POST
    @Path("/")
    @Transactional
    public Response createProduct(@PathParam("tenant") String tenant,
                                  Product product) {
        LOGGER.info("Criando produto no tenant {}", tenant);
        product.persist();
        return Response.ok().entity(product).build();
    }
}
