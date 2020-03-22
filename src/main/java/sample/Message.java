package sample;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;

@Entity
public class Message {

  @Id public Integer id;
  public Text text;
  public Locale locale;

  public Message() {
  }

  public Message(Integer id, String text, Locale locale) {
    this.id = id;
    this.text = new Text(text);
    this.locale = locale;
  }

}
