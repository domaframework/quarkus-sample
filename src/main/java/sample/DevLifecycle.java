package sample;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;

public class DevLifecycle {

  private final DbConfig dbConfig;

  private final ScriptDao scriptDao;

  public DevLifecycle(DbConfig dbConfig, ScriptDao scriptDao) {
    this.dbConfig = dbConfig;
    this.scriptDao = scriptDao;
  }

  void onStart(@Observes StartupEvent event) {
    dbConfig.getSqlFileRepository().clearCache();
    scriptDao.create();
  }
}
