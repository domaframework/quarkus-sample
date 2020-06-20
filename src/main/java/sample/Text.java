package sample;

import org.seasar.doma.Domain;

@Domain(valueType = String.class)
public class Text {

  private final String value;

  public Text(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
