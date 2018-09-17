package by.itsm.course.hw2c;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientMain {

    private static String name;
    private static String message;

    static String getName() {
        return name;
    }

    static String getMessage() {
        return message;
    }

    public static void main(String[] args) {
        name = args[0];
        message = args[1];
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Runnable starter = context.getBean(Runnable.class);
        starter.run();
    }

}