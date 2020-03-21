package sample;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@AnnotateWith(
    annotations = {
      @Annotation(target = AnnotationTarget.CLASS, type = ApplicationScoped.class),
      @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Inject.class)
    })
@Target(ElementType.TYPE)
public @interface DbConfigAware {}
