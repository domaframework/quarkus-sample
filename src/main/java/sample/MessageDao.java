package sample;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import java.util.List;

@Dao
@DbConfigAware
public interface MessageDao {

  @Select
  Message selectById(Integer id);

  @Select
  List<Message> selectByLocale(Locale locale);

  @Insert
  int insert(Message message);
}
