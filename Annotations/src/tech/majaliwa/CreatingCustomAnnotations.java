package tech.majaliwa;

import tech.majaliwa.METADATA.RunImmediately;
import tech.majaliwa.METADATA.VeryImportant;

public class CreatingCustomAnnotations {

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Cat myCat = new Cat("Mimi");
        myCat.jump(); // deprecated warning

        Dog myDog = new Dog();


        // get the annotation from the class
        var isAnnotationPresent = myCat.getClass().isAnnotationPresent(VeryImportant.class);
        if (isAnnotationPresent) {
            System.out.println("Very important cat!");
        } else {
            System.out.println("Not so important cat!");
        }

        var isAnnotationPresent2 = myDog.getClass().isAnnotationPresent(VeryImportant.class);
        if (isAnnotationPresent2) {
            System.out.println("Very important dog!");
        } else {
            System.out.println("Not so important dog!");
        }

        // get all declared methods excluding inherited methods
        var methods = myDog.getClass().getDeclaredMethods();
        for (var method : methods) {
            if (method.isAnnotationPresent(RunImmediately.class)) {
                var annotation = method.getAnnotation(RunImmediately.class);
                var times = annotation.times();

                @SuppressWarnings("unused")
                var exception = annotation.exception();
                try {
                    // use times then decrement it
                    while (times-- > 0) {
                        method.invoke(myDog);
                    }
                } catch (Exception e) {
                    System.out.println("WRT: " + method + " No can do, boss!");
                }
            }
        }

        var fields = myCat.getClass().getDeclaredFields();
        for (var field : fields) {
            if (field.isAnnotationPresent(tech.majaliwa.METADATA.ImportantString.class)) {
                try {
                    Object value = field.get(myCat);

                    if (value instanceof String stringValue) {
                        System.out.println("Important string field: " + stringValue.toUpperCase());
                    } else {
                        System.out.println("No instance of " + field.getName() + " found!");
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}


