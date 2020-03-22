package sample;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;

public class DevLifecycle {

  private final DbConfig dbConfig;

  private final ScriptDao scriptDao;

  private final MessageDao messageDao;

  public DevLifecycle(DbConfig dbConfig, ScriptDao scriptDao, MessageDao messageDao) {
    this.dbConfig = dbConfig;
    this.scriptDao = scriptDao;
    this.messageDao = messageDao;
  }

  void onStart(@Observes StartupEvent event) {
    dbConfig.getSqlFileRepository().clearCache();
    scriptDao.create();
    messageDao.insert(new Message(1, "hello", new Locale("en", "US")));
    messageDao.insert(new Message(2, "世界", new Locale("ja", "JP")));
  }
}
