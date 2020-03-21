package sample;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Message {
  @Id public Integer id;
  public String text;
}
