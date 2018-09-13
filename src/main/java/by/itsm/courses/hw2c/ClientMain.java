package by.itsm.courses.hw2c;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Runnable starter = context.getBean(Runnable.class);
        starter.run();
    }

}