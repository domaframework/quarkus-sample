package sample;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import java.util.Objects;

public class Startup {
  private final ScriptDao scriptDao;

  public Startup(ScriptDao scriptDao) {
    this.scriptDao = Objects.requireNonNull(scriptDao);
  }

  void onStart(@Observes StartupEvent event) {
    scriptDao.create();
  }
}
