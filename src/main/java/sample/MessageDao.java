package sample;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;

import java.util.List;

@Dao
public interface MessageDao {

  @Select
  Message selectById(int id);
}
