package sample;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.seasar.doma.Embeddable;

@Embeddable
public class Locale {
    public final String language;
    public final String country;

    public Locale(String language, String country) {
        this.language = language;
        this.country = country;
    }
}
