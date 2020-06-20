package sample;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import java.util.Objects;

public class Startup {
  private final ScriptDao scriptDao;
  private final MessageRepository messageRepository;

  public Startup(ScriptDao scriptDao, MessageRepository messageRepository) {
    this.scriptDao = Objects.requireNonNull(scriptDao);
    this.messageRepository = Objects.requireNonNull(messageRepository);
  }

  void onStart(@Observes StartupEvent event) {
    scriptDao.create();
    messageRepository.insert(new Message(1, "hello", new Locale("en", "US")));
    messageRepository.insert(new Message(2, "世界", new Locale("ja", "JP")));
  }
}
