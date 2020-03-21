package sample;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

@Dao
@DbConfigAware
public interface MessageDao {

  @Select
  Message selectById(Integer id);
}
