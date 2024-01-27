package sample;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.seasar.doma.jdbc.criteria.Entityql;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.List;

@Path("fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FruitResource {

  private static final Logger LOGGER = Logger.getLogger(FruitResource.class.getName());

  @Inject Entityql entityql;

  @GET
  public List<Fruit> get() {
    var f = new Fruit_();
    return entityql.from(f).orderBy(c -> c.asc(f.name)).fetch();
  }

  @GET
  @Path("{id}")
  public Fruit getSingle(@PathParam Integer id) {
    var f = new Fruit_();
    Fruit entity = entityql.from(f).where(c -> c.eq(f.id, id)).fetchOne();
    if (entity == null) {
      throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
    }
    return entity;
  }

  @POST
  @Transactional
  public Response create(Fruit fruit) {
    if (fruit.getId() != null) {
      throw new WebApplicationException("Id was invalidly set on request.", 422);
    }

    var f = new Fruit_();
    entityql.insert(f, fruit).execute();
    return Response.ok(fruit).status(201).build();
  }

  @PUT
  @Path("{id}")
  @Transactional
  public Fruit update(@PathParam Integer id, Fruit fruit) {
    if (fruit.getName() == null) {
      throw new WebApplicationException("Fruit Name was not set on request.", 422);
    }

    var f = new Fruit_();
    Fruit entity = entityql.from(f).where(c -> c.eq(f.id, id)).fetchOne();

    if (entity == null) {
      throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
    }

    entity.setName(fruit.getName());
    entityql.update(f, entity).execute();

    return entity;
  }

  @DELETE
  @Path("{id}")
  @Transactional
  public Response delete(@PathParam Integer id) {
    var f = new Fruit_();
    Fruit entity = entityql.from(f).where(c -> c.eq(f.id, id)).fetchOne();
    if (entity == null) {
      throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
    }
    entityql.delete(f, entity).execute();
    return Response.status(204).build();
  }

  @Provider
  public static class ErrorMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
      LOGGER.error("Failed to handle request", exception);

      int code = 500;
      if (exception instanceof WebApplicationException) {
        code = ((WebApplicationException) exception).getResponse().getStatus();
      }

      JsonObjectBuilder entityBuilder =
          Json.createObjectBuilder()
              .add("exceptionType", exception.getClass().getName())
              .add("code", code);

      if (exception.getMessage() != null) {
        entityBuilder.add("error", exception.getMessage());
      }

      return Response.status(code).entity(entityBuilder.build()).build();
    }
  }
}
