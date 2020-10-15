package sample;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.seasar.doma.jdbc.criteria.Entityql;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
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
