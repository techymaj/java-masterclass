package tech.majaliwa.METADATA;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// creating custom annotation with @interface
// specify where the annotation can be used with @Target(). undefined means anywhere
//
@Target({ElementType.TYPE, ElementType.METHOD})
// specify how long the annotation should be retained with @Retention()
@Retention(RetentionPolicy.RUNTIME) // runtime means it will be available at runtime
public @interface VeryImportant {

}
