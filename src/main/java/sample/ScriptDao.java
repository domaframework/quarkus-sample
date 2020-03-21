package sample;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

@Dao
@DbConfigAware
public interface ScriptDao {

  @Script
  void create();
}
