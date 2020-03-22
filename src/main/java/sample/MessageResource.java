package sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class MessageResource {

  private final MessageDao messageDao;

  public MessageResource(MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    List<Message> messages = messageDao.selectByLocale(new Locale("en", "US"));
    return messages.stream().findFirst().map(m -> m.text.getValue()).orElseGet(() -> "empty");
  }
}
