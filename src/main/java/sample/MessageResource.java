package sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class MessageResource {

  private final MessageDao messageDao;

  public MessageResource(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    Message message = messageDao.selectById(1);
    return message.text;
  }
}
