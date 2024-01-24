package tech.majaliwa.METADATA;

import tech.majaliwa.Dog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RunImmediately {
    // can't use any type that is not a primitive type, String, Class, enum, annotation or array of those
    int times() default 2; // make it a method, not a normal class field

    // trying out stuff
    Class<Dog> exception() default Dog.class;
    enum Priority {LOW, MEDIUM, HIGH};
    Priority priority() default Priority.MEDIUM;
}
