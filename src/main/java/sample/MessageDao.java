package sample;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;

import java.util.List;

@Dao
public interface MessageDao {

  @Select
  Message selectById(Integer id);

  default List<Message> selectByLocale(Locale locale) {
    Entityql entityql = new Entityql(Config.get(this));
    Message_ m = new Message_();
    return entityql
        .from(m)
        .where(
            c -> {
              c.eq(m.locale.language, locale.language);
              c.eq(m.locale.country, locale.country);
            })
        .fetch();
  }

  default void insert(Message message) {
    Entityql entityql = new Entityql(Config.get(this));
    Message_ m = new Message_();
    entityql.insert(m, message).execute();
  }
}
