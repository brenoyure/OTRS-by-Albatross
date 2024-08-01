package br.albatross.otrs.rest;

import java.util.Optional;

import br.albatross.otrs.domain.dao.apis.problemas.DescricaoProblemaDao;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/problemas")
public class ProblemasController {

    @Inject
    private DescricaoProblemaDao dao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {

        Optional<DescricaoProblema> descricaoProblemaOptional = dao.findById(id);

        if (descricaoProblemaOptional.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(descricaoProblemaOptional.get()).build();

    }

}
