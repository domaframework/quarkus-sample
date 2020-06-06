package sample;

import org.seasar.doma.Dao;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.Entityql;

import java.util.List;

@Dao
@DbConfigAware
public interface MessageDao {

  default Message selectById(Integer id) {
    Entityql entityql = new Entityql(Config.get(this));
    Message_ m = new Message_();
    return entityql.from(m).where(c -> c.eq(m.id, id)).fetchOne();
  }

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
