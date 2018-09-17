package by.itsm.course.hw2c;

import by.itsm.course.hw2c.model.Request;
import by.itsm.course.hw2c.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

@Configuration
@ComponentScan("by.itsm.course.hw2c")
@PropertySource("classpath:client.properties")
public class Config {

    @Value("${host}")
    private String host;
    @Value("${port}")
    private Integer port;

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public String name() {
        return ClientMain.getName();
    }

    @Bean
    public String message() {
        return ClientMain.getMessage();
    }

    @Bean
    public Supplier<Request> supplier(String name, String message) {
        return () -> new Request(name, message);
    }

    @Bean
    public Runnable executor(ObjectMapper mapper, Supplier<Request> supplier) {
        return () -> {
            try {
                Socket socket = new Socket(host, port);
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                Request request = supplier.get();
                String requestString = mapper.writeValueAsString(request);
                writer.writeUTF(requestString);
                writer.flush();
                String responseString = reader.readUTF();
                Response response = mapper.readValue(responseString, Response.class);
                System.out.println(response.getMessage());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}